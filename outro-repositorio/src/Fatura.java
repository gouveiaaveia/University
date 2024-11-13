import com.sun.source.doctree.SystemPropertyTree;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Fatura{
    private String numeroFatura;
    private Cliente cliente;
    private String data;
    private ArrayList<Produtos> listaProdutos;

    Scanner sc = new Scanner(System.in);

    public Fatura(Cliente cliente){
        this.numeroFatura= "";
        this.cliente = cliente;
        this.data= "";
        this.listaProdutos= new ArrayList<>();
    }

    public void criarFatura(){
        System.out.print("Número da fatura: ");
        setNumeroFatura(sc.nextLine());
        System.out.print("Data da fatura (dd/mm/aa): ");
        setData(sc.nextLine());
        System.out.print("Pretende adicionar algum produto? (s/n): ");
        char opcao2;
        do{
            opcao2 = sc.nextLine().charAt(0);
            if(opcao2=='s'){
                criarProduto();
            }else if(opcao2 == 'n'){
                System.out.println("Fatura criada!!");
                break;
            };
            sc.nextLine();
            System.out.print("Deseja adicionar mais algum produto? (s/n):");
        }while(opcao2 == 's');
    }

    public void editarFatura(){
        System.out.print("Deseja alterar o número da fatura? (s/n): ");
        char opcao = sc.nextLine().charAt(0);
        if(opcao== 's'){
            System.out.print("Número da fatura: ");
            setNumeroFatura(sc.nextLine());
        }else{
            System.out.print("Data da fatura (dd/mm/aa): ");
            setData(sc.nextLine());
        }
        System.out.print("Pretende alterar algum produto? (s/n): ");
        if(opcao== 's'){
            System.out.print("1- Editar produto\n2- Remover produto\nOpção: ");
            int opcao2 = sc.nextInt();
            if(opcao2 == 1){
                editarProduto();
            }else{
                removerProduto();
            }
        }
    }

    private void editarProduto(){

    }

    private void removerProduto(){
        System.out.print("Código do produto que deseja remover: ");
        String codigo = sc.nextLine();
        for(Produtos p: getListaProdutos()){
            if(p.getCodigo().equals(codigo)){
                listaProdutos.remove(p);
            }
        }
        System.out.println("Produto removido com sucesso!");
    }

    public void adicionarProduto(Produtos produto){
        this.listaProdutos.add(produto);
    }

    private void criarProduto(){
        System.out.print("Tipo de produto a adicionar:\n1- Produto alimentar\n2- Produto Farmaceutico\nOpção:");
        int opcao = sc.nextInt();
        switch(opcao){
            case 1:
                System.out.print("Produto biológico? (s/n): ");
                char opcao1 = sc.next().charAt(0);
                if(opcao1== 's'){
                    ProdutoAlimentarBiologico p = new ProdutoAlimentarBiologico();
                    p.criarProduto();
                    adicionarProduto(p);
                }else{
                    ProdutoAlimentar p = new ProdutoAlimentar();
                    p.criarProduto();
                    adicionarProduto(p);
                }
                break;

            case 2:
                System.out.print("Tem prescrição médica?");
                /*
                Inserir linhas de código para casos de farmácia
                 */
                break;
        }
    }

    public void faturaUnica() {
        double totalSemIVA = 0;
        double totalComIVA = 0;
        double valorTotalDoIVA = 0;

        System.out.println("\nInformações da fatura:\nNúmero da fatura: " + this.numeroFatura
                + "\nData da fatura (dd/mm/aa): " + this.data + " " +
                getCliente().toString()
                + "\nProdutos:");

        String localizacao = getCliente().getLocalizacao();

        for (Produtos p : this.listaProdutos) {
            // Calcula os totais
            totalSemIVA += p.valorTotalSemIVA();
            double ivaProduto = p.obterIVA(localizacao);
            valorTotalDoIVA += ivaProduto;
            totalComIVA += p.valorTotalComIVA(localizacao);

            //imprime a informação do produto e os valores
            System.out.println(p.toString() +
                    "\n Valor total sem IVA: " + p.valorTotalSemIVA() +
                    "\n Taxa de IVA: " + ivaProduto +
                    "\n Valor com IVA: " + p.valorComIVA(localizacao) +
                    "\n Valor total com IVA: " + p.valorTotalComIVA(localizacao));
        }

        // Exibe informações finais da fatura apenas uma vez
        System.out.println("Informação final da fatura:" +
                "\n Valor total da fatura sem IVA: " + totalSemIVA +
                "\n Valor total do IVA: " + valorTotalDoIVA +
                "\n Valor total da fatura com IVA: " + totalComIVA);
    }


    public String toString(){
        return "Adicionar informação das faturas";
    }

    public String getNumeroFatura() {
        return numeroFatura;
    }

    public void setNumeroFatura(String numeroFatura) {
        this.numeroFatura = numeroFatura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<Produtos> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ArrayList<Produtos> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }
}
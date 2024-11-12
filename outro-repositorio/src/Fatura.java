import com.sun.source.doctree.SystemPropertyTree;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Fatura{
    private int numeroFatura;
    private Cliente cliente;
    private String data;
    private ArrayList<Produtos> listaProdutos;

    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public Fatura(Cliente cliente){
        this.numeroFatura=0;
        this.cliente = cliente;
        this.data=data;
        this.listaProdutos= new ArrayList<>();
    }

    public void criarFatura(){
        System.out.print("Número da fatura: ");
        setNumeroFatura(sc.nextInt());
        sc.nextLine();
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
            System.out.print("Deseja adicionar mais algum produto? (s/n):");
        }while(opcao2 == 1);
    }

    public void editarFatura(){
        System.out.print("Deseja alterar o número da fatura? (s/n): ");
        char opcao = sc.nextLine().charAt(0);
        if(opcao== 's'){
            System.out.print("Número da fatura: ");
            setNumeroFatura(sc.nextInt());
        }else{
            System.out.print("Data da fatura (dd/mm/aa): ");
            setData(sc.nextLine());
        }
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

        System.out.println("Número da fatura: " + this.numeroFatura
                + "\nData da fatura (dd/mm/aa): " + this.data +
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

    public int getNumeroFatura() {
        return numeroFatura;
    }

    public void setNumeroFatura(int numeroFatura) {
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
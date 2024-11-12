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
    }

    public void editarFatura(){
        System.out.print("Deseja alterar o número da fatura?\n 1- SIM\n2- NÃO\nopção: ");
        int opcao = sc.nextInt();
        if(opcao==1){
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

    public void criarProduto(){
        System.out.print("Tipo de produto a adicionar:\n\n1 - Produto alimentar\n2-Produto Farmaceutico\n Opção:");
        int opcao = sc.nextInt();
        switch(opcao){
            case 1:
                System.out.print("Produto biológico?\n 1-sim\n2-não\nOpção:");
                int opcao1 = sc.nextInt();
                if(opcao1==1){
                    ProdutoAlimentarBiologico p = new ProdutoAlimentarBiologico();
                    listaProdutos.add(p);
                }else{
                    ProdutoAlimentar p = new ProdutoAlimentar();
                    listaProdutos.add(p);
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

    public void faturaUnica(){
        System.out.println("Número da fatura: " + this.numeroFatura
                + "\nData da fatura (dd/mm/aa): " + this.data +
                "\nCliente: " + this.cliente.getNome() + " NIF: " + this.cliente.getNif() + " Localização: "+ this.cliente.getLocalizacao()
                + "\nProdutos:");
        for(Produtos p: this.listaProdutos){
            System.out.println("Código: " + p.getCodigo() + " Nome: " + p.getNome() + " Preço unitário " + p.getPrecoUnitario()
                    + "Quantidade: " + p.getQuantidade());
            System.out.println(p); //chama o toString do produto
        }
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
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class Dados implements Serializable{
    private ArrayList<Cliente> clientes;
    private ArrayList<Fatura> faturas;
    private ArrayList<Produtos> produtos;

    public Dados(){
        this.clientes = new ArrayList<>();
        this.faturas = new ArrayList<>();
        this.produtos= new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente){
        this.clientes.add(cliente);
    }


    public Cliente encontrarCliente(String nif){
        for(Cliente cliente: clientes){
            if(nif.equals(cliente.getNif())) return cliente;
        }
        return null;
    }

    public void mostrarListaClientes(){
        for(Cliente c : clientes){
            System.out.println(c.toString());
        }
    }

    public void adicionarFatura(Fatura fatura){
        this.faturas.add(fatura);
    }


    public Fatura encontrarFatura(Scanner sc){
        System.out.print("Número da fatura: ");
        String numeroFatura = sc.nextLine();
        boolean encontrada = false;
        for(Fatura f: faturas){
            if(f.getNumeroFatura().equals(numeroFatura)){
                encontrada = true;
                return f;
            }
        }
        if(!encontrada){
            System.out.println("Fatura não encontrada");
        }
        return null;
    }

    public void mostrarFatura(Scanner sc){
        System.out.print("Número da fatura: ");
        String numeroFatura = sc.nextLine();
        boolean encontrada = false;
        for(Fatura f: faturas){
            if(f.getNumeroFatura().equals(numeroFatura)){
                f.faturaUnica();
                encontrada = true;
                break;
            }
        }
        if(!encontrada){
            System.out.println("Fatura não encontrada!");
        }
    }

    public void mostrarProdutos(){
        if(produtos.isEmpty())System.out.println("vazio");
        for(Produtos p:produtos){
            System.out.println(p.getNome());
        }
    }

    public void mostrarListaFaturas(){
        if(getFaturas().isEmpty()){
            System.out.println("Sem nenhuma fatura registada no sistema!");
            return;
        }
        for(Fatura f : faturas){
            f.calcularValoresIVA();
            System.out.println(f + "Número de produtos: " + f.getListaProdutos().size() + "\nValor total sem IVA: " +
                    f.getValorSemIVA() + "\nValor total com IVA: " + f.getValorComIVA());
        }
    }

    public void estatisticas(){
        int numeroFaturas= getFaturas().size();
        int numeroProdutos= getProdutos().size();
        double totalSemIVA=calculaTotalSemIVA();
        double totalComIVA=calculaTotalComIVA();
        double totalIVA=calculaIVA();
        System.out.printf("Faturas: %d\nProdutos: %d\nValor total sem IVA: %f\nValor total com IVA: %f\nValor total do IVA: %f", numeroFaturas,numeroProdutos,totalSemIVA,totalComIVA,totalIVA);

    }

    private double calculaTotalSemIVA(){
        double total=0.0;
        for(Fatura f: getFaturas()){
            total+=f.getValorSemIVA();
        }
        return total;
    }
    private double calculaTotalComIVA(){
        double total=0.0;
        for(Fatura f: getFaturas()){
            total+=f.getValorComIVA();
        }
        return total;
    }
    private double calculaIVA(){
        double total=0.0;
        for(Fatura f: getFaturas()){
            total+=f.getIVA();
        }
        return total;
    }

    public void adicionarPordutosDados(Produtos produto){
        System.out.println("Produto adicionado");
        this.produtos.add(produto);
    }

    public Produtos encontrarProdutoDados(String codigo){
        for(Produtos p: this.produtos){
            if(codigo.equalsIgnoreCase(p.getCodigo())){
                System.out.println("\nProduto já existe");
                return p;
            }
        }
        System.out.println("\nProduto não existe");
        return null;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Fatura> getFaturas() {
        return faturas;
    }

    public void setFaturas(ArrayList<Fatura> faturas) {
        this.faturas = faturas;
    }

    public ArrayList<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produtos> produtos) {
        this.produtos = produtos;
    }
}
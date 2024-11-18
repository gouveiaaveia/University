import java.util.ArrayList;
import java.util.Scanner;

public class Dados{
    private ArrayList<Cliente> clientes;
    private ArrayList<Fatura> faturas;

    Scanner sc = new Scanner(System.in);

    public Dados(){
        this.clientes = new ArrayList<>();
        this.faturas = new ArrayList<>();
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


    public void encontrarFatura(){
        System.out.print("Número da fatura: ");
        String numeroFatura = sc.nextLine();
        boolean encontrada = false;
        for(Fatura f: faturas){
            if(f.getNumeroFatura().equals(numeroFatura)){
                f.editarFatura();
                encontrada = true;
            }
        }
        if(!encontrada){
            System.out.println("Fatura não encontrada");
        }
    }

    public void mostrarFatura(){
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
}
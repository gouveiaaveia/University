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


    public void encontrarCliente(){

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
        for(Fatura f: faturas){
            if(f.getNumeroFatura().equals(numeroFatura)){
                f.faturaUnica();
            }
        }
    }

    public void mostrarListaFaturas(){
        if(getFaturas().isEmpty()){
            System.out.println("Sem nenhuma fatura registada no sistema!");
            return;
        }
        for(Fatura f : faturas){
            System.out.println(f);
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
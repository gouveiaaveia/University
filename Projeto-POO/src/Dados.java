import java.util.ArrayList;
import java.util.Scanner;

public class Dados{
    private ArrayList<Cliente> clientes;
    private ArrayList<Fatura> faturas;

    Scanner sc = new Scanner(System.in);

    public Dados(){
        this.clientes = new ArrayList<Cliente>();
        this.faturas = new ArrayList<Fatura>();
    }

    public void adicionarCliente(Cliente cliente){
        this.clientes.add(cliente);
    }

    public void editarCliente(){
        System.out.print("NIF: ");
        int nif = sc.nextInt();
        sc.nextLine();
        for(Cliente c: clientes){
            if(c.getNif() == nif){
                System.out.print("Nome: ");
                c.setNome(sc.nextLine());
                System.out.print("Localização: ");
                c.setLocalizacao(sc.nextLine());
            }else{
                System.out.println("Cliente não encontrado");
            }
        }
    }

    public void mostrarListaClientes(){
        for(Cliente c : clientes){
            System.out.println(c.toString());
        }
    }

    public void adicionarFatura(Fatura fatura){
        this.faturas.add(fatura);
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
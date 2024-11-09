import java.util.ArrayList;

public class Fatura{
    private int numeroFatura;
    private Cliente cliente;
    private String data;
    private ArrayList<Produtos> listaProdutos;


    public Fatura(int numeroFatura, Cliente cliente, String data){
        this.numeroFatura=numeroFatura;
        this.cliente= cliente;
        this.data=data;
        this.listaProdutos= new ArrayList<Produtos>();
    }

    public void adicionarProduto(Produtos produto){
        this.listaProdutos.add(produto);
    }

    public String toString(){
        return "";
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
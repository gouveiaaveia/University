import java.util.Random;
import java.util.Scanner;
import java.io.Serializable;

public abstract class Produtos implements Serializable{
    protected String codigo;
    protected String nome;
    protected String descricao;
    protected int quantidade;
    protected double precoUnitario;

    public Produtos(String codigo, String nome, String descricao, int quantidade,double precoUnitario){
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Produtos(){
        this.codigo = "";
        this.nome = "";
        this.descricao = "";
        this.quantidade = 0;
        this.precoUnitario = 0;
    }

    protected int criarProdutosComum(boolean verifica, String codigo, Scanner sc, Verificacoes v){

        if(!verifica){
            setCodigo(codigo); //peço sempre o codigo anteriormente para verificar se ja existe o produto na lista dos dados
            System.out.print("Nome do produto: ");
            setNome(sc.nextLine());
            System.out.print("Descrição do produto: ");
            setDescricao(sc.nextLine());
            double valor2;
            do{
                System.out.print("Preço unitário do produto: ");
                String q = sc.nextLine();
                valor2 = v.stringDouble(q);
            }while(valor2 == 0);
            setPrecoUnitario(valor2);
        }

        int valor;
        do{
            System.out.print("Quantidade do produto: ");
            String q = sc.nextLine();
            valor = v.stringInteger(q);
        }while(valor == 0);
        setQuantidade(valor);
        return (valor);
    }

    protected abstract double valorTotalComIVA(String localizacao);
    protected abstract double valorTotalSemIVA();
    protected abstract double obterIVA(String localizacao);
    protected abstract double valorComIVA(String localizacao);


    public String toString(){
        return "Código: " + getCodigo() + "  Nome: " + getNome() + "  Preço Unitário: " + getPrecoUnitario() + "  Quantidade: " + getQuantidade();
    };


    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public double getPrecoUnitario() {
        return precoUnitario;
    }
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
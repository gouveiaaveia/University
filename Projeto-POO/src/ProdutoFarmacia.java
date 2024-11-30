import java.util.Scanner;
import java.io.Serializable;

public class ProdutoFarmacia extends Produtos implements Serializable{

    private String categoria;

    public ProdutoFarmacia(String codigo, String nome, String descricao, int quantidade,double precoUnitario,String categoria){
        super(codigo,nome,descricao,quantidade,precoUnitario);
        this.categoria=categoria;
    }

    public ProdutoFarmacia(){
        super();
        this.categoria="";
    }

    public void criarNaoPrescrito(boolean verifica, String codigo, Scanner sc, Verificacoes v){
        super.criarProdutosComum(verifica,codigo, sc, v);
        setCategoria(Categoria(sc, v));
    }

    private String Categoria(Scanner sc, Verificacoes v){
        String categoria;

        do{
            System.out.print("\nCategoria (Beleza,BemEstar,Bebes,Animais,Outros):");
            categoria=sc.nextLine();
        }while(!v.verificaCategoria(categoria));
        return categoria;
    }

    public String toString(){
        String str="";
        str+=super.toString();
        str+="Produto Farmacia Não Prescrito\n";
        str+="Categoria: "+this.categoria+"\n";
        return str;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getCategoria() {
        return categoria;
    }

    @Override
    public double obterIVA(String localizacao){
        TabelaIVA tabela= new TabelaIVA(0,0);
        TabelaIVA tabelaValores= tabela.getTabelaPorLocalizacao(localizacao, "farmacia");

        if(this.categoria.equalsIgnoreCase("animais")) return((tabelaValores.getTaxaNormal()*100)-1/100);

        return tabelaValores.getTaxaNormal();
    }

    @Override
    public double valorComIVA(String localizacao){ //iva por cada um produtor
        return getPrecoUnitario() + (getPrecoUnitario() * obterIVA(localizacao));

    }

    @Override
    public double valorTotalSemIVA(){
        return (double) getQuantidade() * getPrecoUnitario();
    }

    @Override
    public double valorTotalComIVA(String localizacao){
        return getQuantidade() * valorComIVA(localizacao);
    }


}
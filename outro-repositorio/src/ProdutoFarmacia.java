import java.util.Scanner;

public class ProdutoFarmacia extends Produtos{

    private String categoria;
    Scanner scanner = new Scanner(System.in);
    Verificacoes verificacoes= new Verificacoes();

    public ProdutoFarmacia(String codigo, String nome, String descricao, int quantidade,double precoUnitario,String categoria){
        super(codigo,nome,descricao,quantidade,precoUnitario);
        this.categoria=categoria;
    }

    public ProdutoFarmacia(){
        super();
        this.categoria="";
    }

    public void CriarNaoPrescrito(boolean verifica, String codigo){
        super.criarProdutosComum(verifica,codigo);
        setCategoria(Categoria());
    }

    private String Categoria(){
        String categoria;

        do{
            System.out.print("\nCategoria (Beleza,BemEstar,Bebes,Animais,Outros):");
            categoria=scanner.nextLine();
        }while(!verificacoes.VerificaCategoria(categoria));
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
        return this.precoUnitario*obterIVA(localizacao);

    }
    @Override
    public double valorTotalSemIVA(){
        return (double)this.quantidade*this.precoUnitario;
    }
    @Override
    public double valorTotalComIVA(String localizacao){
        return this.quantidade*valorComIVA(localizacao);
    }


}
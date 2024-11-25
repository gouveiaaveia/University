import java.util.ArrayList;
public class ProdutoAlimentarBiologico extends ProdutoAlimentar{

    private final boolean biologico;

    public ProdutoAlimentarBiologico(String nome, String codigo,String descricao, int quantidade, double precoUnitario,String taxa, String categoria, ArrayList<String> certificacoes){
        super(nome,codigo,descricao,quantidade,precoUnitario,taxa,categoria,certificacoes);
        this.biologico = true;
    }    

    public ProdutoAlimentarBiologico(){
        super();
        this.biologico = true;
    }


    @Override
    public double valorComIVA(String localizacao){
        return getPrecoUnitario() + (aplicarDescontoBiologico(localizacao) * getPrecoUnitario());
    }

    private double aplicarDescontoBiologico(String localizacao){
        return obterIVA(localizacao) + (double) (10 /100);
    }

    public boolean isBiologico() {
        return biologico;
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class ProdutoAlimentar extends Produtos{

    enum TipoTaxa {
        Reduzida,
        Intermedia,
        Normal;
    }

    protected TipoTaxa tipoTaxa;
    protected String categotia;
    protected ArrayList<String> certificacoes;

    Scanner sc = new Scanner(System.in);
    Verificacoes v = new Verificacoes();

    public ProdutoAlimentar() {
        super();
        this.categotia = "";
        certificacoes = new ArrayList<>();
    }

    public void criarEditarProduto(){
        super.criarEditarProduto();
        setCategotia(categoria());
        setCertificacoes(certificacoes());
        determinarTipoTaxaIVA();
    }

    protected String categoria(){
        System.out.print("Categoria: ");
        return sc.nextLine();
    }

    protected ArrayList<String> certificacoes(){
        ArrayList<String> certificacoes = new ArrayList<>(4);

        int numero = v.numeroCertificacoes();

        for(int i = 0; i< numero; i++){
            System.out.print("Digite a certificação "+(i+1)+": ");
            certificacoes.add(sc.nextLine());
        }
        return certificacoes;
    }

    @Override
    public double valorComIVA(String localizacao){
        if(getCertificacoes().size() == 4){
            return getPrecoUnitario() + (extraCertificacoes(localizacao) * getPrecoUnitario());
        }else if(getCategotia().equalsIgnoreCase("vinho")){
            return getPrecoUnitario() + (extraCategoriaVinho(localizacao) * getPrecoUnitario());
        }

        return getPrecoUnitario() + (obterIVA(localizacao) * getPrecoUnitario());
    }

    @Override
    public double valorTotalSemIVA(){
        return getQuantidade() * getPrecoUnitario();
    }

    @Override
    public double valorTotalComIVA(String localizacao){
        return getQuantidade() * valorComIVA(localizacao);
    }

    @Override
    public double obterIVA(String localizacao) {

        TabelaIVA tabelaBase = new TabelaIVA(0,0,0);
        TabelaIVA tabela = tabelaBase.getTabelaPorLocalizacao(localizacao.toLowerCase(), "alimentar");

        switch (tipoTaxa) {
            case Reduzida:
                return tabela.getTaxaReduzida();
            case Intermedia:
                return tabela.getTaxaIntermedia();
            default:
                return tabela.getTaxaNormal();
        }
    }

    public void determinarTipoTaxaIVA(){
        if(!certificacoes.isEmpty()){
            tipoTaxa = TipoTaxa.Reduzida;
        }else if(getCategotia().equals("congelados") || getCategotia().equals("enlatados") || getCategotia().equals("vinho")){
            tipoTaxa = TipoTaxa.Intermedia;
        }else{
            tipoTaxa = TipoTaxa.Normal;
        }
    }

    protected double extraCategoriaVinho(String localizacao){
        return obterIVA(localizacao) + (double) (1 /100);
    }

    protected double extraCertificacoes(String localizacao){
        return obterIVA(localizacao) - (double) (1/100);
    }

    public String toString(){
        return "Código: " + getCodigo() + "  Nome: " + getNome() + "  Preço Unitário: " + getPrecoUnitario() + "  Quantidade: " + getQuantidade();
    }


    public TipoTaxa getTipoTaxa() {
        return tipoTaxa;
    }

    public void setTipoTaxa(TipoTaxa tipoTaxa) {
        this.tipoTaxa = tipoTaxa;
    }

    public String getCategotia() {
        return categotia;
    }

    public void setCategotia(String categotia) {
        this.categotia = categotia;
    }

    public ArrayList<String> getCertificacoes() {
        return certificacoes;
    }

    public void setCertificacoes(ArrayList<String> certificacoes) {
        this.certificacoes = certificacoes;
    }
}
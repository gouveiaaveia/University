public abstract class ProdutoFarmacia extends Produtos{

    private String categoria;

    enum Categorias{
        Beleza,
        BemEstar,
        Bebes,
        Animais,
        Outro;
    }

    public ProdutoFarmacia(){
        super();
    }

    public void criarProduto(){

    }

    @Override
    public void determinarTipoTaxaIVA() {

    }

    @Override
    public double valorTotalComIVA(String localizacao) {
        return 0;
    }

    @Override
    public double valorTotalSemIVA() {
        return 0;
    }

    @Override
    public double obterIVA(String localizacao) {
        return 0;
    }

    @Override
    public double valorComIVA(String localizacao) {
        return 0;
    }


}
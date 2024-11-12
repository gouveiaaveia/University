public class ProdutoFarmacia extends Produtos implements IVA{

    private String categoria;

    @Override
    public double calcularIVA() {
        return 0;
    }

    @Override
    public int obterIVA(String localizacao) {
        return 0;
    }

    @Override
    public void determinarTipoTaxaIVA() {

    }

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
}
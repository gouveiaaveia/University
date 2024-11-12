public class ProdutoAlimentarBiologico extends ProdutoAlimentar{

    private final boolean biologico;

    public ProdutoAlimentarBiologico(){
        super();
        super.criarProduto();
        this.biologico = true;
    }


    public void aplicarDescontoBiologico(){

    }

    public boolean isBiologico() {
        return biologico;
    }
}

public class ProdutoFarmaciaPrescrito  extends ProdutoFarmacia{

    private String nomeMedico;


    public ProdutoFarmaciaPrescrito(int codigo, String nome, String descricao, int quantidade, double precoUnitario, String nomeMedico) {
        super(codigo, nome, descricao, quantidade, precoUnitario);
        this.nomeMedico = nomeMedico;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }
}

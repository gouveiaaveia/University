import java.util.Scanner;
import java.io.Serializable;

public class ProdutoFarmaciaPrescrito extends Produtos implements Serializable{

    private String nomeMedico;
    private transient Scanner sc = new Scanner(System.in);
    Verificacoes verificacoes= new Verificacoes();

    public ProdutoFarmaciaPrescrito(String codigo, String nome, String descricao, int quantidade,double precoUnitario,String medico) {
        super(codigo,nome,descricao,quantidade,precoUnitario);
        this.nomeMedico =medico;
    }

    public ProdutoFarmaciaPrescrito() {
        super();
        this.nomeMedico = "";
    }

    public void CriarPrescrito(boolean verifica, String codigo){
        super.criarProdutosComum(verifica,codigo);
        setNomeMedico(Medico());

    }

    private String Medico(){
        String medico;
        do{
            System.out.print("\nNome médico:");
            medico=sc.nextLine();
        }while(!verificacoes.verificaString(medico,2));
        return medico;
    }

    public String toString(){
        String str="";
        str+=super.toString();
        str+="Produto Farmacia Prescrito\n";
        str+="Médico: "+ getNomeMedico() +"\n";
        return str;
    }

    public double obterIVA(String localizacao){
        TabelaIVA tabela= new TabelaIVA(0,0);
        TabelaIVA tabelaValores= tabela.getTabelaPorLocalizacao(localizacao, "farmacia");
        return tabelaValores.getPrescricao();
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

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }
}
import java.util.Scanner;

public class ProdutoFarmaciaPrescrito extends Produtos{

    private String nomeMedico;
    Scanner sc = new Scanner(System.in);
    Verificacoes verificacoes= new Verificacoes();


    public ProdutoFarmaciaPrescrito() {
        super();
        this.nomeMedico = "";
    }

    public void CriarPrescrito(){
        super.criarEditarProduto();
        setNomeMedico(Medico());

    }

    private String Medico(){
        String medico;
        do{
            System.out.print("\nNome médico:");
            medico=sc.nextLine();
        }while(!verificacoes.VerificaString(medico,2));
        return medico;
    }

    public String toString(){
        String str="";
        str+=super.toString();
        str+="Produto Farmacia Prescrito\n";
        str+="Médico: "+this.nomeMedico+"\n";
        return str;
    }

    public double obterIVA(String localizacao){
        TabelaIVA tabela= new TabelaIVA(0,0);
        TabelaIVA tabelaValores= tabela.getTabelaPorLocalizacao(localizacao, "farmacia");
        return tabelaValores.getPrescricao();
    }

    public double valorComIVA(String localizacao){ //iva por cada um produtor
        double IVA=this.precoUnitario*obterIVA(localizacao);
        return this.precoUnitario+IVA;

    }

    public double valorTotalSemIVA(){
        return (double)this.quantidade*this.precoUnitario;
    }

    public double valorTotalComIVA(String localizacao){
        return  (double)this.quantidade*valorComIVA(localizacao);
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }




}
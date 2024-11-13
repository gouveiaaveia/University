import java.util.Scanner;

public class Cliente{

    private String nome;
    private String nif;
    private String localizacao;

    Scanner sc = new Scanner(System.in);

    public Cliente(){
        this.nome = "";
        this.nif = "";
        this.localizacao = "";
    }

    public void criarCliente(){
        System.out.print("\nNome: ");
        setNome(sc.nextLine());
        System.out.print("NIF: ");
        setNif(sc.nextLine());
        System.out.print("Localização: ");
        setLocalizacao(sc.nextLine());
    }

    public String toString(){
        return "Nome: " + getNome() + "  NIF: " + getNif() + "  Localização: " + getLocalizacao();
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNif() {
        return nif;
    }
    public void setNif(String nif) {
        this.nif = nif;
    }
    public String getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

}
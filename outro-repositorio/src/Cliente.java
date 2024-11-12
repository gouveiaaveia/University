import java.util.Scanner;

public class Cliente{

    private String nome;
    private int nif;
    private String localizacao;

    Scanner sc = new Scanner(System.in);

    public Cliente(){
        this.nome = "";
        this.nif = 0;
        this.localizacao = "";
    }

    public void criarCliente(){
        System.out.print("\nNome: ");
        setNome(sc.nextLine());
        System.out.print("NIF: ");
        setNif(sc.nextInt());
        sc.nextLine();
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
    public int getNif() {
        return nif;
    }
    public void setNif(int nif) {
        this.nif = nif;
    }
    public String getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

}
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;


public class Cliente implements Serializable{

    private String nome;
    private String nif;
    private String localizacao;

    public Cliente(String nome, String nif, String localizacao){
        this.nome = nome;
        this.nif = nif;
        this.localizacao =localizacao;
    }

    public Cliente(){
        this.nome = "";
        this.nif = "";
        this.localizacao = "";
    }

    public void criarCliente(ArrayList<Cliente> listaCliente, Scanner sc, Verificacoes v){
        String nome;
        boolean encontrado = false;
        do {
            System.out.print("Nome: ");
            nome = sc.nextLine();
        } while (!v.verificaString(nome,2));
        setNome(nome);

        String nif;
        do {
            System.out.print("NIF: ");
            nif = sc.nextLine();
            for (Cliente cliente : listaCliente) {
                if (nif.equals(cliente.getNif())) {
                    System.out.println("Erro: Este NIF não está disponível.");
                    encontrado = true;
                }
            }
        } while (!v.verificaNif(nif, listaCliente) && !encontrado);
        setNif(nif);

        String localizacao;
        do {
            System.out.print("Localização (Madeira,Açores,Portugal Continental): ");
            localizacao = sc.nextLine();
        } while (!v.verificaLocalizacao(localizacao));
        setLocalizacao(localizacao.toLowerCase()); //vai em minusculos por causa do que temmos na tebela
    }


    public void EditaCliente(ArrayList<Cliente> listaCliente, Scanner sc, Verificacoes v){
        System.out.print("\nEditar nome? ");
        char resposta = v.verificaSimNao(sc);
        if(resposta == 's'){
            String nome;
            do{
                System.out.print("\nNome novo:");
                nome=sc.nextLine();
            } while(!v.verificaString(nome,2));
            setNome(nome);
        }

        System.out.print("\nEditar numero de identificação fiscal? ");
        char resposta1 = v.verificaSimNao(sc);
        boolean disponivel = true;

        if(resposta1 == 's'){
            String nif;
            do{
                System.out.println("\nNumero de identificação fiscal novo:");
                nif = sc.nextLine();
                for (Cliente cliente : listaCliente) {
                    if (nif.equals(cliente.getNif())) {
                        System.out.println("Erro: Este NIF não está disponível.");
                        disponivel = false;
                    }
                }
            } while(!v.verificaNif(nif,listaCliente) && !disponivel);
            setNif(nif);
        }

        System.out.print("\nEditar localização? ");
        char resposta2 = v.verificaSimNao(sc);
        if(resposta2 == 's'){
            String localizacao;
            do{
                System.out.print("\nLocalização nova:");
                localizacao = sc.nextLine();
            } while(!v.verificaLocalizacao(localizacao));
            setLocalizacao(localizacao.toLowerCase());
        }
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
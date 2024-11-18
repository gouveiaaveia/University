import java.util.Scanner;

public class Cliente{

    private String nome;
    private String nif;
    private String localizacao;

    Scanner sc = new Scanner(System.in);
    Verificacoes v= new Verificacoes();

    public Cliente(){
        this.nome = "";
        this.nif = "";
        this.localizacao = "";
    }

    public void criarCliente(){
        String nome;
        do {
            System.out.print("\nNome: ");
            nome = scanner.nextLine();
        } while (!v.VerificaString(nome,2));
        setNome(nome);

        String nif;
        do {
            System.out.print("\nNIF: ");
            nif = scanner.nextLine();
        } while (!v.VerificaNif(nif, listaCliente));
        setNif(nif);

       String localizacao;
        do {
            System.out.print("\nLocalização (Madeira,Açores,Portugal Continental): ");
            localizacao = scanner.nextLine();
        } while (!v.VerificaLocalizacao(localizacao));
        setLocalizacao(localizacao.toLowerCase()); //vai em minusculos por causa do que temmos na tebela
    }


    public void EditaCliente(ArrayList<Cliente> listaCliente){
        System.out.print("\nEditar nome?");
        String resposta=scanner.nextLine();
        if(resposta.equalsIgnoreCase("sim")){
            String nome;
           do{
                System.out.print("\nNome novo:");
                nome=scanner.nextLine();  
           } while(!v.VerificaString(nome,2));
            setNome(nome);
        }

        System.out.print("\nEditar numero de identificação fiscal?");
        String resposta1=scanner.nextLine();
        if(resposta1.equalsIgnoreCase("sim")){
            String nif;
            do{
                System.out.println("\nNumero de identificação fiscal novo:");
                nif=scanner.nextLine();
           } while(!v.VerificaNif(nif,listaCliente));
            setNif(nif);
        }

        System.out.print("\nEditar localização?");
        String resposta2=scanner.nextLine();
        if(resposta2.equalsIgnoreCase("sim")){
            String localizacao;
            do{
                System.out.print("\nLocalização nova:");                
                localizacao=scanner.nextLine();
           } while(!v.VerificaLocalizacao(localizacao));
            setLocalizacao(localizacao.toLowerCase());
        }
    }


    public String toString(){
        return "Cliente:  Nome: " + getNome() + "  NIF: " + getNif() + "  Localização: " + getLocalizacao();
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
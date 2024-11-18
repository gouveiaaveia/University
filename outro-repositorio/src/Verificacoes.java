import java.util.Scanner;

public class Verificacoes {

    Scanner sc = new Scanner(System.in);

    public char verificaSimNao(char c){
        while(c != 's' && c != 'n'){
            System.out.print("Caracter Inválido!!\nOpção: ");
            c = sc.next().charAt(0);
        }
        return c;
    }

    public int numeroCertificacoes(){
        boolean entradaValida = false;
        int valor = 0;
        String num;

        while(!entradaValida){

            System.out.print("Quantas certificações pertende adicionar (0-4): ");
            num = sc.nextLine();

            try{
                valor = Integer.parseInt(num);
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.print("Inválido!! Introduza um número entre 1 e 4\nOpção: ");
            }
        }
        return valor;
    }

    public int stringInteger(String op){
        int valor = 0;
        try{
            valor = Integer.parseInt(op);
            return valor;
        }catch (NumberFormatException e){
            System.out.println("Opção inválida!!");
            return 0;
        }
    }

    public double stringDouble(String op){
        double valor = 0;
        try{
            valor = Double.parseDouble(op);
            return valor;
        }catch (NumberFormatException e){
            System.out.println("Opção inválida!!");
            return 0;
        }
    }

    public int opcaoMenu(String op){  //nao precisa estar aqui
        int opcao = 0;
        try{
            opcao = Integer.parseInt(op);
            return opcao;
        }catch (NumberFormatException e){
            return 0;
        }
    }


    public boolean VerificaNif(String nif, ArrayList<Cliente> listaCliente) {
        
        if (nif == null || nif.length() != 9) {
            System.out.println("Erro: O NIF deve ter 9 dígitos.");
            return false;
        }

        if (!nif.matches("^[0-9]+$")) {
            System.out.println("Erro: O NIF deve ter apenas números.");
            return false;
        }

        for (Cliente cliente : listaCliente) {
            if (nif.equals(cliente.getNif())) {
                System.out.println("Erro: Este NIF não está disponível.");
                return false;
            }
        }

        return true;
    }

    public boolean VerificaLocalizacao(String localizacao) {
        if (localizacao == null){
            System.out.println("Erro:Localização incorreta.");
            return false;
        }
        if(!localizacao.equalsIgnoreCase("madeira") && !localizacao.equalsIgnoreCase("portugal continental") && !localizacao.equalsIgnoreCase("açores")){
            System.out.println("Erro:Localização incorreta.");
            return false;
        }
        return true;
    }

    public boolean VerificaData(String data){
        if (data == null || data.length() > 10) {
            return false;
        }
        
        String dia = data.substring(0, 2);
        String mes = data.substring(3, 5);
        String ano = data.substring(6, 10);

        if(!dia.matches("^[0-9]+$") && !mes.matches("^[0-9]+$") && !ano.matches("^[0-9]+$")) return false;
        
        int diaInt = 0, mesInt = 0, anoInt = 0;
        diaInt =Integer.parseInt(dia);
        mesInt = Integer.parseInt(mes);
        anoInt = Integer.parseInt(ano);
        
        
        if (mesInt < 1 || mesInt > 12) return false;
        

        if(anoInt<1500) return false;
        
        if(anoInt%4==0){
            if (mesInt==2) {
                if(diaInt<1 || diaInt>29) return false;
            }
        }

        if(mesInt==6||mesInt==4 || mesInt==9 || mesInt==11){
            if(diaInt<1 || diaInt>30)return false;
        }

        if(mesInt==1||mesInt==3 || mesInt==5 || mesInt==7 || mesInt==8 || mesInt==10 || mesInt==12){
            if(diaInt<1 || diaInt>31)return false;
        }
        return true;
    }


    public boolean VerificaCategoria(String categoria){
        if(!categoria.equalsIgnoreCase("Beleza") && !categoria.equalsIgnoreCase("BemEstar") && !categoria.equalsIgnoreCase("Bebes") && !categoria.equalsIgnoreCase("Animais") && !categoria.equalsIgnoreCase("Outros")){
            System.out.println("Erro: Categoria incorreta.");
            return false;
        }
        return true;
    }
}

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

    public int opcaoMenu(String op){
        int opcao = 0;
        try{
            opcao = Integer.parseInt(op);
            return opcao;
        }catch (NumberFormatException e){
            return 0;
        }
    }
}

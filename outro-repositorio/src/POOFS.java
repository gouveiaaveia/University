import java.awt.*;
import java.util.Scanner;

public class POOFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        Dados dados = new Dados();
        do {
            System.out.println("=======================");
            System.out.println("POO Financial Services");
            System.out.println("=======================\n");
            System.out.println("MENU:\n1 - Criar cliente\n2 - Editar cliente\n3 - Lista de clientes\n4 - Criar fatura\n5 - Editar fatura\n6 - Lista de faturas\n7 - Visualizar fatura\n8 - Sair");
            System.out.print("=======================\nOpção: ");

            int opcao = sc.nextInt();

            switch(opcao) {
                case 1:
                    Cliente cliente = new Cliente();
                    cliente.criarCliente();
                    dados.adicionarCliente(cliente);
                    System.out.println("Cliente adicionado com sucesso!");
                    break;
                case 2:
                    dados.encontrarCliente();
                    break;
                case 3:
                    System.out.println("Lista de clientes:\n");
                    dados.mostrarListaClientes();
                    break;
                case 4:
                    System.out.print("NIF do cliente: ");
                    int nif = sc.nextInt();

                    if(dados.getClientes().isEmpty()){
                        System.out.println("Sem nenhum cliente registado!");
                        break;
                    }

                    for(Cliente c : dados.getClientes()){
                        if(c.getNif() == nif){
                            Fatura fatura = new Fatura(c);
                            fatura.criarFatura();
                            dados.adicionarFatura(fatura);
                            break;
                        }else{
                            System.out.println("Cliente não encontrado!");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Editar fatura");
                    dados.encontrarFatura();
                    break;
                case 6:
                    System.out.println("Lista de faturas:");
                    dados.mostrarListaFaturas();
                    break;
                case 7:
                    dados.mostrarFatura();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    continuar = false; // Encerra o loop
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
                    break;
            }
        } while (continuar); // Repete enquanto `continuar` for verdadeiro

        sc.close();
    }
}

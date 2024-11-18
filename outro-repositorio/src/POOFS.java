import java.util.Scanner;

public class POOFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Verificacoes v = new Verificacoes();
        boolean continuar = true;
        Dados dados = new Dados();
        TesteInicializacao.inicializarDadosDeTeste(dados);
        do {
            System.out.println("\n\n=======================");
            System.out.println("POO Financial Services");
            System.out.println("=======================\n");
            System.out.println("MENU:\n1 - Criar cliente\n2 - Editar cliente\n3 - Lista de clientes\n4 - Criar fatura\n5 - Editar fatura\n6 - Lista de faturas\n7 - Visualizar fatura\n8 - Sair");
            System.out.print("=======================\nOpção: ");

            String op = sc.nextLine();
            int opcao = v.opcaoMenu(op);

            switch(opcao) {
                case 1:
                    System.out.println("\nCriar cliente: ");
                    Cliente cliente = new Cliente();
                    cliente.criarCliente(dados.getClientes());
                    dados.adicionarCliente(cliente);
                    System.out.println("Cliente adicionado com sucesso!");
                    break;

                case 2:
                    if(dados.getClientes().isEmpty())System.out.print("\nErro: Lista vazia");
                    else{
                        String nif;
                        do {
                            System.out.print("\nNIF do Cliente que deseja editar: ");
                            nif = sc.nextLine();
                        } while (!v.VerificaNif(nif, dados.getClientes()));

                        Cliente clienteEditar = dados.encontrarCliente(nif);

                        if(clienteEditar==null)System.out.print("Erro: Cliente não encontrado, tente de novo\n");
                        else clienteEditar.EditaCliente(dados.getClientes());
                    }
                    break;

                case 3:
                    System.out.println("Lista de clientes:\n");
                    dados.mostrarListaClientes();
                    break;

                case 4:
                    System.out.println("\nCriar fatura:");
                    System.out.print("NIF do cliente: ");
                    String nif = sc.nextLine();

                    if(dados.getClientes().isEmpty()){
                        System.out.println("Sem nenhum cliente registado!");
                        break;
                    }

                    boolean clienteEncontrado = false;// Variável de controle
                    boolean existe;
                    String n;

                    for(Cliente c : dados.getClientes()){
                        if(c.getNif().equals(nif)){
                            Fatura fatura = new Fatura(c);
                            do{
                                existe = false;
                                System.out.print("Número da fatura: ");
                                n = sc.nextLine();
                                for(Fatura f: dados.getFaturas()){
                                    if (n.equals(f.getNumeroFatura())){
                                        System.out.println("\nFatura já existe!!");
                                        existe = true;
                                        break;
                                    }
                                }
                            }while(existe);

                            fatura.criarFatura(n);
                            dados.adicionarFatura(fatura);
                            clienteEncontrado = true;  // Cliente foi encontrado
                            break;
                        }
                    }

                    if (!clienteEncontrado) {  // Só exibe a mensagem se o cliente não foi encontrado
                        System.out.println("Cliente não encontrado!");
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

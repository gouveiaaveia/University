import java.util.ArrayList;
import java.util.Scanner;

public class Fatura{
    private String numeroFatura;
    private Cliente cliente;
    private String data;
    private ArrayList<Produtos> listaProdutos;

    private double valorComIVA;
    private double valorSemIVA;

    Scanner sc = new Scanner(System.in);
    Verificacoes v =  new Verificacoes();

    public Fatura(Cliente cliente){
        this.numeroFatura= "";
        this.cliente = cliente;
        this.data= "";
        this.listaProdutos= new ArrayList<>();
    }

    public void criarFatura(String n){
        setNumeroFatura(n);
        System.out.print("Data da fatura (dd/mm/aa): ");
        setData(sc.nextLine());
        System.out.print("Pretende adicionar algum produto? (s/n): ");
        char opcao2;
        do{
            opcao2 = sc.nextLine().charAt(0);
            v.verificaSimNao(opcao2);
            if(opcao2=='s'){
                criarProduto();
            }else if(opcao2 == 'n'){
                System.out.println("Fatura criada!!");
                break;
            }
            sc.nextLine();
            System.out.print("Deseja adicionar mais algum produto? (s/n): ");
        }while(opcao2 == 's');
    }

    public void editarFatura(){
        System.out.print("Deseja alterar o número da fatura? (s/n): ");
        char opcao = sc.nextLine().charAt(0);
        v.verificaSimNao(opcao);
        if(opcao== 's'){
            System.out.print("Número da fatura: ");
            setNumeroFatura(sc.nextLine());
        }else{
            System.out.print("Data da fatura (dd/mm/aa): ");
            setData(sc.nextLine());
        }
        System.out.print("Pretende alterar algum produto? (s/n): ");
        char opcao3 = sc.nextLine().charAt(0);
        v.verificaSimNao(opcao3);

        if(opcao3== 's'){

            System.out.print("1- Editar produto\n2- Remover produto\n");
            int opcao2;
            do{
                System.out.print("Opção: ");
                String valor = sc.nextLine();
                opcao2 = v.stringInteger(valor);
            }while(opcao2 != 1 && opcao2 != 2);
            sc.nextLine();

            if(opcao2 == 1){
                editarProduto();
            }else {
                removerProduto();
            }
        }
    }

    private void editarProduto(){
        System.out.print("Código do produto a editar: ");
        String codigo = sc.nextLine();
        boolean encontrado = false;
        for(Produtos p: getListaProdutos()){
            if(p.getCodigo().equals(codigo)){
                p.criarEditarProduto();
                encontrado = true;
                return;
            }
        }
        if(!encontrado){
            System.out.println("Produto não encontrado!");
        }
    }

    private void removerProduto(){
        System.out.print("Código do produto que deseja remover: ");
        String codigo = sc.nextLine();
        boolean removido = false;
        for(Produtos p: getListaProdutos()){
            if(p.getCodigo().equals(codigo)){
                listaProdutos.remove(p);
                System.out.println("Produto removido com sucesso!");
                break;
            }
        }
        if(!removido){
            System.out.println("Produto não encontrado!");
        }
    }

    public void adicionarProduto(Produtos produto){
        this.listaProdutos.add(produto);
    }

    private void criarProduto(){
        System.out.print("Tipo de produto a adicionar:\n1- Produto alimentar\n2- Produto Farmaceutico\nOpção:");
        int opcao;

        do{
            System.out.print("Opção: ");
            String valor = sc.nextLine();
            opcao = v.stringInteger(valor);
        }while(opcao != 1 && opcao != 2);

        switch(opcao){
            case 1:
                System.out.print("Produto biológico? (s/n): ");
                char opcao1 = sc.next().charAt(0);
                v.verificaSimNao(opcao1);
                if(opcao1== 's'){
                    ProdutoAlimentarBiologico p = new ProdutoAlimentarBiologico();
                    p.criarEditarProduto();
                    adicionarProduto(p);
                }else{
                    ProdutoAlimentar p = new ProdutoAlimentar();
                    p.criarEditarProduto();
                    adicionarProduto(p);
                }
                break;

            case 2:
                System.out.print("Tem prescrição médica?");
                /*
                Inserir linhas de código para casos de farmácia
                 */
                break;
        }
    }

    public void calcularValoresIVA(){
        setValorComIVA(0);
        setValorSemIVA(0);
        for(Produtos p : getListaProdutos()){
            setValorSemIVA(getValorComIVA() + p.valorTotalSemIVA());
            setValorComIVA(getValorComIVA() + p.valorTotalComIVA(cliente.getLocalizacao()));
        }
    }

    public void faturaUnica() {
        double totalSemIVA = 0;
        double totalComIVA = 0;
        double valorTotalDoIVA = 0;

        System.out.println("\nInformações da fatura:\nNúmero da fatura: " + this.numeroFatura
                + "\nData da fatura (dd/mm/aa): " + this.data + " " +
                getCliente().toString()
                + "\nProdutos:");

        String localizacao = getCliente().getLocalizacao();

        for (Produtos p : this.listaProdutos) {
            // Calcula os totais
            totalSemIVA += p.valorTotalSemIVA();
            double ivaProduto = p.obterIVA(localizacao);
            valorTotalDoIVA += ivaProduto;
            totalComIVA += p.valorTotalComIVA(localizacao);

            //imprime a informação do produto e os valores
            System.out.println(p  +
                    "\n Valor total sem IVA: " + p.valorTotalSemIVA() +
                    "\n Taxa de IVA: " + ivaProduto +
                    "\n Valor com IVA: " + p.valorComIVA(localizacao) +
                    "\n Valor total com IVA: " + p.valorTotalComIVA(localizacao));
        }

        // Exibe informações finais da fatura apenas uma vez
        System.out.println("Informação final da fatura:" +
                "\n Valor total da fatura sem IVA: " + totalSemIVA +
                "\n Valor total do IVA: " + valorTotalDoIVA +
                "\n Valor total da fatura com IVA: " + totalComIVA);
    }


    public String toString(){
        return "Número da fatura: " + this.numeroFatura + "\nInformação do cliente: " + getCliente().toString() + "\n";
    }

    public String getNumeroFatura() {
        return numeroFatura;
    }

    public void setNumeroFatura(String numeroFatura) {
        this.numeroFatura = numeroFatura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<Produtos> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ArrayList<Produtos> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public double getValorComIVA() {
        return valorComIVA;
    }

    public void setValorComIVA(double valorComIVA) {
        this.valorComIVA = valorComIVA;
    }

    public double getValorSemIVA() {
        return valorSemIVA;
    }

    public void setValorSemIVA(double valorSemIVA) {
        this.valorSemIVA = valorSemIVA;
    }
}
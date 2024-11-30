import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;


public class Fatura  implements Serializable{
    private String numeroFatura;
    private Cliente cliente;
    private String data;
    private ArrayList<Produtos> listaProdutos;


    private double valorComIVA;
    private double valorSemIVA;

    public Fatura(String numeroFatura, Cliente cliente, String data, ArrayList<Produtos> produtos){
        this.numeroFatura=numeroFatura;
        this.cliente=cliente;
        this.data=data;
        this.listaProdutos=produtos;
        this.valorComIVA=0.0;
        this.valorSemIVA=0.0;
    }


    public Fatura(Cliente cliente){
        this.numeroFatura= "";
        this.cliente = cliente;
        this.data= "";
        this.listaProdutos= new ArrayList<>();
        this.valorComIVA=0.0;
        this.valorSemIVA=0.0;
    }

    public void criarFatura(Dados dados, Scanner sc, Verificacoes v){
        System.out.print("Número da fatura: ");
        setNumeroFatura(sc.nextLine());

        String data;
        do{
            System.out.print("Data da fatura (dd-mm-aaaa): ");
            data = sc.nextLine();
        }while(!v.verificaData(data));

        setData(data);

        System.out.print("Pretende adicionar algum produto? ");
        char opcao2;
        do{
            opcao2 = v.verificaSimNao(sc);

            if(opcao2=='s'){
                criarProduto(dados, sc, v, false, "");
            }else if(opcao2 == 'n'){
                break;
            }
            sc.nextLine();
            System.out.print("\nDeseja adicionar mais algum produto? ");
        }while(opcao2 == 's');
    }

    public void editarFatura(Dados dados, Scanner sc, Verificacoes v){
        System.out.print("\nDeseja alterar o número da fatura? ");
        char opcao = v.verificaSimNao(sc);
        if(opcao== 's'){
            System.out.print("\nNúmero da fatura: ");
            setNumeroFatura(sc.nextLine());
        }
        System.out.print("\nPretende alterar os produtos da fatura? ");
        char opcao3 =  v.verificaSimNao(sc);

        if(opcao3== 's'){
            System.out.print("1 - Editar quantidade do produto\n2 - Remover produto\n3 - Adicionar produto\n");
            int opcao2;
            do{
                System.out.print("Opção: ");
                String valor = sc.nextLine();
                opcao2 = v.stringInteger(valor);
            }while(opcao2 != 1 && opcao2 != 2 && opcao2 !=3);

            if(opcao2 == 1){
                editarQuantidadeProduto(sc, v);
            }else if(opcao2==2){
                removerProduto(sc);
            }else {
                adicionarProdutoFatura(dados, sc, v);
            }
        }
    }

    private void adicionarProdutoFatura(Dados dados, Scanner sc, Verificacoes v){
        String valor;
        boolean verificaCodigo = true;
        int opcao;
        do{
            System.out.print("Código do produto: ");
            valor= sc.nextLine();
            opcao = v.stringInteger(valor);
        }while(opcao==0);

        for(Produtos p: listaProdutos){
            if(p.getCodigo().equals(valor)){
                System.out.println("\nO produto já existe na fatura, altere a quantidade.\n");
                editarQuantidadeProduto(sc, v);
            }else{
                verificaCodigo=false;
            }
        }

        if(!verificaCodigo) criarProduto(dados, sc, v, true, valor);
    }

    private void editarQuantidadeProduto(Scanner sc, Verificacoes v){  //só podemos editar a quantidade porque assim nao faria sentido ver se o codigo ja existe
        System.out.print("Código do produto a editar: ");
        String codigo = sc.nextLine();
        boolean encontrado = false;
        for(Produtos p: getListaProdutos()){
            if(p.getCodigo().equals(codigo)){
                int valor;
                String q;
                do{
                    System.out.print("\nQuantidade do produto: ");
                    q = sc.nextLine();
                    valor = v.stringInteger(q);
                }while(valor == 0);
                p.setQuantidade(valor);
                encontrado = true;
            }
        }
        if(!encontrado){
            System.out.println("Produto não encontrado!");
        }
    }

    private void removerProduto(Scanner sc){
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
        for(Produtos p:listaProdutos){
            if(p.getCodigo().equals(produto.getCodigo())){
                System.out.println("\nProduto já existe na fatura, incrementar quantidade.");
                p.setQuantidade(p.getQuantidade()+produto.getQuantidade());
                return;
            }
        }
        this.listaProdutos.add(produto);
    }

    private void criarProduto(Dados dados, Scanner sc, Verificacoes v, boolean codigoDado, String cod){
        String codigo = cod;

        if(!codigoDado){
            codigo = v.verificaCodigo(sc);
        }

        boolean verifica;
        Produtos produtoEncontrar = dados.encontrarProdutoDados(codigo);
        //verifica se o produto ja esta criado na lista dos dados

        if(produtoEncontrar==null){
            verifica=false;
            System.out.print("Tipo de produto a adicionar:\n1- Produto alimentar\n2- Produto Farmaceutico\n");
            int opcao;
            do{
                System.out.print("Opção: ");
                String valor = sc.nextLine();
                opcao = v.stringInteger(valor);
            }while(opcao != 1 && opcao != 2);



            switch(opcao){
                case 1:
                    System.out.print("Produto biológico? ");

                    char opcao1 = v.verificaSimNao(sc);

                    if(opcao1== 's'){
                        ProdutoAlimentarBiologico p = new ProdutoAlimentarBiologico();
                        p.criarEditarProduto(dados, sc, v, codigo);
                        adicionarProduto(p); //adicionar à lista faturas
                        dados.adicionarPordutosDados(p);

                    }else{
                        ProdutoAlimentar p = new ProdutoAlimentar();
                        p.criarEditarProduto(dados, sc, v, codigo);
                        adicionarProduto(p);
                        dados.adicionarPordutosDados(p);
                    }

                    break;

                case 2:
                    System.out.print("Tem prescrição médica?");
                    String prescricoo= sc.nextLine();
                    if(prescricoo.equalsIgnoreCase("sim")){
                        ProdutoFarmaciaPrescrito farmaciaP= new ProdutoFarmaciaPrescrito();
                        farmaciaP.CriarPrescrito(verifica,codigo, sc, v);
                        adicionarProduto(farmaciaP);
                        dados.adicionarPordutosDados(farmaciaP);
                    }
                    else{
                        ProdutoFarmacia farmacia= new ProdutoFarmacia();
                        farmacia.criarNaoPrescrito(verifica ,codigo, sc, v);
                        adicionarProduto(farmacia);
                        dados.adicionarPordutosDados(farmacia);
                    }

                    break;
            }
        }

        else { //se ja existir eu só vou pedir a quantidade do produto
            //produtoEncontrar vai estar à apontar para a mesma class do outro que ja existe
            verifica = true;
            boolean verificaListaProduto = false;
            produtoEncontrar.criarProdutosComum(verifica,codigo, sc, v);
        }
    }

    public void calcularValoresIVA(){
        setValorComIVA(0.0);
        setValorSemIVA(0.0);
        for(Produtos p : getListaProdutos()){
            if(p==null)System.out.println("nuloo");
            setValorSemIVA(getValorSemIVA() + p.valorTotalSemIVA());
            setValorComIVA(getValorComIVA() + p.valorTotalComIVA(cliente.getLocalizacao()));
        }
    }

    public void faturaUnica() {
        double totalSemIVA = 0;
        double totalComIVA = 0;
        double valorTotalDoIVA = 0;

        System.out.println("\nInformações da fatura:\nNúmero da fatura: " + this.numeroFatura
                + "\nData da fatura (dd-mm-aaaa): " + this.data + " " +
                getCliente().toString()
                + "\n====================\nProdutos\n====================\n");

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
        System.out.println("\n====================\nInformação final da fatura\n====================\n" +
                "\n Valor total da fatura sem IVA: " + totalSemIVA +
                "\n Valor total do IVA: " + valorTotalDoIVA +
                "\n Valor total da fatura com IVA: " + totalComIVA);
    }


    public String toString(){
        return "\nNúmero da fatura: " + this.numeroFatura + "\nInformação do cliente: " + getCliente().toString() + "\n";
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
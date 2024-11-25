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

    Scanner sc = new Scanner(System.in);
    Verificacoes v =  new Verificacoes();

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

    public void criarFatura(Dados dados){
        System.out.print("Número da fatura: ");
        setNumeroFatura(sc.nextLine());
        System.out.print("Data da fatura (dd-mm-aaaa): ");
        setData(sc.nextLine());
        System.out.print("Pretende adicionar algum produto? (s/n): ");
        char opcao2;
        do{
            opcao2 = sc.nextLine().charAt(0);
            v.verificaSimNao(opcao2);
            if(opcao2=='s'){
                criarProduto(dados);
            }else if(opcao2 == 'n'){
                break;
            }
            sc.nextLine();
            System.out.print("\nDeseja adicionar mais algum produto? (s/n): ");
        }while(opcao2 == 's');
    }

    public void editarFatura(Dados dados){
        System.out.print("\nDeseja alterar o número da fatura? (s/n): ");
        char opcao = sc.nextLine().charAt(0);
        v.verificaSimNao(opcao);
        if(opcao== 's'){
            System.out.print("\nNúmero da fatura: ");
            setNumeroFatura(sc.nextLine());
        }
        System.out.print("\nPretende alterar os produtos da fatura? (s/n): ");
        char opcao3 = sc.nextLine().charAt(0);
        v.verificaSimNao(opcao3);

        if(opcao3== 's'){
            System.out.print("1- Editar quantidade do produto\n2- Remover produto\n3-Adicionar produto ");
            int opcao2;
            do{
                System.out.print("Opção: ");
                String valor = sc.nextLine();
                opcao2 = v.stringInteger(valor);
            }while(opcao2 != 1 && opcao2 != 2 && opcao !=3);
            sc.nextLine();

            if(opcao2 == 1){
                EditarQuantidadeProduto();
            }else if(opcao2==2){
                removerProduto();
            }
            else{ //adicionar produto à fatura
                AdicionarProdutoFatura(dados);
            }
        }
    }

    private void AdicionarProdutoFatura(Dados dados){
        String valor;
        boolean verificaCodigo=true;
        int opcao;
        do{
            System.out.print("Código do produto: ");
            valor= sc.nextLine();
            opcao = v.stringInteger(valor);
        }while(valor.length()<12 || opcao==0);

        for(Produtos p: listaProdutos){
            if(p.getCodigo().equals(valor)) System.out.println("\nO produto já existe na fatura, altere a quantidade.");

            else{
                verificaCodigo=false;
            }
        }
        
        if(!verificaCodigo) criarProduto(dados);
    }



    private void EditarQuantidadeProduto(){  //só podemos editar a quantidade porque assim nao faria sentido ver se o codigo ja existe
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
                p.setQuantidade(Integer.parseInt(q));
                encontrado = true;
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

    public void AdicionarProduto(Produtos produto){
        for(Produtos p:listaProdutos){
            if(p.getCodigo().equals(produto.getCodigo())){
                System.out.println("\nProduto já existe na fatura, incrementar quantidade.");
                p.setQuantidade(p.getQuantidade()+produto.getQuantidade());
                return;
            }
        }
        this.listaProdutos.add(produto);
    }

    private void criarProduto(Dados dados){
        String codigo=v.VerificaCodigo();
        boolean verifica;
        Produtos produtoEncontrar=dados.EncontrarProdutoDados(codigo);
        //verifica se o produto ja esta criado na lista dos dados
    
        if(produtoEncontrar==null){
            verifica=false;
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
                        p.criarEditarProduto(dados);
                        AdicionarProduto(p); //adicionar à lista faturas
                        dados.AdicionarPordutosDados(p);

                    }else{
                        ProdutoAlimentar p = new ProdutoAlimentar();
                        p.criarEditarProduto(dados);
                        AdicionarProduto(p);
                        dados.AdicionarPordutosDados(p);
                    }
                 
                    break;

                case 2:
                    System.out.print("Tem prescrição médica?");
                    String prescrição= sc.nextLine();
                    if(prescrição.equalsIgnoreCase("sim")){
                        ProdutoFarmaciaPrescrito farmaciaP= new ProdutoFarmaciaPrescrito();
                        farmaciaP.CriarPrescrito(verifica,codigo);
                        AdicionarProduto(farmaciaP);
                        dados.AdicionarPordutosDados(farmaciaP);
                    }
                    else{
                        ProdutoFarmacia farmacia= new ProdutoFarmacia();
                        farmacia.CriarNaoPrescrito(verifica,codigo);
                        AdicionarProduto(farmacia);
                        dados.AdicionarPordutosDados(farmacia);
                    }

                    break;
            }
        } 
            
        else { //se ja existir eu só vou pedir a quantidade do produto
            //produtoEncontrar vai estar à apontar para a mesma class do ooutro que ja existe
            verifica=true;
            boolean verificaListaProduto=false;
            produtoEncontrar.criarProdutosComum(verifica,codigo);
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
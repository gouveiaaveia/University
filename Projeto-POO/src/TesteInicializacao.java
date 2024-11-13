public class TesteInicializacao {

    public static void inicializarDadosDeTeste(Dados dados) {
        // Criar Cliente
        Cliente clienteTeste = new Cliente();
        clienteTeste.setNome("João Silva");
        clienteTeste.setNif("123456789");
        clienteTeste.setLocalizacao("madeira");
        dados.adicionarCliente(clienteTeste);
        System.out.println("Cliente criado para teste: " + clienteTeste);

        // Criar Produto
        ProdutoAlimentar produtoTeste = new ProdutoAlimentar();
        produtoTeste.setCodigo("P001");
        produtoTeste.setNome("Maçã Biológica");
        produtoTeste.setPrecoUnitario(2);
        produtoTeste.setQuantidade(10);
        produtoTeste.determinarTipoTaxaIVA();
        System.out.println("Produto criado para teste: " + produtoTeste);

        // Criar Fatura e adicionar produto
        Fatura faturaTeste = new Fatura(clienteTeste);
        faturaTeste.setNumeroFatura("F0001");
        faturaTeste.setData("13/11/2023");
        faturaTeste.adicionarProduto(produtoTeste);
        dados.adicionarFatura(faturaTeste);
        System.out.println("Fatura criada para teste: " + faturaTeste);

        // Exibir as informações adicionadas
        System.out.println("=== Dados de Teste Adicionados ===");
        dados.mostrarListaClientes();
        dados.mostrarListaFaturas();
    }
}

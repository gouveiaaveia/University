import java.util.Scanner;
import java.io.Serializable;


/**
 * Representa um produto de farmácia, que pode ser não prescrito, com uma categoria específica.
 */
public class ProdutoFarmacia extends Produtos implements Serializable{

    private String categoria;

    /**
     * Construtor que inicializa um produto de farmácia com os dados fornecidos.
     *
     * @param codigo o código do produto.
     * @param nome o nome do produto.
     * @param descricao a descrição do produto.
     * @param quantidade a quantidade disponível do produto.
     * @param precoUnitario o preço unitário do produto.
     * @param categoria a categoria do produto (ex: Beleza, BemEstar, Bebes, Animais, Outros).
     */
    public ProdutoFarmacia(String codigo, String nome, String descricao, int quantidade,double precoUnitario,String categoria){
        super(codigo,nome,descricao,quantidade,precoUnitario);
        this.categoria=categoria;
    }

    /**
     * Construtor padrão que inicializa um produto de farmácia com valores vazios.
     */
    public ProdutoFarmacia(){
        super();
        this.categoria="";
    }

    /**
     * Cria um produto de farmácia não prescrito com as informações fornecidas pelo usuário.
     *
     * @param verifica indica se o código do produto já foi verificado.
     * @param codigo o código do produto.
     * @param sc o objeto Scanner para leitura da entrada do usuário.
     * @param v o objeto Verificacoes para validação das entradas.
     */
    public void criarNaoPrescrito(boolean verifica, String codigo, Scanner sc, Verificacoes v){
        super.criarProdutosComum(verifica,codigo, sc, v);
        setCategoria(Categoria(sc, v));
    }

    private String Categoria(Scanner sc, Verificacoes v){
        String categoria;

        do{
            System.out.print("\nCategoria (Beleza,BemEstar,Bebes,Animais,Outros):");
            categoria=sc.nextLine();
        }while(!v.verificaCategoria(categoria));
        return categoria;
    }

    /**
     * Retorna uma representação textual do produto de farmácia não prescrito.
     *
     * @return uma string contendo as informações do produto de farmácia.
     */
    public String toString(){
        String str="";
        str+=super.toString();
        str+="Produto Farmacia Não Prescrito\n";
        str+="Categoria: "+this.categoria+"\n";
        return str;
    }

    /**
     * Define a categoria do produto de farmácia.
     *
     * @param categoria a categoria do produto.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    /**
     * Retorna a categoria do produto de farmácia.
     *
     * @return a categoria do produto.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Calcula a taxa de IVA aplicável ao produto com base na localização e categoria.
     *
     * @param localizacao a localização onde o produto será vendido.
     * @return a taxa de IVA para o produto.
     */
    @Override
    public double obterIVA(String localizacao){
        TabelaIVA tabela= new TabelaIVA(0,0);
        TabelaIVA tabelaValores= tabela.getTabelaPorLocalizacao(localizacao, "farmacia");

        if(this.categoria.equalsIgnoreCase("animais")) return((tabelaValores.getTaxaNormal()*100)-1/100);

        return tabelaValores.getTaxaNormal();
    }

    /**
     * Calcula o valor do produto com IVA por unidade, com base na localização.
     *
     * @param localizacao a localização onde o produto será vendido.
     * @return o valor unitário do produto com IVA.
     */
    @Override
    public double valorComIVA(String localizacao){ //iva por cada um produtor
        return getPrecoUnitario() + (getPrecoUnitario() * obterIVA(localizacao));

    }

    /**
     * Calcula o valor total do produto sem IVA, multiplicando o preço unitário pela quantidade.
     *
     * @return o valor total do produto sem IVA.
     */
    @Override
    public double valorTotalSemIVA(){
        return (double) getQuantidade() * getPrecoUnitario();
    }

    /**
     * Calcula o valor total do produto com IVA, multiplicando o preço total com IVA pela quantidade.
     *
     * @param localizacao a localização onde o produto será vendido.
     * @return o valor total do produto com IVA.
     */
    @Override
    public double valorTotalComIVA(String localizacao){
        return getQuantidade() * valorComIVA(localizacao);
    }


}
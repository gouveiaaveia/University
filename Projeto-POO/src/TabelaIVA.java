import java.util.ArrayList;

public class TabelaIVA {
    private int taxaReduzida;
    private int taxaIntermedia;
    private final int taxaNormal; //o valor não pode ser modificado depois desta class
    private int Taxaprescricao;

    public TabelaIVA(int taxaReduzida, int taxaIntermedia, int taxaNormal) {
        this.taxaReduzida = taxaReduzida;
        this.taxaIntermedia = taxaIntermedia;
        this.taxaNormal = taxaNormal;
    }

    public TabelaIVA(int taxaNormal, int TaxaPrescricao) {
        this.taxaNormal = taxaNormal;
        this.Taxaprescricao = TaxaPrescricao;
    }

    // Método não estático para obter a tabela IVA com base na localização
    public TabelaIVA getTabelaPorLocalizacao(String localizacao, String tipoProduto) {
        if(tipoProduto.equals("alimentar")){
            switch (localizacao) {
                case "portugal continental":
                    return new TabelaIVA(6, 13, 23);
                case "madeira":
                    return new TabelaIVA(5, 12, 22);
                case "açores":
                    return new TabelaIVA(4, 10, 18);
                default:
                    throw new IllegalArgumentException("Localização desconhecida");
            }
        }else{
            switch (localizacao){
                case "portugal continental":
                    return new TabelaIVA(6,23);
                case "madeira":
                    return new TabelaIVA(5, 23);
                case "açores":
                    return new TabelaIVA(4, 23);
                default:
                    throw new IllegalArgumentException("Localização desconhecida");
            }
        }
    }

    public double getTaxaReduzida() { return (double) taxaReduzida / 100; }
    public double getTaxaIntermedia() { return (double) taxaIntermedia / 100; }
    public double getTaxaNormal() { return (double) taxaNormal / 100; }
    public double getTaxaprescricao() { return (double) Taxaprescricao / 100; }
}

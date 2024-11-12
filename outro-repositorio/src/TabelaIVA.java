import java.util.ArrayList;

public class TabelaIVA {
    private int taxaReduzida;
    private int taxaIntermedia;
    private int taxaNormal;

    public TabelaIVA(int taxaReduzida, int taxaIntermedia, int taxaNormal) {
        this.taxaReduzida = taxaReduzida;
        this.taxaIntermedia = taxaIntermedia;
        this.taxaNormal = taxaNormal;
    }

    //para não termos que instanciar a class
    public static TabelaIVA getTabelaPorLocalizacao(String localizacao) {
        switch (localizacao) {
            case "Portugal Continental":
                return new TabelaIVA(6, 13, 23);
            case "Madeira":
                return new TabelaIVA(5, 12, 22);
            case "Açores":
                return new TabelaIVA(4, 10, 18);
            default:
                throw new IllegalArgumentException("Localização desconhecida");
        }
    }

    public int getTaxaReduzida() { return taxaReduzida / 100; }
    public int getTaxaIntermedia() { return taxaIntermedia / 100; }
    public int getTaxaNormal() { return taxaNormal / 100; }
}

/**
 * Classe Servidor representa um tipo específico de computador com características de servidor.
 * Extende a classe Computador e define valores específicos para o tipo, nível, e características de desempenho.
 * Inclui métodos para calcular e exibir o consumo de energia.
 *
 * @author Francisco Gouveia e Ricardo
 * @version 1.0
 */
public class Servidor extends Computador {

    /**
     * Construtor da classe Servidor. Inicializa um objeto Servidor com valores predefinidos para tipo,
     * nível, arquitetura, limites de memória RAM, espaço de armazenamento e capacidade de processamento.
     */
    public Servidor() {
        super("Servidor", "Cloud", 2, 7, 10, 10, 14, 3.0F, 4.0F);
    }

    /**
     * Calcula e define o consumo energético do servidor, com base na sua capacidade de processamento.
     * O consumo é determinado multiplicando-se a capacidade de processamento por 80.
     */
    public void consumoEnergia() {
        this.setConsumoEnergetico(80 * this.getCapacidadeProcessamento());
    }

    /**
     * Retorna o consumo energético atual do servidor em formato de string, com precisão de duas casas decimais.
     *
     * @return uma string formatada com o valor do consumo energético.
     */
    public String mostrarConsumo() {
        return String.format("Consumo: %.2f", this.getConsumoEnergetico());
    }

    /**
     * Retorna uma representação em string dos atributos do servidor, utilizando o método toString da classe Computador.
     *
     * @return uma string com as informações detalhadas do servidor.
     */
    @Override
    public String toString() {
        return super.toString(this.getId(), this.getNivel(), this.getRam(), this.getEspacoArmazenamento(), this.getCapacidadeProcessamento());
    }
}

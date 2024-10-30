/**
 * Classe RaspberryPi representa um tipo específico de computador usado em aplicações IoT,
 * baseado na arquitetura e características específicas de um Raspberry Pi.
 * Extende a classe Computador, definindo valores específicos de tipo, nível e características de desempenho.
 *
 * @autor Francisco Gouveia e Ricardo Domingues
 * @versão 1.0
 */
public class RaspberryPi extends Computador {

    /**
     * Construtor da classe RaspberryPi. Inicializa um objeto RaspberryPi com valores predefinidos
     * para tipo, nível, arquitetura, limites de memória RAM, espaço de armazenamento e capacidade de processamento.
     */
    public RaspberryPi() {
        super("Raspberry Pi", "IoT", 2, 1, 4, 4, 8, 1.0F, 2.1F);
    }

    /**
     * Calcula e define o consumo energético do Raspberry Pi com base na capacidade de processamento.
     * Define o consumo energético como 20 vezes o valor da capacidade de processamento.
     */
    public void consumoEnergia() {
        this.setConsumoEnergetico(20 * this.getCapacidadeProcessamento());
    }

    /**
     * Retorna o consumo energético atual do Raspberry Pi em formato de string, com precisão de duas casas decimais.
     *
     * @return uma string formatada com o valor do consumo energético.
     */
    public String mostrarConsumo() {
        return String.format("Consumo: %.2f", this.getConsumoEnergetico());
    }

    /**
     * Retorna uma representação em string dos atributos do Raspberry Pi, utilizando o método toString da classe Computador.
     *
     * @return uma string com as informações detalhadas do Raspberry Pi.
     */
    @Override
    public String toString() {
        return super.toString(this.getId(), this.getNivel(), this.getRam(), this.getEspacoArmazenamento(), this.getCapacidadeProcessamento());
    }
}

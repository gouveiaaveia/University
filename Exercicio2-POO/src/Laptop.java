/**
 * Classe Laptop representa um tipo específico de computador com características de laptop.
 * Extende a classe Computador e define valores específicos para o tipo, nível e características de desempenho.
 * Inclui métodos para calcular e exibir o consumo de energia, considerando a presença de GPU.
 *
 * @author Francisco Gouveia
 * @version 1.0
 */
public class Laptop extends Computador {

    /**
     * Atributo que indica se o laptop possui uma GPU dedicada.
     */
    private boolean GPU;

    /**
     * Construtor da classe Laptop. Inicializa um objeto Laptop com valores predefinidos para tipo,
     * nível, arquitetura, limites de memória RAM, espaço de armazenamento e capacidade de processamento.
     */
    public Laptop() {
        super("Laptop", "Edge", 2, 5, 7, 8, 10, 2.0F, 3.1F);
        setGPU(rand.nextBoolean());
    }

    /**
     * Calcula e define o consumo energético do laptop, considerando a capacidade de processamento.
     * Se o laptop possui uma GPU dedicada, o consumo energético aumenta em 20%.
     */
    public void consumoEnergia() {
        this.setConsumoEnergetico(50 * this.getCapacidadeProcessamento());

        // Verifica se o laptop possui GPU para ajustar o consumo energético
        if (this.getGPU()) {
            this.setConsumoEnergetico(this.getConsumoEnergetico() * 1.2);
        }
    }

    /**
     * Retorna o consumo energético atual do laptop em formato de string, com precisão de duas casas decimais.
     *
     * @return uma string formatada com o valor do consumo energético.
     */
    public String mostrarConsumo() {
        return String.format("Consumo: %.2f", this.getConsumoEnergetico());
    }

    /**
     * Retorna uma representação em string dos atributos do laptop, utilizando o método toString da classe Computador.
     *
     * @return uma string com as informações detalhadas do laptop.
     */
    @Override
    public String toString() {
        return super.toString(this.getId(), this.getNivel(), this.getRam(), this.getEspacoArmazenamento(), this.getCapacidadeProcessamento());
    }

    /**
     * Retorna o estado da presença de uma GPU dedicada no laptop.
     *
     * @return true se o laptop possui GPU, false caso contrário.
     */
    public boolean getGPU() {
        return GPU;
    }

    /**
     * Define a presença de uma GPU dedicada no laptop.
     *
     * @param GPU valor booleano que indica se o laptop possui GPU.
     */
    public void setGPU(boolean GPU) {
        this.GPU = GPU;
    }
}

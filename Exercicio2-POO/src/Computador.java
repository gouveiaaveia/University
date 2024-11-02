/**
 * @author Francisco Gouveia e Ricardo Domingues
 * @version 1.0
 */

import java.util.Random;

/**
 * Class Computador (class pai) contém a atributos e métodos mútuos de cada tipo de computador
 */
public class Computador {
    /**
     * Atributo contém o id do computador
     */
    protected int id;
    /**
     * Atributo contém o ram do computador
     */
    protected int ram;
    /**
     * Atributo contém o armazenamento do computador
     */
    protected int espacoArmazenamento;
    /**
     * Atributo contém a capacidade de processamento do computador
     */
    protected double capacidadeProcessamento;
    /**
     * Atributo contém a arquitetura do computador
     */
    protected String arquitetura;
    /**
     * Atributo contém o tipo do computador
     */
    protected String tipo;
    /**
     * Atributo contém o nível do computador
     */
    protected String nivel;
    /**
     * Atributo contém o consumo energetico do computador
     */
    protected double consumoEnergetico;
    /**
     * Atributo contém as possíveis arquiteturas de um computador
     */
    private String[] arquiteturas;

    /**
     * Atributo para gerar valores aleatórios
     */
    protected Random rand = new Random();

    /**
     * Método responsável por criar um computador e que gera valores aleatórios para os seus componentes
     * @param tipo tipo do computador
     * @param nivel nivel do computador
     * @param arq arquitetura do computador
     * @param ram1 limite inferior da ram
     * @param ram2 limite superior da ram
     * @param esp1 limite inferior do espaço
     * @param esp2 limite superior do espaço
     * @param cap1 limite inferior da capacidade de processamento
     * @param cap2 limite superior da capacidade de processamento
     */
    public Computador(String tipo, String nivel, int arq, int ram1, int ram2, int esp1, int esp2, float cap1, float cap2) {
        this.arquiteturas = new String[]{"x64", "ARM"};

        inicializarAtributosAleatorios(arq, ram1, ram2, esp1, esp2, cap1, cap2);

        this.id = 0;
        this.tipo = tipo;
        this.nivel = nivel;
        this.consumoEnergetico = 0;
    }

    /**
     * Método responsável por inicializar os atributos de forma aleatória.
     * Gera valores aleatórios para os atributos arquitetura, ram, espacoArmazenamento, e capacidadeProcessamento
     * de acordo com os intervalos fornecidos.
     *
     * @param arq valor limite para o índice da arquitetura a ser selecionada
     * @param ram1 limite inferior para geração aleatória da ram
     * @param ram2 limite superior para geração aleatória da ram
     * @param esp1 limite inferior para geração aleatória do espaço de armazenamento
     * @param esp2 limite superior para geração aleatória do espaço de armazenamento
     * @param cap1 limite inferior para a capacidade de processamento
     * @param cap2 limite superior para a capacidade de processamento
     */
    private void inicializarAtributosAleatorios(int arq, int ram1, int ram2, int esp1, int esp2, float cap1, float cap2) {
        this.arquitetura = arquiteturas[rand.nextInt(arq)];
        this.ram = (int) Math.pow(2, rand.nextInt(ram1, ram2));
        this.espacoArmazenamento = (int) Math.pow(2, rand.nextInt(esp1, esp2));
        this.capacidadeProcessamento = cap1 + rand.nextFloat() * (cap2 - cap1);
    }

    /**
     * Método responsável por definir o valor do consumo energético ao valor inicializado,
     * conforme definido no construtor ou posteriormente alterado pelo usuário.
     */
    public void consumoEnergia(){
        setConsumoEnergetico(getConsumoEnergetico());
    }


    /**
     * Método que retorna uma string com as informações principais de um computador.
     *
     * @param id id do computador
     * @param nivel nível do computador
     * @param ram quantidade de memória RAM do computador, em GB
     * @param espaco espaço de armazenamento do computador, em GB
     * @param cap capacidade de processamento do computador, em GHz
     * @return uma string formatada com os dados principais do computador
     */
    public String toString(int id, String nivel, int ram, int espaco, float cap) {
        return String.format("Id: %d\nNivel: %s\nRam: %dGB\nEspaço disco: %dGB\nCapacidade: %.1fGHz\n",
                id, nivel, ram, espaco, cap);
    }

    /**
     * Método que retorna o valor do id
     * @return valor do id (int)
     */
    public int getId() {
        return id;
    }

    /**
     * Método que atualiza o valor do id
     * @param id valor que irá ser definido no atributo id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método que retorna o valor da ram
     * @return valor da ram (int)
     */
    public int getRam() {
        return ram;
    }

    /**
     * Método que atualiza o valor da ram
     * @param ram valor que irá ser definido no atributo ram
     */
    public void setRam(int ram) {
        this.ram = ram;
    }
    /**
     * Método que retorna o valor do aramazenamento do computador
     * @return valor do armazenamento (int)
     */
    public int getEspacoArmazenamento() {
        return espacoArmazenamento;
    }
    /**
     * Método que atualiza o valor do aramazenamento do computador
     * @param espacoArmazenamento valor que irá ser definido no atributo ram
     */
    public void setEspacoArmazenamento(int espacoArmazenamento) {
        this.espacoArmazenamento = espacoArmazenamento;
    }
    /**
     * Método que retorna o valor da capacidade de processamento do computador
     *
     * @return valor da capacidade de processamento (double)
     */
    public float getCapacidadeProcessamento() {
        return (float) capacidadeProcessamento;
    }
    /**
     * Método que atualiza o valor da capacidade de processamento do computador
     * @param capacidadeProcessamento valor que irá ser definido no atributo capacidadeProcessamento
     */
    public void setCapacidadeProcessamento(double capacidadeProcessamento) {
        this.capacidadeProcessamento = capacidadeProcessamento;
    }

    /**
     * Método que retorna o valor da arquitetura do computador
     *
     * @return valor da arquitetura (String)
     */
    public String getArquitetura() {
        return arquitetura;
    }

    /**
     * Método que atualiza o valor da arquitetura do computador
     * @param arquitetura valor que irá ser definido no atributo arquitetura
     */
    public void setArquitetura(String arquitetura) {
        this.arquitetura = arquitetura;
    }

    /**
     * Método que retorna o valor do tipo do computador
     *
     * @return valor do tipo (String)
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Método que atualiza o valor do tipo do computador
     * @param tipo valor que irá ser definido no atributo tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Método que retorna o valor do nivel do computador
     *
     * @return valor do nivel (String)
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * Método que atualiza o valor do nivel do computador
     * @param nivel valor que irá ser definido no atributo nivel
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * Método que retorna o valor do consumo energético do computador
     *
     * @return valor do consumo energético (double)
     */
    public double getConsumoEnergetico() {
        return consumoEnergetico;
    }

    /**
     * Método que atualiza o valor do cosumoEnergetico do computador
     * @param consumoEnergetico valor que irá ser definido no atributo consumoEnergetico
     */
    public void setConsumoEnergetico(double consumoEnergetico) {
        this.consumoEnergetico = consumoEnergetico;
    }
}

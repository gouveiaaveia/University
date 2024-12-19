/**
 * @author Francisco Gouveia e Ricardo Domingues
 * @version 1.0
 */

import java.util.ArrayList;
/**
 * Classe NCSLab é responsável por gerenciar um laboratório com vários computadores.
 * Contém um ArrayList de objetos Computador e oferece funcionalidades para gerar o ID de cada computador,
 * criar diferentes tipos de computadores, e calcular o consumo total de energia.
 */
public class NCSLab {
    /**
     * ArrayList que armazena todos os objetos Computador no laboratório.
     */
    private ArrayList<Computador> computadores;

    /**
     * Construtor da classe NCSLab que inicializa a lista de computadores.
     */
    public NCSLab() {
        computadores = new ArrayList<>();
    }

    /**
     * Gera um ID único para cada computador. Se a lista de computadores estiver vazia, retorna 1;
     * caso contrário, gera o próximo ID sequencial.
     *
     * @return um novo ID único para o próximo computador a ser adicionado.
     */
    private int gerarId() {
        if (computadores.isEmpty()) {
            return 1;
        } else {
            return computadores.get(computadores.size() - 1).getId() + 1;
        }
    }

    /**
     * Cria um conjunto de computadores, adicionando um Servidor, um Laptop, e um RaspberryPi ao ArrayList
     * e gerando IDs únicos para cada um.
     * Este método adiciona 15 computadores ao total (5 de cada tipo).
     */
    public void criarNCS() {
        for (int i = 0; i < 5; i++) {
            Servidor computador = new Servidor();
            Laptop computador2 = new Laptop();
            RaspberryPi computador3 = new RaspberryPi();

            computador.setId(gerarId());
            computadores.add(computador);

            computador2.setId(gerarId());
            computadores.add(computador2);

            computador3.setId(gerarId());
            computadores.add(computador3);
        }
    }

    /**
     * Filtra e retorna as informações dos computadores com arquitetura "x64" em formato de string.
     *
     * @return uma string contendo as informações de todos os computadores com arquitetura "x64".
     */
    public String arquiteturaX64() {
        String retorno = "";
        for (Computador computadore : computadores) {
            if (computadore.getArquitetura().equals("x64")) {
                retorno += computadore.toString();
                retorno += "\n";
            }
        }
        return retorno;
    }

    /**
     * Calcula e exibe o consumo energético total de todos os computadores no laboratório.
     * Para cada computador, chama o método consumoEnergia e acumula o valor total.
     *
     */
    public void consumoTotal() {
        double valor = 0;
        System.out.println("Consumo de cada computador:");
        for (Computador computador : computadores) {
            computador.consumoEnergia();
            System.out.println(computador.getId() + ": " + computador.getConsumoEnergetico() + " watts");
        }
    }

    /**
     * Método toString que retorna uma representação em string de todos os computadores no laboratório,
     * incluindo suas informações detalhadas.
     *
     * @return uma string com as informações de todos os computadores.
     */
    @Override
    public String toString() {
        String retorno = "";
        for (Computador computadore : computadores) {
            retorno += computadore.toString();
            retorno += "\n";
        }

        return retorno;
    }

    /**
     * Retorna o ArrayList de computadores.
     *
     * @return o ArrayList contendo todos os objetos Computador no laboratório.
     */
    public ArrayList<Computador> getComputadores() {
        return computadores;
    }

    /**
     * Define o ArrayList de computadores.
     *
     * @param computadores um ArrayList de objetos Computador a ser configurado.
     */
    public void setComputadores(ArrayList<Computador> computadores) {
        this.computadores = computadores;
    }
}

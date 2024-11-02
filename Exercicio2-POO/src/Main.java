/**
 * @author Francisco Gouveia e Ricardo Domingues
 * @version 1.0
 *
 * Class main responsável por iniciar o programa, este programa tem o objetivo de criar uma laboratório NCS que contém alguns computadores
 * Mostra todos os computadores da NCS, depois todos os computadores de arquitetura x64
 * Por fim mostra o consumo de cada computador e o consumo total
 */
public class Main {
    /**
     * Método estático main
     * @param args
     */
    public static void main(String[] args) {

        NCSLab ncsLab = new NCSLab();
        ncsLab.criarNCS();

        System.out.println("Todos os computadores da NCS:\n" + ncsLab);
        System.out.println("Computadores com arquitetura x64:\n" + ncsLab.arquiteturaX64());
        ncsLab.consumoTotal();

    }
}
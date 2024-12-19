public class Exercicio1 {

    public static void mostrarInfo(String[][] tabelaEspecialidades, int contador){
        System.out.printf("\n");
        //percorre a matriz de especialidades e mostra os seu valores
        for(int i = 0; i < contador; i++){
            System.out.printf("%s: %.7s\n", tabelaEspecialidades[i][0], tabelaEspecialidades[i][1]);
        }
    }

    public static int totalEspecialidade(String[][] tabelaEspecialidades, String especialidade, int contador, double salario){
        boolean existe = false;
        for(int i = 0; i<contador; i++){
            if(tabelaEspecialidades[i][0].equals(especialidade)){
                existe = true;
                //adicionar salário
                double transformarDouble = Double.parseDouble(tabelaEspecialidades[i][1]);
                transformarDouble += salario;
                String resultado = String.format("%.2f", transformarDouble);
                tabelaEspecialidades[i][1] = resultado;
                break;
            }
        }
        //caso não exista a especialidade, adicona à tabela com o respetivo salário
        if(!existe){
            tabelaEspecialidades[contador][0] = especialidade;
            tabelaEspecialidades[contador][1] = String.valueOf(salario);
            contador++;
        }

        return contador;
    }

    public static double anosServico(String anos, double salario){
        int ano = Integer.parseInt(anos);
        for(int i = 0; i < ano/5; i++){
            salario += salario * 0.04;
        }

        return salario;
    }

    public static double horasExtras(String horas, String custoHoras){
        int hora = Integer.parseInt(horas);
        int custo = Integer.parseInt(custoHoras);

        return hora*custo;
    }

    public static void calcularSalario(String[] especialidades, String[] medicos) {
        String[][] tabelaEspecialidades = new String[especialidades.length][2];

        int contador = 0;
        for(String medico: medicos){
            String[] infoMedico = medico.split("/", 4);
            double salario = 0;

            if(infoMedico.length != 4){
                continue;
            }

            //defenir as variáveis para melhor manutenção do código
            String nomeMedico = infoMedico[0];
            String especialidadeMedico = infoMedico[1];
            String anosServico = infoMedico[2];
            String horasExtrasMedico = infoMedico[3];

            for(String especialidade: especialidades){
                String[] infoEspecialidade = especialidade.split("/", 3);

                //garantir que tem todas as informações necessárias
                if(infoEspecialidade.length < 3){
                    continue;
                }

                //defenir as variáveis para melhor manutenção do código
                String nomeEspecialidade = infoEspecialidade[0];
                String salarioBase = infoEspecialidade[1];
                String extra = infoEspecialidade[2];

                String especialidadeMinuscula = especialidadeMedico.toLowerCase();

                if(especialidadeMinuscula.equals(nomeEspecialidade.toLowerCase())){
                    //salário base
                    salario += Double.parseDouble(salarioBase);

                    //anos de serviço
                    salario = anosServico(anosServico, salario);

                    //horas extra
                    salario += horasExtras(horasExtrasMedico, extra);

                    //adicona a especialidade a uma tabela e a soma do salário
                    contador = totalEspecialidade(tabelaEspecialidades, nomeEspecialidade, contador, salario);
                    
                    //mostrar o salário de cada médico ao longo da execução
                    System.out.printf("%s: %.2f\n", nomeMedico, salario);
                    break;
                }
            }
        }
        mostrarInfo(tabelaEspecialidades, contador);
    }

    public static void main(String[] args) {

        String[] especialidades = {

                //especialidade/salário base/custo da hora extra
                "Radiologia/2030/50",
                "Oftalmologia/2500/70",
                "Pediatria/2700/75"
        };

        String[] medicos = {

                //nome/especialidade/anos de serviço/ horas extra
                "Vasco Santana/Radiologia/15/10",
                "Laura Alves/oftalmologia/5/7",
                "António Silva/oftalmologia/12/5",
        };

        //verificar se existem dados nas tabelas
        if(especialidades.length == 0 || medicos.length == 0){
            System.out.println("O sistema não tem dados sufecientes para calcular os salarios");
        }

        calcularSalario(especialidades, medicos);

    }
}

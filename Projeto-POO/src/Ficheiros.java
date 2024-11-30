import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Ficheiros {
    private final String caminhoRelativoFicheiro;
    private final String caminhoFicheiroObjetos;
    private final String caminhoFicheiroFaturas;

    public Ficheiros(String caminhoRelativoFicheiro, String caminhoFicheiroObjetos) {
        this.caminhoRelativoFicheiro = caminhoRelativoFicheiro;
        this.caminhoFicheiroObjetos = caminhoFicheiroObjetos;
        this.caminhoFicheiroFaturas = "";
    }

    public Ficheiros(String caminhoFicheiroFaturas) {
        this.caminhoRelativoFicheiro = "";
        this.caminhoFicheiroObjetos = "";
        this.caminhoFicheiroFaturas = caminhoFicheiroFaturas;
    }

    public boolean verificaFicheiro() {
        return new File(this.caminhoFicheiroObjetos).exists();
    }

    public void lerFicheiroTexto(Dados dados) {
        File f = new File(this.caminhoRelativoFicheiro);

        if (!f.exists() || !f.isFile()) {
           System.out.print("\nFicheiro não existe.");
           return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                switch (line) {
                    case "Clientes":
                        lerClientes(dados, br);
                        break;
                    case "Produtos":
                        lerProdutos(dados, br);
                        break;
                    case "Fatura":
                        lerFaturas(dados, br);
                        break;
                    default:
                        System.out.println("Seção desconhecida: " + line);
                        break;
                }
            }
        } catch (IOException ex) {
            System.out.print("\nErro ao ler o ficheiro de texto: " + ex.getMessage());
        }
    }


    public void lerFicheiroFaturas(Dados dados){
        File f = new File(this.caminhoFicheiroFaturas);

        if (!f.exists() || !f.isFile()) {
            System.out.println("Ficheiro não existe!!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

            String line;

            while ((line = br.readLine()) != null) {
                lerFaturas(dados, br);
            }

        } catch (IOException ex) {
            System.out.print("\nErro ao ler o ficheiro de texto: " + ex.getMessage());
        }
    }

    public void escreverFicheiroFaturas(Dados dados){
        File f = new File(this.caminhoFicheiroFaturas);

        try{
            FileWriter fw = new FileWriter(f, false);
            BufferedWriter bw = new BufferedWriter(fw);
            escreverFaturas(dados, bw);
            bw.close();
        }catch (IOException ex){
            System.out.println("Erro a escrever no ficheiro!");
        }
    }

    private void lerClientes(Dados dados, BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals("-")) break;
            String[] listaDadosClientes = line.split("/");
            Cliente clienteNovo = new Cliente(listaDadosClientes[0], listaDadosClientes[1], listaDadosClientes[2]);
            dados.adicionarCliente(clienteNovo);
        }
    }

    private void lerProdutos(Dados dados, BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals("-")) break;
            String[] listaDadosProdutos = line.split("/", -1); // Preserva espaços vazios
            switch (listaDadosProdutos[0]) {
                case "alimentar":
                    ArrayList<String> certificacoes = new ArrayList<>(Arrays.asList(listaDadosProdutos[7].split(",")));
                    ProdutoAlimentar p1 = new ProdutoAlimentar(
                            listaDadosProdutos[1], listaDadosProdutos[2], listaDadosProdutos[3],
                            Integer.parseInt(listaDadosProdutos[4]), Double.parseDouble(listaDadosProdutos[5]),
                            listaDadosProdutos[6],certificacoes
                    );
                    dados.adicionarPordutosDados(p1);
                    break;
                case "biologico":
                    certificacoes = new ArrayList<>(Arrays.asList(listaDadosProdutos[7].split(",")));
                    ProdutoAlimentarBiologico p2 = new ProdutoAlimentarBiologico(
                            listaDadosProdutos[1], listaDadosProdutos[2], listaDadosProdutos[3],
                            Integer.parseInt(listaDadosProdutos[4]), Double.parseDouble(listaDadosProdutos[5]),
                            listaDadosProdutos[6],certificacoes);

                    dados.adicionarPordutosDados(p2);
                    break;
                case "farmacia":
                    ProdutoFarmacia p3 = new ProdutoFarmacia(
                            listaDadosProdutos[1], listaDadosProdutos[2], listaDadosProdutos[3],
                            Integer.parseInt(listaDadosProdutos[4]), Double.parseDouble(listaDadosProdutos[5]),
                            listaDadosProdutos[6]
                    );
                    dados.adicionarPordutosDados(p3);
                    break;
                default:
                    ProdutoFarmaciaPrescrito p4 = new ProdutoFarmaciaPrescrito(
                            listaDadosProdutos[1], listaDadosProdutos[2], listaDadosProdutos[3],
                            Integer.parseInt(listaDadosProdutos[4]), Double.parseDouble(listaDadosProdutos[5]),
                            listaDadosProdutos[6]
                    );
                    dados.adicionarPordutosDados(p4);
                    break;
            }
        }
    }

    private void lerFaturas(Dados dados, BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] listaDadosFatura = line.split(";");
            String[] fatura = listaDadosFatura[0].split("/");
            Cliente cliente = dados.encontrarCliente(fatura[1]);
            ArrayList<Produtos> listaProdutos = new ArrayList<>();

            for (String i : listaDadosFatura[1].split("/")) {
                Produtos produto = dados.encontrarProdutoDados(i);
                listaProdutos.add(produto);
            }

            Fatura f = new Fatura(fatura[0], cliente, fatura[2], listaProdutos);
            dados.adicionarFatura(f);
        }
    }

    private void escreverFaturas(Dados dados, BufferedWriter bw) throws IOException {
        for(Fatura f : dados.getFaturas()){
            bw.write(f.getNumeroFatura() + "/" + f.getCliente().getNif() + "/" +
                    f.getData() + ";");
            int count = 0;
            for(Produtos p : f.getListaProdutos()){
                if(count > 0){
                    bw.write("/" + p.getCodigo());
                }else{
                    count += 1;
                    bw.write(p.getCodigo());
                }
            }
            bw.newLine();
        }
    }

    public void lerFicheiroObjetos(Dados dados){
        try{
            FileInputStream fis = new FileInputStream(this.caminhoFicheiroObjetos);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Cliente> clientes = (ArrayList<Cliente>) ois.readObject();
            ArrayList<Produtos> produtos = (ArrayList<Produtos>) ois.readObject();
            ArrayList<Fatura> faturas = (ArrayList<Fatura>) ois.readObject();
            dados.setClientes(clientes);
            dados.setProdutos(produtos);
            dados.setFaturas(faturas);
            ois.close();
        }catch (FileNotFoundException ex){
            System.out.print("\nFicheiro não encontrado: " + ex.getMessage());
        }catch (IOException ex){
            System.out.print("\nErro ao ler o ficheiro: " + ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.print("\nClasse não encontrada: ");
        }
    }

    public void escreverFicheiroObjetos(Dados dados) {
        try (FileOutputStream fos = new FileOutputStream(this.caminhoFicheiroObjetos);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(dados.getClientes());
            oos.writeObject(dados.getProdutos());
            oos.writeObject(dados.getFaturas());
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.print("\nFicheiro não encontrado: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.print("\nErro ao escrever no ficheiro");
        }
    }
}
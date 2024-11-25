import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Ficheiros{
    private String caminhoRelativoFicheiro1;
    private String caminhoFicheiroObjetos;

    public Ficheiros(String caminhoRelativoFicheiro, String caminhoFicheiroObjetos){
        this.caminhoRelativoFicheiro=caminhoRelativoFicheiro;
        this.caminhoFicheiroObjetos=caminhoFicheiroObjetos;
    }

    public boolean VerificaFicheiro(){
        File f= new File(this.caminhoFicheiroObjetos);
        if(f.exists()) return true;
        else false;
    }

    public void LerFicheiroTexto(Dados dados){
        System.out.print("lerr");
        File f=new File(this.caminhoRelativoFicheiro);
        if(f.exists() && f.isFile()){
            try{
                FileReader fr= new FileReader(f);
                BufferedReader br= new BufferedReader(fr);
                String line;
                while((line=br.readLine())!=null){
                    if(line.equals("Clientes")){
                        LerClientes(dados,br);
                    }
                    if(line.equals("Produtos")){
                        System.out.println("ProdutoSS");
                        LerProdutos(dados,br);
                    }
                    if(line.equals("Fatura")) {
                        LerFaturas(dados,br);
                    }
                }
                br.close();
            }
            catch(FileNotFoundException ex){
              System.out.print("\nErro a abrir o ficheiro de texto.");   
            }
            catch(IOException ex){
              System.out.print("\nErro a ler o ficheiro de texto.");   
            }


        }
        else{
            System.out.print("\nFicheiro não existe.");
        }

    }

    public void EscreverFicheiroObjetos(Dados dados){
        File f=new File(this.caminhoFicheiroObjetos);

        try{
            FileOutputStream fos=new FileOutputStream(f);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            EscreverObjetos(dados,oos);
            oos.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Erro a criar ficheiro.");
        }
        catch(IOException ex){
            System.out.println("Erro a escrever para o ficheiro.");
        }


    }

    public void LerFicheiroObjeto(Dados dados){
        File f=new File(this.caminhoFicheiroObjetos);

        try{
            FileIntputStream fis=new FileInputStream(f);
            ObjecInputStream ois= new ObjecInputStream(fis);
            while(true){
                try{
                    String tipo=ois.readUTF();
                    Object obj=ois.readObject();
                    if(obj.equals("Clientes")){
                        Produto produto=
                    }


                }
                catch(EOFException e){
                    System.out.println("Objetos lidos.");
                    break;
                }
            }

        }
        catch(FileNotFoundException ex){
            System.out.println("Erro a criar ficheiro.");
        }
        catch(IOException ex){
            System.out.println("Erro a escrever para o ficheiro.");
        }
        catch(ClassNotFoundExcption ex){
            System.out.println("Erro a converter objetos.");
        }

    }



    private void LerClientes(Dados dados, BufferedReader br) throws IOException{
        String line;
        while((line=br.readLine())!=null){
            if(line.equals("-")) break;
            String[] listaDadosClientes=line.split("/");
            Cliente clieteNovo= new Cliente(listaDadosClientes[0],listaDadosClientes[1],listaDadosClientes[2]);
            dados.adicionarCliente(clieteNovo);
        }

    }

    private void LerProdutos(Dados dados, BufferedReader br) throws IOException{
        String line;
        String[] listaDadosProdutos=new String[9];
        while((line=br.readLine())!=null){
            if(line.equals("-")) break;
            listaDadosProdutos=line.split("/",-1);//para preservar espacos sem nada
            if(listaDadosProdutos[0].equals("alimentar")){
                ArrayList<String> certificacoes = new ArrayList<>(Arrays.asList(listaDadosProdutos[8].split(",")));
                ProdutoAlimentar p= new ProdutoAlimentar(listaDadosProdutos[1],listaDadosProdutos[2],listaDadosProdutos[3],Integer.parseInt(listaDadosProdutos[4]),Double.parseDouble(listaDadosProdutos[5]),listaDadosProdutos[6],listaDadosProdutos[7],certificacoes);
                dados.AdicionarPordutosDados(p);
            }
            else if(listaDadosProdutos[0].equals("biologico")){
                ArrayList<String> certificacoes = new ArrayList<>(Arrays.asList(listaDadosProdutos[8].split(",")));
                ProdutoAlimentarBiologico p= new ProdutoAlimentarBiologico(listaDadosProdutos[1],listaDadosProdutos[2],listaDadosProdutos[3],Integer.parseInt(listaDadosProdutos[4]),Double.parseDouble(listaDadosProdutos[5]),listaDadosProdutos[6],listaDadosProdutos[7],certificacoes);
                dados.AdicionarPordutosDados(p);
            }
            else if(listaDadosProdutos[0].equals("farmacia")){
                ProdutoFarmacia p= new ProdutoFarmacia(listaDadosProdutos[1],listaDadosProdutos[2],listaDadosProdutos[3],Integer.parseInt(listaDadosProdutos[4]),Double.parseDouble(listaDadosProdutos[5]),listaDadosProdutos[6]);
                dados.AdicionarPordutosDados(p);

            }
            else{
                ProdutoFarmaciaPrescrito p= new  ProdutoFarmaciaPrescrito(listaDadosProdutos[1],listaDadosProdutos[2],listaDadosProdutos[3],Integer.parseInt(listaDadosProdutos[4]),Double.parseDouble(listaDadosProdutos[5]),listaDadosProdutos[6]);
                dados.AdicionarPordutosDados(p);
            }


        }

    }

    private void LerFaturas(Dados dados, BufferedReader br)throws IOException{
        String line;
        String[] listaDadosFatura=new String[2];
        String[] fatura= new String[3];
        
        Produtos produto;
        while((line=br.readLine())!=null){
            ArrayList<Produtos> listaProdutos= new ArrayList<>();
            listaDadosFatura=line.split(";");
            fatura=listaDadosFatura[0].split("/");
            Cliente cliente=dados.encontrarCliente(fatura[1]);
            for(String i:listaDadosFatura[1].split("/")){
                produto=dados.EncontrarProdutoDados(i);
                listaProdutos.add(produto);
            }
            Fatura f=new Fatura(fatura[0],cliente,fatura[2],listaProdutos);
            dados.adicionarFatura(f);
        }
    }
    
    private void EscreverObjetos(Dados dados, ObjectOutputStream oos)throws IOException{
        oos.writeUTF("Clientes");
        for(Cliente c:dados.clientes){
            oos.writeObject(c);
        }
        oos.writeUTF("Produtos");
        for(Produtos p:dados.produtos){
            oos.writeObject(p);
        }
        oos.writeUTF("Faturas");
        for(Faturas f:dados.faturas){
            oos.writeObject(f);
        }

    }
}

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gabriel
 */

// Notas->getListaProdutos().
//for(int i = 0; i <  notas.size(); i++)
//{
//    for(int j =0; j < notas.get(i).getListaProdutos().size(); j++)
//    {
//        Produto produto = notas.get(i).getListaProdutos().get(j);
//        produto.getPrecoDeCompra() * produto.getQuantEstoque();
//    }
//}
public class ControleNota {

    private ArrayList<Nota> notas;

    public ControleNota() {
        this.notas = new ArrayList<>();
        try {
            this.lerNotas(); // le arquivo e preenche array de notas
            //System.console().writer().println("arquivo notas.ser lido");
        } catch (Exception exc) {
            //System.console().writer().println("erro ao ler arquivo");
        }
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }

    public void adicionarNota(Nota novaNota) {
        notas.add(novaNota);
        try {
            this.gravarNotas();
            //System.console().writer().println("Nota adicionada em notas");
        } catch (Exception exc) {
            //System.console().writer().println("erro ao ler notas");			
        }
    }

    public void gravarNotas() throws Exception {
        try {
            FileOutputStream arquivo = new FileOutputStream("notas.ser");
            ObjectOutputStream out = new ObjectOutputStream(arquivo);
            out.writeObject(notas);
            out.flush();
            out.close();
            arquivo.close();
        } catch (Exception exc) {
            throw new Exception("Arquivo notas não encontrado!");
        }
    }

    public void lerNotas() throws Exception {
        try {
            FileInputStream arquivo = new FileInputStream("notas.ser");
            ObjectInputStream in = new ObjectInputStream(arquivo);
            notas = (ArrayList<Nota>) in.readObject();
            in.close();
        } catch (Exception ex) {
            notas = new ArrayList<>();
        }
    }

    public void consultaFatProduto(int codProduto) {
        String saida = "Produto não encontrado.\n";
        boolean temProduto = false;
        double fatTotal = 0;
        for (int i = 0; i < notas.size(); i++) {
            for (int j = 0; j < notas.get(i).getListaProduto().size(); j++) {
                if (notas.get(i).getListaProduto().get(j).getCodigo() == codProduto) {
                    temProduto = true;
                    fatTotal += notas.get(i).getListaProduto().get(j).getValorDeVenda();
                }

            }
        }
        if (temProduto == false) {
            JOptionPane.showMessageDialog(null, saida);
        } else {
            saida = "Código do produto pesquisado: " + codProduto + "\n" + "Faturamento do produto: R$ " + fatTotal + "\n";
            JOptionPane.showMessageDialog(null, saida);
        }
    }

    public void consultaFatPeriodo(Date dataInicial, Date dataFinal) {
        String saida = "";
        double fatTotal = 0;
        for (int i = 0; i < notas.size(); i++) {
            if (notas.get(i).getDataEmissao().after(dataInicial) && notas.get(i).getDataEmissao().before(dataFinal)) {
                fatTotal += notas.get(i).getValorTotalCompra();
            }
        }
        saida = "O Faturamento no período de " + dataInicial.toString() + " a " + dataFinal.toString() + " Foi de:\n";
        saida += "R$ " + fatTotal;
        JOptionPane.showMessageDialog(null, saida);
    }

    //LUCRO LÍQUIDO = FATURAMENTO TOTAL – CUSTO TOTAL
    public void consultaLucroLiquido(Date dataInicial, Date dataFinal) {
        String saida = "";
        double fatTotal = 0;
        double custoTotal = 0;
        
        //achando o faturamento total
        for (int i = 0; i < notas.size(); i++) {
            if (notas.get(i).getDataEmissao().after(dataInicial) && notas.get(i).getDataEmissao().before(dataFinal)) {
                fatTotal += notas.get(i).getValorTotalCompra();
            }
        }
        
        //achando o custo total ????????
        for (int i = 0; i < notas.size(); i++) {
            if (notas.get(i).getDataEmissao().after(dataInicial) && notas.get(i).getDataEmissao().before(dataFinal)) {
                for (int j = 0; j < notas.get(i).getListaProduto().size(); i++) {
                    //custoTotal += notas.get(i).getListaProduto().get(i).
                }
            }
        }
    }

    /*
    Coisa a se pensar: de que forma iremos consultar os 10 produtos mais vendidos?
        Pela alteração quantidade no estoque talvez? Aparentemente viavel, mas tem muitos contras;
        Fazendo FORs que percorrem todas as notas e todos os produtos somando os totais de cada um? Complicado e com problemas pra identificar
            no final quais totais são de quais produtos
        Alguma outra sugestão? Escrevam aqui que eu procuro fazer...
     */
    public void topDezMaisVendidos() {
        for (int i = 0; i < notas.size(); i++) {
            for (int j = 0; j < notas.get(i).getListaProduto().size(); j++) {

            }
        }
    }

    // //Emitir nota é o responsável pelas compras dos clientes
    // Coisas para lembrar:
    //     Ao emitir uma nota deve-se alterar a quantEstoque do produto de acordo com a compra;
    //     Uma nota pode ter no máximo 10 tipos de produtos diferentes;
    // public String emitirNota() {
    //     return null;
    // }
}

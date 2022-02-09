package input_output;

import org.jopendocument.dom.OOUtils;//serve solo per aprire il file
import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

//deve essere in un package perchè senno non posso usarla in mostraCatalogo() ha costruttore
// privato così non può essere instanziata
public class EditorODS {

    private EditorODS(){}

    public static void scriviNuovoODS(ArrayList<String[]> infoVini, String[] titoli, String path) throws Error {
        try {
            final String[][] infoArray = new String[infoVini.size()][titoli.length];

            for (int i=0;i<infoArray.length;i++) {
                infoArray[i] = Arrays.copyOf(infoVini.get(i), infoVini.get(i).length);//faccio un array con una locazione in piu

            }

            TableModel modello = new DefaultTableModel(infoArray, titoli);

            // Save the data to an ODS file and open it.
            final File file = new File(path);
            SpreadSheet.createEmpty(modello).saveAs(file);

            OOUtils.open(file);//apre calc perché viene aperto il file che sta a questo percorso che è leggibile tramite calc
        } catch (IOException e){
            e.printStackTrace();
            throw new Error("\nScrittura del file fallita!");
        }

    }


    public static ArrayList<String[]> leggiODS(File file) throws Error {
        SpreadSheet spreadsheet;

        ArrayList<String[]> listaInfoVini = new ArrayList<String[]>();

        try {
            spreadsheet = SpreadSheet.createFromFile(file);
            //getSheet(int) prende in ingresso l'indice del numero della pagina nel file
            //Qua si prendono indice righe e colonne della tabella
            int numCol = spreadsheet.getSheet(0).getColumnCount();
            int numRighe = spreadsheet.getSheet(0).getRowCount();

            MutableCell cell = null;

            String[] infoVino = new String[numCol];

            //nella prima riga ci sono i titoli, quindi si parte dalla seconda
            for(int indRighe = 1; indRighe < numRighe; indRighe++) {
                for(int indColonne = 0; indColonne < numCol; indColonne++) {
                    cell = spreadsheet.getSheet(0).getCellAt(indColonne, indRighe);

                    String strToInsert = cell.getTextValue();//ottiene il testo

                    infoVino[indColonne] = strToInsert;

                    //System.out.print("\n"+cell.getValue()+ " ");
                }
                listaInfoVini.add(infoVino);
                infoVino = new String[numCol];
                //System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();//TODO cosa fa?
            throw new Error("Lettura file fallita");
        }
        return listaInfoVini;
    }

    public static Integer getRigaVinoTabella(String nome, File file) throws FileNotFoundException {
        SpreadSheet spreadsheet;

        try {
            spreadsheet = SpreadSheet.createFromFile(file);

            int numRighe = spreadsheet.getSheet(0).getRowCount();
            MutableCell cell = null;

            for(int indRighe = 0; indRighe < numRighe; indRighe++) {
                cell = spreadsheet.getSheet(0).getCellAt(1, indRighe);

                String strToInsert = cell.getTextValue();//ottiene il testo
                //System.out.println("\n"+strToInsert);
                if (strToInsert.equals(nome)){
                    return indRighe-1;  //-1 per mettere in parallelo l'arrayList con lo Spreadsheet
                }

            }

        } catch (IOException ioe) {
            ioe.printStackTrace();//TODO cosa fa?
            throw new FileNotFoundException("Lettura file fallita");
        }

        return null;
    }
}

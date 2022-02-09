package vino;

import input_output.EditorODS;

import javax.management.InvalidAttributeValueException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//ha solo oggetti immutabili
public final class SchedaTecnica {
    private final String provenienzaMosto;
    private final String annata;
    private final String qualifica;
    private final String bio;
    private final String vitigni;
    private final String prezzoL;

    //NOTA: dato che è static, è una Nested Class
     static class SchedaTecnicaBuilder {
        private final int numeroVino;
        private final ArrayList<String[]> listaInfoVini;

        SchedaTecnicaBuilder(String nomeVino) throws FileNotFoundException {
            File file;
            int numForn = 1;
            Integer found;
           do {
               file = new File("files/Fornitura" + numForn + ".ods");
               found = EditorODS.getRigaVinoTabella(nomeVino, file);
               numForn++;
           } while (found == null);

            this.numeroVino = found.intValue();
            this.listaInfoVini = EditorODS.leggiODS(file);
        }

        String buildProvenienza() {
            return listaInfoVini.get(numeroVino)[2];
        }

        String buildAnnata() {
            return listaInfoVini.get(numeroVino)[3];
        }

        String buildQualifica() {
            return listaInfoVini.get(numeroVino)[4];
        }

        String buildBio() {
            return listaInfoVini.get(numeroVino)[5];
        }

        String buildVitigni() {
            return listaInfoVini.get(numeroVino)[6];
        }

        String buildPrezzoL() {
            return listaInfoVini.get(numeroVino)[7];
        }

        SchedaTecnica build(){
            return new SchedaTecnica(this);
        }

    }

    private SchedaTecnica (SchedaTecnicaBuilder stb) {
        this.provenienzaMosto = stb.buildProvenienza();
        this.annata = stb.buildAnnata();
        this.qualifica = stb.buildQualifica();
        this.bio = stb.buildBio();
        this.vitigni = stb.buildVitigni();
        this.prezzoL = stb.buildPrezzoL();
    }

    public void printSchedaTecnica() {
        System.out.println("Provenienza Mosto: "+provenienzaMosto);
        System.out.println("Vitigni: "+vitigni);
        System.out.println("Annata: "+annata);
        System.out.println("Qualifica: "+qualifica);
        System.out.println("Bio: "+bio);
        System.out.println("Prezzo al litro: "+prezzoL);
    }


    public String getProvenienzaMosto() {
        return provenienzaMosto;
    }

    public String getAnnata() {
        return annata;
    }

    public String getQualifica() {
        return qualifica;
    }

    public String getBio() {
        return bio;
    }

    public String getVitigni() {
        return vitigni;
    }

    public String getPrezzoL() {
        return prezzoL;
    }
}

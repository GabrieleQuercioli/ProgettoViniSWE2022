package businesslogic;

import compravendita.*;
import java.util.Scanner;

public class ClientePageController {
    private Cliente cliente;

    //costruttore default

    public void setCliente (String nome, String indirizzo, String CAP, boolean isAzienda) {
        if (!isAzienda) {
            System.out.println("\nInserisci il tuo cognome:");
            Scanner inputCognome = new Scanner(System.in);
            String cognome = inputCognome.nextLine();
            this.cliente = new Privato(nome,cognome,indirizzo,CAP);
        }
        else {
            System.out.println("\nInserisci il codice della tua partita IVA:");
            Scanner inputIVA = new Scanner(System.in);
            String pIVA = inputIVA.nextLine();
            this.cliente = new Azienda(nome, indirizzo, CAP, pIVA);
        }
    }

    public void compraVino() {
        Cantina cantina = Cantina.getCantina(0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSCEGLI UN VINO DAL CATALOGO");
        System.out.println("Scegli un nome");
        String nomeVino = scanner.nextLine();
        System.out.println("\nIndica la quantità (intera) di litri da acquistare");
        int quantita = scanner.nextInt();

        try {
            float[] acquisto = cantina.vendi(nomeVino, quantita);

            if (acquisto == null)
                throw new NullPointerException(cliente.getNome() + " non può acquistare un vino non presente nel catalogo!");

            if (cliente instanceof Azienda)
                acquisto[0] = cantina.calcolaSconto(acquisto[0], acquisto[1]);

            System.out.println("\n"+cliente.getNome()+" ha speso "+acquisto[0]+" €");
        } catch (IllegalArgumentException e) {  //se non ho inserito un nome corretto, l'eccezione viene lanciata da vendi()
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("\nPremere un tasto per continuare");
        sc.nextLine();
    }

    public void visionaCatalogo() throws EmptyCatalogoException {
        Cantina cantina = Cantina.getCantina(0);
        cantina.mostraCatalogo();
    }

    public String getNomeCliente() {
        return cliente.getNome();
    }
}

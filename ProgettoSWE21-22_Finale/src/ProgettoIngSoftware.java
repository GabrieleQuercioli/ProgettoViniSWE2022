import businesslogic.ClientePageController;
import businesslogic.OperatorePageController;
import compravendita.*;

import java.util.Scanner;

public class ProgettoIngSoftware {
    public static void main(String[] args) {

        int numRifornimenti = 0;
        OperatorePageController opc = new OperatorePageController(4);//è la facciata a instanziare la facciata

        opc.accettaRifornimento("files/Fornitura1.ods");
        numRifornimenti++;

        opc.printCantina();

        System.out.println("\nCosa vuoi fare?");
        System.out.println("1) Nuovo rifornimento");
        System.out.println("2) Gestisci tutte le stanze della cantina");
        Scanner firstInput = new Scanner(System.in);
        int pRisp;
        do {
             pRisp = firstInput.nextInt();
            switch (pRisp) {
                case 1:
                    opc.accettaRifornimento("files/Fornitura2.ods");
                    numRifornimenti++;
                    System.out.println("\nAdesso verranno gestite le stanze della cantina");
                    opc.gestisciCantina();
                    opc.printCantina();
                    //va alla compravendita
                    break;
                case 2:
                    opc.gestisciCantina();
                    opc.printCantina();
                    //va alla compravendita
                    break;
                default:
                    System.out.println("\nInserisci una risposta tra quelle elencate!");
                    break;
            }
        } while (pRisp != 1 && pRisp != 2 && pRisp != 3);


        System.out.println("\nCOMPRAVENDITA");

        ClientePageController cpc = new ClientePageController();
        System.out.println("\nInserisci il tuo nome:");
        Scanner inputNome = new Scanner(System.in);
        String nome = inputNome.nextLine();
        System.out.println("\nInserisci il tuo indirizzo:");
        Scanner inputIndirizzo = new Scanner(System.in);
        String indirizzo = inputIndirizzo.nextLine();
        System.out.println("\nInserisci il codice CAP della tua città:");
        Scanner inputCAP = new Scanner(System.in);
        String CAP = inputCAP.nextLine();

        String risposta;
        do {
            System.out.println("\nSei un privato o un'azienda?");
            Scanner input1 = new Scanner(System.in);
            risposta = input1.nextLine();
            if (risposta.equals("privato"))
                cpc.setCliente(nome, indirizzo, CAP, false);
            else if (risposta.equals("azienda"))
                cpc.setCliente(nome, indirizzo, CAP, true);
            else
                System.out.println("\nRispondi 'privato' o 'azienda'");
        } while (!risposta.equals("privato") && !risposta.equals("azienda"));

        boolean emptyCatalogo = false;

        boolean acqNullo = true;
        System.out.println("\nVuoi acquistare un vino?");
        System.out.println("1) Si");
        System.out.println("2) No");
        firstInput = new Scanner(System.in);
        int primaRisp;

        do {
            primaRisp = firstInput.nextInt();
            switch (primaRisp) {
                case 1:
                    while (acqNullo && !emptyCatalogo) {
                        try {
                            cpc.visionaCatalogo();
                            cpc.compraVino();
                            System.out.println("\nVuoi acquistare altro vino?");
                            System.out.println("1) Si");
                            System.out.println("2) No");
                            Scanner secInput = new Scanner(System.in);
                            int secRisp = secInput.nextInt();
                            switch (secRisp) {
                                case 1:
                                    System.out.println("\nNuovo acquisto per " + cpc.getNomeCliente());
                                    break;
                                case 2:
                                    acqNullo = false;
                                    break;
                                default:
                                    System.out.println("\nInserisci una risposta tra quelle elencate!");
                                    break;
                            }
                        } catch (NullPointerException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Ritenta l'acquisto");
                        } catch (EmptyCatalogoException e) {
                            System.out.println("\nLa compravendita è finita: non ci sono più vini nella cantina");
                            emptyCatalogo = true;
                            acqNullo = false;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Il cliente " + cpc.getNomeCliente() + " non acquistato nulla");
                    break;
                default:
                    System.out.println("\nInserisci una risposta tra quelle elencate!");
                    break;
            }
        } while (primaRisp != 1 && primaRisp != 2);


        if (numRifornimenti < 2) {
            System.out.println("\nÈ disponibile una nuova fornitura: vuoi accettarla?");
            System.out.println("1) Sì");
            System.out.println("2) No");
            Scanner thirdInput = new Scanner(System.in);
            int tRisp;
            do {
                tRisp = thirdInput.nextInt();
                switch (tRisp) {
                    case 1:
                        opc.accettaRifornimento("files/Fornitura2.ods");
                        opc.printCantina();
                        break;
                    case 2:
                        System.out.println("\nNon hai effettuato il rifornimento");
                        break;
                    default:
                        System.out.println("\nInserisci una risposta tra quelle elencate!");
                        break;
                }
            } while (tRisp != 1 && tRisp != 2);
        }
    }
}

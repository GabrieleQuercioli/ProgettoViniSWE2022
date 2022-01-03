package vino;

public class VinoRosato extends Vino {

    public enum TipoRosato{Vulcano, Marques, ValdelleCorti, Voria};
    private final TipoRosato denominazione;

    public VinoRosato(String name, SchedaTecnica st) {
        super(name, st);
        denominazione = TipoRosato.valueOf(name);
    }

    @Override
    public boolean varia() {
        anidrideSolforosa = 150 + (float)(Math.random()*50);    //tra 150 e 200
        pH = (float)(3.1 + Math.random()*0.4);                  //tra 3.1 e 3.5
        zuccheriRiduttori = 190 + (float)(Math.random()*50);    //tra 190 e 240
        gradoAlcolico =  (float)(9 + Math.random()*8);          //tra 9 e 17
        ossigeno = (float)(0.3 + Math.random() * 0.5);          //tra 0.3 e 0.8

        this.safePrintVino();

        setChanged();

        if (anidrideSolforosa > 190) {
            notifyObservers(new Float(anidrideSolforosa));     //il notify alla fine chiama clearChanged
            return true;
        }
        else if (pH < 3.2 || pH > 3.4) {
            notifyObservers(new Float(pH));
            return true;
        }
        else if (zuccheriRiduttori > 230){
            notifyObservers(new Float(zuccheriRiduttori));
            return true;
        }
        else if (gradoAlcolico < 10 || gradoAlcolico > 16){
            notifyObservers(new Float(gradoAlcolico));
            return true;
        }
        else if (ossigeno >= 0.5){
            notifyObservers(new Float(ossigeno));
            return true;
        }
        else{
            System.out.println("\nIl Vino " + denominazione + " non ha richiesto alcun intervento");
            return false;
        }
    }
}

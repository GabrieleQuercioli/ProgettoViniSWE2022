package vino;

public class VinoRosso extends  Vino {

    public VinoRosso(String name, boolean bio) {
        super(name, bio);
    }

    @Override
    public boolean varia() {
        //TODO implementa il metodo
        anidrideSolforosa = 140 + (float)(Math.random()*50);    //tra 140 e 190
        pH = 2 + (float)(Math.random()*4);                      //tra 2 e 6
        zuccheriRiduttori = 190 + (float)(Math.random()*50);    //tra 190 e 240
        gradoAlcolico =  (float)(7.5 + Math.random()*13);       //tra 7.5 e 20.5
        ossigeno = (float)(0.3 + Math.random() * 0.5);          //tra 0.3 e 0.8

        setChanged();

        if (anidrideSolforosa > 160) {
            notifyObservers(new Float(anidrideSolforosa));     //il notify alla fine chiama clearChanged
            return true;
        }
        else if (pH < 3.3 || pH > 3.5) {
            notifyObservers(new Float(pH));
            return true;
        }
        else if (zuccheriRiduttori > 210){
            notifyObservers(new Float(zuccheriRiduttori));
            return true;
        }
        else if (gradoAlcolico < 10 || gradoAlcolico > 16){
            notifyObservers(new Float(gradoAlcolico));
            return true;
        }
        else if (ossigeno > 0.5){
            notifyObservers(new Float(ossigeno));
            return true;
        }
        else return false;
    }
}

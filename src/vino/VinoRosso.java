package vino;

public class VinoRosso extends  Vino {

    public VinoRosso(String name, boolean bio) {
        super(name, bio);
    }

    @Override
    public boolean varia() {
        //TODO implementa il metodo
        anidrideSolforosa = 120 + (float)(Math.random()*50);    //tra 120 e 170
        pH = (float)(3.2 + Math.random()*0.5);                  //tra 3.2 e 3.7
        zuccheriRiduttori = 170 + (float)(Math.random()*50);    //tra 170 e 220
        gradoAlcolico =  (float)(8 + Math.random()*10);         //tra 8 e 18
        ossigeno = (float)(0.3 + Math.random() * 0.5);           //tra 0.3 e 0.8

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

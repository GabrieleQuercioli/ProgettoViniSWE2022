package vino;

public class VinoRosso extends  Vino {

    public VinoRosso(String name, boolean bio) {
        super(name, bio);
    }

    @Override
    public boolean varia() {
        //TODO implementa il metodo
        anidrideSolforosa = 140 + Math.random()*50;    //tra 140 e 190
        pH = 2 + Math.random()*4;                      //tra 2 e 6
        zuccheriRiduttori = 190 + Math.random()*50;    //tra 190 e 240
        gradoAlcolico =  7.5 + Math.random()*13;        //tra 7.5 e 20.5
        ossigeno = 0.3 + Math.random() * 0.5;          //tra 0.3 e 0.8

        setChanged();

        if (anidrideSolforosa > 160) {
            notifyObservers(new Double(anidrideSolforosa));
            return true;
        }
        else if (pH < 3.3 || pH > 3.5) {
            notifyObservers(new Double(pH));
            return true;
        }
        else if (zuccheriRiduttori > 210){
            notifyObservers(new Double(zuccheriRiduttori));
            return true;
        }
        else if (gradoAlcolico < 10 || gradoAlcolico > 16){
            notifyObservers(new Double(gradoAlcolico));
            return true;
        }
        else if (ossigeno > 0.5){
            notifyObservers(new Double(ossigeno));
            return true;
        }
        else return false;
    }
}

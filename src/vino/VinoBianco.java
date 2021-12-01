package vino;

public class VinoBianco extends Vino {

    public VinoBianco(String name, boolean bio) {
        super(name, bio);
    }

    @Override
    public boolean varia() {
        anidrideSolforosa = 190 + Math.random()*50;    //tra 190 e 240
        pH = 2 + Math.random()*4;                      //tra 2 e 6
        zuccheriRiduttori = 240 + Math.random()*50;    //tra 230 e 290
        gradoAlcolico =  7.5 + Math.random()*9;        //tra 7.5 e 16.5
        ossigeno = 0.3 + Math.random() * 0.5;          //tra 0.3 e 0.8

        setChanged();

        if (anidrideSolforosa > 210) {
            notifyObservers(new Double(anidrideSolforosa));
            return true;
        }
        else if (pH < 3 || pH > 3.3) {
            notifyObservers(new Double(pH));
            return true;
        }
        else if (zuccheriRiduttori > 260){
            notifyObservers(new Double(zuccheriRiduttori));
            return true;
        }
        else if (gradoAlcolico < 10 || gradoAlcolico > 14){
            notifyObservers(new Double(gradoAlcolico));
            return true;
        }
        else if (ossigeno > 0.5){
            notifyObservers(new Double(ossigeno));
            return true;
        }
        else return false;                              //in caso tutti i parametri siano ancora accettabili
    }
}
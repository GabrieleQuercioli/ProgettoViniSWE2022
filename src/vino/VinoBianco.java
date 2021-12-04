package vino;

public class VinoBianco extends Vino {

    public VinoBianco(String name, boolean bio) {
        super(name, bio);
    }

    @Override
    public boolean varia() {
        anidrideSolforosa = 170 + (float)(Math.random()*50);    //tra 170 e 220
        pH = (float)(2.9 + Math.random()*0.5);                  //tra 2.9 e 3.4
        zuccheriRiduttori = 220 + (float)(Math.random()*50);    //tra 220 e 270
        gradoAlcolico =  (float)(9 + Math.random()*6);          //tra 9 e 15 */
        ossigeno = (float)(0.3 + Math.random() * 0.5);          //tra 0.3 e 0.8

        setChanged();

        if (anidrideSolforosa > 210) {
            notifyObservers(new Float(anidrideSolforosa));
            return true;
        }
        else if (pH < 3 || pH > 3.3) {
            notifyObservers(new Float(pH));
            return true;
        }
        else if (zuccheriRiduttori > 260){
            notifyObservers(new Float(zuccheriRiduttori));
            return true;
        }
        else if (gradoAlcolico < 10 || gradoAlcolico > 14){
            notifyObservers(new Float(gradoAlcolico));
            return true;
        }
        else if (ossigeno > 0.5){
            notifyObservers(new Float(ossigeno));
            return true;
        }
        else return false;                              //in caso tutti i parametri siano ancora accettabili
    }
}

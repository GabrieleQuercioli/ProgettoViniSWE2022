package vino;
import java.util.Observable;

public abstract class Vino extends Observable {
    protected String name;
    protected boolean bio;
    protected double anidrideSolforosa; // in mg/L
    protected int pH;
    protected double zuccheriRiduttori; // in mg/L
    protected double gradoAlcolico;  //in %
    protected double ossigeno; //in mg/L

    public Vino(String name, boolean bio)       //mettere parametri accettabili che non inneschino osservazione
    {                                           //TODO mettere solo super() nel costruttore delle derivate
        this.name = name;
        this.bio = bio;
        anidrideSolforosa = Math.random();  //TODO scegli il range per un corretto funzionamento delle strategie
        pH = (int) (Math.random());
        zuccheriRiduttori = Math.random();
        gradoAlcolico = Math.random();
        ossigeno = Math.random();
    }

    public void correggiVino(){     //non è astratto perchè è uguale per tutti e due i tipi di vino
    }

    public abstract void siSciupa();    //FIXME è davvero astratto? (le derivate lo implementano diversamente?)

}

package vino;
import java.util.Observable;

public abstract class Vino extends Observable {
    protected String name;
    protected boolean bio;
    protected float anidrideSolforosa; // in mg/L
    protected float pH;
    protected float zuccheriRiduttori; // in mg/L
    protected float gradoAlcolico;  //in %
    protected float ossigeno; //in mg/L

    public Vino(String name, boolean bio)       //mettere parametri accettabili che non inneschino osservazione
    {                                           //TODO mettere solo super() nel costruttore delle derivate
        this.name = name;
        this.bio = bio;
        anidrideSolforosa = 110 + (float)(Math.random()*50);  //tra 110 e 160 escluso
        pH =  3.3f;
        zuccheriRiduttori = 160 + (float)(Math.random()*50);  // tra 160 e 210
        gradoAlcolico = 10 + (float)(Math.random()*4);        // tra 10 e 14
        ossigeno = (float)(Math.random() * 0.5);              // tra 0 e 0.5
    }

    //non è astratto perchè è uguale per tutti e due i tipi di vino
    //final perchè non deve avere override
    public final void correggiVino(){
        ossigeno = (float)(Math.random() * 0.5);
    }

    public abstract boolean varia();    //FIXME è davvero astratto? (le derivate lo implementano diversamente?)
    //questo metodo dovrà occuparsi di inviare alla notify il parametro ossigeno se solo quello è fuori soglia,
    //e se fossero fuori soglia ossigeno e un altro parametro invierà quest'ultimo

    public String getName() {
        return name;
    }

    public float getOssigeno() {return ossigeno;}

    public void printVino() {
        System.out.println("Nome: " + name + "\n");
        System.out.println("Anidride Solforosa: " + anidrideSolforosa + " mg/L");
        System.out.println("pH: " + pH);
        System.out.println("Zuccheri Riduttori: " + zuccheriRiduttori + " mg/L");
        System.out.println("Grado Alcolico: " + gradoAlcolico + " %");
        System.out.println("Ossigeno: " + ossigeno + " mg/L\n");
    }
}

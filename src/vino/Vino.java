package vino;
import javax.naming.InvalidNameException;
import javax.naming.directory.InvalidAttributeValueException;
import java.security.InvalidParameterException;
import java.util.Observable;

public abstract class Vino extends Observable {
    protected String name;
    protected boolean bio;
    protected float anidrideSolforosa; // in mg/L
    protected float pH;
    protected float zuccheriRiduttori; // in mg/L
    protected float gradoAlcolico;  //in %
    protected float ossigeno; //in mg/L

    public Vino(String name, boolean bio)
    {
        this.name = name;
        this.bio = bio;
        anidrideSolforosa = 110 + (float)(Math.random()*50);  //tra 110 e 160 escluso
        pH =  3.3f;
        zuccheriRiduttori = 160 + (float)(Math.random()*50);  // tra 160 e 210
        gradoAlcolico = 10 + (float)(Math.random()*4);        // tra 10 e 14
        ossigeno = (float)(Math.random() * 0.5);              // tra 0 e 0.5
    }

    //non è astratto perchè è uguale per tutti e due i tipi di vino
    public void correggiVino(){
        System.out.println("\nLa quantità di ossigeno nel Vino " + name + " verrà corretta, poichè il suo valore è: " + ossigeno + "\n");
        ossigeno = (float)(Math.random() * 0.5);
    }

    public abstract boolean varia();    //è astratto (ognuna delle derivate lo implementa diversamente)
    //questo metodo dovrà occuparsi di inviare alla notify il parametro ossigeno se solo quello è fuori soglia,
    //e se fossero fuori soglia ossigeno e un altro parametro invierà quest'ultimo

    public String getName() {
        return name;
    }

    public float getOssigeno() {return ossigeno;}

    public void printVino() throws InvalidParameterException {      //FIXME questo tipo di eccezione non va bene
        if (this.getName() == null || this.getName().isEmpty()){
            throw new InvalidParameterException("\nNon è possibile stampare un vino senza nome");
        }
        System.out.println("\nNome: " + name + "\n");
        System.out.println("Anidride Solforosa: " + anidrideSolforosa + " mg/L");
        System.out.println("pH: " + pH);
        System.out.println("Zuccheri Riduttori: " + zuccheriRiduttori + " mg/L");
        System.out.println("Grado Alcolico: " + gradoAlcolico + " %");
        System.out.println("Ossigeno: " + ossigeno + " mg/L");
    }
}

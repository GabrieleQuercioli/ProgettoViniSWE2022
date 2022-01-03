package vino;

public class SchedaTecnica {
    private final boolean bio;
    private final String provenienzaMosto;
    private final int annata;
    private final String vitigno;
    private final String qualifica;

    public SchedaTecnica(boolean bio, String provenienzaMosto, int annata, String vitigno, String qualifica) {
        this.bio = bio;
        this.provenienzaMosto = provenienzaMosto;
        this.annata = annata;
        this.vitigno = vitigno;
        this.qualifica = qualifica;
    }


    void printSchedaTecnica() {
        System.out.println("Provenienza mosto: " + provenienzaMosto);
        System.out.println("Vitigno: " + vitigno);
        System.out.println("Annata: " + annata);
        System.out.println("Qualifica: " + qualifica);
        System.out.println("Bio: " + bio + "\n");
    }

}

public class Cellule {
    private int val;
    private boolean modi;

    public Cellule(int valeur, boolean modi) {
        this.val = valeur;
        this.modi = modi;
    }

    public int getValeur() {
        return val;
    }

    public void setValeur(int val) {
        this.val = val;
    }

    public boolean isModifiable() {
        return modi;
    }

   
}

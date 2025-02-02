public class Grille {
    Cellule[][] matrice;

    public Grille() {
        this.matrice = new Cellule[9][9];
        initial();
    }

    public void initial() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.matrice[i][j] = new Cellule(0, true);
            }
        }
    }

    public boolean estValide(int ligne, int colonne, int val) {
        for(int i = 0; i < 9; i++){
            if(matrice[ligne][i].getValeur() == val || matrice[i][colonne].getValeur() == val){
                return false;
            }
        }
        int ligneDebut = ligne - ligne % 3;
        int colonneDebut = colonne - colonne % 3;
        
        for(int i = ligneDebut; i < ligneDebut + 3; i++){
            for(int j = colonneDebut; j < colonneDebut + 3; j++){
                if(matrice[i][j].getValeur() == val){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean estRemplie(Cellule[][] matrice) {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(matrice[i][j].getValeur() == 0){
                    return false;
                }
            }
        }
        return true;
    }
    public Cellule[][] getMatrice() {
        return matrice;
    }

}

public class ResoudreSudoku {

    // Méthode principale de résolution
    public boolean resoudre(Cellule[][] matrice) {
        int ligne = -1, colonne = -1;
        boolean vide = true;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (matrice[i][j].getValeur() == 0) {
                    ligne = i;
                    colonne = j;
                    vide = false;
                    break;
                }
            }
            if (!vide) break;
        }

        if (vide) return true; // Sudoku résolu

        for (int val = 1; val <= 9; val++) {
            if (valide(ligne, colonne, val, matrice)) {
                matrice[ligne][colonne].setValeur(val);

                if (resoudre(matrice)) return true;

                matrice[ligne][colonne].setValeur(0); // Backtracking
            }
        }
        return false;
    }

    // Méthode surchargée pour accepter un objet Grille
    public boolean resoudre(Grille grille) {
        return resoudre(grille.getMatrice());
    }

    
}

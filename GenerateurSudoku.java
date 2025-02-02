public class GenerateurSudoku {

    public boolean resoudre(Grille g) {
        Cellule[][] matrice = g.getMatrice(); // Récupération de la matrice de la grille
        int ligne = -1, colonne = -1;
        boolean vide = true;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (matrice[i][j].getValeur() == 0) { // Vérifier la valeur avec getValeur()
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
            if (valide(ligne, colonne, val, g)) { // Vérification avec la méthode valide()
                matrice[ligne][colonne].setValeur(val); // Modifier la valeur

                if (resoudre(g)) return true; // Récursion

                matrice[ligne][colonne].setValeur(0); // Backtracking
            }
        }
        return false;
    }

    public boolean valide(int ligne, int colonne, int val, Grille g) {
        Cellule[][] matrice = g.getMatrice();

        // Vérifier la ligne
        for (int j = 0; j < 9; j++) {
            if (matrice[ligne][j].getValeur() == val) return false;
        }

        // Vérifier la colonne
        for (int i = 0; i < 9; i++) {
            if (matrice[i][colonne].getValeur() == val) return false;
        }

        // Vérifier le carré 3x3
        int debutLigne = (ligne / 3) * 3;
        int debutColonne = (colonne / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrice[debutLigne + i][debutColonne + j].getValeur() == val) return false;
            }
        }

        return true;
    }
}

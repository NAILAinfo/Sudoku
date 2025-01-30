public class Sudoku {
    int[][] grille = new int[9][9];

    public Sudoku() {
        this.grille = new int[9][9];
        initial();
        resoudre(grille);
    }

    public void initial() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.grille[i][j] = 0;
            }
        }
    }

    public boolean valide(int ligne, int colonne, int val) {
        for(int i = 0; i < 9; i++){
            if(grille[ligne][i] == val || grille[i][colonne] == val){
                return false;
            }
        }
        int ligneDebut = ligne - ligne % 3;
        int colonneDebut = colonne - colonne % 3;
        
        for(int i = ligneDebut; i < ligneDebut + 3; i++){
            for(int j = colonneDebut; j < colonneDebut + 3; j++){
                if(grille[i][j] == val){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean resoudre(int[][] matrice) {
        int ligne = -1, colonne = -1;
        boolean vide = true;
        
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(matrice[i][j] == 0){
                    ligne = i;
                    colonne = j;
                    vide = false;
                    break;
                }
            }
            if(!vide) break;
        }

        if(vide) return true;

        for(int val = 1; val <= 9; val++){
            if(valide(ligne, colonne, val)){
                grille[ligne][colonne] = val;
                if(resoudre(grille)) return true;
                grille[ligne][colonne] = 0;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(sudoku.grille[i][j] + " ");
            }
            System.out.println();
        }
    }
}

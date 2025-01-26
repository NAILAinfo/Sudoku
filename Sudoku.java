import java.util.Scanner;

public class Sudoku {
    
    public void initial(int[][] matrice){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                matrice[i][j] = 0;
            }
        }
    }

    public boolean valide(int[][] matrice, int ligne, int colonne, int val) {
        for(int i = 0; i < 9; i++){
            if(matrice[ligne][i] == val || matrice[i][colonne] == val){
                return false;
            }
        }
        int ligneDebut = ligne - ligne % 3;
        int colonneDebut = colonne - colonne % 3;
        
        for(int i = ligneDebut; i < ligneDebut + 3; i++){
            for(int j = colonneDebut; j < colonneDebut + 3; j++){
                if(matrice[i][j] == val){
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
            if(valide(matrice, ligne, colonne, val)){
                matrice[ligne][colonne] = val;
                if(resoudre(matrice)) return true;
                matrice[ligne][colonne] = 0;
            }
        }
        return false;
    }

    public void afficherGrille(int[][] matrice) {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        int[][] matrice = new int[9][9];
        sudoku.initial(matrice);
        if(sudoku.resoudre(matrice))
        {
            sudoku.afficherGrille(matrice);
                }
            
        else{
            System.out.println("Pas de solution");
        }

    }
    
}

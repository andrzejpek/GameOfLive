package com.company;



public class GameOfLive {
    // tworzymy plansze na komórki
    private int[][] vector2D;
    private int xSize;
    private int ySize;

    /**
     * konstruktor
     *
     * @param vector2D
     * @param xSize
     * @param ySize
     */
    public GameOfLive(int[][] vector2D, int xSize, int ySize) {
        this.vector2D = vector2D;
        this.xSize = xSize;
        this.ySize = ySize;
    }
    //następna generacja gry
    public void nextGeneretion() {
        int[][] nextGenerationVector2D = new int[xSize][ySize];
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                nextGenerationVector2D[x][y] = isAlive(x, y);
            }
        }
        vector2D = nextGenerationVector2D;
    }
    // zamianiamy liczby 0,1 na znaki i wyświetlamy
    public void display(){
        for (int[] arr : vector2D) {
            for(int cell : arr) {
                if(cell == 0){
                    System.out.print("   ");
                } else {
                    System.out.print(" X ");
                }
            }
            System.out.println();
        }
    }

    private int isAlive(int x, int y) {
        int aLiveCellCounter = 0;
        // sprwadzanie komorek dookola x,y
        for (int xTemp = -1; xTemp <= 1; xTemp++) {
            for (int yTemp = -1; yTemp <= 1; yTemp++) {
                // sprawdzamy czy nie wychodzimy poza zakres tablicy
                if (x + xTemp >= 0 && x + xTemp < xSize
                        && y + yTemp >= 0 && y + yTemp < ySize) {
                    // dodajemy 1 jesli zywa, 0 jesli martwa
                    aLiveCellCounter += vector2D[x + xTemp][y + yTemp];
                }
            }
        }
        // odejmujemy sprawdzana komorke, jesli zywa to odchodzi 1, jesli nie to wynik bez zmian
        aLiveCellCounter -= vector2D[x][y];
        // jeżeli sprawdzana komórka ma 3 żywych sąsiadów to żyje w następnej generacji
        if (aLiveCellCounter == 3) {
            return 1;
        }
        // jeżeli sprawdzana komórka ma dwóch żywych sąsiadów to jej stan się nie zmienia
        if (aLiveCellCounter == 2) {
            return vector2D[x][y];
        }
        // w pozostałych przypadkach komórka umiera
        return 0;
    }
}

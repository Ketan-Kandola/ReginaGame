import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

class Square{
    public boolean hasPlayer;
    public boolean hasMonster;
    public boolean hasTreasure;
    public char squareIcon; // -,P,M,T
    
    //Constructor
    public Square(boolean hasPlayer, boolean hasTreasure, boolean hasMonster){
        this.hasPlayer = hasPlayer;
        this.hasTreasure = hasTreasure;
        this.hasMonster = hasMonster;
    }
    
    public void updateChar(){
        if(hasPlayer == true){
            squareIcon = 'P';
        }
        else if(hasTreasure == true){
            squareIcon = 'T';
        }
        else if(hasMonster == true){
            squareIcon = 'M';
        }
        else{
            squareIcon = '-';
        }
    }
}

public class TreasureHuntGame{
    
    private Square[][] grid;
    private int playerRow;
    private int playerCol;
    private int treasureRow;
    private int treasureCol;
    private int monsterRow;
    private int monsterCol;
    private int gridSize;
    private double playerDistance;
    
    //contructor to initialise the grid size
    public TreasureHuntGame(int gridSize){
        this.gridSize = gridSize;
        this.grid = new Square[gridSize][gridSize];
        initialisePositions();
        initialiseGrid();
    }
    
    //place player and treasure at random location on grid
    public void initialisePositions(){
        // creates random location
        playerRow = (int) Math.floor(Math.random() * gridSize);
        playerCol = (int) Math.floor(Math.random() * gridSize);
        treasureRow = (int) Math.floor(Math.random() * gridSize);
        treasureCol = (int) Math.floor(Math.random() * gridSize);
        monsterRow = (int) Math.floor(Math.random() * gridSize);
        monsterCol = (int) Math.floor(Math.random() * gridSize);

        while (playerRow==treasureRow && playerCol==treasureCol) {
            treasureRow = (int) Math.floor(Math.random() * gridSize);
            treasureCol = (int) Math.floor(Math.random() * gridSize);
        }

        while ((playerRow==monsterRow && playerCol==monsterCol) || (treasureRow==monsterRow && treasureCol==monsterCol)) {
            monsterRow = (int) Math.floor(Math.random() * gridSize);
            monsterCol = (int) Math.floor(Math.random() * gridSize);
        }
        
    }
    
    //initialise empty grid
    public void initialiseGrid(){
        for(int i = 0; i < gridSize; i++){
           for(int j = 0; j < gridSize; j++){
               if(playerRow == i && playerCol == j)
               {
                grid[i][j] = new Square(true, false, false);
               }
               else if(treasureRow == i && treasureCol == j){
                   grid[i][j] = new Square(false, true, false);
               }
               else if(monsterRow == i && monsterCol == j){
                   grid[i][j] = new Square(false, false, true);
               }
               else{
                   grid[i][j] = new Square(false, false, false);
               }
           }
        }
    }
    
    public void displayGrid(){
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
               grid[i][j].updateChar();
            }
        }
        
        
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
               System.out.print(grid[i][j].squareIcon + " ");
            }
            System.out.println("");
        }
    }

    public void displayDistance(){
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if(grid[i][j].hasPlayer==true){
                    playerRow = i;
                    playerCol = j;
                }
                else if(grid[i][j].hasTreasure==true){
                    treasureRow = i;
                    treasureCol = j;
                } 
            }
        }

        playerDistance = Math.round(Math.sqrt(Math.pow((treasureRow-playerRow),2) + Math.pow((treasureCol-playerCol),2)));
        System.out.println("You are " + playerDistance + " units away from the treasure!");
    }

    public void movePlayer(String direction){
        if (direction.equals("UP") && playerRow < gridSize){
            playerRow--;
        } else if (direction.equals("DOWN") && playerRow > 0){
            playerRow++;            
        }  else if (direction.equals("LEFT") && playerCol > 0){
            playerCol--;            
        }  else if (direction.equals("RIGHT") && playerCol < gridSize){
            playerCol++;            
        } 

    }
        
    public static void main(String[] args){
        int gridSize = 10;
        TreasureHuntGame game = new TreasureHuntGame(gridSize);
        game.displayGrid();
        game.displayDistance();
        
    }
    
}
package me.xplore.snakegame.snake;

public class Snake {
    // Variable Declaration
    private long posX, posY;
    boolean isDead = false;
    int size = 3;

    // Snake Functions
    public void MoveUp(){
        posY += 5;
    }
    public void MoveDown(){
        posY -= 5;
    }
    public void MoveLeft(){
        posX -= 5;
    }
    public void MoveRight(){
        posX += 5;
    }
    public void Reset(){
        size = 3;
        isDead = false;
    }

}

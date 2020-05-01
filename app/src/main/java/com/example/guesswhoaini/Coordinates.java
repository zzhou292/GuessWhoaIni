package com.example.guesswhoaini;
/**
 * Represents the coordinates of the fingerpaths (the lines which are drawn on the screen).
 */
public class Coordinates {
    private float x;
    private float y;
    private int action;
    private int color;

    public Coordinates(){
        /* Empty for Firebase to be able to deserialize coordinates */
    }

    public Coordinates(float x, float y, int action,int color) {
        this.x = x;
        this.y = y;
        this.action = action;
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    public int getAction() {
        return action;
    }

    public int getColor(){return color;}
}
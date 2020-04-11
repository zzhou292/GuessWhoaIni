package com.example.guesswhoaini;

import android.graphics.Path;

class FingerPath {

    public int color;
    public int strokeWidth;
    public Path path;
    public int type;
    public float x;
    public float y;
    public float mx;
    public float my;

    public FingerPath(){}

    public FingerPath(int color, int strokeWidth, Path path, float x, float y, float mx, float my) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.path = path;
        this.type = 1;
        this.x = x;
        this.y = y;
        this.mx = mx;
        this.my = my;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getMx() {
        return mx;
    }

    public void setMx(float mx) {
        this.mx = mx;
    }

    public float getMy() {
        return my;
    }

    public void setMy(float my) {
        this.my = my;
    }

}
package com.example.guesswhoaini;

import android.graphics.Path;

class FingerPath {

    public int color;
    public int strokeWidth;
    public Path path;
    public int type;

    public FingerPath(int color, int strokeWidth, Path path) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.path = path;
        this.type = 1;
    }

    public int getType() {
        return type;
    }
}
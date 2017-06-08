package com.a75f.floor.floorapplication;

public class Item {
    private String name;
    float xPos;
    float yPos;
    float width;
    float height;


    Item(String name, float xPos, float yPos, float width, float height) {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}

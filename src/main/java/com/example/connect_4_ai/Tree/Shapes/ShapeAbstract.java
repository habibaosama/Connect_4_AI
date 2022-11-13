package com.example.connect_4_ai.Tree.Shapes;

public abstract class ShapeAbstract implements Shape {
    protected double x_axis;
    protected double y_axis;
    protected int parentId;
    protected int chosenCol;
    protected int value = 0;


    public void setCoordinates(double x, double y) {
        this.x_axis = x;
        this.y_axis = y;
    }

    public int getParentId() {
        return parentId;
    }

    public void setChosenCol(int col) {
        this.chosenCol = col;
    }

    public int getChosenCol() {
        return this.chosenCol;
    }

    public double getY_axis() {
        return y_axis;
    }

    public double getX_axis() {
        return x_axis;
    }


}
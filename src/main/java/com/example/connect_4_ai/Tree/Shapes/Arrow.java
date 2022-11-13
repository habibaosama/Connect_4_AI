package com.example.connect_4_ai.Tree.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Arrow implements Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final int chosenCol;
    private final int col;

    public Arrow(int col, ShapeAbstract shape1, ShapeAbstract shape2, int chosenCol) {
        this.col = col + 1;
        this.x1 = shape1.getX_axis();
        this.y1 = shape1.getY_axis() + 40;
        this.x2 = shape2.getX_axis();
        this.y2 = shape2.getY_axis();
        this.chosenCol = chosenCol;

    }

    @Override
    public void draw(GraphicsContext ctx) {
        ctx.beginPath();
        ctx.moveTo(x1, y1);
        ctx.lineTo(x2, y2 - 5);
        double midX = (x1 + x2) / 2;
        double midY = (y1 + y2) / 2;
        ctx.setFill(Color.BLACK.darker().darker().darker());
        if (this.col == this.chosenCol + 1) {
            ctx.setFill(Color.RED.darker().darker().darker());
        }
        ctx.fillText("" + this.col, midX - 8, midY);
        ctx.setStroke(Color.BLACK);
        if (this.col == this.chosenCol + 1) {
            ctx.setStroke(Color.RED);
            ctx.setLineWidth(2);
        }
        //triangle for the arrow
        ctx.stroke();
        ctx.setFill(Color.BLACK);
        ctx.beginPath();
        ctx.moveTo(x2 - 5, y2 - 5);//point 1
        ctx.lineTo(x2 + 5, y2 - 5);//point 2
        ctx.lineTo(x2, y2);//point 3
        ctx.closePath();// go back to point 1
        ctx.setStroke(Color.BLACK);
        ctx.fill();
        ctx.stroke();
    }

}
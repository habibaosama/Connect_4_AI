package com.example.connect_4_ai.Tree.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class trapezoidDown extends ShapeAbstract {
    public trapezoidDown(int parentId, int value) {
        this.parentId = parentId;
        this.value = value;
    }


    @Override
    public void draw(GraphicsContext ctx) {
        ctx.setStroke(Color.YELLOW);
        ctx.setFill(Color.YELLOW.darker());
        ctx.beginPath();
        ctx.moveTo(this.x_axis - 22, this.y_axis);   // point 1
        ctx.lineTo(this.x_axis + 22, this.y_axis);  // point 2
        ctx.lineTo(this.x_axis + 15, this.y_axis + 30); // point 3
        ctx.lineTo(this.x_axis - 15, this.y_axis + 30);  // point 4
        ctx.closePath();      // go back to point 1
        ctx.fill();
        ctx.setFill(Color.BLACK.darker().darker().darker());
        ctx.fillText("" + this.value, this.x_axis - 13, this.y_axis + 25);
    }


}

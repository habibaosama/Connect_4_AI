package com.example.connect_4_ai.Tree.Shapes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TrapezoidUp extends ShapeAbstract {
    public TrapezoidUp(int parentId, int value) {
        this.parentId = parentId;
        this.value = value;
    }


    @Override
    public void draw(GraphicsContext ctx) {
        ctx.setFill(Color.RED.darker());
        ctx.setStroke(Color.RED.darker().darker());
        ctx.beginPath();
        ctx.moveTo(this.x_axis-15, this.y_axis);   // point 1
        ctx.lineTo(this.x_axis+15, this.y_axis);  // point 2
        ctx.lineTo(this.x_axis+22, this.y_axis+30); // point 3
        ctx.lineTo(this.x_axis-22, this.y_axis+30);  // point 4
        ctx.closePath();      // go back to point 1
        ctx.fill();
        ctx.setFill(Color.BLACK.darker().darker().darker());
        ctx.fillText( ""+ this.value, this.x_axis - 10, this.y_axis + 25);
    }





}

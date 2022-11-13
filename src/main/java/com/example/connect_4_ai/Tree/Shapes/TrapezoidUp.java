package com.example.connect_4_ai.Tree.Shapes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;


public class TrapezoidUp extends ShapeAbstract {
    public TrapezoidUp(int parentId, int value, boolean visited) {
        this.parentId = parentId;
        this.value = value;
        this.visited = visited;
    }


    public void draw(Group root) {
        Polygon trapezoid = new Polygon();
        Text text;
        trapezoid.setStroke(Color.RED.darker().darker());
        trapezoid.setFill(Color.RED.darker());
        trapezoid.getPoints().addAll(x_axis - 15, this.y_axis,
                this.x_axis + 15, this.y_axis,
                this.x_axis + 22, this.y_axis + 30,
                this.x_axis - 22, this.y_axis + 30);
        if (this.visited) {
            text = new Text(this.x_axis - 10, this.y_axis + 25, this.value + "");
        } else {
            text = new Text(this.x_axis - 13, this.y_axis + 25, "X");
        }
        root.getChildren().addAll(trapezoid, text);
    }


}

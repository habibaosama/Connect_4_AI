package com.example.connect_4_ai.Tree.Shapes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;


public class TrapezoidUp extends ShapeAbstract {
    public TrapezoidUp(int parentId, int value) {
        this.parentId = parentId;
        this.value = value;
    }


    public void draw(Group root) {
        Polygon trapezoid = new Polygon();
        trapezoid.setStroke(Color.RED.darker().darker());
        trapezoid.setFill(Color.RED.darker());
        trapezoid.getPoints().addAll(x_axis - 15, this.y_axis,
                this.x_axis + 15, this.y_axis,
                this.x_axis + 22, this.y_axis + 30,
                this.x_axis - 22, this.y_axis + 30);
        Text text = new Text(this.x_axis - 10, this.y_axis + 25, this.value + "");
        root.getChildren().addAll(trapezoid, text);
    }


}

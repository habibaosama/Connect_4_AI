package com.example.connect_4_ai.Tree.Shapes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

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

    public void draw(Group root) {
        Line line = new Line(x1, y1, x2, y2 - 5);
        line.setFill(Color.BLACK.darker().darker().darker());
        double midX = (x1 + x2) / 2;
        double midY = (y1 + y2) / 2;
        line.setStroke(Color.BLACK);
        if (this.col == this.chosenCol + 1) {
            //line.setFill(Color.RED);
            line.setStroke(Color.RED);
            line.setStrokeWidth(2);
        }

        Text text = new Text(midX - 8, midY, "" + this.col);

        //for the triangle
        Polygon triangle = new Polygon();
        triangle.getPoints().setAll(
                x2 - 5, y2 - 5,
                x2 + 5, y2 - 5,
                x2, y2
        );
        triangle.setFill(Color.BLACK);
        triangle.setStroke(Color.BLACK);
        root.getChildren().addAll(line, text, triangle);
        //triangle for the arrow


    }

}

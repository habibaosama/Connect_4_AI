package com.example.connect_4_ai.Tree;

import com.example.connect_4_ai.utilities.Node;
import com.example.connect_4_ai.Tree.Shapes.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;

public class ShowTreeController implements Initializable {
    @FXML
    public Canvas canvas = new Canvas();

    public LinkedList<Node> parent = new LinkedList<>();
    public LinkedList<Integer> pID = new LinkedList<>();
    public LinkedList<Node> children = new LinkedList<>();
    public LinkedList<Integer> cID = new LinkedList<>();
    private HashMap<Integer, Shape> shapes;
    private double width = 100;
    private double middleCanvas;
    private int TotalIDs = 0;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.middleCanvas = this.canvas.getWidth() / 2;
    }

    public void showTree(Node rootState) {
        setTree(rootState);//set nodes
        draw();//draw the tree
    }

    public void setTree(Node evalState) {
        shapes = new HashMap<>();
        ShapeAbstract shape = new TrapezoidUp(0, evalState.score);
        shape.setCoordinates(middleCanvas, 0);
        shape.setChosenCol(evalState.col);
        System.out.println(evalState.col);
        shapes.put(TotalIDs, shape);
        parent.add(evalState);
        pID.add(TotalIDs);
        Node state;
        int parentId;
        char player = 'r';
        while (!parent.isEmpty()) {

            if (player == 'r') {
                player = 'y';
            } else {
                player = 'r';
            }

            while (!parent.isEmpty()) {
                state = parent.pop();
                parentId = pID.pop();

                for (Node child : state.children) {
                    TotalIDs++;
                    children.add(child);
                    if (player == 'y') {//minimization
                        shape = new trapezoidDown(parentId, child.score);
                        shape.setChosenCol(child.getChosenCol());
                    } else {//maximization
                        shape = new TrapezoidUp(parentId, child.score);
                        shape.setChosenCol(child.getChosenCol());
                    }
                    cID.add(TotalIDs);
                    shapes.put(TotalIDs, shape);
                }

            }

            constructShapes(children, cID);//set coordinates
            parent = children;
            pID = cID;
            children = new LinkedList<>();
            cID = new LinkedList<>();
        }

    }

    public void constructShapes(LinkedList<Node> stateList, LinkedList<Integer> IDList) {

        int shapeWidth = 40;
        double levelWidth = (IDList.size() * shapeWidth) + (IDList.size() - 1) * this.width;
        double x = this.middleCanvas - (levelWidth / 2);
        double inc = shapeWidth + this.width;
        this.width = this.width / 5;

        Iterator<Node> itr1 = stateList.iterator();
        Iterator<Integer> itr2 = IDList.iterator();

        while (itr1.hasNext()) {

            Node state = itr1.next();
            ShapeAbstract shape = (ShapeAbstract) shapes.get(itr2.next());
            ShapeAbstract parentShape = (ShapeAbstract) shapes.get(shape.getParentId());

            int levelDifference = 200;
            shape.setCoordinates(x, parentShape.getY_axis() + levelDifference);
            Shape arrow = new Arrow(state.col, parentShape, shape,parentShape.getChosenCol());

            TotalIDs++;
            shapes.put(TotalIDs, arrow);
            x += inc;
        }
    }


    public void draw() {
        System.out.println("draw" + shapes.size());
        GraphicsContext ctx = canvas.getGraphicsContext2D();
        ctx.setFill(Color.TRANSPARENT);
        ctx.clearRect(0, 0, canvas.getWidth() - 1, canvas.getHeight() - 1);
        for (Shape shape : shapes.values()) {
            shape.draw(ctx);
        }
    }
}

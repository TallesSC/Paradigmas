package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.LinkedList;

public class Vertice {
    public Circle circ;
    public LinkedList<Aresta> connections;

    public Vertice(double x, double y) {
        this.circ = new Circle(x, y, 16, Color.DARKTURQUOISE);
        this.circ.setStroke(Color.BLACK);
        this.circ.setStrokeWidth(2);
        this.connections = new LinkedList<Aresta>();
    }

    public LinkedList<Aresta> getConnections() {
        return connections;
    }

    public void addConection(Aresta vizinho){
        this.connections.add(vizinho);
    }

    public Circle getCircle() { return circ; }

    public double getRadius(){
        return this.circ.getRadius();
    }

    public double getCircX(){
        return this.circ.getCenterX();
    }

    public double getCircY(){
        return this.circ.getCenterY();
    }

}


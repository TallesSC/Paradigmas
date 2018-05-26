package sample;

import javafx.scene.shape.Circle;
import java.util.LinkedList;

public class Vertice {
    public Circle circ;
    public LinkedList<Aresta> connections;

    public Vertice(Circle c) {
        this.circ = c;
        this.connections = new LinkedList<Aresta>();
    }

    public void addConection(Aresta vizinho){
        this.connections.add(vizinho);
    }

    public Circle getC() {
        return circ;
    }
}


package sample;

import javafx.scene.shape.Circle;
import java.util.LinkedList;

public class Grafo {
    public LinkedList<Vertice> vertices;
    public LinkedList<Aresta> arestas;

    public Grafo() {
        this.vertices = new LinkedList<Vertice>();
        this.arestas = new LinkedList<Aresta>();
    }

    public void addVertice(Circle c){
        Vertice v = new Vertice(c);
        vertices.add(v);
    }

    public void addAresta(Vertice v1, Vertice v2) {
        Aresta a = new Aresta(v1, v2);
        arestas.add(a);
        v1.addConection(a);
        v2.addConection(a);
    }

    public int nVertices(){
        return vertices.size();
    }

    public int nArestas(){
        return arestas.size();
    }
}

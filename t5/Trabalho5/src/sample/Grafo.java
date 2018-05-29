package sample;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.lang.Math;
import java.awt.geom.Line2D;

public class Grafo {
    public LinkedList<Vertice> vertices;
    public LinkedList<Aresta> arestas;
    public int nVertices;
    public int nArestas;

    public Grafo() {
        this.vertices = new LinkedList<Vertice>();
        this.arestas = new LinkedList<Aresta>();
        this.nVertices = 0;
        this.nArestas = 0;
    }

    // Adiciona vértice ao grafo
    public void addAresta(Vertice v1, Vertice v2, Line l) {
        Aresta a = new Aresta(v1, v2, l);
        this.arestas.add(a);
        v1.addConection(a);
        v2.addConection(a);
        this.nArestas++;
    }

    // Adiciona aresta ao grafo
    public void addVertice(Circle c){
        Vertice v = new Vertice(c);
        vertices.add(v);
        this.nVertices++;
    }

    // Verifica se coordenadas estão dentro de um círculo
    public boolean inCircle(double x, double y, Circle circ){
        double distance = Math.hypot(x-circ.getCenterX(), y-circ.getCenterY());
        if (distance <= circ.getRadius() + 4){
            return true;
        }else{
            return false;
        }
    }

    // Verifica se coordenada está dentro de algum vértice
    public boolean pontoValido(double x, double y){
        for (Vertice ref: this.vertices){
            if (inCircle(x, y, ref.getCircle())){
                return true;
            }
        }
        return false;
    }

    // Retorna vértice a partir de coordenadas x e y
    public Vertice buscaVertice(double x, double y){
        for (Vertice ref: this.vertices){
            if (inCircle(x, y, ref.getCircle())){
                return ref;
            }
        }
        return null;
    }

    public int nIntersections(){
        int cont = 0;
        for (Aresta ref1: this.arestas){
            for (Aresta ref2: this.arestas){
                if (ref1.getIni().equals(ref2.getIni()) || ref1.getIni().equals(ref2.getFim()) ||
                        ref1.getFim().equals(ref2.getIni()) || ref1.getFim().equals(ref2.getFim())){
                    continue;
                }
                if (Line2D.linesIntersect(ref1.getIni().getCircX(), ref1.getIni().getCircY(), ref1.getFim().getCircX(), ref1.getFim().getCircY(),
                        ref2.getIni().getCircX(), ref2.getIni().getCircY(), ref2.getFim().getCircX(), ref2.getFim().getCircY())){
                    cont++;
                }
            }
        }
        return cont/2;
    }

    public void resetN(){
        this.nVertices = 0;
        this.nArestas = 0;
    }

    public int getnVertices() {
        return nVertices;
    }

    public int getnArestas() {

        return nArestas;
    }

    public LinkedList<Vertice> getVertices() {
        return vertices;
    }

    public LinkedList<Aresta> getArestas() {
        return arestas;
    }
}

package sample;

import javafx.scene.shape.Line;

public class Aresta {
    public Vertice ini;
    public Vertice fim;
    public Line linha;

    public Aresta(Vertice begin, Vertice end) {
        this.ini = begin;
        this.fim = end;
        this.linha = new Line(begin.getC().getCenterX(), begin.getC().getCenterY(), end.getC().getCenterX(), end.getC().getCenterY());;
    }
}

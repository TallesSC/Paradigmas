package sample;

import javafx.scene.shape.Line;

public class Aresta {
    public Vertice ini;
    public Vertice fim;
    public Line linha;

    public Aresta(Vertice begin, Vertice end, Line l) {
        this.ini = begin;
        this.fim = end;
        this.linha = l;
    }

    public Vertice getIni() {
        return ini;
    }

    public Vertice getFim() {
        return fim;
    }

    public Line getLinha() {
        return linha;
    }
}

package sample;

import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class Aresta {
    public Vertice ini;
    public Vertice fim;
    public Line linha;

    public Aresta(Vertice begin, Vertice end, Line l) {
        this.ini = begin;
        this.fim = end;
        this.linha = l;
        this.linha.setStrokeWidth(8);
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

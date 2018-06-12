package sample;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import javax.swing.*;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;
import javafx.scene.layout.Pane;

public class Grafo {
    public LinkedList<Vertice> vertices;
    public LinkedList<Aresta> arestas;

    // Construtor
    public Grafo(Pane pane, int level) {
        this.vertices = new LinkedList<Vertice>();
        this.arestas = new LinkedList<Aresta>();
        generateGrafo(pane, level);
    }

    // Gera grafo planar aleatório
    public void generateGrafo(Pane pane, int level){
        pane.getChildren().clear();
        for (int i = 0; i < 6 + level; i++) {
            double randomX = randDouble(50.0, 750.0);
            double randomY = randDouble(25.0, 525.0);
            addVertice(randomX, randomY);
        }
        for (int j = 1; j<3 ; j++) {
            for (int i = 0; i+j < 6+level; i+=j) {
                Line l = new Line(vertices.get(i).getCircX(), vertices.get(i).getCircY(), vertices.get(i+j).getCircX(), vertices.get(i+j).getCircY());
                addAresta(vertices.get(i), vertices.get(i+j), l);
            }
        }
        Line l = new Line(vertices.getFirst().getCircX(), vertices.getFirst().getCircY(), vertices.getLast().getCircX(), vertices.getLast().getCircY());
        addAresta(vertices.getFirst(), vertices.getLast(), l);
        for (Aresta refA: this.arestas) {
            pane.getChildren().add(refA.getLinha());
        }
        for (Vertice refV: this.vertices){
            pane.getChildren().add(refV.getCircle());
        }
    }

    // Reseta grafo para avanaçar de fase
    public void reset(){
        this.vertices.clear();
        this.arestas.clear();
    }

    // Gera número aleatório em determinado intervalo
    public double randDouble(double min, double max) {
        Random rand = new Random();
        double result = min + rand.nextDouble() * (max - min);
        return result;
    }

    // Redistribui os vértices do grapo pela tela aleatoriamente, ajustando suas arestas de acordo
    public void shuffle(){
        for (Vertice v: this.vertices){
            double randomX = randDouble(50.0, 750.0);
            double randomY = randDouble(25.0, 525.0);
            for (Aresta a: v.getConnections()){
                if (v.getCircX() == a.getLinha().getStartX() && v.getCircY() == a.getLinha().getStartY()){
                    a.getLinha().setStartX(randomX);
                    a.getLinha().setStartY(randomY);
                }else if(v.getCircX() == a.getLinha().getEndX() && v.getCircY() == a.getLinha().getEndY()){
                    a.getLinha().setEndX(randomX);
                    a.getLinha().setEndY(randomY);
                }
            }
            v.getCircle().setCenterX(randomX);
            v.getCircle().setCenterY(randomY);
        }
    }

    // Adiciona vértice ao grafo
    public void addVertice(double x, double y){
        Vertice v = new Vertice(x, y);
        vertices.add(v);
    }

    // Adiciona aresta ao grafo
    public void addAresta(Vertice v1, Vertice v2, Line l) {
        Aresta a = new Aresta(v1, v2, l);
        this.arestas.add(a);
        v1.addConection(a);
        v2.addConection(a);
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

    // Retorna número de pontos de intersecção pelas arestas do grafo
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

    public LinkedList<Vertice> getVertices() {
        return vertices;
    }

    public LinkedList<Aresta> getArestas() {
        return arestas;
    }
}

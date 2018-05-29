package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Separator;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.SVGPath;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import java.awt.geom.Line2D;
import javafx.stage.Modality;

public class GraphsEditor extends Application {

    Grafo grafo = new Grafo();
    Line l;

    @Override
    public void start(Stage stage) {

        // Layout
        Image Icon = new Image("icon.png");
        BorderPane pane = new BorderPane();
        Pane canvas = new Pane();
        ToolBar tbTop = new ToolBar();
        ToolBar tbLeft = new ToolBar();
        HBox infoBox = new HBox();
        tbLeft.setOrientation(Orientation.VERTICAL);

        // Botões superiores //
        //
        Button btnNew = new Button("New");
        Button btnSave = new Button("Save");
        Button btnExit = new Button("Exit");

        // Botôes da esquerda //
        //
        // Vértices / Arestas
        ToggleGroup choice = new ToggleGroup();
        RadioButton rbV = new RadioButton("Vértice ");
        RadioButton rbA = new RadioButton("Aresta ");
        rbV.setToggleGroup(choice);
        rbA.setToggleGroup(choice);
        rbV.setSelected(true);
        Button btnDefault = new Button("Default Size");
        //
        // Color Picker
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.RED);
        //
        // Slider para grossura da linha
        Slider slider = new Slider();
        slider.setMin(10);
        slider.setMax(25);
        slider.setValue(15);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(3);

        // Adiciona itens em Toolbars
        tbTop.getItems().addAll(btnNew, btnSave, btnExit);
        tbLeft.getItems().addAll(rbV, rbA, colorPicker, slider,btnDefault);

        // Textos informativos inferiores
        Separator separator1 = new Separator();
        Separator separator2 = new Separator();
        Separator separator3 = new Separator();
        separator1.setOrientation(Orientation.VERTICAL);
        separator2.setOrientation(Orientation.VERTICAL);
        separator3.setOrientation(Orientation.VERTICAL);
        Text t1 = new Text(" Vértices: ");
        Text t2 = new Text(" Arestas: ");
        Text t3 = new Text(" Arestas sobrepostas: ");
        Text nV = new Text(Integer.toString(grafo.getnVertices())+"  ");
        Text nA = new Text(Integer.toString(grafo.getnArestas())+"  ");
        Text nI = new Text(Integer.toString(grafo.nIntersections())+"  ");
        t1.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        t2.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        t3.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        infoBox.getChildren().add(t1);
        infoBox.getChildren().add(nV);
        infoBox.getChildren().add(2, separator1);
        infoBox.getChildren().add(t2);
        infoBox.getChildren().add(nA);
        infoBox.getChildren().add(5, separator2);
        infoBox.getChildren().add(t3);
        infoBox.getChildren().add(nI);
        infoBox.getChildren().add(8, separator3);

        btnNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Stage NewGraph = new Stage();
                NewGraph.initModality(Modality.NONE);
                BorderPane bPane = new BorderPane();
                HBox hBoxA = new HBox();
                HBox hBoxB = new HBox(25);
                hBoxA.setPadding(new Insets(15, 0, 0, 0));
                Text t1 = new Text("Deseja limpar o grafo?");
                Button btnYes = new Button("Sim");
                btnYes.setFocusTraversable(false);
                btnYes.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        nV.setText("0  ");
                        nA.setText("0  ");
                        nI.setText("0  ");
                        start(stage);
                    }
                });
                Button btnNo = new Button("Não");
                btnNo.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        NewGraph.close();
                    }
                });
                hBoxA.getChildren().add(t1);
                hBoxB.getChildren().addAll(btnYes, btnNo);
                hBoxA.setAlignment(Pos.CENTER);
                hBoxB.setAlignment(Pos.CENTER);
                bPane.setTop(hBoxA);
                bPane.setCenter(hBoxB);
                Scene NewScene = new Scene(bPane, 200, 100);
                NewGraph.getIcons().add(Icon);
                NewGraph.setTitle("New");
                NewGraph.setScene(NewScene);
                NewGraph.show();
            }
        });

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //SVGPath svgPath = new SVGPath();
                //svgPath.setContent();
            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Stage Exit = new Stage();
                Exit.initModality(Modality.NONE);
                BorderPane bPane = new BorderPane();
                HBox hBox1 = new HBox();
                HBox hBox2 = new HBox(25);
                hBox1.setPadding(new Insets(15, 0, 0, 0));
                Text t1 = new Text("Deseja sair do programa?");
                Button btnYes = new Button("Sim");
                btnYes.setFocusTraversable(false);
                btnYes.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.exit(0);
                    }
                });
                Button btnNo = new Button("Não");
                btnNo.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        Exit.close();
                    }
                });
                hBox1.getChildren().add(t1);
                hBox2.getChildren().addAll(btnYes, btnNo);
                hBox1.setAlignment(Pos.CENTER);
                hBox2.setAlignment(Pos.CENTER);
                bPane.setTop(hBox1);
                bPane.setCenter(hBox2);
                Scene ExitScene = new Scene(bPane, 200, 100);
                Exit.getIcons().add(Icon);
                Exit.setTitle("Exit");
                Exit.setScene(ExitScene);
                Exit.show();
            }
        });

        btnDefault.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                slider.setValue(15);
            }
        });

        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (choice.getSelectedToggle() == rbV){
                    Circle c = new Circle(e.getX(), e.getY(), slider.getValue(), colorPicker.getValue());
                    c.setStroke(Color.BLACK);
                    c.setStrokeWidth(slider.getValue()/4);
                    grafo.addVertice(c);
                    canvas.getChildren().add(c);
                    nV.setText(Integer.toString(grafo.getnVertices())+"  ");
                }else{
                    if (grafo.pontoValido(e.getX(), e.getY())){
                        double xIni = grafo.buscaVertice(e.getX(), e.getY()).getCircX();
                        double yIni = grafo.buscaVertice(e.getX(), e.getY()).getCircY();
                        l = new Line(xIni, yIni, e.getX(), e.getY());
                        l.setStroke(colorPicker.getValue());
                        l.setStrokeWidth(slider.getValue());
                        l.setStrokeLineCap(StrokeLineCap.ROUND);
                        canvas.getChildren().add(l);
                    }
                }
            }
        });

        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (choice.getSelectedToggle() == rbA){
                    l.setEndX(e.getX());
                    l.setEndY(e.getY());
                }
            }
        });

        canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (choice.getSelectedToggle() == rbA){
                    if (grafo.pontoValido(e.getX(), e.getY())){
                        l.setEndX(grafo.buscaVertice(e.getX(), e.getY()).getCircX());
                        l.setEndY(grafo.buscaVertice(e.getX(), e.getY()).getCircY());
                        grafo.addAresta(grafo.buscaVertice(l.getStartX(),l.getStartY()), grafo.buscaVertice(l.getEndX(),l.getEndY()), l);
                        nA.setText(Integer.toString(grafo.getnArestas())+"  ");
                        nI.setText(Integer.toString(grafo.nIntersections())+"  ");
                    }else{
                        canvas.getChildren().remove(l);
                    }
                }
            }
        });

        pane.setCenter(canvas);
        pane.setTop(tbTop);
        pane.setLeft(tbLeft);
        pane.setBottom(infoBox);

        Scene scene = new Scene(pane, 800, 600);
        stage.getIcons().add(Icon);
        stage.setTitle("Editor de Grafos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// TAREFAS //
//
// - Botão Save em SVG
// - Bug ultima aresta colocada ao clicar no vazio
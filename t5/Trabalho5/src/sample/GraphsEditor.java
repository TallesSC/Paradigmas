package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.InnerShadow;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.image.Image;
import javafx.geometry.Orientation;
import javafx.stage.Modality;
import javafx.scene.canvas.Canvas;

public class GraphsEditor extends Application {

    Grafo grafo = new Grafo();

    @Override
    public void start(Stage stage) {

        // Layout
        Image Icon = new Image("icon.png");
        BorderPane pane = new BorderPane();
        ToolBar tbTop = new ToolBar();
        ToolBar tbLeft = new ToolBar();
        tbLeft.setOrientation(Orientation.VERTICAL);

        // Botões superiores //
        //
        Button btnInfo = new Button("Info");
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
        //
        // Color Picker
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.RED);
        //
        // Slider para grossura da linha
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(10);
        slider.setValue(4);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(5);

        tbTop.getItems().addAll(btnNew, btnSave, btnInfo, btnExit);
        tbLeft.getItems().addAll(rbV, rbA, colorPicker, slider);

        pane.setTop(tbTop);
        pane.setLeft(tbLeft);

        btnNew.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                start(stage);
            }
        });

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        btnInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Stage Info = new Stage();
                Info.initModality(Modality.NONE);
                VBox InfoVbox = new VBox(20);
                Text t1 = new Text("Vértices");
                Text t2 = new Text("Arestas");
                Text t3 = new Text("Arestas sobrepostas");
                t1.setUnderline(true);
                t2.setUnderline(true);
                t3.setUnderline(true);
                t1.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                t2.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                t3.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                InfoVbox.getChildren().add(t1);
                InfoVbox.getChildren().add(new Text(Integer.toString(grafo.nVertices())));
                InfoVbox.getChildren().add(t2);
                InfoVbox.getChildren().add(new Text(Integer.toString(grafo.nArestas())));
                InfoVbox.getChildren().add(t3);
                InfoVbox.getChildren().add(new Text("???"));
                InfoVbox.setAlignment(Pos.CENTER);
                Scene InfoScene = new Scene(InfoVbox, 250, 250);
                Info.getIcons().add(Icon);
                Info.setTitle("Informações");
                Info.setScene(InfoScene);
                Info.show();
            }
        });

        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (choice.getSelectedToggle() == rbV){
                    Circle c = new Circle(e.getX(), e.getY(), 20, colorPicker.getValue());
                    c.setStroke(Color.BLACK);
                    c.setStrokeWidth(5);
                    grafo.addVertice(c);
                    pane.getChildren().add(c);
                }else{
                    // DESENHA ARESTA
                }
            }
        });

        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (choice.getSelectedToggle() == rbV){

                }
            }
        });

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
////////////////////////////////////////////
//
//   Talles Siqueira Ceolin
//    tsceolin@inf.ufsm.br
//  Paradigmas da Programação
//         -UFSM-
////////////////////////////////////

package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.stage.Modality;
import java.util.Date;

public class Planarity extends Application {

    Grafo grafo;
    Date date1 = new Date();
    long time1 = date1.getTime();
    int score = 0;
    int level = 1;

    @Override
    public void start(Stage stage) {
        // --------------- LAYOUT --------------- //
        BorderPane borderPane = new BorderPane();
        Pane pane = new Pane();
        pane.getStyleClass().add("bg");
        grafo = new Grafo(pane, level);
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);


        // --------------- INFORMAÇÕES INFERIORES --------------- //
        HBox infoBox = new HBox();
        infoBox.getStyleClass().add("hbox");
        Separator separator1 = new Separator();
        Separator separator2 = new Separator();
        separator1.setOrientation(Orientation.VERTICAL);
        separator2.setOrientation(Orientation.VERTICAL);
        Text scoreText = new Text(" Score:");
        Text levelText = new Text("Level:");
        Text scoreText2 = new Text(Integer.toString(score));
        Text levelText2 = new Text(Integer.toString(level));
        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        infoBox.getChildren().addAll(scoreText, scoreText2, separator1, levelText, levelText2, separator2);


        // --------------- TOOLBAR --------------- //
        final Pane spacer = new Pane();
        Button checkBtn = new Button("Check ✔");
        checkBtn.getStyleClass().add("check-button");
        Button skipBtn = new Button("Skip \uD83E\uDC7A");
        Button shuffleBtn = new Button("Shuffle \uD83D\uDD04");
        Button exitBtn = new Button("Exit \u2B8D");
        exitBtn.getStyleClass().add("exit-button");
        HBox.setHgrow(spacer, Priority.SOMETIMES);
        final ToolBar toolBar = new ToolBar(checkBtn, skipBtn, shuffleBtn, spacer, exitBtn);
        checkBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (grafo.nIntersections() == 0){
                    Date date2 = new Date();
                    long time2 = date2.getTime();
                    long acumulado = 10000000/(time2 - time1) + (1000*level);
                    score += acumulado;
                    time1 = time2;
                    level++;
                    scoreText2.setText(Integer.toString(score));
                    levelText2.setText(Integer.toString(level));
                    grafo.reset();
                    grafo.generateGrafo(pane, level);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Parabéns!");
                    alert.setHeaderText("Parabéns, você passou de nível!");
                    alert.setContentText("Nível "+Integer.toString(level-1)+" \uD83E\uDC7A "+Integer.toString(level)+"\n+"+acumulado+" pontos!");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Erro!");
                    alert.setHeaderText("O grafo ainda possui arestas sobrepostas!");
                    alert.showAndWait();
                }
            }
        });
        skipBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                level++;
                levelText2.setText(Integer.toString(level));
                grafo.reset();
                grafo.generateGrafo(pane, level);
            }
        });
        shuffleBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                grafo.shuffle();
            }
        });
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
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
                Exit.getIcons().add(icon);
                Exit.setTitle("Exit");
                Exit.setScene(ExitScene);
                Exit.show();
            }
        });


        // --------------- MECÂNICA --------------- //
        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (grafo.pontoValido(e.getX(), e.getY())){
                    Vertice v = grafo.buscaVertice(e.getX(), e.getY());
                    for (Vertice ref: grafo.getVertices()){
                        ref.getCircle().setFill(Color.DARKTURQUOISE);
                    }
                    for (Aresta ref: v.getConnections()){
                        ref.getIni().getCircle().setFill(Color.GREEN);
                        ref.getFim().getCircle().setFill(Color.GREEN);
                    }
                    v.getCircle().setFill(Color.RED);
                    for (Aresta a: v.getConnections()){
                        if (v.getCircX() == a.getLinha().getStartX() && v.getCircY() == a.getLinha().getStartY()){
                            a.getLinha().setStartX(e.getX());
                            a.getLinha().setStartY(e.getY());
                        }else if(v.getCircX() == a.getLinha().getEndX() && v.getCircY() == a.getLinha().getEndY()){
                            a.getLinha().setEndX(e.getX());
                            a.getLinha().setEndY(e.getY());
                        }
                    }
                    v.getCircle().setCenterX(e.getX());
                    v.getCircle().setCenterY(e.getY());
                    pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent event) {
                            for (Vertice ref: grafo.getVertices()){
                                ref.getCircle().setFill(Color.DARKTURQUOISE);
                            }
                        }
                    });
                }
            }
        });


        // --------------- SCENE --------------- //
        borderPane.setCenter(pane);
        borderPane.setTop(toolBar);
        borderPane.setBottom(infoBox);
        Scene scene = new Scene(borderPane, 800, 600);
        scene.getStylesheets().add("Style.css");
        stage.setTitle("Planarity Puzzle");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
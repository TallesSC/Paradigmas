package sample;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.chart.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class Dashboard extends Application {
    private TableView<TableData> table = new TableView<TableData>();
    private ObservableList<TableData> data = FXCollections.observableArrayList();
    Controller control = new Controller();

    @Override
    public void start(Stage stage) throws Exception{
        // Tamanho da tela
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        // ---------- GRÁFICO BARRAS ---------- //
        BarChart barChart = criaBarChart();


        // ---------- GRÁFICO PIZZA ---------- //
        PieChart pieChart = criaPieChart();


        // ---------- MAPA ---------- //
        String imgPath = "https://maps.googleapis.com/maps/api/staticmap?size=800x400&maptype=roadmap&markers=icon:https://goo.gl/xpmPM1%7C"+"-22.910809"+","+"-43.270634";
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(imgPath);


        // ---------- TABLE ---------- //
        TableColumn<TableData,String> dataCol = new TableColumn<TableData,String>("DataHora");
        dataCol.setCellValueFactory(cellData -> cellData.getValue().dataHoraProperty());
        dataCol.setMinWidth(primaryScreenBounds.getWidth()/25);
        TableColumn<TableData,String> ordemCol = new TableColumn<TableData,String>("Ordem");
        ordemCol.setCellValueFactory(cellData -> cellData.getValue().ordemProperty());
        TableColumn<TableData,String> linhaCol = new TableColumn<TableData,String>("Linha");
        linhaCol.setCellValueFactory(cellData -> cellData.getValue().linhaProperty());
        TableColumn<TableData,String> latCol = new TableColumn<TableData,String>("Latitude");
        latCol.setCellValueFactory(cellData -> cellData.getValue().latitudeProperty());
        TableColumn<TableData,String> longCol = new TableColumn<TableData,String>("Longitude");
        longCol.setCellValueFactory(cellData -> cellData.getValue().longitudeProperty());
        TableColumn<TableData,Float> velCol = new TableColumn<TableData,Float>("Velocidade");
        velCol.setCellValueFactory(cellData -> cellData.getValue().velocidadeProperty().asObject());

        table.getColumns().addAll(dataCol, ordemCol, linhaCol, latCol, longCol, velCol);
        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setMinWidth(primaryScreenBounds.getWidth()/2.5);
        table.setPlaceholder(new Label("\uD83E\uDC47 Cliquei em 'Obter Dados' ou 'Abrir Arquivo' para gerar a tabela \uD83E\uDC47"));
        table.getSelectionModel().selectedItemProperty().addListener(x ->{
            String lat = table.getSelectionModel().getSelectedItem().getLatitude();
            String lgt = table.getSelectionModel().getSelectedItem().getLongitude();
            String newPath = "https://maps.googleapis.com/maps/api/staticmap?size=800x400&maptype=roadmap&markers=icon:https://goo.gl/xpmPM1%7C"+lat+","+lgt;
            webEngine.load(newPath);
        });


        // ---------- INFORMAÇÕES INFERIORES ---------- //
        Text info1 = new Text(" Última leitura de dados:  ");
        Text info2 = new Text(" Data-hora mais recente:  ");
        Text info3 = new Text(" Data-hora menos recente:  ");
        Text info4 = new Text(" Veículos na última leitura do servidor:  ");
        info1.getStyleClass().add("info-text");
        info2.getStyleClass().add("info-text");
        info3.getStyleClass().add("info-text");
        info4.getStyleClass().add("info-text");


        // ---------- BOTÕES ---------- //
        Button obtainBtn = new Button("Obter Dados");
        obtainBtn.setOnAction(x -> control.geraTabela(x, true,data, pieChart.getData(), barChart, info1, info2, info3, info4, stage));
        Button abrirBtn = new Button("Abrir Arquivo");
        abrirBtn.setOnAction(x -> control.geraTabela(x, false,data, pieChart.getData(), barChart, info1, info2, info3, info4, stage));


        // ---------- LAYOUT ---------- //
        // --- GRÁFICOS --- //
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        HBox chartBox = new HBox(pieChart, separator, barChart);
        // --- MAPA --- //
        Separator separator2 = new Separator();
        Separator separator3 = new Separator();
        Label label = new Label("\uD83E\uDC78 Selecione um ônibus na lista para ver sua localização");
        label.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
        VBox mapBox = new VBox(separator2, label, browser, separator3);
        mapBox.getStyleClass().add("inferior");
        mapBox.getChildren().addAll();
        BorderPane rightPane = new BorderPane();
        rightPane.setTop(chartBox);
        rightPane.setCenter(mapBox);
        // --- RODAPÉ --- //
        final Pane spacer = new Pane();
        final Pane spacer2 = new Pane();
        final Pane spacer3 = new Pane();
        HBox.setHgrow(spacer, Priority.SOMETIMES);
        HBox.setHgrow(spacer2, Priority.SOMETIMES);
        HBox.setHgrow(spacer3, Priority.SOMETIMES);
        Separator separatorA = new Separator();
        separatorA.setOrientation(Orientation.VERTICAL);
        Separator separatorB = new Separator();
        separatorB.setOrientation(Orientation.VERTICAL);
        Separator separatorC = new Separator();
        separatorC.setOrientation(Orientation.VERTICAL);
        HBox hBoxBot = new HBox(obtainBtn, abrirBtn, info1,spacer, separatorA,info2,spacer2, separatorB,info3, spacer3, separatorC, info4);
        hBoxBot.getStyleClass().add("inferior");
        // --- BORDER PANE --- //
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(table);
        borderPane.getStyleClass().add("table");
        borderPane.setRight(rightPane);
        borderPane.setBottom(hBoxBot);


        // ---------- SCENE ---------- //
        Scene scene = new Scene(borderPane, 1024, 768);
        stage.getIcons().add(new Image("icon.png"));
        stage.setMaximized(true);
        scene.getStylesheets().add("Style.css");
        stage.setTitle("Dashboard: Ônibus Rio de Janeiro");
        stage.setScene(scene);
        stage.show();
    }

    public PieChart criaPieChart(){
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Em Movimento", 50),
                        new PieChart.Data("Parados", 50));
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Veículos");
        pieChart.setMaxWidth(primaryScreenBounds.getWidth()/15);
        return pieChart;
    }

    public BarChart criaBarChart(){
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis,yAxis);
        barChart.setTitle("Veículos por linha");
        xAxis.setLabel("Linha");
        yAxis.setLabel("Veículos");
        return barChart;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

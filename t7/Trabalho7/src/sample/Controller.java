package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Controller{

    public void geraTabela(ActionEvent event, boolean web, ObservableList<TableData> data, ObservableList<PieChart.Data> pieChartData, BarChart<String,Number> barChart, Text lastRead, Text data1, Text data2, Text veiculos, Stage stage) {
        lastRead.setText(" Última leitura de dados:  " + new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
        HttpJSONService http = new HttpJSONService();
        Map json = null;
        if (web){
            try {
                json = http.sendGet("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes");
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Connection failed");
                alert.setContentText("Please check your Internet connection!");
                alert.showAndWait();
            }
        }else{
            File jsonFile = getFile(stage);
            try {
                FileReader fileReader = new FileReader(jsonFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                JSONParsing jsonParsing = new JSONParsing();
                json = jsonParsing.parseJSON(bufferedReader.readLine());
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possível ler o arquivo");
                alert.setContentText("Por favor, escolha um arquivo válido!");
                alert.showAndWait();
            }
        }
        if (json != null) {
            List lDados = (List) json.get("DATA");
            LinkedList<TableData> lTdata = new LinkedList<TableData>();
            lDados.forEach(o -> {
                TableData tData = new TableData(
                        ((List) o).get(0).toString(),
                        ((List) o).get(1).toString(),
                        ((List) o).get(2).toString(),
                        ((List) o).get(3).toString(),
                        ((List) o).get(4).toString(),
                        Float.parseFloat(((List) o).get(5).toString())
                );
                data.add(tData);
                lTdata.add(tData);
            });
            veiculos.setText(" Veículos na última leitura do servidor:  " + lTdata.size());
            data1.setText(" Data-hora mais recente:  " + lTdata.getLast().getDataHora());
            data2.setText(" Data-hora menos recente:  " + lTdata.getFirst().getDataHora());
            atualizaPieChart(lTdata.stream().filter(x -> x.getVelocidade() > 0).collect(Collectors.toList()).size(), pieChartData, lTdata.size());
            criaBarChart(lTdata, barChart);
        }
    }

    public void atualizaPieChart(long movimento, ObservableList<PieChart.Data> pieChartData, int size){
        pieChartData.get(0).setPieValue(movimento);
        pieChartData.get(1).setPieValue(size - movimento);
    }

    public void criaBarChart(LinkedList<TableData> data, BarChart<String,Number> barChart){

        Map<String, Long> linhaInfo = data.stream().filter(k -> k.getVelocidade() > 0 && !k.getLinha().isEmpty())
                        .collect(Collectors.groupingBy(TableData::getLinha, Collectors.counting()));

        linhaInfo.forEach((linha, num) ->{
            XYChart.Series series = new XYChart.Series();
            series.getData().add(new XYChart.Data(linha, num));
            barChart.getData().add(series);
        });
    }

    public File getFile(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir arquivo .json");
        File jsonFile = fileChooser.showOpenDialog(stage);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
        return jsonFile;
    }
}

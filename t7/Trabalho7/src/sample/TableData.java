package sample;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableData {
    private SimpleStringProperty DataHora;
    private SimpleStringProperty Ordem;
    private SimpleStringProperty Linha;
    private SimpleStringProperty Latitude;
    private SimpleStringProperty Longitude;
    private SimpleFloatProperty Velocidade;

    public TableData(String dataHora, String ordem, String linha, String latitude, String longitude, Float velocidade) {
        this.DataHora = new SimpleStringProperty(dataHora);
        this.Ordem = new SimpleStringProperty(ordem);
        this.Linha = new SimpleStringProperty(linha);
        this.Latitude = new SimpleStringProperty(latitude);
        this.Longitude = new SimpleStringProperty(longitude);
        this.Velocidade = new SimpleFloatProperty(velocidade);
    }

    public String getDataHora() {
        return DataHora.get();
    }

    public SimpleStringProperty dataHoraProperty() {
        return DataHora;
    }

    public void setDataHora(String dataHora) {
        this.DataHora.set(dataHora);
    }

    public String getOrdem() {
        return Ordem.get();
    }

    public SimpleStringProperty ordemProperty() {
        return Ordem;
    }

    public void setOrdem(String ordem) {
        this.Ordem.set(ordem);
    }

    public String getLinha() {
        return Linha.get();
    }

    public SimpleStringProperty linhaProperty() {
        return Linha;
    }

    public void setLinha(String linha) {
        this.Linha.set(linha);
    }

    public String getLatitude() {
        return Latitude.get();
    }

    public SimpleStringProperty latitudeProperty() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        this.Latitude.set(latitude);
    }

    public String getLongitude() {
        return Longitude.get();
    }

    public SimpleStringProperty longitudeProperty() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        this.Longitude.set(longitude);
    }

    public float getVelocidade() {
        return Velocidade.get();
    }

    public SimpleFloatProperty velocidadeProperty() {
        return Velocidade;
    }

    public void setVelocidade(float velocidade) {
        this.Velocidade.set(velocidade);
    }
}

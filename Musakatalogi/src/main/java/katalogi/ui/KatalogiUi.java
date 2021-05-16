package katalogi.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import katalogi.domain.Album;
import katalogi.domain.KatalogiService;


/**
 * JavaFX App
 */
public class KatalogiUi extends Application {

    private Scene startScene;
    private Scene newScene;
    private Scene queryScene;
    private Scene statScene;
    private KatalogiService service;
    private HashMap stats;
    private VBox vboxQuery;
    GridPane resultSet;
    
    @Override
    public void start(Stage stage) throws Exception {

        service = new KatalogiService();
        
        BorderPane bpStart   = new BorderPane();

        VBox vboxStart              = new VBox();
        HBox hboxStart              = new HBox(3);
        Button btUusi               = new Button("Uusi levy");
        Button btHaku               = new Button("Haku");
        Button btTilasto            = new Button("Tilasto");
        vboxStart.getChildren().add(new Label(""));
        vboxStart.getChildren().add(new Label("Levyjen hallinta"));
        vboxStart.getChildren().add(new Label(""));

        vboxQuery               = new VBox();
        HBox hboxQuery          = new HBox(3);
        Button btReturn2        = new Button("Alkuun");
        
        vboxQuery.getChildren().add(btReturn2);
        vboxQuery.getChildren().add(new Label(""));
        vboxQuery.getChildren().add(new Label("Hakukriteeri"));
        
        ChoiceBox queryParam    = new ChoiceBox();
        queryParam.getItems().addAll("","Esittaja", "Nimi", "Vuosi", "Tyylilaji", "Omistaja");
        TextField tfParam    = new TextField("");
        Button btQuery            = new Button("Hae");

        resultSet     = new GridPane();
        
        hboxQuery.getChildren().add(queryParam);
        hboxQuery.getChildren().add(tfParam);
        hboxQuery.getChildren().add(btQuery);
        
        vboxQuery.getChildren().add(hboxQuery);
        vboxQuery.getChildren().add(new Label(""));
        
        
        BorderPane bpStat       = new BorderPane();
        VBox vboxStat           = new VBox();
        Button btReturn3        = new Button("Alkuun");
        bpStat.setCenter(vboxStat);
        
        
        BorderPane bpNew = new BorderPane();
        VBox vbox = new VBox();
        bpNew.setCenter(vbox);
        Button btAdd = new Button("Lisää");
        Button btReturn = new Button("Alkuun");
        Label lbInfo = new Label("");
        lbInfo.setTextFill(Color.RED);
        
        VBox vbox2 = new VBox(2);
        bpNew.setBottom(vbox2);
        vbox2.getChildren().add(lbInfo);
        vbox.getChildren().add(btReturn);
        vbox.getChildren().add(new Label(""));
        vbox2.getChildren().add(btAdd);

        
        TextField tfArtist      = new TextField("");
        TextField tfName        = new TextField("");
        TextField tfYear        = new TextField("");
        TextField tfGenre       = new TextField("");
        TextField tfOwner       = new TextField("");
        
        vbox.getChildren().add(new Label("Esittäjä"));
        vbox.getChildren().add(tfArtist);
        vbox.getChildren().add(new Label("Nimi"));
        vbox.getChildren().add(tfName);
        vbox.getChildren().add(new Label("Vuosi"));
        vbox.getChildren().add(tfYear);
        vbox.getChildren().add(new Label("Tyylilaji"));
        vbox.getChildren().add(tfGenre);
        vbox.getChildren().add(new Label("Omistaja"));
        vbox.getChildren().add(tfOwner);
        
        btUusi.setOnAction(e->{
            stage.setScene(newScene);
        });
        
        btHaku.setOnAction(e->{
            stage.setScene(queryScene);
        });
        
        btTilasto.setOnAction(e->{
            stats = service.getStatistics();
            stage.setScene(statScene);

            GridPane statRows = new GridPane();

            statRows.add(new Label("Esittäjiä"), 0, 0);
            statRows.add(new Label("" + stats.get("Esittäjiä")), 1, 0);
            statRows.add(new Label("Nimiä"), 0, 1);
            statRows.add(new Label("" + stats.get("Nimiä")), 1, 1);
            statRows.add(new Label("Vuosia"), 0, 2);
            statRows.add(new Label("" + stats.get("Vuosia")), 1, 2);
            statRows.add(new Label("Tyylilajeja"), 0, 3);
            statRows.add(new Label("" + stats.get("Tyylilajeja")), 1, 3);
            statRows.add(new Label("Omistajia"), 0, 4);
            statRows.add(new Label("" + stats.get("Omistajia")), 1, 4);
            
            statRows.getColumnConstraints().add(new ColumnConstraints(100));
            
            vboxStat.getChildren().clear();
            vboxStat.getChildren().add(btReturn3);
            vboxStat.getChildren().add(new Label(""));
            vboxStat.getChildren().add(statRows);
            
        });
        
        btReturn.setOnAction(e->{
            stage.setScene(startScene);
        });
        
        btReturn2.setOnAction(e->{
            stage.setScene(startScene);
        });
        
        btReturn3.setOnAction(e->{
            stage.setScene(startScene);
        });
        
        btQuery.setOnAction(e->{
            
            List<Album> albums;
            
            if (queryParam.getValue() == null) {
                
                albums = service.get("", "");
            } else {

                albums = service.get((String)queryParam.getValue(), tfParam.getText());
            }
            
            drawResults(albums);
        });
        
        btAdd.setOnAction(e->{
            
            Album album = new Album();
            album.setArtist(tfArtist.getText());
            album.setName(tfName.getText());
            album.setYear(tfYear.getText());
            album.setGenre(tfGenre.getText());
            album.setOwner(tfOwner.getText());

            int ok = 0;

            try {
                ok = service.add(album);
            } catch (IOException ex) {
                Logger.getLogger(KatalogiUi.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (ok == 0) {
                lbInfo.setText("Levy lisätty.");
            } else if (ok == 1) {
                lbInfo.setText("Virhetilanne.");
            } else if (ok == 2) {
                lbInfo.setText("Levy jo olemassa.");
            } else if (ok == 3) {
                lbInfo.setText("Puutteellinen syöte.");
            } else if (ok == 4) {
                lbInfo.setText("Virheellinen syöte.");
            }
            
        });

        bpStart.setTop(vboxStart);
        
        bpStart.setCenter(hboxStart);
        hboxStart.getChildren().addAll(btUusi, btHaku, btTilasto);
        
        startScene = new Scene(bpStart, 240, 180);
        newScene = new Scene(bpNew, 240, 340);
        queryScene = new Scene(vboxQuery, 600, 340);
        statScene = new Scene(bpStat, 400, 340);

        stage.setScene(startScene);
        stage.show();
    }

    private void drawResults(List<Album> albums) {

        CheckBox cbRemove;

        if (vboxQuery.getChildren().contains(resultSet)) {
            int ind = vboxQuery.getChildren().indexOf(resultSet);
            resultSet = new GridPane();
            vboxQuery.getChildren().set(ind, resultSet);
        } else {
            vboxQuery.getChildren().add(resultSet);
        }

        resultSet.add(new Label("Esittäjä"), 0, 0);
        resultSet.getColumnConstraints().add(new ColumnConstraints(100));
        resultSet.add(new Label("Nimi"), 1, 0);
        resultSet.getColumnConstraints().add(new ColumnConstraints(100));
        resultSet.add(new Label("Vuosi"), 2, 0);
        resultSet.getColumnConstraints().add(new ColumnConstraints(100));
        resultSet.add(new Label("Tyylilaji"), 3, 0);
        resultSet.getColumnConstraints().add(new ColumnConstraints(100));
        resultSet.add(new Label("Omistaja"), 4, 0);
        resultSet.getColumnConstraints().add(new ColumnConstraints(100));
        
        for (int i=0; i<albums.size(); i++) {

            Album album = albums.get(i);
            
            cbRemove = new CheckBox("Poista");

            resultSet.add(new Label(album.getArtist()), 0, i+1);
            resultSet.add(new Label(album.getName()), 1, i+1);
            resultSet.add(new Label(album.getYear()), 2, i+1);
            resultSet.add(new Label(album.getGenre()), 3, i+1);
            resultSet.add(new Label(album.getOwner()), 4, i+1);
            resultSet.add(cbRemove, 5, i+1);

            String id = cbRemove.getId();
            boolean valittu = cbRemove.isSelected();
            
            cbRemove.setOnAction(e->{
                List<Album> albumList = service.remove(album);

                drawResults(albums);
            });
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
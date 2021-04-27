package katalogi.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
import katalogi.dao.KatalogiDao;
import katalogi.domain.Levy;


/**
 * JavaFX App
 */
public class KatalogiUi extends Application {

    private Scene alkuScene;
    private Scene uusiScene;
    private Scene hakuScene;
    private Scene tilastoScene;
    private KatalogiDao dao;
    private ArrayList<Levy> levyt;
    private HashMap tilasto;
    //private ArrayList tulosrivit;
    //private VBox tulosrivit;
    private VBox vboxHaku;
    //private Button btPoista;
    GridPane tulosrivit;
    
    @Override
    public void start(Stage stage) throws Exception {

        dao = new KatalogiDao("levyt.txt");
        
        BorderPane bpAlku   = new BorderPane();

        VBox vboxAlku       = new VBox();
        HBox hboxAlku       = new HBox(3);
        Button button1      = new Button("Uusi levy");
        Button button2      = new Button("Haku");
        Button button3      = new Button("Tilasto");
        vboxAlku.getChildren().add(new Label(""));
        vboxAlku.getChildren().add(new Label("Levyjen hallinta"));
        vboxAlku.getChildren().add(new Label(""));

        vboxHaku            = new VBox();
        HBox hboxHaku       = new HBox(3);
        Button btAlkuun2    = new Button("Alkuun");
        
        vboxHaku.getChildren().add(btAlkuun2);
        vboxHaku.getChildren().add(new Label(""));
        vboxHaku.getChildren().add(new Label("Hakukriteeri"));
        
        ChoiceBox hakukriteeri  = new ChoiceBox();
        hakukriteeri.getItems().addAll("","Esittaja", "Nimi", "Vuosi", "Tyylilaji", "Omistaja");
        TextField tfKriteeri    = new TextField("");
        Button btHae            = new Button("Hae");
        //GridPane tulosrivit           = new VBox();
        tulosrivit     = new GridPane();
        
        hboxHaku.getChildren().add(hakukriteeri);
        hboxHaku.getChildren().add(tfKriteeri);
        hboxHaku.getChildren().add(btHae);
        
        vboxHaku.getChildren().add(hboxHaku);
        vboxHaku.getChildren().add(new Label(""));
        
        
        BorderPane bpTilasto    = new BorderPane();
        VBox vboxTilasto        = new VBox();
        Button btAlkuun3        = new Button("Alkuun");
        bpTilasto.setCenter(vboxTilasto);
        
        
        BorderPane bpUusi = new BorderPane();
        VBox vbox = new VBox();
        bpUusi.setCenter(vbox);
        Button btLisays = new Button("Lisää");
        Button btAlkuun = new Button("Alkuun");
        Label ilmo = new Label("");
        ilmo.setTextFill(Color.RED);
        
        VBox vbox2 = new VBox(2);
        bpUusi.setBottom(vbox2);
        vbox2.getChildren().add(ilmo);
        vbox.getChildren().add(btAlkuun);
        vbox.getChildren().add(new Label(""));
        //HBox hboxHaku2 = new HBox();
        //hboxHaku2.getChildren().add(btLisays);
        //hboxHaku2.getChildren().add(new Label("       "));
        //hboxHaku2.getChildren().add(btAlkuun);
        //vbox2.getChildren().add(hboxHaku2);
        vbox2.getChildren().add(btLisays);

        
        TextField tfEsittaja    = new TextField(""); //TextField tfEsittaja    = new TextField("Esittaja1");
        TextField tfNimi        = new TextField(""); //TextField tfNimi        = new TextField("Nimi1");
        TextField tfVuosi       = new TextField(""); //TextField tfVuosi       = new TextField("2021");
        TextField tfTyylilaji   = new TextField(""); //TextField tfTyylilaji   = new TextField("Tyylilaji");
        TextField tfOmistaja    = new TextField(""); //TextField tfOmistaja    = new TextField("Omistaja");
        
        vbox.getChildren().add(new Label("Esittäjä"));
        vbox.getChildren().add(tfEsittaja);
        vbox.getChildren().add(new Label("Nimi"));
        vbox.getChildren().add(tfNimi);
        vbox.getChildren().add(new Label("Vuosi"));
        vbox.getChildren().add(tfVuosi);
        vbox.getChildren().add(new Label("Tyylilaji"));
        vbox.getChildren().add(tfTyylilaji);
        vbox.getChildren().add(new Label("Omistaja"));
        vbox.getChildren().add(tfOmistaja);
        
        button1.setOnAction(e->{
            stage.setScene(uusiScene);
        });
        
        button2.setOnAction(e->{
            stage.setScene(hakuScene);
        });
        
        button3.setOnAction(e->{
            tilasto = dao.tilastoi();
            stage.setScene(tilastoScene);
            
            GridPane tilastonRivit = new GridPane();
            
            tilastonRivit.add(new Label("Esittäjiä"), 0, 0);
            tilastonRivit.add(new Label("" + tilasto.get("Esittäjiä")), 1, 0);
            tilastonRivit.add(new Label("Nimiä"), 0, 1);
            tilastonRivit.add(new Label("" + tilasto.get("Nimiä")), 1, 1);
            tilastonRivit.add(new Label("Vuosia"), 0, 2);
            tilastonRivit.add(new Label("" + tilasto.get("Vuosia")), 1, 2);
            tilastonRivit.add(new Label("Tyylilajeja"), 0, 3);
            tilastonRivit.add(new Label("" + tilasto.get("Tyylilajeja")), 1, 3);
            tilastonRivit.add(new Label("Omistajia"), 0, 4);
            tilastonRivit.add(new Label("" + tilasto.get("Omistajia")), 1, 4);
            
            tilastonRivit.getColumnConstraints().add(new ColumnConstraints(100));
            
            
            vboxTilasto.getChildren().clear();
            vboxTilasto.getChildren().add(btAlkuun3);
            vboxTilasto.getChildren().add(new Label(""));
            vboxTilasto.getChildren().add(tilastonRivit);
            
            
            /*
            vboxTilasto.getChildren().clear();
            vboxTilasto.getChildren().add(btAlkuun3);
            vboxTilasto.getChildren().add(new Label(""));
            vboxTilasto.getChildren().add(new Label("Esittäjiä      " + tilasto.get("Esittäjiä")));
            vboxTilasto.getChildren().add(new Label("Nimiä          " + tilasto.get("Nimiä")));
            vboxTilasto.getChildren().add(new Label("Vuosia         " + tilasto.get("Vuosia")));
            vboxTilasto.getChildren().add(new Label("Tyylilajeja    " + tilasto.get("Tyylilajeja")));
            vboxTilasto.getChildren().add(new Label("Omistajia      " + tilasto.get("Omistajia")));
            */
            
        });
        
        btAlkuun.setOnAction(e->{
            stage.setScene(alkuScene);
        });
        
        btAlkuun2.setOnAction(e->{
            stage.setScene(alkuScene);
        });
        
        btAlkuun3.setOnAction(e->{
            stage.setScene(alkuScene);
        });
        
        /*
        cbPoista.setOnAction(e->{
            System.out.println(" cbPoista ");
        });
        */
        
        btHae.setOnAction(e->{
            
            if (hakukriteeri.getValue() == null) {
                
                levyt = dao.hae("", "");
            } else {

                levyt = dao.hae((String)hakukriteeri.getValue(), tfKriteeri.getText());
            }
            
            luoLevylista();
        });
        
        btLisays.setOnAction(e->{
            
            Levy levy = new Levy();
            levy.setEsittaja(tfEsittaja.getText());
            levy.setNimi(tfNimi.getText());
            levy.setVuosi(tfVuosi.getText());
            levy.setTyylilaji(tfTyylilaji.getText());
            levy.setOmistaja(tfOmistaja.getText());
            
            //boolean ok = dao.lisaa(levy);
            int ok = 0;

            try {
                ok = dao.lisaa(levy);
            } catch (IOException ex) {
                Logger.getLogger(KatalogiUi.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (ok == 0) {
                    //System.out.println(" dao.lisaa ok ");
                ilmo.setText("Levy lisätty.");
            } else if (ok == 1) {
                    //System.out.println(" dao.lisaa ei ok ");
                ilmo.setText("Virhetilanne.");
            } else if (ok == 2) {
                    //System.out.println(" dao.lisaa ei ok ");
                ilmo.setText("Levy jo olemassa.");
            } else if (ok == 3) {
                ilmo.setText("Puutteellinen syöte.");
            } else if (ok == 4) {
                ilmo.setText("Virheellinen syöte.");
            }
            
        });
        


        //bpAlku.setTop(new Label("Levyjen hallinta"));
        bpAlku.setTop(vboxAlku);
        
        bpAlku.setCenter(hboxAlku);
        hboxAlku.getChildren().addAll(button1, button2, button3);
        
        alkuScene = new Scene(bpAlku, 240, 180);
        uusiScene = new Scene(bpUusi, 240, 340);
        hakuScene = new Scene(vboxHaku, 400, 340);
        tilastoScene = new Scene(bpTilasto, 400, 340);

        stage.setScene(alkuScene);
        stage.show();
        
    }

    private void luoLevylista() {

        //GridPane tulosrivi;
        CheckBox cbPoista;
        
        if (vboxHaku.getChildren().contains(tulosrivit)) {
            
            int ind = vboxHaku.getChildren().indexOf(tulosrivit);
            
            tulosrivit = new GridPane();
            
            vboxHaku.getChildren().set(ind, tulosrivit);
            
        } else {
            
            vboxHaku.getChildren().add(tulosrivit);
            
        }
        
        
        for (int i=0; i<levyt.size(); i++) {

            Levy levy = (Levy)levyt.get(i);
            //tulosrivi   =   new HBox(5);
            //tulosrivi   =   new GridPane();
            
            //box.setPadding(new Insets(0,5,0,5));
            //btPoista = new Button("Poista");
            cbPoista = new CheckBox("Poista");
            
            tulosrivit.add(new Label(levy.getEsittaja()+"   "), 0, i);
            tulosrivit.add(new Label(levy.getNimi()+"   "), 1, i);
            tulosrivit.add(new Label(levy.getVuosi()+"   "), 2, i);
            tulosrivit.add(new Label(levy.getTyylilaji()+"   "), 3, i);
            tulosrivit.add(new Label(levy.getOmistaja()+"   "), 4, i);
            tulosrivit.add(cbPoista, 5, i);

            String id = cbPoista.getId();
            boolean valittu = cbPoista.isSelected();
            
            cbPoista.setOnAction(e->{
                
                    //System.out.println("cbPoista ");

                    //System.out.println(" "+levy);
                
                levyt = dao.poista(levy);
                
                    //System.out.println("1 "+levyt);
                
                try {
                    boolean ok = dao.tallennaTiedostoon();
                } catch (IOException ex) {
                    Logger.getLogger(KatalogiUi.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                    //System.out.println("2 "+levyt);
                
                luoLevylista();

            });
            
            //tulosrivit.getChildren().add(tulosrivi);
            
            //tulosrivi.getChildren().add(new Label(levy.getEsittaja()));
            //tulosrivi.getChildren().add(new Label(levy.getNimi()));
            //tulosrivi.getChildren().add(new Label(levy.getVuosi()));
            //tulosrivi.getChildren().add(new Label(levy.getTyylilaji()));
            //tulosrivi.getChildren().add(new Label(levy.getOmistaja()));
            //tulosrivi.getChildren().add(btPoista);
            
            //tulosrivi.setPadding(new Insets(0,5,0,5));
            //tulosrivi.setPrefWidth(240);
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
package katalogi.ui;

import java.io.IOException;
import java.util.ArrayList;
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
    private KatalogiDao dao;
    private ArrayList<Levy> levyt;
    //private ArrayList tulosrivit;
    private VBox tulosrivit;
    private VBox vbox3;
    //private Button btPoista;
    
    @Override
    public void start(Stage stage) throws Exception {

        dao = new KatalogiDao("levyt.txt");
        
        BorderPane bpAlku = new BorderPane();
        HBox box = new HBox(3);
        Button button1 = new Button("Uusi levy");
        Button button2 = new Button("Haku");

        vbox3               = new VBox();
        HBox hbox           = new HBox(3);
        Button btAlkuun2    = new Button("Alkuun");
        vbox3.getChildren().add(new Label(""));
        vbox3.getChildren().add(btAlkuun2);
        vbox3.getChildren().add(new Label("Levyjen haku"));
        vbox3.getChildren().add(new Label(""));
        vbox3.getChildren().add(new Label("Hakukriteeri"));
        
        ChoiceBox hakukriteeri = new ChoiceBox();
        hakukriteeri.getItems().addAll("","Esittaja", "Nimi", "Vuosi", "Tyylilaji", "Omistaja");
        TextField tfKriteeri = new TextField("");
        Button btHae         = new Button("Hae");
        tulosrivit           = new VBox();
        
        hbox.getChildren().add(hakukriteeri);
        hbox.getChildren().add(tfKriteeri);
        hbox.getChildren().add(btHae);
        
        vbox3.getChildren().add(hbox);
        vbox3.getChildren().add(new Label(""));
        
        
        BorderPane bpUusi = new BorderPane();
        VBox vbox = new VBox();
        bpUusi.setTop(new Label("Levyn lisäys"));
        bpUusi.setCenter(vbox);
        Button btLisays = new Button("Lisää");
        Button btAlkuun = new Button("Alkuun");
        Label ilmo = new Label("");
        ilmo.setTextFill(Color.RED);
        
        VBox vbox2 = new VBox(2);
        bpUusi.setBottom(vbox2);
        vbox2.getChildren().add(ilmo);
        HBox hbox2 = new HBox();
        hbox2.getChildren().add(btLisays);
        hbox2.getChildren().add(new Label("       "));
        hbox2.getChildren().add(btAlkuun);
        vbox2.getChildren().add(hbox2);

        
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
        
        btAlkuun.setOnAction(e->{
            stage.setScene(alkuScene);
        });
        
        btAlkuun2.setOnAction(e->{
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
        


        bpAlku.setTop(new Label("Levyjen hallinta"));
        bpAlku.setCenter(box);
        box.getChildren().addAll(button1, button2);
        
        bpAlku.setTop(new Label("Levyjen hallinta"));
        
        alkuScene = new Scene(bpAlku, 240, 180);
        uusiScene = new Scene(bpUusi, 240, 340);
        hakuScene = new Scene(vbox3, 400, 340);

        stage.setScene(alkuScene);
        stage.show();
        
    }

    private void luoLevylista() {

        GridPane tulosrivi;
        CheckBox cbPoista;
        
        if (vbox3.getChildren().contains(tulosrivit)) {
            
            int ind = vbox3.getChildren().indexOf(tulosrivit);
            
            tulosrivit = new VBox();
            
            vbox3.getChildren().set(ind, tulosrivit);
            
        } else {
            
            vbox3.getChildren().add(tulosrivit);
        }
        
        
        for (int i=0; i<levyt.size(); i++) {

            Levy levy = (Levy)levyt.get(i);
            //tulosrivi   =   new HBox(5);
            tulosrivi   =   new GridPane();
            
            //box.setPadding(new Insets(0,5,0,5));
            //btPoista = new Button("Poista");
            cbPoista = new CheckBox("Poista");
            
            tulosrivi.add(new Label(levy.getEsittaja()+"   "), 0, i);
            tulosrivi.add(new Label(levy.getNimi()+"   "), 1, i);
            tulosrivi.add(new Label(levy.getVuosi()+"   "), 2, i);
            tulosrivi.add(new Label(levy.getTyylilaji()+"   "), 3, i);
            tulosrivi.add(new Label(levy.getOmistaja()+"   "), 4, i);
            tulosrivi.add(cbPoista, 5, i);

            String id = cbPoista.getId();
            boolean valittu = cbPoista.isSelected();
            
            cbPoista.setOnAction(e->{
                
                    System.out.println("cbPoista ");

                    System.out.println(" "+levy);
                
                levyt = dao.poista(levy);
                
                    System.out.println("1 "+levyt);
                
                try {
                    boolean ok = dao.tallennaTiedostoon();
                } catch (IOException ex) {
                    Logger.getLogger(KatalogiUi.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                    System.out.println("2 "+levyt);
                
                luoLevylista();

            });
            
            tulosrivit.getChildren().add(tulosrivi);
            
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
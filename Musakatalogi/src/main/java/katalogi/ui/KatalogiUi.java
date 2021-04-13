package katalogi.ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
    private KatalogiDao dao;
    
    @Override
    public void start(Stage stage) throws Exception {

        //var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        //var scene = new Scene(new StackPane(label), 640, 480);
        
        //alkuScene = new Scene(new StackPane(label), 640, 480);
        
        /*
        BorderPane bp = new BorderPane();
        VBox vbox = new VBox(3);
        bp.setTop(new Label(""));
        bp.setLeft(new Label(""));
        bp.setCenter(vbox);
        Button button1 = new Button("done");
        Button button2 = new Button("done");
        vbox.getChildren().add(button1);
        vbox.getChildren().add(button2);
        */
        
        dao = new KatalogiDao("levyt.txt");
        
        BorderPane bpAlku = new BorderPane();
        HBox box = new HBox(3);
        Button button1 = new Button("Uusi levy");
        
        
        //Button button2 = new Button("Haku");
        //Button button3 = new Button("Tilastot");

        
        BorderPane bpUusi = new BorderPane();
        VBox vbox = new VBox();
        bpUusi.setTop(new Label("Levyn lisäys"));
        bpUusi.setCenter(vbox);
        Button btLisays = new Button("Lisää");
        Label ilmo = new Label("");
        ilmo.setTextFill(Color.RED);
        
        VBox vbox2 = new VBox(2);
        bpUusi.setBottom(vbox2);
        vbox2.getChildren().add(ilmo);
        vbox2.getChildren().add(btLisays);
        //bpUusi.setBottom(btLisays);
        
        /*
        TextField tfEsittaja    = new TextField("Esittaja1");
        TextField tfNimi        = new TextField("Nimi1");
        TextField tfVuosi       = new TextField("2021");
        TextField tfTyylilaji   = new TextField("Tyylilaji");
        TextField tfOmistaja    = new TextField("Omistaja");
        */
        
        TextField tfEsittaja    = new TextField("");
        TextField tfNimi        = new TextField("");
        TextField tfVuosi       = new TextField("");
        TextField tfTyylilaji   = new TextField("");
        TextField tfOmistaja    = new TextField("");
        
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
        //vbox.getChildren().add(ilmo);

        
        button1.setOnAction(e->{
            stage.setScene(uusiScene);
        });
        
        btLisays.setOnAction(e->{
            //stage.setScene(uusiScene);
            
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
            
            /*
            if (tfEsittaja.getText().equals("") | tfNimi.getText().equals("") | 
                    tfVuosi.getText().equals("") | tfTyylilaji.getText().equals("") |
                    tfOmistaja.getText().equals("")) {
                
                ilmo.setText("Puutteellinen syöte.");
            }
            else {
                
                try {
                    Integer.parseInt(tfVuosi.getText());
                } catch (Exception e2) {
                    
                    ilmo.setText("Virheellinen syöte.");
                }
                
                if (ilmo.getText().equals("")) {
                    try {
                        ok = dao.lisaa(levy);
                    } catch (IOException ex) {
                        Logger.getLogger(KatalogiUi.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                }
            }
            */
            
            /*
            try {
                boolean ok = dao.lisaa(levy);
                
                if (ok) {
                    ilmo.setText("Levy lisätty.");
                }
                
            } catch (IOException ex) {
                Logger.getLogger(KatalogiUi.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
        });
        
        
        


        bpAlku.setTop(new Label("Levyjen hallinta"));
        //bp.setCenter(new Label("Center"));
        bpAlku.setCenter(box);
        //box.getChildren().addAll(button1, button2, button3);
        box.getChildren().add(button1);
        
        bpAlku.setTop(new Label("Levyjen hallinta"));
        
        alkuScene = new Scene(bpAlku,240, 180);
        uusiScene = new Scene(bpUusi,240, 340);

        stage.setScene(alkuScene);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch();
    }

}
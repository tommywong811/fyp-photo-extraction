import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    @FXML
    private VBox labelVbox;

    @FXML
    private VBox imageVbox;

    @FXML
    private GridPane gridPane;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private VBox pos1;

    @FXML
    private VBox pos2;

    @FXML
    private VBox pos3;

    @FXML
    private VBox pos4;

    @FXML
    private VBox pos5;

    @FXML
    private VBox pos6;

    @FXML
    private VBox pos7;

    @FXML
    private VBox pos8;

    @FXML
    private VBox pos9;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private Button extractButton;

    ExecutorService pool = Executors.newFixedThreadPool(8);

    @FXML
    private void initialize(){
        List<VBox> vBoxList = new ArrayList<>();
        vBoxList.add(pos1);
        vBoxList.add(pos2);
        vBoxList.add(pos3);
        vBoxList.add(pos4);
        vBoxList.add(pos5);
        vBoxList.add(pos6);
        vBoxList.add(pos7);
        vBoxList.add(pos8);
        vBoxList.add(pos9);
        File f = new File("src/main/resources/input");
        String[] files = f.list();
        for (String s : files){
            Label button = new Label();
            button.setText(s);
            button.setId(s + "Button");
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    imageVbox.getChildren().removeAll();
                    File file = new File("src/main/resources/input/" + s);
                    String[] imageFile = file.list();
                    for(String t : imageFile){
                        Image image = new Image(getClass().getResourceAsStream("input/" + s + "/" + t),200,100,false,false);
                        ImageView imageView = new ImageView(image);
                        imageView.setAccessibleText(s + "/" + t);
                        imageView.setOnDragDetected(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
                                ClipboardContent content = new ClipboardContent();
                                content.putImage(imageView.getImage());
                                content.putString(imageView.getAccessibleText());
                                db.setContent(content);
                                event.consume();
                            }
                        });
                        imageView.setOnDragDone(new EventHandler<DragEvent>() {
                            @Override
                            public void handle(DragEvent event) {
                                if(event.getTransferMode() == TransferMode.MOVE) {
                                    imageVbox.getChildren().remove(imageVbox.getChildren().indexOf(imageView));
                                }
                                event.consume();
                            }
                        });
                        imageVbox.getChildren().add(imageView);
                    }
                }
            });
            labelVbox.getChildren().add(button);
        }
        for (VBox v:
             vBoxList) {
            v.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    if (event.getGestureSource()!= v && event.getDragboard().hasImage())
                        event.acceptTransferModes(TransferMode.ANY);
                }
            });
            v.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasImage()) {
                        ImageView i = new ImageView(db.getImage());
                        i.setFitWidth(v.getWidth());
                        i.setFitHeight(v.getWidth()/2);
                        i.setAccessibleText(db.getString());
                        System.out.println(i.getAccessibleText());
                        v.getChildren().add(i);
                        success = true;
                    }
                    event.setDropCompleted(success);

                    event.consume();
                }
            });
        }
    }

    @FXML
    private void extract() throws URISyntaxException {
        extractButton.setDisable(true);
        List<VBox> vBoxList = new ArrayList<>();
        vBoxList.add(pos1);
        vBoxList.add(pos2);
        vBoxList.add(pos3);
        vBoxList.add(pos4);
        vBoxList.add(pos5);
        vBoxList.add(pos6);
        vBoxList.add(pos7);
        vBoxList.add(pos8);
        vBoxList.add(pos9);
        for (int i = 0; i < 9; i++){
            for(int j = 0; j < 3; j++){
                Quarter quarter = new Quarter(new File(getClass().getResource("input/" + vBoxList.get(i).getChildren().get(j).getAccessibleText()).toURI()),i, vBoxList.get(i).getChildren().get(j).getAccessibleText().split("/")[0]);
                pool.execute(quarter);
            }
        }
        pool.shutdown();
        while(pool.isShutdown() == false);
        for(VBox v : vBoxList)
            v.getChildren().removeAll();
        extractButton.setDisable(false);
        Label temp = (Label)labelVbox.lookup("#" + vBoxList.get(0).getChildren().get(0).getAccessibleText().split("/")[0] + "Button");
        temp.setStyle("-fx-background-color: green;");
    }
}


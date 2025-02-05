import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {

    private static final int DIM = 3;
    private Cubie[] cube = new Cubie[DIM * DIM * DIM];
    TextArea movesTextArea = new TextArea();
    private StringBuilder textHistory = new StringBuilder();

    @Override
    public void start(Stage primaryStage) {
        // vytvorenie scény
        int width = 800;
        int height = 550;
        SmartGroup group = new SmartGroup();
        SubScene subScene = new SubScene(group, width, height, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.GREY);
        Camera camera = new PerspectiveCamera();
        subScene.setCamera(camera);

        AmbientLight light = new AmbientLight(null);
        light.setLightOn(true);
        group.getChildren().add(light);
        
        // vytvorenie a umiestnenie tlačidiel a textového okna
        HBox rightRotation = new HBox(10);
        HBox leftRotation = new HBox(10);
        VBox buttons = new VBox(10);
        buttons.getChildren().addAll(rightRotation, leftRotation);

        movesTextArea.setEditable(false);
        movesTextArea.setPrefWidth(200);
        movesTextArea.setPrefHeight(80);
        movesTextArea.setWrapText(true);

        // pozicia kocky
        group.translateXProperty().set(width / 2);
        group.translateYProperty().set(height / 2);
        group.translateZProperty().set(0);

        // rozloženie a zobrazenie okna
        BorderPane root = new BorderPane();
        root.setCenter(subScene);
        root.setBottom(buttons);
        root.setRight(movesTextArea);

        Scene mainScene = new Scene(root, width+200, height+80);

        primaryStage.setTitle("3D Rubik's Cube");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        initMouseControl(group, subScene, primaryStage);

        // vykreslenie kocky
        int index = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    cube[index] = new Cubie(x, y, z);
                    group.getChildren().add(cube[index].getMeshView());
                    index++;
                }
            }
        }

        mainScene.setOnKeyPressed(this::keyPressed);

        // Tlačitka
        rightRotation.setPadding(new Insets(5));
        leftRotation.setPadding(new Insets(5));
        rightRotation.setAlignment(Pos.CENTER);
        leftRotation.setAlignment(Pos.CENTER);
        int w = 50;
        Button buttonX1 = new Button("Left"); buttonX1.setPrefWidth(w);
        Button buttonX2 = new Button("Right"); buttonX2.setPrefWidth(w);
        Button buttonY1 = new Button("Top"); buttonY1.setPrefWidth(w);
        Button buttonY2 = new Button("Down"); buttonY2.setPrefWidth(w);
        Button buttonZ1 = new Button("Front"); buttonZ1.setPrefWidth(w);
        Button buttonZ2 = new Button("Back"); buttonZ2.setPrefWidth(w);
        Button bReset = new Button("Reset"); bReset.setPrefWidth(w);

        Button buttonx1 = new Button("Left'"); buttonx1.setPrefWidth(w);
        Button buttonx2 = new Button("Right'"); buttonx2.setPrefWidth(w);
        Button buttony1 = new Button("Top'"); buttony1.setPrefWidth(w);
        Button buttony2 = new Button("Down'"); buttony2.setPrefWidth(w);
        Button buttonz1 = new Button("Front'"); buttonz1.setPrefWidth(w);
        Button buttonz2 = new Button("Back'"); buttonz2.setPrefWidth(w);
        Button mix = new Button("Mix"); mix.setPrefWidth(w);

        rightRotation.getChildren().addAll(buttonX1, buttonX2, buttonY1, buttonY2, buttonZ1, buttonZ2, bReset);
        leftRotation.getChildren().addAll(buttonx1, buttonx2, buttony1, buttony2, buttonz1, buttonz2, mix);

        buttonX1.setOnAction(e -> {turnX(-1); updateMovesTextArea("L");});
        buttonX2.setOnAction(e -> {turnx(1); updateMovesTextArea("R");});
        buttonY1.setOnAction(e -> {turnY(-1); updateMovesTextArea("T");});
        buttonY2.setOnAction(e -> {turny(1); updateMovesTextArea("D");});
        buttonZ1.setOnAction(e -> {turnZ(-1); updateMovesTextArea("F");});
        buttonZ2.setOnAction(e -> {turnz(1); updateMovesTextArea("B");});
        
        buttonx1.setOnAction(e -> {turnx(-1); updateMovesTextArea("L'");});
        buttonx2.setOnAction(e -> {turnX(1); updateMovesTextArea("R'");});
        buttony1.setOnAction(e -> {turny(-1); updateMovesTextArea("T'");});
        buttony2.setOnAction(e -> {turnY(1); updateMovesTextArea("D'");});
        buttonz1.setOnAction(e -> {turnz(-1); updateMovesTextArea("F'");});
        buttonz2.setOnAction(e -> {turnZ(1); updateMovesTextArea("B'");});

        mix.setOnAction(e -> mixCube(movesTextArea));
        bReset.setOnAction(e -> resetCube(movesTextArea));
    }

    void resetCube(TextArea movesTextArea) {
        movesTextArea.clear();
        textHistory.delete(0, textHistory.length());
        for (Cubie qb : cube) {
            qb.reset();
        }
    }
    void mixCube(TextArea movesTextArea) {
        Timeline timeline = new Timeline();
        for (int i = 0; i < 100; i++) {
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i * 100), event -> {
                int r = (int)(Math.random() * 12) + 1;
                String move = "";
                switch(r){
                    case 1 : turnX(-1); move = "L";
                        break;
                    case 2 : turnX(1); move = "R";
                        break;
                    case 3 : turnx(-1); move = "L'";
                        break;
                    case 4 : turnx(1); move = "R'";
                        break;
                    case 5 : turnY(-1); move = "T";
                        break;
                    case 6 : turnY(1); move = "D";
                        break;
                    case 7 : turny(-1); move = "T'";
                        break;
                    case 8 : turny(1); move = "D'";
                        break;
                    case 9 : turnZ(-1); move = "F";
                        break;
                    case 10 : turnZ(1); move = "B";
                        break;
                    case 11 : turnz(-1); move = "F'";
                        break;
                    case 12 : turnz(1); move = "B'";
                        break;
                    default: break;
                }
                textHistory.append(move).append(",  ");
                movesTextArea.setText(textHistory.toString());
            }));
        }
        timeline.play();
    }

    void updateMovesTextArea(String move) {
        textHistory.append(move).append(",  ");
        movesTextArea.setText(textHistory.toString());
    }

    // otáčanie strán podľa X Y Z
    void turnX(int index) {
        for (Cubie qb : cube) {
            if (qb.getX() == index) {
                double newZ = qb.getY();
                double newY = -qb.getZ();
                qb.rotateFacesX();
                qb.update(qb.getX(), (int) Math.round(newY), (int) Math.round(newZ));
            }
        }
    }
    void turnx(int index) {
        for (Cubie qb : cube) {
            if (qb.getX() == index) {
                double newZ = -qb.getY();
                double newY = qb.getZ();
                qb.rotateFacesx();
                qb.update(qb.getX(), (int) Math.round(newY), (int) Math.round(newZ));
            }
        }
    }

    void turnY(int index) {
        for (Cubie qb : cube) {
            if (qb.getY() == index) {
                double newX = qb.getZ();
                double newZ = -qb.getX();
                qb.rotateFacesY();
                qb.update((int) Math.round(newX), qb.getY(), (int) Math.round(newZ));
            }
        }
    }
    void turny(int index) {
        for (Cubie qb : cube) {
            if (qb.getY() == index) {
                double newX = -qb.getZ();
                double newZ = qb.getX();
                qb.rotateFacesy();
                qb.update((int) Math.round(newX), qb.getY(), (int) Math.round(newZ));
            }
        }
    }

    void turnZ(int index) {
        for (Cubie qb : cube) {
            if (qb.getZ() == index) {
                double newX = qb.getY();
                double newY = -qb.getX();
                qb.rotateFacesZ();
                qb.update((int) Math.round(newX), (int) Math.round(newY), qb.getZ());
            }
        }
    }
    void turnz(int index) {
        for (Cubie qb : cube) {
            if (qb.getZ() == index) {
                double newX = -qb.getY();
                double newY = qb.getX();
                qb.rotateFacesz();
                qb.update((int) Math.round(newX), (int) Math.round(newY), qb.getZ());
            }
        }
    }

    // miešanie kocky cez klávesy
    private void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case NUMPAD1 : turnX(-1);
                break;
            case NUMPAD2 : turnx(1);
                break;
            case NUMPAD4 : turnY(-1);
                break;
            case NUMPAD5 : turny(1);
                break;
            case NUMPAD7 : turnZ(-1);
                break;
            case NUMPAD8 : turnz(1);
                break;
            default:
                break;
        }
    }

    // otáčanie a približovanie kocky myšou
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private void initMouseControl(SmartGroup group, SubScene scene, Stage stage) {
        Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
        group.getTransforms().addAll(xRotate, yRotate);

        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() + delta * 2);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

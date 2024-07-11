import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class App extends Application {

  private static final int DIM = 3;
  private Cubie[] cube = new Cubie[DIM * DIM * DIM];

  @Override
  public void start(Stage primaryStage) {
    //nastavenie okna
      SmartGroup group = new SmartGroup();
      Scene scene = new Scene(group, 800, 600, true);
      scene.setFill(Color.GREY);
      Camera camera = new PerspectiveCamera();
      scene.setCamera(camera);
      AmbientLight light = new AmbientLight(null);
      light.setLightOn(true);
      group.getChildren().add(light);

      //poloha kocky
      group.translateXProperty().set(800 / 2);
      group.translateYProperty().set(600 / 2);
      group.translateZProperty().set(0);

      //zobrazenie okna
      primaryStage.setTitle("3D Rubik's Cube");
      primaryStage.setScene(scene);
      primaryStage.show();

      initMouseControl(group, scene, primaryStage);

    //vykreslenie kocky
      int index = 0;
      for (int x = -1; x <= 1; x++) {
          for (int y = -1; y <= 1; y++) {
              for (int z = -1; z <= 1; z++) {
                  cube[index] = new Cubie(x, y, z);
                  group.getChildren().add(cube[index].getBox());
                  index++;
              }
          }
      }
      cube[0].setFarba(true);
      cube[2].setFarba(true);
      scene.setOnKeyPressed(this::keyPressed);
  }

// aktualizacia pozicie kociek
void turnX(int index) {
  for (Cubie qb : cube) {
    if (qb.getX() == index) {
        double newZ = qb.getY();
        double newY = -qb.getZ();
        qb.update(qb.getX(), (int) Math.round(newY), (int) Math.round(newZ));
      }
    }
  }
  
  void turnY(int index) {
    for (Cubie qb : cube) {
      if (qb.getY() == index) {
          double newX = qb.getZ();
          double newZ = -qb.getX();
          qb.update((int) Math.round(newX), qb.getY(), (int) Math.round(newZ));
        }
      }
    }

    void turnZ(int index) {
      for (Cubie qb : cube) {
        if (qb.getZ() == index) {
            double newX = qb.getY();
            double newY = -qb.getX();
            qb.update((int) Math.round(newX), (int) Math.round(newY), qb.getZ());
          }
        }
      }

    // otačanie kocky klavesami
  private void keyPressed(KeyEvent event) {
    switch (event.getCode()) {
      case NUMPAD1 : turnX(-1);
      break;
      case NUMPAD2 : turnX(1);
      break; 
      case NUMPAD4 : turnY(-1);
      break;
      case NUMPAD5 : turnY(1);
      break; 
      case NUMPAD7 : turnZ(-1);
      break;
      case NUMPAD8 : turnZ(1);
      break;
      default:
      break;
    }
  }
    
// ovládanie kamery myšou
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private void initMouseControl(SmartGroup group, Scene scene, Stage stage) {
      Rotate xRotate= new Rotate(0, Rotate.X_AXIS);
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
        group.translateZProperty().set(group.getTranslateZ() + delta*2);
      });
    }
  
    public static void main(String[] args) {
      launch(args);
    }
}

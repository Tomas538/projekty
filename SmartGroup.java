import javafx.scene.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

// stanovenie ako sa bude otočať kocka
class SmartGroup extends Group {
    Rotate r;
    Transform t = new Rotate();

    void rotateByX(int ang) {
      r = new Rotate(ang, Rotate.X_AXIS);
      t = t.createConcatenation(r);
      this.getTransforms().clear();
      this.getTransforms().add(t);
    }

    void rotateByY(int ang) {
      r = new Rotate(ang, Rotate.Y_AXIS);
      t = t.createConcatenation(r);
      this.getTransforms().clear();
      this.getTransforms().add(t);
    }
  }
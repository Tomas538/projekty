import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;

public class Cubie {

    //vytvorenie kocky
    private Box box;
    private boolean farba = false;
    private PhongMaterial material;
    private int x, y, z;
    private final int len = 50;

    public Cubie(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        box = new Box(len, len, len);
        material = new PhongMaterial();
        material.setDiffuseColor(Color.WHITE);
        box.setMaterial(material);
        box.getTransforms().add(new Translate(x * len, y * len, z * len));
    }

    // Zmena farby na indexe
    public void setFarba(boolean farba) {
        this.farba = farba;
        if (farba) {
            material.setDiffuseColor(Color.RED);
        } else {
            material.setDiffuseColor(Color.WHITE);
        }
    }

    // Aktualizácia pozície
    public void update(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        box.getTransforms().clear();
        box.getTransforms().add(new Translate(x * len, y * len, z * len));
    }
    // Gettery pre pozície
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Box getBox() {
        return box;
    }
}

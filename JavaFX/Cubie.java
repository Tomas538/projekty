import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Translate;

public class Cubie {
    private MeshView meshView;
    private int x, y, z;
    private final int len = 40;
    private int[] faceColors = {0, 1, 2, 3, 4, 5};
    private final int initialX, initialY, initialZ;
    private final int[] initialFaceColors = {0, 1, 2, 3, 4, 5};

    public Cubie(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.initialX = x;
        this.initialY = y;
        this.initialZ = z;
        meshView = createColoredCube();
        meshView.getTransforms().add(new Translate(x * len, y * len, z * len));
    }

    private MeshView createColoredCube() {
        TriangleMesh mesh = new TriangleMesh();

        // rozloženie / tvar objektu
        float h = len / 2.0f;
        float[] points = {
                -h, -h, -h,
                h, -h, -h,
                h, h, -h,
                -h, h, -h,
                -h, -h, h,
                h, -h, h,
                h, h, h,
                -h, h, h
        };
        mesh.getPoints().addAll(points);

        //nastavenie farieb
        float[] texCoords = {
                0.1f, 0.1f,   // front blue
                0.8f, 0.2f,   // back green
                0.7f, 0.5f,   // right orange
                0.2f, 0.5f,   // left red
                0.27f, 0.85f, // top white
                0.7f, 0.85f,  // down yellow

        };
        mesh.getTexCoords().addAll(texCoords);

        // označenie strán
        int[] faces = {
            0, faceColors[0], 2, faceColors[0], 1, faceColors[0],  0, faceColors[0], 3, faceColors[0], 2, faceColors[0],  // Front Blue
            4, faceColors[1], 5, faceColors[1], 6, faceColors[1],  4, faceColors[1], 6, faceColors[1], 7, faceColors[1],  // Back Green
            1, faceColors[2], 2, faceColors[2], 6, faceColors[2],  1, faceColors[2], 6, faceColors[2], 5, faceColors[2],  // Right Orange
            3, faceColors[3], 0, faceColors[3], 4, faceColors[3],  3, faceColors[3], 4, faceColors[3], 7, faceColors[3],  // Left Red
            0, faceColors[4], 1, faceColors[4], 5, faceColors[4],  0, faceColors[4], 5, faceColors[4], 4, faceColors[4],  // Top White
            2, faceColors[5], 3, faceColors[5], 7, faceColors[5],  2, faceColors[5], 7, faceColors[5], 6, faceColors[5],  // Bottom Yellow
            
        };
        mesh.getFaces().addAll(faces);

        MeshView meshView = new MeshView(mesh);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(createDiffuseMap());
        meshView.setMaterial(material);

        return meshView;
    }

    // vytvorenie farieb kocky
    private Image createDiffuseMap() {
        int width = 2;
        int height = 3;
        int[] colors = {
          0xFF0000FF, // Blue
          0xFF00FF00, // Green
          0xFFFF0000, // Orange
          0xFFFFA500, // Red
          0xFFFFFFFF, // White
          0xFFFFFF00,// Yellow
        };
        WritableImage image = new WritableImage(width, height);
        PixelWriter writer = image.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                writer.setArgb(x, y, colors[y * width + x]);
            }
        }
        return image;
    }

    // Aktualizácia pozície
    public void update(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        meshView.getTransforms().clear();
        meshView.getTransforms().add(new Translate(x * len, y * len, z * len));
        meshView.setMesh(createColoredCube().getMesh());
    }

    // resetovanie kocky
    public void reset() {
        this.x = initialX;
        this.y = initialY;
        this.z = initialZ;
        System.arraycopy(initialFaceColors, 0, faceColors, 0, faceColors.length);
        meshView.getTransforms().clear();
        meshView.getTransforms().add(new Translate(x * len, y * len, z * len));
        meshView.setMesh(createColoredCube().getMesh());
    }

    // otáčanie strán kocky / zmena farieb
    public void rotateFacesX() {
        int temp = faceColors[4];
        faceColors[4] = faceColors[1];
        faceColors[1] = faceColors[5];
        faceColors[5] = faceColors[0];
        faceColors[0] = temp;
    }
    public void rotateFacesx() {
        int temp = faceColors[0];
        faceColors[0] = faceColors[5];
        faceColors[5] = faceColors[1];
        faceColors[1] = faceColors[4];
        faceColors[4] = temp;
    }

    public void rotateFacesY() {
        int temp = faceColors[2];
        faceColors[2] = faceColors[1];
        faceColors[1] = faceColors[3];
        faceColors[3] = faceColors[0];
        faceColors[0] = temp;
    }
    public void rotateFacesy() {
        int temp = faceColors[0];
        faceColors[0] = faceColors[3];
        faceColors[3] = faceColors[1];
        faceColors[1] = faceColors[2];
        faceColors[2] = temp;
    }

    public void rotateFacesZ() {
        int temp = faceColors[4];
        faceColors[4] = faceColors[3];
        faceColors[3] = faceColors[5];
        faceColors[5] = faceColors[2];
        faceColors[2] = temp;
    }
    public void rotateFacesz() {
        int temp = faceColors[2];
        faceColors[2] = faceColors[5];
        faceColors[5] = faceColors[3];
        faceColors[3] = faceColors[4];
        faceColors[4] = temp;
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
    public MeshView getMeshView() {
        return meshView;
    }
}

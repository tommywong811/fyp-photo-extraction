import java.io.File;

public class Extraction {

    private File file;
    private int[] array;
    private String string;

    public Extraction(File file, int[] array, String string) {
       this.file = file;
       this.array = array;
       this.string = string;
    }

    public void run() {
        Image image = new Image(file.getPath());
        for (int k : array) {

            Image newImage = new Image(2150, 1075, image.getType(),k, file.getName(),0, string);
            for (int i = 0; i < 1075; i++) {
                for (int j = 0; j < 2150; j++) {
                    Coordinate coordinate = new Coordinate(j, i);
                    coordinate.setCenterCoordinate(k / 12.0, 0.33);
                    coordinate.gnometricProjection();
                    newImage.setPixel(j, i, image.getPixel(coordinate.getLatX(), coordinate.getLongY()));
                }
            }
            newImage.saveImage();
        }
        for (int k :array) {

            Image newImage = new Image(2150, 1075, image.getType(),k, file.getName(),1,string);
            for (int i = 0; i < 1075; i++) {
                for (int j = 0; j < 2150; j++) {
                    Coordinate coordinate = new Coordinate(j, i);
                    coordinate.setCenterCoordinate(k / 12.0, 0.5);
                    coordinate.gnometricProjection();
                    newImage.setPixel(j, i, image.getPixel(coordinate.getLatX(), coordinate.getLongY()));
                }
            }
            newImage.saveImage();
        }
        for (int k :array) {

            Image newImage = new Image(2150, 1075, image.getType(),k, file.getName(),2,string);
            for (int i = 0; i < 1075; i++) {
                for (int j = 0; j < 2150; j++) {
                    Coordinate coordinate = new Coordinate(j, i);
                    coordinate.setCenterCoordinate(k / 12.0, 0.83);
                    coordinate.gnometricProjection();
                    newImage.setPixel(j, i, image.getPixel(coordinate.getLatX(), coordinate.getLongY()));
                }
            }
            newImage.saveImage();
        }
    }
}

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    private int height;
    private int width;
    BufferedImage image;
    int imageNumber;
    String filename;
    int position;
    String string;

    public Image(int width,int height,int type,int imageNumber,String filename,int position,String string) {
        this.image = new BufferedImage(width,height,type);
        this.height = height;
        this.width = width;
        this.imageNumber = imageNumber;
        this.filename = filename;
        this.position = position;
        this.string = string;
    }

    public Image(String filename) {
        try{
            File input = new File(filename);
            image = ImageIO.read(input);
            height = image.getHeight();
            width = image.getWidth();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setPixel(int x, int y, int value){
        image.setRGB(x,y,value);
    }

    public int getPixel(int x, int y) {
        return image.getRGB(x,y);
    }
    public void saveImage(){
        try{
            File f = new File("src/output/" + string + "/" + (filename.replace(".jpg","")) + "(" + position + "." + imageNumber + ")" + ".jpg");
            f.getParentFile().mkdirs();
            ImageIO.write((RenderedImage) image, "jpg", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getType(){
        return image.getType();
    }
}

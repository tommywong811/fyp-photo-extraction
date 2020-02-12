public class Pixel {
    int value;
    int alpha;
    int red;
    int green;
    int blue;

    public Pixel(int value){

        this.value = value;
        this.alpha = (value>>24) & 0xff;
        this.red = (value>>16) & 0xff;
        this.green = (value>>8) & 0xff;
        this.blue = value & 0xff;
    }

    public int getAlpha() {
        return alpha;
    }

    public int getValue() {
        return value;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}

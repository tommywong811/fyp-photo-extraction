import java.io.File;

public class Quarter extends Thread{

    public static int totalFile;
    public static int completeFile = 0;
    private File file;
    private int position;
    private int[][] pattern;
    private String string;
    private int[] pos1 = {3,4,5,6};
    private int[] pos2 = {3,4,5,6,7,8,9};
    private int[] pos3 = {6,7,8,9};
    private int[] pos4 = {0,1,2,3,4,5,6};
    private int[] pos5 = {0,1,2,3,4,5,6,7,8,9,10,11,12};
    private int[] pos6 = {6,7,8,9,10,11,0};
    private int[] pos7 = {0,1,2,3};
    private int[] pos8 = {9,10,11,0,1,2,3};
    private int[] pos9 = {9,10,11,0};

    public Quarter(File file, int position,String string) {
        this.file = file;
        this.pattern = new int[][]{pos1, pos2, pos3, pos4, pos5, pos6,pos7,pos8,pos9};
        this.position = position;
        this.string = string;
    }

    @Override
    public void run(){
            Extraction extraction = new Extraction(file,pattern[position],string);
            extraction.run();
        completeFile++;
        System.out.println("Extraction complete " + file.getName() + "(" + (completeFile) +"/" + totalFile + ")");
    }
}

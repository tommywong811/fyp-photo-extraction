public class Coordinate{

    private double centercoordinatex = 0.5;
    private double centercoordinatey = 0.5;
    private int x;
    private int y;
    private double screencoordinatex;
    private double screencoordinatey;
    private double latitude;
    private double longitude;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
        this.screencoordinatex = this.x/2150.0;
        this.screencoordinatey = this.y/1075.0;
    }

    public void gnometricProjection(){
        double cpx = (centercoordinatex*2.0-1.0)*Math.PI;
        double cpy = (centercoordinatey*2.0-1.0)*Math.PI/2;

        double screenx = (screencoordinatex*2.0-1.0)*Math.PI*0.4;
        double screeny = (screencoordinatey*2.0-1.0)*Math.PI/2*0.4;

        double rou = Math.sqrt(screenx*screenx + screeny*screeny);
        double c = Math.atan(rou);
        double sinc = Math.sin(c);
        double cosc = Math.cos(c);

        this.longitude = Math.asin(cosc*Math.sin(cpy) + (screeny*sinc*Math.cos(cpy))/rou);
        this.latitude = cpx + Math.atan2(screenx*sinc,(rou*Math.cos(cpy)*cosc) - (screeny * Math.sin(cpy)*sinc));


    }
    public void setCenterCoordinate(double centercoordinatex, double centercoordinatey) {
        this.centercoordinatex = centercoordinatex;
        this.centercoordinatey = centercoordinatey;
    }

    public double getCentercoordinatex(){
        return centercoordinatex;
    }

    public double getCentercoordinatey() {
        return centercoordinatey;
    }

    public int getLatX(){
        int latx = (int) Math.round(latitude*(5376/(2*Math.PI))) + 5376/2;
        if(latx < 0)
            return latx + 5376;
        else if(latx >= 5376)
            return latx - 5376;
        else
        return latx;
    }

    public int getLongY(){
        int longy = (int) Math.round(longitude*(2688/(Math.PI)))+2688/2;
        if(longy < 0)
            return longy + 2688;
        else if(longy >= 2688)
            return longy - 2688;
        else
        return longy;
    }
}

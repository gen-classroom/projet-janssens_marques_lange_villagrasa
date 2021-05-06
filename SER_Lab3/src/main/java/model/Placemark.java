import java.util.ArrayList;

public class Placemark {

    private String name;
    private String styleId;
    private String type;
    private ArrayList<Polygon> polygons;

    public Placemark(String name, String type, String styleId){
        this.name = name;
        this.type = type;
        this.styleId = styleId;
        this.polygons = new ArrayList<>();
    }

    public void addPolygon(Polygon polygon){
        this.polygons.add(polygon);
    }


}

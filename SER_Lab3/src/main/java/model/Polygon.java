import java.util.ArrayList;

public class Polygon {
    private ArrayList<Coordinate> coordinates;

    public Polygon(){
        this.coordinates = new ArrayList<>();
    }

    public Polygon(ArrayList<Coordinate> coordinates){
        this.coordinates = coordinates;
    }

    public void addCoordinate(Coordinate coordinate){
        this.coordinates.add(coordinate);
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }
}

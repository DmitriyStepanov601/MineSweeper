/**
 * A class that describes the playing field
 * @author Dmitriy Stepanov
 */
public class Matrix {
    private Box [][] matrix;

    /**
     * Constructor - creating a new playing field
     * @param defaultBox - default Box
     * @see Matrix#Matrix(Box)
     */
    public Matrix(Box defaultBox){
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for(Coord coord : Ranges.getAllCoords())
            matrix[coord.x][coord.y] = defaultBox;
    }

    public Box get(Coord coord){
        if(Ranges.inRange(coord))
            return matrix[coord.x][coord.y];
        return null;
    }

    public void set(Coord coord, Box box){
        if(Ranges.inRange(coord))
            matrix[coord.x][coord.y] = box;
    }
}
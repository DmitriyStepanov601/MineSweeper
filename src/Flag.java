/**
 * Class describing the flag
 * @author Dmitriy Stepanov
 */
public class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;

    public void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    public Box get (Coord coord){
        return flagMap.get(coord);
    }

    public void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        countOfClosedBoxes--;
    }

    public void toggleFlagedToBox(Coord coord) {
        switch(flagMap.get(coord)) {
            case FLAGED: setClosedToBox(coord); break;
            case CLOSED: setFlagedToBox(coord); break;
        }
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }
    private void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
    }
    public int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }
    public void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    public void setOpenedToClosedBombs(Coord coord) {
        if(flagMap.get(coord) == Box.CLOSED)
            flagMap.set(coord, Box.OPENED);
    }

    public void setNobombToFlagedSafeBox(Coord coord) {
        if(flagMap.get(coord) == Box.FLAGED)
            flagMap.set(coord, Box.NOBOMB);
    }

    public int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for(Coord around: Ranges.getCoordAround(coord))
            if(flagMap.get(around) == Box.FLAGED)
                count++;
        return count;
    }
}
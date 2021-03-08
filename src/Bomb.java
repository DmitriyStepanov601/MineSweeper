/**
 * Bomb class with properties <b>bombMap</b> и <b>totalBombs</b>.
 * @author Dmitriy Stepanov
 */
public class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    /**
     * Constructor - creating a new bomb
     * @param totalBombs - count bombs
     * @see Bomb#Bomb(int)
     */
    public Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombCount();
    }

    public void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int j = 0; j < totalBombs; j++)
            placeBomb();
    }

    public Box get(Coord coord){
        return bombMap.get(coord);
    }

    private void fixBombCount() {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if(totalBombs > maxBombs)
            totalBombs = maxBombs;
    }

    private void placeBomb() {
        while(true) {
            Coord coord = Ranges.getRandomCoord();
            if(Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord, Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }

    private void incNumbersAroundBomb(Coord coord) {
        for(Coord around: Ranges.getCoordAround(coord))
            if(Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
    }

    public int getTotalBombs() {
        return totalBombs;
    }
}
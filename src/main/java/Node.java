import java.util.ArrayList;

public class Node {
    public int xCoordinate;
    public int yCoordinate;
    public String symbolChar;
    public ArrayList<Node> neighbourNodes;
    public int currDistance;
    public boolean visited = false;

    public Node(int xCoordinate, int yCoordinate, String symbolChar) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.symbolChar = symbolChar;
        this.neighbourNodes = new ArrayList<>();
        this.currDistance = 0;
    }

    @Override
    public String toString() {
        return this.xCoordinate + ", " + this.yCoordinate;
    }

}

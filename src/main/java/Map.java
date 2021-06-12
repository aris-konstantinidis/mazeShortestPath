import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Map {

    public Node[][] map;
    int nRows;
    int nCols;

    public Map(int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.map = new Node[this.nRows][this.nCols];
        construct();
    }

    public void construct() {
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if (i == 0 || i == nRows-1 || j == 0 || j == nCols-1 ||
                    i == 3 && (j > 2 && j < 5) ||
                    i == 7 && (j > 6 && j <= 13)
                ) {
                    map[i][j] = new Node(i, j, "*");
                } else {
                    map[i][j] = new Node(i, j, "-");
                }
            }
        }
    }

    public void printMap() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                stringBuffer.append(map[i][j].symbolChar + "\t"); // change c to distance if needed
            }
            stringBuffer.append("\n");
        }
        System.out.println(stringBuffer);
    }

    public void setNode(Node node) {
        map[node.xCoordinate][node.yCoordinate] = node;
    }

    public void setNexts() {
        for (int x = 0; x < nRows; x++) {
            for (int y = 0; y < nCols; y++) {
               if (!map[x][y].symbolChar.equals("*") ) { // if empty node
                   if (x - 1 > 0 && !map[x-1][y].symbolChar.equals("*")) { // if in lower bounds and lower ok
                       map[x][y].neighbourNodes.add(map[x-1][y]);
                   }
                   if (x + 1 < nRows && !map[x+1][y].symbolChar.equals("*")) { // if in upper bounds and upper ok
                       map[x][y].neighbourNodes.add(map[x+1][y]);
                   }
                   if (y - 1 > 0 && !map[x][y-1].symbolChar.equals("*")) { // if in left bounds and left ok
                       map[x][y].neighbourNodes.add(map[x][y-1]);
                   }
                   if (y + 1 < nCols && !map[x][y+1].symbolChar.equals("*")) { // if in right bounds and right ok
                       map[x][y].neighbourNodes.add(map[x][y+1]);
                   }
               }
            }
        }
    }

    // LEE ALGORITHM (BFS QUEUE && BACKTRACK WITH STACK)
    // STEP 1 CREATE DISTANCES
    // STEP 2 BACKTRACK DEPENDING ON DISTANCES
    public Stack<Node> leeAlgoSearch(Node fromNode, Node toNode) {
        /*
        Takes care of marking the graph with distances
         */
        fromNode.visited = true; // mark root as visited
        Queue<Node> queue = new LinkedList<>(); // used for breadth first search
        queue.add(fromNode); // add root to queue
        while (!queue.isEmpty()) {
            for (Node node : queue.peek().neighbourNodes) { // the next attribute contains all neighbours of this cell
                if (!node.visited) {
                    node.currDistance = queue.peek().currDistance + 1; // increase relative
                    node.visited = true;
                    queue.add(node);
                }
            }
            queue.poll();
        }
        /*
        Takes care of backtracking the optimal path
         */
        Stack<Node> backtrack = new Stack<>();
        backtrack.add(toNode);
        boolean notFound = true;
        while (notFound) {
            Node bestNext = backtrack.peek().neighbourNodes.get(0);
            for (Node node : backtrack.peek().neighbourNodes) {
                if (node.currDistance < bestNext.currDistance) {
                    bestNext = node;
                }
            }
            backtrack.add(bestNext);
            if (bestNext.currDistance == 1) {
                notFound = false;
            }
        }
        return backtrack;
    }

    public void movePiece(Node node, int x, int y) {
        map[node.xCoordinate][node.yCoordinate].symbolChar = " ";
        map[x][y].symbolChar = "X";
    }
}

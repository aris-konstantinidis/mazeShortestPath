import java.util.Stack;

public class Driver {
    public static void main(String[] args) {
        Map map = new Map(15, 15);
        Node op = new Node(1,1, "X");
        Node po = new Node(13, 13, "O");
        map.setNode(op);
        map.setNode(po);
        map.setNexts();
        Stack<Node> nodePath = map.leeAlgoSearch(op, po);
        for (Node nextNode : nodePath) {
            map.movePiece(op, nextNode.xCoordinate, nextNode.yCoordinate);
        }
        map.printMap();
    }
}

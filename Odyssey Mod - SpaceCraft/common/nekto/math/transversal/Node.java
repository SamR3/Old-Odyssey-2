package nekto.math.transversal;

public class Node
{
    public int xPos;
    public int yPos;
    public int zPos;

    public Node(int x, int y, int z)
    {
        this.xPos = x;
        this.yPos = y;
        this.zPos = z;
    }
    
    public boolean isEqualTo(Node node)
    {
        return node.xPos == node.xPos && this.yPos == node.yPos && this.zPos == node.zPos;
    }
}

package nekto.math.transversal;

import net.minecraft.world.World;

public class TraversalManager implements Runnable
{
    public Thread traversalThread;
    
    private NodeManager nodeManager;
    private World world;
    private Node currNode;
    
    private int[][] neighbors = 
        {
            {0, 0, 1},
            {0, 1, 0},
            {1, 0, 0},
            {0, 0, -1},
            {0, -1, 0},
            {-1, 0, 0}
        };
    
    public TraversalManager(Node node, World minecraftWorld)
    {
        this.nodeManager = new NodeManager(node);
        this.world = minecraftWorld;
        
        this.currNode = node;
        
        traversalThread = new Thread(this, "TraversalThread");
        traversalThread.start();
    }
    
    private void checkEdges()
    {
        for(int i = 0; i < 6; i++)
        {
            if(world.getBlockId(this.currNode.xPos + neighbors[i][0], this.currNode.yPos + neighbors[i][1], this.currNode.zPos + neighbors[i][2]) == 1)
            {
                Node unknown = new Node(this.currNode.xPos + neighbors[i][0], this.currNode.yPos + neighbors[i][1], this.currNode.zPos + neighbors[i][2]);
                
                if(!this.nodeManager.hasNode(this.nodeManager.masterNodeList, unknown))
                {
                    this.nodeManager.addUnvisitedNode(unknown);
                    System.out.println("Added Node " + unknown.xPos + ", " + unknown.yPos + ", " + unknown.zPos);
                }
            }
        }
    }

    @Override
    public void run()
    {
        checkEdges();
        
        System.out.println("Finished node discovery.");
        
        for(Node node : this.nodeManager.masterNodeList)
        {
            world.setBlock(node.xPos, node.yPos, node.zPos, 3);
        }
        
        for(Node node : this.nodeManager.unvisitedEdges)
        {
            world.setBlock(node.xPos, node.yPos, node.zPos, 3);
        }
    }
}

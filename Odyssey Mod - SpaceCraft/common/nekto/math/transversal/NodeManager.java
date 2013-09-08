package nekto.math.transversal;

import java.util.HashSet;
import java.util.Set;

public class NodeManager
{
    public Set<Node> masterNodeList = new HashSet<Node>();
    public Set<Node> unvisitedEdges = new HashSet<Node>();
    
    public NodeManager(Node root)
    {
        this.masterNodeList.add(root);
    }
    
    public void addNode(Node node)
    {
        if(!this.hasNode(this.masterNodeList, node))
        {
            this.masterNodeList.add(node);
        }
    }
    
    public boolean hasUnvisitedNode()
    {
        return !this.unvisitedEdges.isEmpty();
    }

    public void addUnvisitedNode(Node... node)
    {
        for(Node edge : node)
        {
            if(!this.hasNode(this.unvisitedEdges, edge))
            {
                this.unvisitedEdges.add(edge);
            }
        }
    }
    
    public Node getUnvisitedEdge()
    {
        if(unvisitedEdges.iterator().hasNext())
        {
            return unvisitedEdges.iterator().next();
        }
        
        return null;
    }
    
    public boolean hasNode(Set<Node> set, Node node)
    {
        for(Node comp : set)
        {
            if(node.isEqualTo(comp))
            {
                return true;
            }
        }
        
        return false;
    }
}

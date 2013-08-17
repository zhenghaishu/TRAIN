package twhomework.train;


/**
 * Data structure of town
 */
public class Node 
{
	private String name;
	private boolean visited;
	
	public Node(String name)
	{
		this.name = name;
		this.visited = false;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public boolean getVisited()
	{
		return visited;
	}
	
	public void setVisited(boolean visit)
	{
		this.visited = visit;
	}
	

	
	@Override
	public boolean equals(Object obj)
	{
		if(null == obj || obj.getClass() != getClass())
		{
			return false;
		}
		
		Node node = (Node)obj;
		return this.name.equals(node.name);
	}
	
	@Override
	public int hashCode()
	{
		if(null == this.name)
		{
			return 0;
		}
		
		return this.name.hashCode();
	}
}

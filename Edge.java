package twhomework.train;

/**
 * Data structure of route between two towns
 */
public class Edge 
{
	private Node start;
	private Node end;
	private int weight;
	private Edge next;
	
	public Edge(Node start, Node end, int weight)
	{
		this.setStart(start);
		this.setEnd(end);
		this.setWeight(weight);
		this.next = null;
	}
	
	public Node getStart() 
	{
		return start;
	}

	public void setStart(Node start) 
	{
		this.start = start;
	}

	public Node getEnd() 
	{
		return end;
	}

	public void setEnd(Node end) 
	{
		this.end = end;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}
	
	public Edge getNext()
	{
		return next;
	}
	
	public void setNext(Edge next)
	{
		this.next = next;
	}
}

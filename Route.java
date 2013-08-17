package twhomework.train;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 
 * @author Zheng Haishu
 * 
 * @create 2013-8-10
 * 
 * @update 2013-8-11
 * 
 */
public class Route 
{
	private Hashtable<Node, Edge> routeTable;
	
	/*
	 * Constructor
	 */
	public Route() 
	{
		this.routeTable = new Hashtable<Node, Edge>();
	}
	
	/*
	 * Getter
	 */
	public Hashtable<Node, Edge> getRouteTable()
	{
		return routeTable;
	}

	/*
	 * Calculates the distance of a specific path
	 */
	public int distance(ArrayList<Node> towns) throws Exception
	{
		// No city or 1 town
		if(towns.size() < 2)
		{
			return 0;
		}
		int distance = 0;
		int depth = 0;
		int i = 0;

		while(i < towns.size() - 1)
		{
			// Check if the city in cities exists
			if(this.routeTable.containsKey(towns.get(i))) 
			{
				Edge route = this.routeTable.get(towns.get(i));
				
				// Check if route exists 
				while(null != route) 
				{
					if(route.getEnd().equals(towns.get(i + 1)))
					{
						distance += route.getWeight();
						depth++;
						break;
					}
					route = route.getNext();
				}
			}
			else
			{
				throw new Exception("NO SUCH ROUTE");
			}
			
			i++;
		}

		// Check if route depth is equal to cities count - 1
		if(depth != towns.size() - 1)
		{
			throw new Exception("NO SUCH ROUTE");
		}

		return distance;
	}

	/*
	 *  count of trip
	 *  for example, there are 2 routes from C to c:CDC, CEBC, so count is 2
	 */
	public int tripCount(Node start, Node end, int maxStops) throws Exception
	{
		return calcTripCount(start, end, 0, maxStops);
	}

	/*
	 * Finds count of trip from start to end
	 *
	 */
	private int calcTripCount(Node start, Node end, int depth, int maxStops) throws Exception
	{
		int routes = 0;
		if(this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) 
		{
			// Check if depth level is within limits
			// Mark start node as visited
			start.setVisited(true);		
			Edge edge = this.routeTable.get(start);
			while(null != edge) 
			{
				depth++;
				if(depth <= maxStops)
				{
					// If destination matches, increment route count, and continue to next node at same depth
					if(edge.getEnd().equals(end))
					{
						routes++;
						depth--;

					}
					
					// If destination not match, and destination node has not been visited, recursively traverse destination node
					else if(!edge.getEnd().getVisited())
					{
						routes += calcTripCount(edge.getEnd(), end, depth, maxStops);
						depth--;
					}
				}
				else
				{
					depth--;
				}
				edge = edge.getNext();
			}
		}
		else
		{
			throw new Exception("NO SUCH ROUTE");
		}

		// reset start node as unvisited
		start.setVisited(false);
		return routes;
	}

	public int exactTripCount(Node start, Node end, int maxStops) throws Exception
	{
		return calcExactTripCount(start, end, 0, maxStops);
	}

	/*
	 * Finds count of trip from start to end
	 *
	 */
	private int calcExactTripCount(Node start, Node end, int depth, int maxStops) throws Exception
	{
		int routes = 0;
		if(this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) 
		{
//			start.setVisited(true);		
			Edge edge = this.routeTable.get(start);
			while(null != edge) 
			{
				depth++;
				if(depth < maxStops)
				{
					routes += calcExactTripCount(edge.getEnd(), end, depth, maxStops);
					depth--;
				}
				else if(depth == maxStops)
				{
					if(edge.getEnd().equals(end))
					{
						routes++;
					}
				}
				else
				{
					depth--;
				}
				edge = edge.getNext();
			}
		}
		else
		{
			throw new Exception("NO SUCH ROUTE");
		}

		// reset start node as unvisited
		return routes;
	}
	/*
	 * Shortest route;
	 */
	public int shortestRoute(Node start, Node end) throws Exception 
	{
		return calcShortestRoute(start, end, 0, 0);
	}

	/*
	 * Finds the shortest distance between start node and end node
	 */
	private int calcShortestRoute(Node start, Node end, int weight, int shortestRoute) throws Exception
	{
		if(this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) 
		{
			// Traverse all possible routes and for each, check if it is destination
			Edge edge = this.routeTable.get(start);
			while(null != edge) 
			{
				start.setVisited(true);
				if(edge.getEnd() == end || !edge.getEnd().getVisited())
				{
					weight += edge.getWeight();
				}

				if(edge.getEnd().equals(end)) 
				{
					if(shortestRoute == 0 || weight < shortestRoute)
					{
						shortestRoute = weight;
						// Decrement for next recursive calculation
						weight -= edge.getWeight();	
					}
					start.setVisited(false);
				}

				else if(!edge.getEnd().getVisited()) 
				{
					shortestRoute = calcShortestRoute(edge.getEnd(), end, weight, shortestRoute);
					weight -= edge.getWeight();
				}
				edge = edge.getNext();
			}
		}
		else
		{
			throw new Exception("NO SUCH ROUTE");
		}

		// reset start node as unvisited
		start.setVisited(false);
		return shortestRoute;
	}

	/*
	 * Total routes from start node to end node
	 */
	public int routeCount(Node start, Node end, int maxDistance) throws Exception 
	{
		return calcRouteCount(start, end, 0, maxDistance);
	}

	private int calcRouteCount(Node start, Node end, int weight, int maxDistance) throws Exception
	{
		int routes = 0;
		if(this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) 
		{
			Edge edge = this.routeTable.get(start);
			while(null != edge) 
			{
				weight += edge.getWeight(); 
				if(weight <= maxDistance) 
				{
					if(edge.getEnd().equals(end)) 
					{
						routes++;
						routes += calcRouteCount(edge.getEnd(), end, weight, maxDistance);
						edge = edge.getNext();
						continue;
					}
					else 
					{
						routes += calcRouteCount(edge.getEnd(), end, weight, maxDistance);
						weight -= edge.getWeight();	
					}
				}
				else
				{
					weight -= edge.getWeight();
				}

				edge = edge.getNext();
			}
		}
		else
		{
			throw new Exception("NO SUCH ROUTE");
		}

		return routes;
	}	
}


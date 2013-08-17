package twhomework.train;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;

public class RouteTest {
	static Route graph;
	static Node a, b, c, d, e;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		graph = new Route(); 

		a = new Node("A");
		b = new Node("B");
		c = new Node("C");
		d = new Node("D");
		e = new Node("E");
	
		Edge a0 = new Edge(a, b, 5);
		Edge a1 = new Edge(a, d, 5);
		Edge a2 = new Edge(a, e, 7);
		a0.setNext(a1);
		a1.setNext(a2);
		
		Edge b0 = new Edge(b, c, 4);
		
		Edge c0 = new Edge(c, d, 8);
		Edge c1 = new Edge(c, e, 2);
		c0.setNext(c1);
		
		Edge d0 = new Edge(d, c, 8);
		Edge d1 = new Edge(d, e, 6);
		d0.setNext(d1);
		
		Edge e0 = new Edge(e, b, 3);
		
		graph.getRouteTable().put(a, a0);
		graph.getRouteTable().put(b, b0);
		graph.getRouteTable().put(c, c0);
		graph.getRouteTable().put(d, d0);
		graph.getRouteTable().put(e, e0);
	}

	//test1
	@Test
	public void testDistance_ABC() throws Exception {
		ArrayList<Node> route = new ArrayList<Node>(); 
		route.add(a);
		route.add(b);
		route.add(c);
		assertEquals(9, graph.distance(route));
	}
	
	//test2
	@Test
	public void testDistance_AD() throws Exception {
		ArrayList<Node> route = new ArrayList<Node>(); 
		route.add(a);
		route.add(d);
		assertEquals(5, graph.distance(route));
	}
	
	//test3
	@Test
	public void testDistance_ADC() throws Exception  {
		ArrayList<Node> route = new ArrayList<Node>(); 
		route.add(a);
		route.add(d);
		route.add(c);
		assertEquals(13, graph.distance(route));
	}

	//test4
	@Test
	public void testDistance_AEBCD() throws Exception  {
		ArrayList<Node> route = new ArrayList<Node>(); 
		route.add(a);
		route.add(e);
		route.add(b);
		route.add(c);
		route.add(d);
		assertEquals(22, graph.distance(route));
	}

	//test5
	@Test(expected=Exception.class)
	public void testDistance_AED() throws Exception  {
		ArrayList<Node> route = new ArrayList<Node>(); 
		route.add(a);
		route.add(e);
		route.add(d);
		assertEquals(-1, graph.distance(route));
	}

	//test6
	@Test
	public void testTripCount_CC3() throws Exception {
		int tripCount = graph.tripCount(c, c, 3);
		System.out.println(tripCount);
		assertEquals(2, tripCount);
	}

	//test7
	@Test
	public void testTripCount_AC4() throws Exception {
		int tripCount = graph.exactTripCount(a, c, 4);
		System.out.println(tripCount);
		assertEquals(3, tripCount);
	}

	//test8
	@Test
	public void testShortestRoute_AC() throws Exception {
		int shortestRoute = graph.shortestRoute(a, c);
		assertEquals(9, shortestRoute);
	}
	
	//test9
	@Test
	public void testShortestRoute_BB() throws Exception {
		int shortestRoute = graph.shortestRoute(b, b);
		assertEquals(9, shortestRoute);
	}

	//test10
	@Test
	public void testRouteCount_CC30() throws Exception {
		int count = graph.routeCount(c, c, 30);
		assertEquals(7, count);
	}

	//test11
	@Test
	public void testEquals() {
		Node a1 = new Node("A");
		Node a2 = new Node("A");
		Node b = new Node("B");

		assertEquals(true, a1.equals(a2));
		assertEquals(false, a1.equals(b));
		assertEquals(true, (new Node("Test").equals(new Node("Test"))));
	}
}

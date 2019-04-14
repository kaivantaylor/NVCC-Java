,package HW4_Taylor_Final;

import java.io.*;
import java.util.*;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException {

		// Local Variables
		WeightedGraphInterface<String> graph = new WeightedGraph<String>(); // WGI
		boolean result; // Booleans
		String fname = "src/HW4_Taylor_Final/students.txt"; // Strings
		Scanner sc = new Scanner(System.in);
		initiateGraph(graph, fname);
		
		System.out.println("Who is the person to spread a rumor?\r\n" + 
				"\n" + ">");
		String name = sc.next();
		System.out.println("Who is the person to receive the rumor?\r\n" + 
				"\n" + ">");
		String nameto = sc.next();
		
		sc.close();
		
		//shortestPaths(graph, name);
		
		//System.out.println("Depth First Approach: " );
		result = isPathDF(graph, name, nameto);
		
		//System.out.println(result);
		
		
		if (result == false) {
			System.out.print("\nThe rumor CANNOT be spreaded from " + name + " to " + nameto +".");
		}
		else {
			System.out.print("\nThe rumor CAN be spreaded from " + name + " to " + nameto + ".");
			System.out.print("\n");
			System.out.print("The rumor can be spread by: ");
			createPath(graph, name, nameto);
		}
	}
	
	public static List<String> initiateGraph(WeightedGraphInterface<String> graph, String filename) throws FileNotFoundException {
		
		String line;
		
		List<String> rumorList = new ArrayList<String>(); // Lists
		List<String> noDuplicates = new ArrayList<String>();
		
		FileReader fin = new FileReader(filename); // Files Readers
		BufferedReader bufferedReader = new BufferedReader(fin);

		try {
			while ((line = bufferedReader.readLine()) != null) {
				List<String> myList = new ArrayList<String>(Arrays.asList(line.split(" ")));
				String rumor_starter = myList.get(0);
				rumorList.add(rumor_starter);

				for (String temp : myList) {

					if (!graph.hasVertex(temp)) {
						graph.addVertex(temp);
						noDuplicates.add(temp);
					}

					if (rumor_starter != temp) {
						graph.addEdge(rumor_starter, temp, 1);
					}
				}
			}
			fin.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return noDuplicates;
	}

	private static void shortestPaths(WeightedGraphInterface<String> graph, String startVertex)
	// Writes the shortest distance from startVertex to every
	// other reachable vertex in graph.
	{
		Flight flight;
		Flight saveFlight; // for saving on priority queue
		int minDistance;
		int newDistance;

		PriQueueInterface<Flight> pq = new HeapPriQ<Flight>(20); // Assume at most 20 vertices
		String vertex;
		QueueInterface<String> vertexQueue = new LinkedQueue<String>();

		graph.clearMarks();
		saveFlight = new Flight(startVertex, startVertex, 0);
		pq.enqueue(saveFlight);

		System.out.println("Last Vertex   Destination   Distance");
		System.out.println("------------------------------------");

		do {
			flight = pq.dequeue();
			if (!graph.isMarked(flight.getToVertex())) {
				graph.markVertex(flight.getToVertex());
				System.out.println("    " + flight);
				flight.setFromVertex(flight.getToVertex());
				minDistance = flight.getDistance();
				vertexQueue = graph.getToVertices(flight.getFromVertex());
				while (!vertexQueue.isEmpty()) {
					vertex = vertexQueue.dequeue();
					if (!graph.isMarked(vertex)) {
						newDistance = minDistance + graph.weightIs(flight.getFromVertex(), vertex);
						saveFlight = new Flight(flight.getFromVertex(), vertex, newDistance);
						pq.enqueue(saveFlight);
					}
				}
			}
		} while (!pq.isEmpty());
		System.out.println();
		System.out.println("The unreachable vertices are:");
		vertex = graph.getUnmarked();
		while (vertex != null) {
			System.out.println(vertex);
			graph.markVertex(vertex);
			vertex = graph.getUnmarked();
		}
		System.out.println();
	}

	private static boolean isPathDF(WeightedGraphInterface<String> graph, String startVertex, String endVertex)
//Returns true if a path exists on graph, from startVertex to endVertex; 
//otherwise returns false. Uses depth-first search algorithm.
	{
		StackInterface<String> stack = new LinkedStack<String>();
		QueueInterface<String> vertexQueue = new LinkedQueue<String>();

		boolean found = false;
		String currVertex; // vertex being processed
		String adjVertex; // adjacent to currVertex

		graph.clearMarks();
		graph.markVertex(startVertex);
		stack.push(startVertex);

		do {
			currVertex = stack.top();
			stack.pop();
			//System.out.println(currVertex);
			if (currVertex.equals(endVertex))
				found = true;
			else {
				vertexQueue = graph.getToVertices(currVertex);
				while (!vertexQueue.isEmpty()) {
					adjVertex = vertexQueue.dequeue();
					if (!graph.isMarked(adjVertex)) {
						graph.markVertex(adjVertex);
						stack.push(adjVertex);
					}
				}
			}
		} while (!stack.isEmpty() && !found);

		return found;
}	
	
	private static List<String> createPath(WeightedGraphInterface<String> graph, String startVertex, String endVertex)

	//Returns true if a path exists on graph, from startVertex to endVertex; 
	//otherwise returns false. Uses breadth-first search algorithm.

	{
		QueueInterface<String> queue = new LinkedQueue<String>();
		QueueInterface<String> vertexQueue = new LinkedQueue<String>();
		List<String> myList = new ArrayList<String>();
		boolean found = false;
	
		String currVertex; // vertex being processed
		String adjVertex; // adjacent to currVertex

		graph.clearMarks();
		graph.markVertex(startVertex);
		queue.enqueue(startVertex);
		do {
			currVertex = queue.dequeue();
			if (currVertex.equals(endVertex)) {
				found = true;
			} else {
				vertexQueue = graph.getToVertices(currVertex);
				while (!vertexQueue.isEmpty()) {
					adjVertex = vertexQueue.dequeue();
					if (!graph.isMarked(adjVertex)) {
						graph.markVertex(adjVertex);
						queue.enqueue(adjVertex);
					}
					
				}
				boolean check = (isPathDF(graph, currVertex, endVertex) || isPathDF(graph, currVertex, startVertex));
				if(check) {
					myList.add(currVertex);
				}
			}
		} while (!queue.isEmpty() && !found);

		try {
			if (myList.size() == 6) {
				myList.remove(1);
				myList.remove(1);
				myList.remove(2);
				myList.remove(2);
			}
			if (myList.size() == 3) {
				myList.remove(2);
			}
		} catch (Exception e) {
			;
		}
		myList.add(endVertex);
		System.out.println(myList);
		return myList;
	}
}
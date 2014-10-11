/**
 * 
 * @author Mukul Gupta
 * Time complexity: O(E log V )
 * Using Java Priority Class
 * Sample input Format:
 * No of Vertices
 * No of Edges 
 * List of Edges u v w on each of the following lines
 * Source vertex
 * for example:
	9
	14
	0 1 4
	0 7 8
	1 7 11
	1 2 8
	2 8 2
	2 3 7
	2 5 4
	3 4 9
	3 5 14
	5 6 2
	5 4 10
	6 7 1
	7 8 7
	6 8 6
	0
	Output:
	Minimum Distance of all the nodes from source node
	0
	4
	12
	19
	21
	11
	9
	8
	14
	Graph will be something like this:
	http://d2o58evtke57tz.cloudfront.net/wp-content/uploads/Fig-11.jpg
 * 
 *
 */
public class Dijkstra {

	/**
	 * @param args
	 */
	private static int[] dist;
	private static int[] path;
	private static boolean[] visited;
	private static class Node{
		private int vertex;
		private int weight;
		public Node(int vertex,int weight){
			this.vertex = vertex;
			this.weight = weight;
		}
		private int getWeight(){
			return this.weight;
		}
		private int getVertex(){
			return this.vertex;
		}
	}
	private static class Graph{
		private int vertices;
		private int edges;
		private HashMap<Integer,LinkedList<Node>> adjList = new HashMap<Integer, LinkedList<Node>>();
		public Graph(int vertices,int edges){
			this.vertices = vertices;
			this.edges = edges;
			for(int i=0;i<vertices;i++){
				adjList.put(i, new LinkedList<Node>());
			}
		}
		
		private void addNode(int u, Node node){
			adjList.get(u).add(node);
		}
		private LinkedList<Node> getAdjacentVertices(int u){
			return adjList.get(u);
		}
		private int getVerticesCount(){
			return this.vertices;
		}
	}
	
	private static void calculateShortestDistance(Graph graph,int src){
		int V = graph.getVerticesCount();
		dist = new int[V];
		visited = new boolean[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(visited, false);
		path = new int[V];
		PriorityQueue<Node> queue = new PriorityQueue<Dijkstra.Node>(V, 
				new Comparator<Node>() {
			
				public int compare(Node o1, Node o2){
					return o1.getWeight() - o2.getWeight();
				}
		}
		);
		dist[src] = 0;
		path[src] = -1;
		queue.add(new Node(src,0));
		while(!queue.isEmpty()){
			Node u = queue.remove();
			LinkedList<Node> lis = graph.getAdjacentVertices(u.getVertex());
			Iterator<Node> itr = lis.iterator();
			while(itr.hasNext()){
				Node v = itr.next();
				if(!visited[v.getVertex()] && dist[u.getVertex()]+ v.getWeight() < dist[v.getVertex()]){
						dist[v.getVertex()] = dist[u.getVertex()] + v.getWeight();
						path[v.getVertex()] = u.getVertex();
						queue.add(new Node(v.getVertex(),dist[v.getVertex()]));	
				}
			}
			visited[u.getVertex()] = true;
		}
	}
	
	private static void printSolution(Graph graph,int src){
		System.out.println("Minimum Distance of all the nodes from source node");
		for(int i=0;i<graph.getVerticesCount();i++){
			System.out.println(dist[i]);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int vertices = Integer.parseInt(br.readLine());
		int edges = Integer.parseInt(br.readLine());
		Graph graph = new Graph(vertices, edges);
		for(int i=0;i<edges;i++){
			String[] str = br.readLine().split(" ");
			int u = Integer.parseInt(str[0]);
			int v = Integer.parseInt(str[1]);
			int w = Integer.parseInt(str[2]);
			graph.addNode(u, new Node(v, w));
			graph.addNode(v, new Node(u,w));
		}
		int src = Integer.parseInt(br.readLine());
		calculateShortestDistance(graph,src);
		printSolution(graph,src);
	}

}

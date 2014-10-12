import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author Mukul Gupta
 * Time complexity: O(n^2 * 2^n)
 *
 */
public class TravellingSalesman {

	/**
	 * @param args
	 */
	private static int noOfNodes;
	private static long[][] minWeightFunction;
	private static long[][] graph;
	private static int[][] path;
	
	public static long computeMinimumWeightPath(int start, int set){
		long result = Long.MAX_VALUE;
		if(minWeightFunction[start][set]!= -1){
			return minWeightFunction[start][set];
		}
		else{
			for(int i=0;i<noOfNodes;i++){
				int mask = (int) (((Math.pow(2, noOfNodes))-1) - (Math.pow(2,i)));
				int masked = set&mask;
				if(masked != set){
					long temp = computeMinimumWeightPath(i, masked) + graph[start][i];
					if(temp<result){
						result = temp;
						path[start][set] = i;
					}
				}
			}
			return minWeightFunction[start][set] = result;
		}

	}
	
	public static void printPath(int start, int set){
		if(path[start][set] == -1L){
			return;
		}
		int x = path[start][set];
		int mask =  ((((int) Math.pow(2, noOfNodes)) - 1) - ((int) Math.pow(2, x)));
		int masked = set & mask;
		System.out.println(x+" ");
		printPath(x, masked);
	}
	
 	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		noOfNodes = Integer.parseInt(br.readLine());
 		graph = new long[noOfNodes][noOfNodes];
 		for(int i=0;i<noOfNodes;i++){
 			String str = br.readLine();
 			int j=0;
 			for(String s : str.split(" ")){
 				graph[i][j++] = Long.parseLong(s);
 			}
 		}
 		minWeightFunction = new long[noOfNodes][1<<noOfNodes];
 		path = new int[noOfNodes][(int) ((Math.pow(2, noOfNodes)))];
 		for(int i=0;i<noOfNodes;i++){
 			for(int j=0;j<(int) ((Math.pow(2, noOfNodes)));j++){
 				minWeightFunction[i][j] = -1;
 				path[i][j] = -1;
 			}
 		}
 		for(int i=0;i<noOfNodes;i++){
 			minWeightFunction[i][0] = graph[i][0];
 		}
 		long result = computeMinimumWeightPath(0, (int) ((Math.pow(2, noOfNodes)) -2));
 		System.out.println("Shortest distance = "+result);
 		System.out.println("Shortest Path : \n" + "0" + " ");
 		printPath(0, (int) ((Math.pow(2, noOfNodes)) -2));
 		System.out.println("0");
 	}

}

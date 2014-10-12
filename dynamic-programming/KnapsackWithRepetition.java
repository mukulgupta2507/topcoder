import java.io.*;
/**
 * 
 * @author Mukul Gupta
 * Given n items with value[] and weight[] and a knapsack with weight W. Find the maximum value that can be obtained from the items.
 * Repetition of items is allowed. It's a 0-1 knapsack, you can either pick an item or not.
 * Time complexity: O(W*n)
 * Space Complexity: O(W)
 *
 */
public class KnapsackWithRepetition {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int W = Integer.parseInt(br.readLine());
		int[] value = new int[n];
		int[] weight = new int[n];
		String val = br.readLine();
		int i =0;
		for(String s : val.split(" ")){
			value[i++] = Integer.parseInt(s);
		}
		i = 0;
		String wt = br.readLine();
		for(String s : wt.split(" ")){
			weight[i++] = Integer.parseInt(s);
		}
		solveKnapsackDP(value,weight,n,W);
	}

	private static int solveKnapsackDP(int[] value, int[] weight, int n, int w) {
		int [] dp = new int[w+1];
		int[] prev = new int[w+1];
		dp[0] = 0;
		for(int i=1;i<=w;i++){
			int max=0;
			for(int j=0;j<n;j++){
				if(weight[j]<=i && max < dp[i-weight[j]]+value[j]){
					dp[i] = dp[i-weight[j]]+value[j];
					prev[i] = j;
				}
			}
		}
		
		int sum = w;
		while(sum > 0){
			int k = prev[sum];
			System.out.println("Item #: " + (k+1));
			sum = sum - weight[k];
		}
		return dp[w];
	}

}


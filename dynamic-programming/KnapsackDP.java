import java.io.*;
/**
 * 
 * @author Mukul Gupta
 * Given n items with value[] and weight[] and a knapsack with weight W. Find the maximum value that can be obtained from the items.
 * Repetition of items is not allowed. It's a 0-1 knapsack, you can either pick an item or not.
 * Time complexity: O(n*W)
 * Space Complexity: O(n*W)
 *
 */
public class KnapsackDP {

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
		int[][] dp = new int[n+1][w+1];
		
		for(int i=0;i<=n;i++){
			for(int j=0;j<=w;j++){
				if(i==0 || j==0){
					dp[i][j] = 0;
				}
				else if(weight[i-1] <= j){
					dp[i][j] = Math.max(dp[i-1][j-weight[i-1]]+value[i-1],dp[i-1][j]);
				}
				else{
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		int result = dp[n][w];
		System.out.println("Items contributing to maximum value");
		int i = n;
		int j = w;
		while(i>0 && j>0){
			if(dp[i][j] != dp[i-1][j]){
				System.out.println("Item #: "+ i);             // Items will be 1-based
				j = j - weight[i-1];
			}
			i--;
		}
		return result;
	}

}

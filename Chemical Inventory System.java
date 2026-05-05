
// Chemical Mangement System - Input and Ouput sample program

//import java.io.*;
import java.util.*;




public class ass1_comp3010
{
	static int [][] matrix ;
	
	static void Subarray(int[][] s,int gNum ,int start, int end, int size) {
		
		if(start == size) {
			return; 
		}	
		if(start < end) {
			insetIntoMatrix(s[gNum][start] ,s[gNum][end]);
			Subarray(s,gNum , start, end -1 ,size);
		}else {
			
			Subarray(s,gNum, start +1, size -1, size );
		}
	} 
	
	static void insetIntoMatrix(int a, int b) {
			matrix[b][a] = 1;
			matrix[a][b] = 1;
		
	}
	static int dfs(int start, boolean[] visited,int[] room, int rCount,int r) 
	{
		int label = 0;				
		// if we have traversed all nodes return
		if(start == visited.length)
			return label;

		// is the start have not been  visited 
		if(visited[start] != true) {
			
			int holder = rCount;
			if(holder >= r) {
				holder = rCount % r;				
			}	
			Stack<Integer> s = new Stack<Integer>();
			visited[start] = true;
			s.add(start);
			for(int i = start; i < matrix.length; i++) {
				if(matrix[start][i] == 0 && visited[i] == false) {
					if(matrix[s.peek()][i] == 0) {
						visited[i] = true;
						s.add(i);
					}			
				}
			}
			while(!s.empty()) 
			{
				int x = s.pop();
				if(rCount >= r) {
					label++;
				}
				// can two of them be in the same room
				room[x] = holder;
			}
			rCount ++;
		}
		return label += dfs(start + 1, visited,room,rCount ,r);
	}
	
	
	
	

    public static void main(String[] args) 
    {
		int g = 0; // number of groups of incompatible chemicals
		
		int n = 0; // number of chemicals
		
		int r = 0; // number of storage rooms
		
		int p = 0; // number of necessaryrequired labels to print

	
		Scanner keyboard  = new Scanner(System.in);
	
		System.out.println("Enter the number of chemicals to manage:  ");
		n = keyboard.nextInt();
		
		if (n < 1) {
		    System.out.println("Too few chemicals");
		    System.exit(0);
		}
	
		System.out.println("Enter the number of storage rooms: ");
		r = keyboard.nextInt();
	    
		System.out.println("Enter the number of groups of incompatible chemicals you must separate: ");
		g = keyboard.nextInt();
	
		if(g < 1){
		    System.out.println("Too few group");
		    System.exit(0);
		}
	
		int [] room = new int[n+1]; // room allocated for each chemical    
		
		int[][] s = new int[g][n]; // incompatible chemicals groups
		
		int [] size = new int[g];  // size of each group
		    
		System.out.println("Enter the list of incompatible chemicals of each group (one group per line, each terminated by 0): ");
	    
		for (int i = 0; i < g; i++) 
		{
		    int j = 0;

		    // save it in matrix
		    s[i][j] = keyboard.nextInt();;
		    while (s[i][j] != 0){
		    	s[i][++j] =  keyboard.nextInt();;
		    }
		    size[i] = j;
		}
		
		matrix = new int[n+1][n+1];
		
		for(int i = 0; i < g; i++) {
			Subarray(s,i , 0 , size[i] -1 , size[i]);
		}
		
		boolean[] visited = new boolean[n+ 1];
		p = dfs(1 , visited, room, 0, r);		
		p *= 2;

		// output
		System.out.println();
		System.out.println("The number of required labels and the different room allocations are: ");
		System.out.println(p);
		// loop from i to r = 2;
		for (int i = 0; i < r; i++) {
			// loop from 1 to n = chemicals = 3
		    for (int j=1; j <= n; j++)
		    	if (room[j] == i) 
		    		System.out.print(j + " ");
		    System.out.println("0");     
		}
	
    }
    
}


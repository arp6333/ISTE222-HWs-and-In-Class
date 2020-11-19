/**
* Ellie Parobek
* ISTE222.01S2
* Exercise 4 - Graph Algorithms
*/
public class Dijkstra { 
   
   static int[][] dijkstra(int graph[][], int source) { 
      int N = graph.length;
      // denotes shortest distance from source node to all other nodes
      int distances[][] = new int[N][2]; 
      // indicates if the node has been visited or not (defaults to false)
      boolean visited[] = new boolean[N];
   
      // initialize shortest distance to all nodes as "infinity" 
      for (int i = 0; i < N; i++)
         distances[i][0] = Integer.MAX_VALUE; 
    
      distances[source][0] = 0; // distance from source vertex to itself is 0
      distances[source][1] = source;
   
      // find shortest path to all nodes 
      for(int count = 0; count < N - 1; count++) { 
         // choose the minimum distance node from the set of nodes 
         // not yet visited 
         int min = Integer.MAX_VALUE;
         int minIndex = -1; 
      
         for(int i = 0; i < N; i++) {
            if(!visited[i] && distances[i][0] <= min) { 
               min = distances[i][0]; 
               minIndex = i; 
            } 
         }
      
         // mark the minimum distance node as visited
         visited[minIndex] = true;
         
         for(int i = 0; i < N; i++) {
            // Update distances only if node has not been visited, 
            // there is an edge from minimum distance node to node i,
            // and the total length of path from source to node i
            // via minimum distance node is smaller than current value
            // stored in distances 
            if(!visited[i] && (graph[minIndex][i] != 0) && (distances[minIndex][0] + graph[minIndex][i] < distances[i][0])) {           
               distances[i][0] = distances[minIndex][0] + graph[minIndex][i];
               distances[i][1] = minIndex;
            }          
         } 
      }  
      
      return distances;
   } 
   
   static void printDistances(int distances[][], char[] nodes) { 
      int N = distances.length;
      System.out.println("Node  Distance  Previous Node"); 
      
      for(int i = 0; i < N; i++) {
         System.out.println("  " + nodes[i] + "\t\t" + distances[i][0] + "\t\t\t\t" + nodes[distances[i][1]]); 
      }
   }
   
   private static void printPaths(int[][] distances, char[] parents, int startVertex) {
      int N = distances.length;
      for (int i = 0; i < N - 1; i++) {
         System.out.print("Shortest path from Node G to Node " + parents[i] + " is: ");
         int previous = i;
         while (previous != 6) {
            previous = distances[previous][1];
            System.out.print(parents[previous]);
            if (previous != 6){
               System.out.print(", ");
            }
         }
         System.out.println("");
      }
   }
  
   public static void main(String[] args) { 
      char[] nodeNames = {'A', 'B', 'C', 'D', 'E', 'F', 'G'}; // Nodes 0, 1, 2, 3, 4, 5, 6
      int graph[][] = new int[][] { { 0, 1, 0, 1, 0, 0, 0 }, 
                                    { 1, 0, 1, 0, 0, 0, 0 }, 
                                    { 0, 1, 0, 4, 0, 0, 5 }, 
                                    { 1, 0, 4, 0, 4, 6, 0 }, 
                                    { 0, 0, 0, 4, 0, 3, 0 }, 
                                    { 0, 0, 0, 6, 3, 0, 0 }, 
                                    { 0, 0, 5, 0, 0, 0, 0 } }; 
   
      int distances[][] = dijkstra(graph, 6);
      
      // print the graph[][]
      for (int[] x : graph) {
         for (int y : x){
            System.out.print(y + " ");
         }
         System.out.println();
      }
      
      // print the distances
      printDistances(distances, nodeNames);
      
      // print the paths
      printPaths(distances, nodeNames, 6); 
   } 
}
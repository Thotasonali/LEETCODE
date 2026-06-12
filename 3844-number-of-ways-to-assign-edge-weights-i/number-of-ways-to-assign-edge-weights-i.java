class Solution {
    public int assignEdgeWeights(int[][] edges) {
        //"lets find how depth of nodes"
        int n =edges.length+1;
        //"creat adjancy list bfs"
        List<List<Integer>> adj=new ArrayList<>();
        //" add ajdacents into"
        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());

        }
        // adding all onnections mkng tree
        for(int[] edge: edges){//"edge(1,2) add to empty[] as 1[ as 2 ] 2[as 1] so it makes connected"
            int u=edge[0];
            int v=edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        //apply bfs 
        //queue 
        Queue<Integer> qu=new LinkedList<>();
        boolean[] visited = new boolean[n + 1];//al nodes array as not visited
        qu.offer(1);
        visited[1]=true;//1st node r
        int maxDepth=0;

        // gng each lvls
        while (!qu.isEmpty()) {
            int size = qu.size(); 
            
            // Number of nodes at the current depth level
            for(int i=0;i<size;i++){
                int current=qu.poll();


                //travel alndzz in this level
                for(int getneig: adj.get(current)){
                    if(!visited[getneig]){
                        visited[getneig]=true;
                        qu.offer(getneig);

                    }
                }
            }
            if(!qu.isEmpty()){
                maxDepth++;
            }
            
        }
        // 4. Calculate the total combinations: (2^(maxDepth - 1)) % (10^9 + 7)
        long MOD = 1_000_000_007;
        long ans = 1;
        
        // Fast exponentiation loop to compute 2^(maxDepth - 1) % MOD safely
        for (int i = 0; i < maxDepth - 1; i++) {
            ans = (ans * 2) % MOD;
        }
        
        return (int) ans;
    }
}



        
  
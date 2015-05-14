import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/***
 * LIST OF HELPFUL THINGS
 * 1. lexicographic sorter
 * 2. some weird coin thing - ignore 
 * 3. union find
 * 4. fenwick tree
 * 5. segment tree
 * 6. sieve of erastothenes (sp?)
 * 7. polynomial evaluation
 * 8. pseudo code for LIS
 * 9. factorials table
 * 10. dfs on graph stored as adj matrix
 * 11. bfs on graph stored as adj matrix
 * 12. topological sort
 * 13. articulation points (didn't check this at all) lecture 11 p 4 ==> articulation points/bridges
 * 14. mergesort
 * 15. n choose k (big decimal + big integer)
 * 16. suffix tree
 * @author dannakelmer
 *
 */

public class play {
	
	
	
    static class LexicoComp implements Comparator<ArrayList<Integer>> {
		public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
		    int m = Math.min(a.size(),b.size());
		    for (int i = 0; i < m; ++i)
		    {
			int av = a.get(i), bv = b.get(i);
			if (av != bv) return av < bv ? -1 : 1;
		    }
		    if (a.size() != b.size()) 
			return a.size() < b.size() ? -1 : 1;
		    return 0;
		}
    }

//==============================================================================//    
		
    static double[][] cache;

    static double solve(int runLength, int tossesLeft) {	
		if (runLength == 7) return 1;
		if (tossesLeft + runLength < 7) return 0;
		if (cache[runLength][tossesLeft] > -1) 
		    return cache[runLength][tossesLeft];
		return cache[runLength][tossesLeft] = (solve(runLength+1,tossesLeft-1)+solve(1,tossesLeft-1))/2.0;
    }
    
//==============================================================================//    
   
    static class UnionFind { // OOP style private: 
		private int[] p;
		private int[] rank;
		private int disjoint;
		UnionFind(int N) { 
			p = new int[N];
			rank = new int[N];
			for (int i = 0; i < N; i++) {
				p[i] = i; 
			}
			disjoint = N;
		}
		
		int findSet(int i) { 
			return (p[i] == i) ? i : (p[i] = findSet(p[i])); 
		}
		
		boolean isSameSet(int i, int j) { 
			return findSet(i) == findSet(j); 
		}
		
		void unionSet(int i, int j) {
			if (!isSameSet(i, j)) { // if from different set 
				int x = findSet(i); 
				int y = findSet(j);
				if (rank[x] > rank[y]) {
					p[y] = x; // rank keeps the tree short 
				}
				else { 
					p[x] = y;
					if (rank[x] == rank[y]) {
						rank[y]++; }
				}
				disjoint--;
			} 
		} 
		
		int numDisjointSets() {
			return disjoint;
		}
	}

  //==============================================================================//    

    static class FenwickTree {
		
		int[] ft;
		
		public FenwickTree(int n) {
			ft = new int[n+1];
		}
		
		public int rangeSum(int b) {
			int sum = 0;
			for (; b > 0; b -= LSOne(b)) {
				sum += ft[b];
			}
			return sum;
		}
		
		public int rangeSum(int a, int b) {
			return rangeSum(b) - (a == 1 ? 0 : rangeSum(a - 1)); 
		}
		
		void adjust(int k, int v) { // note: n = ft.size() - 1
			for (; k < ft.length; k += LSOne(k)) {
				ft[k] += v; }
		}
	}

  //==============================================================================//    

    static class SegmentTree {
    	public ArrayList<Integer> list;
    	public int[] st;

    	public SegmentTree(ArrayList<Integer> list) {
    		this.list = list;
    		this.st = new int[4 * list.size()];
    		build(0, list.size() - 1, 0);
    	}

    	// Initialize: build(0, list.size() - 1, 0);
    	public void build(int nL, int nR, int n) {
    		if (nL == nR)
    			st[n] = nL;
    		else {
    			int mid = (nL + nR) / 2, l = left(n), r = right(n);
    			build(nL, mid, l);
    			build(mid + 1, nR, r);
    			st[n] = list.get(st[l]) <= list.get(st[r]) ? st[l] : st[r];
    		}
    	}

    	// Index of left child
    	int left(int n) {
    		return 2 * n + 1;
    	}

    	// Index of right child
    	int right(int n) {
    		return left(n) + 1;
    	}

    	public void update(int pos, int value) {
    		update(pos, value, 0, list.size() - 1, 0);
    	}

    	// Update segment tree at given position with given value.
    	// Current node n has index range [nL,nR]
    	public void update(int pos, int value, int nL, int nR, int n) {
    		if (nL == nR) {
    			list.set(pos, value);
    			st[n] = pos;
    		} else {
    			int mid = (nL + nR) / 2, l = left(n), r = right(n);
    			if (pos <= mid)
    				update(pos, value, nL, mid, l);
    			else
    				update(pos, value, mid + 1, nR, r);
    			st[n] = list.get(st[l]) <= list.get(st[r]) ? st[l] : st[r];
    		}
    	}

    	public int minQuery(int L, int R) {
    		return minQuery(L, R, 0, list.size() - 1, 0);
    	}

    	// Get index of minimum value in index range [L,R]
    	// Current node n has index range [nL,nR]
    	public int minQuery(int L, int R, int nL, int nR, int n) {
    		if (L <= nL && nR <= R)
    			return st[n];
    		int lMin = -1, rMin = -1;
    		int mid = (nL + nR) / 2;
    		if (L <= mid)
    			lMin = minQuery(L, R, nL, mid, left(n));
    		if (mid + 1 <= R)
    			rMin = minQuery(L, R, mid + 1, nR, right(n));
    		if (lMin == -1 || rMin == -1)
    			return lMin == -1 ? rMin : lMin;
    		return list.get(lMin) <= list.get(rMin) ? lMin : rMin;
    	}
    }
    
    static int LSOne(int a) {
		return a & (-a);
	}

    
  //==============================================================================//    

    //create sieve of erastothenes of size n (boolean)
    static boolean[] createSieve(int n) {
		boolean[] primes=new boolean[n];
	    Arrays.fill(primes,true);        // assume all integers are prime.
	    primes[0]=primes[1]=false;       // we know 0 and 1 are not prime.
	    for (int i=2;i<primes.length;i++) {
	        //if the number is prime, 
	        //then go through all its multiples and make their values false.
	        if(primes[i]) {
	            for (int j=2;i*j<primes.length;j++) {
	                primes[i*j]=false;
	            }
	        }
	    }
	    return primes;
	}
    
    
  //==============================================================================//    
   
    public static double polyEval(List<Double> coefficients, double x) {
        //Collections.reverse(coefficients); //need coefficients to be in reverse order ax^3 + bx^2 + cx + d 
    									//should be given in order d, c, b, a
        double accumulator = coefficients.get(coefficients.size()-1);
        for (int i = coefficients.size()-2; i >= 0; i--) {
              accumulator = (accumulator * x) + (Double) coefficients.get(i);       
        }
        return accumulator;
    }
    
    //==============================================================================//    

    //Longest Increasing Subsequence pseudo code
    // given array of numbers
    //create s[i] = max {s[j] + 1 if a[i] > a[j] for j = 1,..,i-1; 1}
    
 //==============================================================================//    
 
    static BigInteger[] fillFactorials(int n) {
		BigInteger[] factorials = new BigInteger[n];
		factorials[0] = BigInteger.ONE;
		for(int i = 1; i < factorials.length; i++) {
			factorials[i] = factorials[i-1].multiply(BigInteger.valueOf(i));
		}
		return factorials;
	}
    
    
 //==============================================================================//    
  
    static int INIT = 0;
    static int PROCESSING = 1; 
    static int FINISHED = 2;
    static void dfs(ArrayList<Integer>[] adj, int vert, int[] state) { //starting at given vertex
    	if(state[vert] != INIT) {
    		return;
    	}
    	state[vert] = PROCESSING;
    	for(int i = 0; i < adj[vert].size(); i++) {
    		dfs(adj, adj[vert].get(i), state);
    	}
    	state[vert] = FINISHED;
    }
    
    static void dfs(ArrayList<Integer>[] adj) {
    	int[] state = new int[adj.length];
    	for(int i = 0; i < state.length; i++) {
    		if(state[i] == INIT) {
    			dfs(adj, i, state);
    		}
    	}
    }

    
//==============================================================================//   
    
    static void bfs(ArrayList<Integer>[] adj, int vert, int[] state) {
    	Queue<Integer> q = new PriorityQueue<Integer>();
    	q.add(vert);
    	while(!q.isEmpty()) {
    		int v = q.poll();
    		for(int i = 0; i < adj[v].size(); i++) {
    			int w = adj[v].get(i);
    			if(state[w] == INIT) {
    				q.add(w);
    				state[w] = PROCESSING;
    			}
    		}
    		state[v] = FINISHED;
    	}
    }
    
    static void bfs(ArrayList<Integer>[] adj) {
    	int[] state = new int[adj.length];
    	for(int i = 0; i < state.length; i++) {
    		if(state[i] == INIT) {
    			bfs(adj, i, state);
    		}
    	}
    }

  //==============================================================================//   

    
    static void dfs(ArrayList<Integer>[] adj, int vert, int[] state, ArrayList<Integer> list) { //starting at given vertex
    	if(state[vert] != INIT) {
    		return;
    	}
    	state[vert] = PROCESSING;
    	for(int i = 0; i < adj[vert].size(); i++) {
    		dfs(adj, adj[vert].get(i), state);
    	}
    	state[vert] = FINISHED;
    	list.add(vert);
    }
    static ArrayList<Integer> topologicalSort(ArrayList<Integer>[] adj, int vert, int[] state) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	for(int i = 0; i < state.length; i++) {
    		if(state[i] == INIT) {
    			dfs(adj, i, state, list);
    		}
    	}
    	Collections.reverse(list);
    	return list;
    }
//==============================================================================//   
    
    //C CODE FOR THIS
//    void articulationPointAndBridge(int u) {
//    	  dfs_low[u] = dfs_num[u] = dfsNumberCounter++; // dfs_low[u] <= dfs_num[u] 
//    	  for (int j = 0; j < (int)AdjList[u].size(); j++) {
//    	    ii v = AdjList[u][j];
//    	    if (dfs_num[v.first] == UNVISITED) {  // a tree edge
//    	      dfs_parent[v.first] = u;
//    	      if (u == dfsRoot)
//    	        rootChildren++;  // special case
//    	      articulationPointAndBridge(v.first);
//    	      if (dfs_low[v.first] >= dfs_num[u])  // for articulation point
//    	        articulation_vertex[u] = true;  // store this information first
//    	      if (dfs_low[v.first] > dfs_num[u])  // for bridge
//    	        printf(" Edge (%d, %d) is a bridge\n", u, v.first);
//    	      dfs_low[u] = min(dfs_low[u], dfs_low[v.first]); // update dfs_low[u] 
//    	    }
//    	    else if (v.first != dfs_parent[u]) // a back edge and not direct cycle
//    	    dfs_low[u] = min(dfs_low[u], dfs_num[v.first]); // update dfs_low[u]
//    	  }
//    	}
    
    //==============================================================================//   
    
    private static int[] numbers;
	  private static int[] helper;

	  private static int number;
	  
	  private static long numSwaps = 0;

	  public static void sort(int[] values) {
	    numbers = values;
	    number = values.length;
	    helper = new int[number];
	    mergesort(0, number - 1);
	  }

	  private static void mergesort(int low, int high) {
	    // check if low is smaller then high, if not then the array is sorted
	    if (low < high) {
	      // Get the index of the element which is in the middle
	      int middle = low + (high - low) / 2;
	      // Sort the left side of the array
	      mergesort(low, middle);
	      // Sort the right side of the array
	      mergesort(middle + 1, high);
	      // Combine them both
	      merge(low, middle, high);
	    }
	  }

	  private static void merge(int low, int middle, int high) {

	    // Copy both parts into the helper array
	    for (int i = low; i <= high; i++) {
	      helper[i] = numbers[i];
	    }

	    int i = low;
	    int j = middle + 1;
	    int k = low;
	    // Copy the smallest values from either the left or the right side back
	    // to the original array
	    //numSwaps = 0;
	    while (i <= middle && j <= high) {
	      if (helper[i] <= helper[j]) {
	        numbers[k] = helper[i];
	        i++;
	      } else {
	        numbers[k] = helper[j];
	        numSwaps += (j-k);
	        j++;
	      }
	      k++;
	    }
	    // Copy the rest of the left side of the array into the target array
	    while (i <= middle) {
	      numbers[k] = helper[i];
	      k++;
	      i++;
	    } 

	  }
	  
//==============================================================================//   
	  
	  static BigDecimal nChoosek(int n, int k) {
		//n choose k = n!/((n-k)!k!

		  
		BigInteger[] nfactorial = fillFactorials(n);
		
		if(k > n) return BigDecimal.ZERO;
		if(k < 0) return BigDecimal.ZERO;
		if(k == 0) return BigDecimal.ONE;
		
		BigInteger nfact = nfactorial[n];
		BigInteger kfact = nfactorial[k];
		BigInteger nkfact = nfactorial[n-k];
		
		BigInteger denom = nkfact.multiply(kfact);
		BigDecimal res = new BigDecimal(nfact.divide(denom));
		return res;
	 }
	  
//==============================================================================//   
  
	 
	  public class SuffixTree
	  {
	  	private static final int INF = Integer.MAX_VALUE-1;
	  	private int last;
	  	private String str;
	  	private Node root, sentinel;

	  	/**
	  	 * Edge of the SuffixTree.  
	  	 */
	  	public class Edge
	  	{
	  		/** An inclusive range [a,b] of indices into str representing the edge label. */
	  		public int a, b;
	  		/** Node where the edge points to */
	  		public Node end;
	  		public Edge(int a, int b, Node end) { this.a = a; this.b = b; this.end = end; }
	  		/** Returns the first character on the edge. */
	  		public char getFirst() { return str.charAt(a); }
	  		/** Length of the string on the edge label. */
	  		public int length() { return Math.min(last,b) - a + 1; } 
	  		@Override
	  		public String toString() { return str.substring(a, Math.min(b,last)+1); }
	  	}
	  	/**
	  	 * Node of the SuffixTree.
	  	 */
	  	public class Node implements Iterable<Edge>
	  	{
	  		/** Maps first letters to edges leading to children */
	  		public TreeMap<Character,Edge> edges = new TreeMap<Character,Edge>();
	  		/** Suffix link used in linear tree building algorithm */
	  		private Node suffix;
	  		/** An inclusive range [a,b] of indices into str representing a prefix leading to this node */
	  		public int a, b;
	  		/** Adds an edge to the map */
	  		public void add(Edge e) { edges.put(e.getFirst(),e); }
	  		/** Gets the edge using the given first character */
	  		public Edge get(char c) { return edges.get(c); }
	  		/** Returns the number of children */
	  		public int numChildren() { return edges.size(); }
	  		@Override
	  		public Iterator<Edge> iterator() { return edges.values().iterator(); }
	  		public String toString() { return str.substring(a,Math.min(b,last)+1); }
	  	}
	  	public SuffixTree(String str)
	  	{
	  		this.str = str;
	  		buildTree();
	  		setPrefix(root,null,0);
	  	}
	  	@Override
	  	/** Pretty prints the tree.  For each node it prints the prefix leading to that node in quotes.
	  	 * Then it prints each edge leaving the node indexed by first letter, the edge label, the inclusive range of indices,
	  	 * and the length of the label.
	  	 */
	  	public String toString() { return prettyFormat(new StringBuilder(), root, new StringBuilder()).toString(); }
	  	private int fix(int x) { return x == INF ? last : x; }
	  	private StringBuilder prettyFormat(StringBuilder sb, Node n, StringBuilder tab)
	  	{
	  		sb.append(tab).append('"').append(n).append('"').append('\n');
	  		for (Edge e : n)
	  		{
	  			char c = e.getFirst();
	  			sb.append(tab).append(c).append(" : ").append(e).append(" = ").append(e.a).append(',').append(fix(e.b)).append(',').append(e.length()).append('\n');
	  			tab.append("  ");
	  			prettyFormat(sb,e.end,tab);
	  			tab.delete(tab.length()-2, tab.length());
	  		}
	  		return sb;
	  	}
	  	/** Returns the string used to make the suffix tree */
	  	public String getStr() { return str; }
	  	/** Returns the root node */
	  	public Node getRoot() { return root; }
	  	private void setPrefix(Node n, Edge e, int len)
	  	{
	  		for (Edge edge : n) setPrefix(edge.end,edge,len+Math.min(edge.b,last)-edge.a+1);
	  		if (e == null) { n.a = 0; n.b = -1; }
	  		else { n.b = Math.min(e.b,last); n.a = n.b - len+1; }
	  	}
	  	private void buildTree()
	  	{
	  		root = new Node();
	  		sentinel = new Node();
	  		root.suffix = sentinel;
	  		Node s = root;
	  		int[] k = {0};
	  		last = -1;
	  		for (int i = 0; i < str.length(); ++i)
	  		{
	  			last++;
	  			s = update(s,k,i);
	  			s = canonize(s,k,i);
	  		}
	  	}
	  	private Node update(Node s, int[] k, int i)
	  	{
	  		Node oldr = root, r = testAndSplit(s,k[0],i-1,str.charAt(i));
	  		while (r != null)
	  		{
	  			Node rp = new Node();
	  			Edge e = new Edge(i,INF,rp);
	  			r.add(e);
	  			if (oldr != root) oldr.suffix = r;
	  			oldr = r;
	  			s = canonize(s.suffix,k,i-1);
	  			r = testAndSplit(s,k[0],i-1,str.charAt(i));
	  		}
	  		if (oldr != root) oldr.suffix = s;
	  		return s;
	  	}
	  	private Node testAndSplit(Node s, int k, int p, char c)
	  	{
	  		if (k > p) return s == sentinel ? null : s.get(c) == null ? s : null;
	  		Edge e = s.get(str.charAt(k));
	  		if (c == str.charAt(e.a+p-k+1)) return null; //check if char after implicit node is c
	  		Node r = new Node();
	  		Edge re = new Edge(e.a+p-k+1,e.b,e.end);
	  		r.add(re);
	  		Edge se = new Edge(e.a,e.a+p-k,r);
	  		s.add(se);
	  		return r;
	  	}
	  	private Node canonize(Node s, int[] k, int p)
	  	{
	  		if (p < k[0]) return s;
	  		if (s == sentinel) { s = root; k[0]++; if (p < k[0]) return s; }
	  		Edge e = s.get(str.charAt(k[0]));
	  		while (e.b - e.a <= p - k[0])
	  		{
	  			k[0] = k[0] + e.b - e.a + 1;
	  			s = e.end;
	  			if (k[0] <= p) e = s.get(str.charAt(k[0]));
	  		}
	  		return s;
	  	}
	  	/** Can be used to test the suffix tree on a given string given as a cmdline argument. */
//	  	public static void main(String[] args) 
//	  	{
//	  		System.out.println(new SuffixTree(args[0]));
//	  	}
	}

}

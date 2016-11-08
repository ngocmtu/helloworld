package src;

import java.util.Stack;
import java.util.StringTokenizer;

public class TributaryTree {
	public TributaryNode triHeader;
	public int count;
	
	public TributaryTree(TributaryNode triHeader){
		this.triHeader = triHeader;
	}
	
	public int getNumTree(){
		return count;
	}
	
	public TributaryTree makeTree(String input){
		//create a stack to create a binary tree
		Stack  s = new Stack();
		
		TributaryNode preTri;
		preTri = triHeader;
		
		Tuple headerTuple = new Tuple(0,preTri);
		s.push(headerTuple);
		//this while loop does the following
		//@run through the river in the header location
		//@build the river structure
		//
		//this uses the algorithm convert forest to BT
		
		count = 0;//this int keeps track of how many tributaries there are in the location
		
		StringTokenizer st = new StringTokenizer(input," ");
		while(st.hasMoreTokens()){
			count++;
			String next = st.nextToken();
			int level = Integer.parseInt(next.substring(0,1)); //turn the first character in tributary name into level
			TributaryNode tri = new TributaryNode(); //create the next tributary node
			tri.setRight(null);
			tri.setLeft(null);
			tri.setName(next.substring(1)); //get the name of the stream
			tri.setLevel(level); //get the level of the stream
			
			Tuple cur = new Tuple(level, tri); //create tuple holding the river and its level
			
			 Tuple pre ;
			 pre = (Tuple) s.peek(); //look at the value of the first item on top of stack
			 
			 //If level of cur greater than pre, then set the left link
			 //of pre to point to cur
			 if(cur.getLevel() > pre.getLevel()){
				 
				 //Rule: insert node to the LEFT of current node
				 tri.setRight(preTri); //right link points to the immediate preceding node
				 tri.setRightBool(true); //making it a thread
				 tri.setLeft(preTri.getLeft());
				 tri.setLeftBool(preTri.getLeftBool());
				 preTri.setLeft(tri);
				 preTri.setLeftBool(false);
				 preTri = preTri.getLeft(); //set new tributary node to equal precede node
				 
				 cur = new Tuple(level,tri); //update the cur tuple with the new tributary node 
				 s.push(cur);
				 
			 } else {
				 while(pre.getLevel() > cur.getLevel())
					 pre = (Tuple) s.pop();
				 if(pre.getLevel() < cur.getLevel())
					 System.out.println("Error");
				 else {
					 //Rule: insert new node to the RIGHT of current node
					 tri.setLeft(preTri); //right link points to the immediate preceding node, making it a thread
					 tri.setLeftBool(true); //left tag is true => it's a thread relationship
					 tri.setRight(preTri.getRight());
					 tri.setRightBool(preTri.getRightBool());
					 preTri.setRight(tri);
					 preTri.setRightBool(false);
					 preTri = tri; //set new tributary node to equal precede node
					 cur = new Tuple(level,tri);
					 s.push(cur);
				 }
			 }
		}
		TributaryTree tree = new TributaryTree(triHeader);
		return tree;
	}
}

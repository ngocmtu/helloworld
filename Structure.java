package src;

import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Structure {
	public LocationNode location;
	public TributaryNode tributary;
	
	public Structure(){
		
	}
	
	public void makeStructure(String line){
		//create the header for Location Node
		LocationNode locationHeader = new LocationNode();
		locationHeader.setRight(locationHeader);
		locationHeader.setLeft(null);
		locationHeader.setName("Location Header");
		locationHeader.setNum(0);
		LocationNode preLoc;
		preLoc = locationHeader;
		StringTokenizer st = new StringTokenizer(line, "$$$$$");
		
		while(st.hasMoreTokens()){
			String location = st.nextToken(); //location name and all rivers in it
			
			//@build the location node
			//@separate the location string into the location name and all the rivers within it
			LocationNode locationNode = new LocationNode();
			StringTokenizer st1 = new StringTokenizer(location, " ");
			try{
				String locationName = st1.nextToken(); //location name
				System.out.println("Location "+locationName);
				locationNode.setName(locationName);	
				LocationNode curLoc;
				
				//create a stack to create a binary tree
				Stack  s = new Stack();
				
				//This tributary node serves as the tributary header
				//for the river structure below
				TributaryNode triHeader = new TributaryNode();
				triHeader.setRight(triHeader);
				triHeader.setRightBool(false);
				triHeader.setLeft(triHeader);
				triHeader.setLeftBool(true); //this is a thread
				triHeader.setName("Head");
				triHeader.setLevel(0);
				TributaryNode preTri;
				preTri = triHeader;
				
				locationNode.setLeft(triHeader); //set the location node to point to the tributary header
				
				Tuple headerTuple = new Tuple(0,triHeader);
				s.push(headerTuple);
				//this while loop does the following
				//@run through the river in the header location
				//@build the river structure
				//
				//this uses the algorithm convert forest to BT
				
				int count = 0;//this int keeps track of how many tributaries there are in the location
				
				while (st1.hasMoreTokens()){
					String next = st1.nextToken();
					count++;
					try{
						 int level = Integer.parseInt(next.substring(0,1)); //turn the first character in tributary name into level
						 TributaryNode tri = new TributaryNode(); //create the next tributary node
						 tri.setRight(null);
						 tri.setRightBool(false);
						 tri.setLeft(null);
						 tri.setLeftBool(false);
						 tri.setName(next.substring(1)); //get the name of the stream
						 tri.setLevel(level); //get the level of the stream
						 Tuple cur = new Tuple(level, tri); //create tuple holding the river and its level
						 
						 Tuple pre ;
						 pre = (Tuple) s.peek(); //look at the value of the first item on top of stack
						 //set left  to 
						 
						 //If level of cur greater than pre, then set the left link
						 //of pre to point to cur
						 if(cur.getLevel() > pre.getLevel()){
							 
							 //Rule: insert node to the LEFT of current node
							 tri.setRight(preTri); //right link points to the immediate preceding node
							 tri.setRightBool(true);
							 tri.setLeft(preTri.getLeft());
							 System.out.println("1. Left thread of preTri "+preTri.getLeft().getName());
							 tri.setLeftBool(preTri.getLeftBool());
							 preTri.setLeft(tri);
							 preTri.setLeftBool(false);
							 preTri = preTri.getLeft(); //set new tributary node to equal precede node
							 System.out.println("2. Normal left link of preTri "+preTri.getName());
							 
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
								 System.out.println("3. Right thread of preTri "+preTri.getRight().getName());
								 preTri.setRight(tri);
								 preTri.setRightBool(false);
								 preTri = preTri.getRight(); //set new tributary node to equal precede node
								 System.out.println("4. Normal right link of preTri "+preTri.getName());
								 cur = new Tuple(level,tri);
								 s.push(cur);
							 }
						 }
					} catch(NoSuchElementException e){
						System.out.println("End of string");
					}
				}
				
				//complete other fields in the location node
				locationNode.setNum(count);
				locationNode.setRight(locationHeader);
				
				//Push the pointers of LocationNode pre to cur so that the
				//newly added node points back to the LocationHeader
				//and the previously final node points to the newly added node
				curLoc = locationNode;
				
				System.out.println("Header of "+locationNode.getName()+" is "+locationNode.getLeft().getName());
				try{
					preLoc.setRight(curLoc);
					preLoc = preLoc.getRight();
				} catch(NullPointerException e){
					System.err.println();
				}
				//////////////////////////////////////////////////////
			} catch(NoSuchElementException e){
				System.err.println();
			}
		}
	}
}

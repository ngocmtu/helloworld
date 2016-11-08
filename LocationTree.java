package src;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class LocationTree {
	public LocationNode locHeader;
	
	public LocationTree(LocationNode locHeader){
		this.locHeader = locHeader;
	}
	
	public LocationTree makeTree(String input){
		//input is the string of the entire file
		
		LocationNode preLoc;
		preLoc = locHeader;
		
		//this st breaks the entire file into long strings of location and rivers in it
		StringTokenizer st = new StringTokenizer(input, "$$$$$");
		
		//while the file still returns location and river information, keeps making location nodes
		//and add them to looped linked list
		while(st.hasMoreTokens()){
			LocationNode loc = new LocationNode();
			StringTokenizer st1 = new StringTokenizer(st.nextToken(), " ");
			String locName = st1.nextToken();
			loc.setName(locName);//return location name in the long string of input
			loc.setRight(locHeader);

			//create a string out of all the rivers in this location
			String concat = "";
			while(st1.hasMoreTokens()){
				concat += " "+st1.nextToken();
			}
			
			//make tributary node header
			//this tributary node header is to the left of the newly created location node
			//it is the start of a tributary tree under the location node
			TributaryNode triHeader = new TributaryNode();
			triHeader.setRight(triHeader);
			triHeader.setRightBool(false);
			triHeader.setLeft(triHeader);
			triHeader.setLeftBool(true); //this is a thread
			triHeader.setName("Head");
			triHeader.setLevel(0);
			
			//make tributary tree attached to the new location node
			TributaryTree triTree = new TributaryTree(triHeader);
			triTree.makeTree(concat);
			loc.setLeft(triHeader);
			loc.setNum(triTree.getNumTree());
			
			preLoc.setRight(loc);
			preLoc = preLoc.getRight();
		}
		
		LocationTree tree = new LocationTree(locHeader);
		return tree;
	}
	
	public LocationNode getLocationHeader(){
		return locHeader;
	}
}

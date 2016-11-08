package src;


public class LocationNode implements LocationInterface{
	int i;
	TributaryNode left;
	LocationNode right;
	String s;
	
	public TributaryNode getLeft(){
		return left;
	}
	
	public void setLeft(TributaryNode left){
		this.left = left;
	}
	
	public LocationNode getRight(){
		return right;
	}
	
	public void setRight(LocationNode right){
		this.right = right;
	}
	
	public String getName(){
		return s;
	}
	
	public void setName(String s){
		this.s = s;
	}
	
	public int getNum(){
		return i;
	}
	
	public void setNum(int i){
		this.i = i;
	}
}

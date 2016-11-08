package src;


public class TributaryNode implements TributaryInterface, Comparable<TributaryNode>{
	Boolean b1, b2;
	TributaryNode left, right;
	String name;
	int level, i;
	
	public boolean getLeftBool(){
		return b1;
	}
	public void setLeftBool(boolean b1){
		this.b1 = b1;
	}
	public boolean getRightBool(){
		return b2;
	}
	public void setRightBool(boolean b2){
		this.b2 = b2;
	}
	public void setLeft(TributaryNode left){
		this.left =  left;
	}
	public TributaryNode getLeft(){
		return left;
	}
	public void setRight(TributaryNode right){
		this.right = right;
	}
	public TributaryNode getRight(){
		return right;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setLevel(int level){
		this.level = level;
	}
	public int getLevel(){
		return level;
	}
	public int compareTo(TributaryNode node){
		return i;
	}
}

package src;

public class Tuple<level,triNode> {
	public int level;
	public TributaryNode triNode;
	public Tuple(int level, TributaryNode triNode){
		this.level = level;
		this.triNode = triNode;
	}
	public int getLevel(){
		return level;
	}
	public TributaryNode getNode(){
		return triNode;
	}
}

package src;


public interface TributaryInterface {
	public boolean getLeftBool();
	public void setLeftBool(boolean b1);
	public boolean getRightBool();
	public void setRightBool(boolean b2);
	public void setLeft(TributaryNode left);
	public TributaryNode getLeft();
	public void setRight(TributaryNode right);
	public TributaryNode getRight();
	public String getName();
	public void setName(String name);
	public int getLevel();
	public void setLevel(int level);
}

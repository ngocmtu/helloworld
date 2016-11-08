package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.AbstractQueue;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.awt.event.ActionListener;
import java.util.Queue;

import javax.swing.*;

///////////////////////////////NGOC TU LAB 2 DATA STRUCTURE//////////////////////////////////
//Working functions
//1. Display in preorder and inorder
//2. Add a new stream
//3. Rename a stream
//4. Show immediate tributaries
//5. Show all tributates

public class Lab2 implements ActionListener {
	JFrame openFrame = new JFrame("Lab 2");
	JFrame inputFrame = new JFrame("Lab 2");
	JFrame addFrame = new JFrame("Add A Stream");
	JFrame delFrame = new JFrame("Delete A Stream");
	JFrame reFrame = new JFrame("Rename A Stream");
	
	//panels on the first frame
	JPanel openjp = new JPanel();
	JPanel openjp1 = new JPanel();
	JPanel openjp2 = new JPanel();
	
	//panels on the second frame
	JPanel inputjp = new JPanel();
	JPanel inputjp1 = new JPanel();
	JPanel inputjp2 = new JPanel();
	JPanel inputjp3 = new JPanel();
	JPanel inputjp4 = new JPanel();
	JPanel inputjp5 = new JPanel();
	
	//panels in the add frame
	JPanel addjp = new JPanel();
	JPanel addjp2 = new JPanel();
	JPanel addjp3 = new JPanel();
	
	JPanel deljp = new JPanel();
	
	JPanel rejp = new JPanel();
	JPanel rejp2 = new JPanel();
	JPanel rejp3 = new JPanel();
	
	JTextArea jta = new JTextArea(20,50);
	JScrollPane scroll = new JScrollPane(jta);
	
	//open frame labels
	JLabel jl = new JLabel("Water Tributary System");
	JLabel jl1 = new JLabel("Choose data file");
	JLabel jl2 = new JLabel("Water Tributary System");
	JLabel jl3 = new JLabel("Enter Parent Stream Name");
	JLabel jl4 = new JLabel("Enter Stream Name of Deletion");
	JLabel jl5 = new JLabel("Display");
	JLabel jl6 = new JLabel("Find tributary (Insert name and choose function)");
	JLabel jl7 = new JLabel("Enter New Stream Name");
	JLabel jl8 = new JLabel("Enter Stream Name to be Replaced");
	JLabel jl9 = new JLabel("Enter New Stream Name");
	
	
	
	JButton choose = new JButton("Choose file");
	JButton add = new JButton("Add");
	JButton delete = new JButton("Delete");
	JButton rename  = new JButton("Rename");
	JButton addDone = new JButton("Done");
	JButton delDone = new JButton("Done");
	JButton reDone = new JButton("Done");
	
	JComboBox displayBox = new JComboBox();
	JComboBox flowBox = new JComboBox();
	JComboBox childBox = new JComboBox();
	
	JTextField jtf = new JTextField(30);
	JTextField jtf2 = new JTextField(30);
	JTextField jtf3 = new JTextField(15);
	JTextField jtf4 = new JTextField(30);
	JTextField jtf5 = new JTextField(30);
	JTextField jtf6 = new JTextField(30);
	
	LocationTree tree;
	
	public Lab2() throws IOException {
		initiate();
	}
	
	public void initiate(){
		openFrame.setVisible(false);
		openFrame.setSize(500,200);
		openFrame.add(openjp);
			openjp.setLayout(new BorderLayout());
			openjp.add(openjp1, BorderLayout.NORTH);
				openjp1.add(jl);
					jl.setFont(new Font("Serif", Font.BOLD, 40));
			openjp.add(choose, BorderLayout.SOUTH);
				choose.addActionListener(this);
			openjp.add(openjp2, BorderLayout.CENTER);
				openjp2.add(jl1);
					jl1.setFont(new Font("Serif", Font.BOLD, 25));
					
		inputFrame.setVisible(false);
		inputFrame.setSize(700,480);
		inputFrame.add(inputjp);
			inputjp.setLayout(new BorderLayout());
			inputjp.add(inputjp1, BorderLayout.NORTH);
				inputjp1.add(jl2);
					jl2.setFont(new Font("Serif", Font.BOLD, 40));
			inputjp.add(inputjp2, BorderLayout.CENTER);
				inputjp2.add(scroll);
			inputjp.add(inputjp4, BorderLayout.SOUTH);
				inputjp4.setLayout(new BorderLayout());
				inputjp4.add(inputjp3, BorderLayout.NORTH);
					inputjp3.add(add); add.setSize(80,25); add.addActionListener(this);
					inputjp3.add(delete); delete.setSize(80,25); delete.addActionListener(this);
					inputjp3.add(rename); rename.setSize(80,25); rename.addActionListener(this);
					inputjp3.add(jl5); 
					inputjp3.add(displayBox); displayBox.addActionListener(this);
						displayBox.addItem(" ");
						displayBox.addItem("Normal Preorder");
						displayBox.addItem("Normal Inorder");
				inputjp4.add(inputjp5);
					inputjp5.add(jl6);
					inputjp5.add(jtf3);
					inputjp5.add(flowBox); flowBox.addActionListener(this);
						flowBox.addItem(" ");
						flowBox.addItem("Immediate Tributary");
						flowBox.addItem("All Tributary");
						flowBox.addItem("Trace sources");
				
		addFrame.setVisible(false);
		addFrame.setSize(400,175);
		addFrame.add(addjp);
			addjp.setLayout(new BorderLayout());
			addjp.add(addjp2, BorderLayout.CENTER);
				addjp2.add(jl3);
				addjp2.add(jtf);
				addjp2.add(jl7);
				addjp2.add(jtf4);
			addjp.add(addjp3, BorderLayout.SOUTH);
			addjp3.add(addDone);
				addDone.addActionListener(this);
			addjp3.add(childBox);
				childBox.addItem(" ");
				childBox.addItem("As 1st child");
				childBox.addItem("As 2nd child");
				childBox.addItem("As 3rd child");
				childBox.addItem("As 4th child");
		
		delFrame.setVisible(false);
		delFrame.setSize(200,100);
		delFrame.add(deljp);
			deljp.setLayout(new BorderLayout());
			deljp.add(jl4, BorderLayout.NORTH);
			deljp.add(jtf2, BorderLayout.CENTER);
			deljp.add(delDone, BorderLayout.SOUTH);
				delDone.addActionListener(this);
			
		reFrame.setVisible(false);
		reFrame.setSize(400,175);
		reFrame.add(rejp);
			rejp.setLayout(new BorderLayout());
			rejp.add(rejp2, BorderLayout.CENTER);
				addjp2.add(jl8);
				addjp2.add(jtf5);
				addjp2.add(jl9);
				addjp2.add(jtf6);
			rejp.add(rejp3, BorderLayout.SOUTH);
			rejp3.add(reDone);
				reDone.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == choose){
			JFileChooser chooser = new JFileChooser();
			int result = chooser.showOpenDialog(openFrame);
			
			//select File
			//from file, use read(input) to return text string from file
			//
			//make location head node to start a new location tree
			if(result == JFileChooser.APPROVE_OPTION){
				File input = chooser.getSelectedFile();
				LocationNode locHead = new LocationNode();
				locHead.setLeft(null);
				locHead.setName("Location Head");
				locHead.setRight(locHead);
				locHead.setNum(0);
				tree = new LocationTree(locHead);
				tree.makeTree(read(input));
			}
			openFrame.setVisible(false);
			inputFrame.setVisible(true);
			
		} else if (e.getSource() == displayBox){
			
			//This action performs different action depending on the item selected in the combobox
			//@Preorder displays the tree in preorder
			//@Reverse preorder displays the tree in reverse order
			//@Level order displays in level order
			
			if(String.valueOf(displayBox.getSelectedItem()).equals("Normal Preorder")){
				LocationNode locNode = tree.getLocationHeader();
				locNode = locNode.getRight();
				while(locNode != tree.getLocationHeader()){
					jta.append("-----------------------------------------------------------"+"\n"+"LOCATION "+locNode.getName()+"\n");
					preorder(locNode.getLeft());
					locNode = locNode.getRight();
					jta.append("\n");
				}
			} else if(String.valueOf(displayBox.getSelectedItem()).equals("Normal Inorder")){
				LocationNode locNode = tree.getLocationHeader();
				locNode = locNode.getRight();
				while(locNode != tree.getLocationHeader()){
					jta.append("-----------------------------------------------------------"+"\n"+"LOCATION "+locNode.getName()+"\n");
					inorder(locNode.getLeft());
					locNode = locNode.getRight();
					jta.append("\n");
				}
			}
			
		} else if (e.getSource() == flowBox){
			//This action either shows the flow of water or shows the sources that flow into
			//a water, depending on whichever option the user chooses
			String search = jtf3.getText().trim();
			
			//The if statement draws the rivers that flow after the searched river
			//It returns all the river behind the searched node in the preorder string
			if(String.valueOf(flowBox.getSelectedItem()).equals("All Tributary")){
				LocationNode locNode = tree.getLocationHeader();
				locNode = locNode.getRight();
				while(locNode != tree.getLocationHeader()){
					TributaryNode tri = locNode.getLeft(); //header node of this tributary tree
					try{
						if(!search(search,tri).getName().equals("")){
							TributaryNode found = search(search,tri); //retrieve the node with the river name
							if(found.getLeftBool() == false){
								while(found.getRightBool() == false){
									jta.append(found.getRight().getName()+"\n");
									found = found.getRight();
								}
							} else {
								jta.append("");
								jta.append(found.getName());
							}
						}
						break; 
					} catch(NullPointerException e1){
						jta.setText("");
						jta.append("This river is not in the system");
					}
					locNode = locNode.getRight();
				}
			} else if(String.valueOf(flowBox.getSelectedItem()).equals("Immediate Tributary")){
				LocationNode locNode = tree.getLocationHeader();
				locNode = locNode.getRight();
				while(locNode != tree.getLocationHeader()){
					TributaryNode tri = locNode.getLeft(); //header node of this tributary tree
					try{
						if(!search(search,tri).getName().equals("")){
							TributaryNode found = search(search,tri); //retrieve the node with the river name
							if(found.getLeftBool() == true){
								while(found.getRightBool() == false){
									jta.append(found.getRight().getName()+"\n");
									found = found.getRight();
								}
							}
						}
						break;
					} catch (NullPointerException e2){
						jta.setText("");
						jta.append("This river is not in the system"+"\n");
					}
				}
				locNode = locNode.getRight();
			}
			
		} else if (e.getSource() == add){
			addFrame.setVisible(true); //display the add frame so user can log in the information of the new river
		} else if (e.getSource() == delete){
			delFrame.setVisible(true); //display the delete frame so user can specify which river they want to delete
			
		} else if (e.getSource() == addDone){
			String parent = jtf.getText();
			String child = jtf4.getText();
			if(parent.equals("") || (child.equals(""))){
				jtf.setText("Please type in parent name");
				jtf4.setText("Please type in child name");
			}
			
			//////////////////////////////////TRIBUTARY INSERTION METHOD////////////////////////////
			//This method takes in the name of the parent and insert the new river underneath
			//Currently the method only allows to add as the last child
			LocationNode locNode = tree.getLocationHeader();
			locNode = locNode.getRight();
			TributaryNode newChild = new TributaryNode();
			newChild.setName(child);
			while(locNode != tree.getLocationHeader()){
				TributaryNode tri = locNode.getLeft(); //header node of this tributary tree
					if(!search(parent,tri).getName().equals("")){
						TributaryNode found = search(parent,tri);
						
						if(childBox.getSelectedIndex() == 0){
							jtf4.setText("Please select the kth descendant");
						} else if(childBox.getSelectedIndex() == 1){
							insert(found,newChild,1);
						} else if(childBox.getSelectedIndex() == 2){
							insert(found,newChild,2);
						} else if(childBox.getSelectedIndex() == 3){
							insert(found,newChild,3);
						} else if(childBox.getSelectedIndex() == 4){
							insert(found,newChild,2);
						}  else
							jta.append("Program currently does not support that many children" + "\n"+
						"Through the code does allow to have more than 4 children"+"\n"+
						"The programmer did have enough time to allow the user to choose the kth descendant"+"\n"+
						"Sorry for the inconvenience!");
					}
				locNode = locNode.getRight();
			}
			addFrame.setVisible(false);
			
			//////////////////////////DELETION/////////////////////////////
		} else if (e.getSource() == delDone){
			String delete = jtf2.getText();
			LocationNode locNode = tree.getLocationHeader();
			locNode = locNode.getRight();
			TributaryNode tobeDeleted = new TributaryNode();
			tobeDeleted.setName(delete);
			while(locNode != tree.getLocationHeader()){
				TributaryNode tri = locNode.getLeft(); //header node of this tributary tree
				try{
					if(!search(delete,tri).getName().equals("")){
						tobeDeleted = search(delete,tri);
					}
				}
				catch(NullPointerException e4){
					jta.append("The river name does not exist");
				}
			locNode = locNode.getRight();
			}
			delFrame.setVisible(false);
		} else if (e.getSource() == rename){
			reFrame.setVisible(true);
		} else if (e.getSource() == reDone){
			String old = jtf5.getText();
			String n = jtf6.getText();
			LocationNode locNode = tree.getLocationHeader();
			locNode = locNode.getRight();
			while(locNode != tree.getLocationHeader()){
				TributaryNode tri = locNode.getLeft(); //header node of this tributary tree
				try{
					if(!search(old,tri).getName().equals("")){
						search(old,tri).setName(n);;
					}
				}
				catch(NullPointerException e4){
					jta.append("The river name does not exist");
				}
			locNode = locNode.getRight();
			}
			reFrame.setVisible(false);
		}
	}
	
	//The read(File) method takes in the file, takes in String of each line
	//then concat them all to a big string concat with which we can use to build the tree
	public String read(File file){
		EasyReader ez = new EasyReader(file.getAbsolutePath());
		String line;
		String concat = "";
		while(!ez.eof()){
			try{
				line = ez.readLine().replaceAll("\\s","");
				concat += " "+line;
			} catch(NullPointerException e){
				System.out.println("End of file");
			}
		}
		return concat;
	}
	
	////////////////////EXTRA FEATURE!!!!!!!!!!!!/////////////////////
	///////INORDER TRAVERSAL////////
	//The following two methods make up the inorder traversal algorithm
	//
	//inorder(TriNode) takes in the header node and draws the tree
	//
	//inorderSuccessor(TriNode) returns the inorder successor of the supplied node
	public void inorder(TributaryNode node){
		TributaryNode temp = new TributaryNode();
		temp = node;
		do{
			temp = inorderSuccessor(temp);
		} while(temp != node);
	}
	
	public TributaryNode inorderSuccessor(TributaryNode node){
		TributaryNode successorNode = node.getRight();
		if(node.getRightBool() == false){
			while(successorNode.getLeftBool() == false){
				successorNode = successorNode.getLeft();
			}
			return successorNode;
		} else {
		return successorNode;
		}
	}
	
	///////PREORDER TRAVERSAL////////
	//The following two methods make up the preorder traversal algorithm
	//
	//preorder(TriNode) takes in the header node and draws the tree
	//
	//preorderSuccessor(TriNode) returns the preorder successor of the supplied node
	public void preorder(TributaryNode node){
		TributaryNode temp = new TributaryNode();
		temp = node;
		do{
			jta.append(temp.getName()+"\n");
			temp = preorderSuccessor(temp);
		} while(temp != node);
	}
	
	public TributaryNode preorderSuccessor(TributaryNode node){
		TributaryNode successorNode;
		if(node.getLeftBool() == false)
			return node.getLeft();
		else 
		{
			successorNode = node;
			while(successorNode.getRightBool() == true){
				successorNode = successorNode.getRight();
			}
			return successorNode.getRight();
		} 
	}
	
	///////LEVEL ORDER TRAVERSAL////////
	//This method takes in the root node of the tree and returns node order in each level of tree
	public void levelOrder(TributaryNode node){
		TributaryNode triNode;
		Queue<TributaryNode> q = new PriorityQueue<TributaryNode>();
		node.setRightBool(true);
		q.add(node);
		while(q.size() > 0){
			triNode =  q.remove();
			System.out.println("Level "+triNode.getName());
			
			if(triNode.getRightBool() == false )
				q.add(triNode.getRight());
			if(triNode.getLeftBool() == false )
				q.add(triNode.getLeft());
		} 
	}
	
	///////////////SEARCH METHOD////////////////////////
	//This methods takes in a string and a header node
	//The recursive call will run until it matches
	private TributaryNode search(String search, TributaryNode node){
		if(node.getName().equals(search))
			return node;
		else {
			TributaryNode found = new TributaryNode();
			if(node.getLeftBool() == false){
				found = search(search, node.getLeft());
			} else if(node.getRightBool() == false){
				found = search(search, node.getRight());
			} 
			return found;
		} 
	}
	
	///////////////////////CHILD INSERTION ALGORITHM///////////////////////////
	//This method takes in the name of the parent and insert the new river to the right
	//Depending on the child order, this would insert the new node to a new position
	
	public void insert(TributaryNode original, TributaryNode insert, int count){
		if(count == 1){
			insert.setLeft(original.getLeft());
			insert.setLeftBool(original.getLeftBool());
			insert.setRight(original);
			insert.setRightBool(true);
			original.setLeft(insert);
			original.setLeftBool(false);
			if(insert.getLeftBool() == false){
				TributaryNode temp = insert.getLeft();
				temp.setRight(insert);
				temp.setRightBool(true);
				}
		} else {
			if(original.getLeftBool() == false) { 
				original = original.getLeft();
				count = count - 2;
				while(count > 0){
					original = original.getRight();
					count--;
				}
				insert.setRight(original.getRight());
				insert.setRightBool(original.getRightBool());
				insert.setLeft(original);
				insert.setLeftBool(true);
				original.setRight(insert);
				original.setRightBool(false);
				if(insert.getRightBool() == false){
					TributaryNode temp = inorderSuccessor(insert);
					temp.setLeft(insert);
					temp.setLeftBool(true);
				}
			}
		}
	}

	//////////////////////////////////DELETION ALGORITHM///////////////////////
	//This method replaces the deleted method with the immediate one to the right or to the left
	//In case it has both right and left descendant, 
	/*private TributaryNode delete(TributaryNode delete){
		if(delete.getLeftBool() == true)
			return delete.getRight();
		else if(delete.getRightBool() == true)
			return delete.getLeft();
		else {
			TributaryNode temp = delete.getRight();
			if(temp.getLeftBool() == false)
				TributaryNode TributaryNode hey = inorderSuccessor(delete);
				while(temp.getLeftBool() == false){
					
				}
		}
	}
	*/
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab2 window = new Lab2();
					window.openFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

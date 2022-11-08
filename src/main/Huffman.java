package main;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.Gui;
import lib.*;
import lib.List_To_Tree;
import struct.TreeNode;

public class Huffman {
	
	//public  static TreeNode frequency_Tree;
	public static Reader reader = new Reader();	
	public static final File default_file = new File("src/LetterCountAscending.txt");	//default file for frequency table
	public static  TreeNode default_frequency_Tree;
	public static List<TreeNode> default_node_List;	
	
	
	public static List<TreeNode> node_List;	
	public static  TreeNode currTree;
	public static File currFile;
	static List_To_Tree makeTree = new List_To_Tree();

	// Start of application 
	
	
	public static void main(String[] args)  {

		
		currFile = default_file;
		
		// Get list of nodes from file
		// dataset in file must be as follows:
		// first element is character
		// second element integer value representing frequency
		// nodes contains Char_Set objects -  Char_Set(char character, int frequency)
		// IMPORTANT: elements must be separated by TAB			
		try {
			node_List = reader.file_to_List(currFile);
			default_node_List = new ArrayList<>(node_List);
			default_frequency_Tree  = makeTree.list_To_Tree(default_node_List);
			currTree = default_frequency_Tree;
		} catch (IOException e1) {
			System.out.println("Cant open default file");
		}
		
		
		
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			System.out.println("cant set Look and Feel\n"+e);
		}
		
		 new Gui();
		
	}
	
}

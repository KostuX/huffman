package gui;

import lib.Char_Set;
import lib.Coder;
import lib.List_To_Tree;
import lib.TreeNode;
import main.Huffman;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;

public class Gui extends JFrame implements ActionListener{
    
	String about_txt = "g";


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea txt_Area;	
	JTextArea bit_Area;	
	JTextArea info_Area;
	
	JButton decode;
	
	JPanel btn_Panel;
	JPanel outerPanel;
	JPanel innerPanel;
	public static Container contentPane;
	JMenuItem about_Menu;
	JMenuItem import_Menu_Text;
	JMenuItem import_Menu_Code;
	JMenuItem export_Menu_Text;
	JMenuItem export_Menu_Code;
	JMenuItem export_dataset;
	JMenuItem choose_dataset;
	JMenuItem dataset_export;
	JMenuItem dataset_export_bit;
	JMenuItem dataset_display_dataset;
	JRadioButton default_dataset;
	JRadioButton custom_dataset;
	JRadioButton generate_dataset;
	
	
	
	Coder coder = new Coder();
	
	public Gui() {

		contentPane = getContentPane();
		outerPanel = new JPanel(new BorderLayout());
		innerPanel = new JPanel();
		btn_Panel = new JPanel();
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu import_menu = new JMenu("Import",true);
		import_Menu_Text = new JMenuItem("Import Text");
		import_Menu_Code = new JMenuItem("Import Code");
		import_Menu_Text.addActionListener(this);
		import_Menu_Code.addActionListener(this);
		import_menu.add(import_Menu_Text);
		import_menu.add(import_Menu_Code);
		
		JMenu export_menu = new JMenu("Export",true);
		export_Menu_Text = new JMenuItem("Export Text");
		export_Menu_Code = new JMenuItem("Export Code");
		export_Menu_Text.addActionListener(this);
		export_Menu_Code.addActionListener(this);
		export_menu.add(export_Menu_Text);
		export_menu.add(export_Menu_Code);
		
		
		
		JMenu dataset_menu = new JMenu("DataSet",true);
		JMenu dataset_Choose_subMenu =  new JMenu("Choose Dataset");
		JMenu dataset_Exp_subMenu =  new JMenu("Export Dataset");
		
		dataset_display_dataset = new JMenuItem("Display dataset");
		dataset_display_dataset.addActionListener(this);
		
		
		default_dataset = new JRadioButton("Use Default",true);		
		custom_dataset = new JRadioButton("Choose File");		
		generate_dataset = new JRadioButton("Generate From Text");
		
		default_dataset.addActionListener(this);
		custom_dataset.addActionListener(this);
		generate_dataset.addActionListener(this);
		
		dataset_export = new JMenuItem("Export dataset");
		dataset_export_bit = new JMenuItem("Export dataset with bitcode");
		
		ButtonGroup dataset_group = new ButtonGroup();
		dataset_group.add(default_dataset);
		dataset_group.add(custom_dataset);
		dataset_group.add(generate_dataset);
		
		dataset_Exp_subMenu.add(dataset_export);
		dataset_Exp_subMenu.add(dataset_export_bit);
		dataset_export.addActionListener(this);
		dataset_export_bit.addActionListener(this);
		 
		dataset_Choose_subMenu.add(default_dataset);
		dataset_Choose_subMenu.add(custom_dataset);
		dataset_Choose_subMenu.add(generate_dataset);
		
		
		dataset_menu.add(dataset_Choose_subMenu);
		dataset_menu.add(dataset_Exp_subMenu);
		dataset_menu.add(dataset_display_dataset);
		
		
		
		
		
		//help menu ?
		//JMenu about_menu = new JMenu("About",true);		
		about_Menu = new JMenuItem("About");		
		about_Menu.addActionListener(this);
		
		
		menuBar.add(import_menu);
		menuBar.add(export_menu);
		menuBar.add(dataset_menu);
		menuBar.add(about_Menu);
		
		
		setJMenuBar(menuBar);
		
		
		txt_Area = new JTextArea();
		txt_Area.setPreferredSize(new Dimension(250, 150));
		txt_Area.setText("HelloWorld");
		txt_Area.setLineWrap(true);
		txt_Area.setWrapStyleWord(true);
		
		
	
		
		
		bit_Area = new JTextArea();
		bit_Area.setPreferredSize(new Dimension(250, 150));
		bit_Area.setText("...");
		bit_Area.setLineWrap(true);
		bit_Area.setWrapStyleWord(true);
		
		info_Area = new JTextArea();
		info_Area.setPreferredSize(new Dimension(250, 100));
		info_Area.setEditable(false);
		info_Area.setBackground(getForeground());
		info_Area.setText("Summary..");
		

        //JScrollPane scrollPane_txt = new JScrollPane(txt_Area);
        //JScrollPane scrollPane_bit = new JScrollPane(bit_Area);
		
 
		
		JLabel title = new JLabel();
		
		ImageIcon iconLogo = new ImageIcon("src/Img/Logo.png");
		//ImageIcon icon = new ImageIcon(image); 
		//JLabel thumb = new JLabel();
		title.setIcon(iconLogo);
		
		
		JButton encode = new JButton("encode");
		encode.setFocusable(false);
		encode.addActionListener(
				(e)->{
					String str = txt_Area.getText().trim();	
					String unsuportedCharString = Huffman.reader.isCharOnList(str.toUpperCase());
					if(unsuportedCharString.length() > 0 ) { 
						JOptionPane.showMessageDialog(contentPane, "Some characters are not on frequency data set. "
								+ "										\nThese characters cannot be encoded\n" 
								+" Unsupported characters: "+ unsuportedCharString);
							}
						bit_Area.setText(coder.encode(str));
						info_Area.setText(Huffman.reader.prepareStats(str, coder.encode(str),unsuportedCharString ));
						
						}
				);
		JButton decode = new JButton("decode");
		decode.setFocusable(false);
		decode.addActionListener(
				(e)->{
				String str = bit_Area.getText();
					txt_Area.setText(coder.decode(str));
//TODO : add info summary
					}
				);
		
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Img/Ico.png"));
		
		
		btn_Panel.add(encode);
		btn_Panel.add(decode);
		
		
		innerPanel.add(title);
		innerPanel.add(txt_Area);
		innerPanel.add(btn_Panel);
		innerPanel.add(bit_Area);
		innerPanel.add(info_Area);
		
		outerPanel.add(innerPanel);
		contentPane.add(outerPanel);
			
		this.setTitle("Huffman");
		this.setSize(300, 650);
		this.setLocation(500, 200);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(about_Menu)) {
			String msg ="Huffman Code"
					+ "\nThis application is created as part of assesment for "
					+ "\n'Data Structures and Algorithms' module "
					+ "at TU Dublin"
					+ "\nAuthor: B00148740 Konstantinas A."
					+ "\nTutor: Dr. Simon McLoughlin"
					+ "\n2022";
			
			JOptionPane.showMessageDialog(contentPane,msg);
		}
		
		
		if(e.getSource().equals(import_Menu_Text)) {
			String string = "";
			
			string = 	Huffman.reader.file_to_String();			
			txt_Area.setText(string);
			}
		
		if(e.getSource().equals(import_Menu_Code)) {
		String string = "";
			
			string = 	Huffman.reader.file_to_String();			
			bit_Area.setText(string);
			}
		if(e.getSource().equals(export_Menu_Text)) {
			Huffman.reader.string_to_file(txt_Area.getText());
			}
		if(e.getSource().equals(export_Menu_Code)) {
			Huffman.reader.string_to_file(bit_Area.getText());
			}
		
		
		//TODO: test
	
		if(e.getSource().equals(default_dataset)) {
			
			Huffman.currTree = Huffman.default_frequency_Tree;
			Huffman.node_List =  new ArrayList<>(Huffman.default_node_List) ;
		}
		
		
		
		if(e.getSource().equals(custom_dataset)) {
			
			
		File file =	Huffman.reader.fileChooser();
		
		
			List_To_Tree makeTree = new List_To_Tree();
			List<TreeNode> node_List = null;
			
			try {
				Huffman.node_List = Huffman.reader.file_to_List(file);
				
				

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Cant open file");
				
			}
			
			TreeNode frequency_Tree = makeTree.list_To_Tree(Huffman.node_List);	
		
			Huffman.currTree = frequency_Tree;
			
			
			}
		
		
		
		
		if(e.getSource().equals(generate_dataset)) {
			String str = txt_Area.getText();
			String charString = "";
			int frequency = 1;
			
			
			List_To_Tree makeTree = new List_To_Tree();
			List<Char_Set> char_Sets = new ArrayList<>();
			List<TreeNode> nodeList = new ArrayList<>();
			
			
			
			for (int i = 0; i < str.length(); i++) {
				
				if(!charString.contains(str.charAt(i)+"")) {
					charString = charString + str.charAt(i);
					char_Sets.add(new Char_Set(str.charAt(i),frequency ));
				}	
				
				else {					
					for (Char_Set char_Set : char_Sets) {
						if (char_Set.getCharacter() == str.charAt(i)) {
							char_Set.setFrequency(char_Set.getFrequency()+1); 
						}
					}
				}
			}
			
			for (Char_Set char_Set : char_Sets) {
			 nodeList.add( new TreeNode(char_Set));
			}
				
			
				
			TreeNode text_tree  = makeTree.list_To_Tree(nodeList);
		//nodeList.forEach(a->System.out.println(a.getItem().getCharacter() + " "+a.getItem().getFrequency() ) );
		
		
		
		Huffman.node_List = nodeList;
		Huffman.currTree = text_tree;
		
		}
		
		//TODO: alot
		
		if(e.getSource().equals(dataset_export)) {
			String out = "";
	
			for (TreeNode node : Huffman.node_List) {
						
				out = out + node.getItem().getCharacter() + "\t" + node.getItem().getFrequency()+"\n";
				
			}
			//System.out.println(out);
			Huffman.reader.string_to_file(out);
			}
		
		if(e.getSource().equals(dataset_export_bit)) {
			
			
				coder.char_To_bitcode(Huffman.currTree, "", ' ');
			String out = "";
			
			for (TreeNode node : Huffman.node_List) {
				out = out + node.getItem().getCharacter() + "\t" + node.getItem().getFrequency()+ "\t" + node.getItem().getBitcode()+"\n";
			}

			Huffman.reader.string_to_file(out);

			}
		if(e.getSource().equals(dataset_display_dataset)) {
			
			coder.char_To_bitcode(Huffman.currTree, "", ' ');
			String out = "Char | Frequency | Bitcode\n";
			
			for (TreeNode node : Huffman.node_List) {
				out = out + node.getItem().getCharacter() + "   |   " + node.getItem().getFrequency()+ "   |   " + node.getItem().getBitcode()+"\n";
			}
			
			JOptionPane.showMessageDialog(contentPane,out);
		}
		
		
		
	}

	
	

}

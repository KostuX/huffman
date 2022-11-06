package lib;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.w3c.dom.Document;

import gui.Gui;
import main.Huffman;

public class Reader {
	
	
public	static List<Character> charList = new ArrayList<Character>();
 
 
public void string_to_file(String str) {

	 File file = null;
	 String absolutePath = "";
	 
	 FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
	  JFileChooser fileChooser = new JFileChooser();
	  fileChooser.setFileFilter(extensionFilter);	  
	  fileChooser.setDialogTitle("Specify a file to save");
	  
	  
	  try { 
	  int userSelection = fileChooser.showSaveDialog(Gui.contentPane);
	  
	  if (userSelection == JFileChooser.APPROVE_OPTION) {
		  file = fileChooser.getSelectedFile(); 
		  absolutePath = fileChooser.getSelectedFile().getAbsolutePath(); 
		  if (!absolutePath.substring(absolutePath.lastIndexOf(".")+1).equals("txt"))     
			     absolutePath += ".txt";
			
	 }
	  
		 FileWriter myWriter = new FileWriter(absolutePath, false); 
		 //BufferedWriter bw = new BufferedWriter(myWriter); 
		  myWriter.write(str);
		//  bw.newLine();
		  
		  myWriter.close();
		  
		  } catch (IOException e) {
			  System.out.println("Log: write file - no file selected");
			  }
		 
 }
		  
public String file_to_String(){
	 
		File file = fileChooser();
	     String str = null;
	     
	     if(file != null) {
	try {
		return str = Files.readString(file.toPath());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("Log: open file - no file selected");
		str = "error opening file";
	}
	     }
	 return str;
 }
 
  public File fileChooser() {
	 
	 File file;   
	
	JFileChooser fileChooser = new JFileChooser();	
	FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
	fileChooser.setFileFilter(extensionFilter);
	int optionVal=fileChooser.showOpenDialog(Gui.contentPane);
	if (optionVal == JFileChooser.APPROVE_OPTION) {
	file = fileChooser.getSelectedFile();

	} else {
		file=null;
	System.out.println("No File Found"); 
	}
	

	 return file;
 }
 
	
public  List<TreeNode> file_to_List(File file) throws IOException
    {
		List<TreeNode> nodeList = new ArrayList<>();

		charList.clear();
        BufferedReader br;
        String str;
	try {
	    br = new BufferedReader(new FileReader(file));
	    
	    
	    while((str = br.readLine())!= null)
	    {
	    	 String s[] = str.split("\t");	    	 
	    	 nodeList.add( new TreeNode(new Char_Set(s[0].charAt(0), Integer.parseInt(s[1]))) );	    
	    	 charList.add(s[0].charAt(0));
	    }
	} catch (FileNotFoundException e) { e.printStackTrace();}
 
	return nodeList;	  

}
		
public boolean isCharOnList(char ch) {
		
		for (Character character : charList) {
			if(character == ch)return true;
		}
		return false;
	}
	
public String isCharOnList(String str) {
	
	String unsuportedChar = "";	
	  String charString ="";
	  
	   for (char character : charList) { 
		   charString = charString + character; 
		   }
	  
	  for (int i = 0; i < str.length(); i++) {
	  
	  if(!charString.contains(str.charAt(i)+"")){ 
		  unsuportedChar = unsuportedChar + str.charAt(i);
		//  charString = charString+str.charAt(i);
		//  if(str.charAt(i) == 32) {unsuportedChar = unsuportedChar+ "space";}
		  }  
	  }
		return unsuportedChar;
	}

public String prepareStats(String str, String bit,String unsupported) {
	String statString = "";
	int bitPerChar = 7;
	
	String string = unsupported.replaceAll("space", "");
	int unspBits = string.length() * bitPerChar;
	int str_length = str.length();
	int str_bits = str_length * bitPerChar;
	int bit_length = bit.length() + unspBits - unsupported.length();
	float compression_rate = (float)str_bits / (float)bit_length;
	
	
	String unsuportedChar = "";	
	  
	
	  for (int i = 0; i < unsupported.length(); i++) {
	  if(!unsuportedChar.contains(unsupported.charAt(i)+"")){ 
		  unsuportedChar = unsuportedChar + unsupported.charAt(i);		
		//  if(str.charAt(i) == 32) {unsuportedChar = unsuportedChar+ "space";}
		  }  
	  }
	 // System.out.println(unsuportedChar);
	
	statString = "Text is: "+str_length+" characters (" + str_bits +"bit) \n"
					+ "Unsupported characters: " + unsuportedChar 
					+ "\nUnsupported: "+ unsupported.length() +" characters ("+unspBits+"bit)\n"
					+ "Encoded Size: " + bit_length+"bits\n"
					+ "Compression Rate: "+ compression_rate
			;
	
	return statString;
	
	
}


	
	
}
package lib;

import main.Huffman;
import struct.TreeNode;

public class Coder {
    
    String temp_bit;
   // public static TreeNode tree;  
    char character;
    
    
    public Coder(){
	this.temp_bit = "";
	
    }
    
    
    
    public String decode(String bitString)
    {

	    /*
	     * bitString = bitString.replaceAll(" ", "\t "); String strArr[] =
	     * bitString.split(" ");
	     * 
	     * for(int i = 0; i< strArr.length; i++) { try { out = out +
	     * bit_To_Char(strArr[i]); } catch (NullPointerException e) { return
	     * "invalid bitcode"; }
	     * 
	     * } return out.replace("\t", " ");
	     */
	    
	    return bit_To_Char(bitString);
    }
    
    
    private String bit_To_Char(String bitcode) throws NullPointerException {
	    TreeNode currNode = Huffman.currTree;
	    String out = "";
	   
	    for (int i = 0; i < bitcode.length(); i++) {
		
		if(currNode != null && Huffman.reader.isCharOnList(currNode.getItem().getCharacter()) )
		{
		    out = out+ currNode.getItem().getCharacter();
		    currNode = Huffman.currTree;
		    i--;
		}

		else {
		    if(bitcode.charAt(i) == '0') {currNode = currNode.getLeft();}
			    else if(bitcode.charAt(i) == '1') {currNode = currNode.getRight();}
				    else {
					out = out + bitcode.charAt(i);
					continue;
				    }
			}
	    }
	    return (out + currNode.getItem().getCharacter()).trim() ;
	}
    
// encode() takes string to encode and returns string of bit code
// if string to encode contains leter that not on the frequency list it just add it as is
    
    
    public String encode(String str) {
    str = str.toUpperCase();
    String out = "";
    

    for(int i = 0; i< str.length(); i++)
    {
	if(!Huffman.reader.isCharOnList(str.charAt(i)) ) {out = out + str.charAt(i); }
	else {
	    out = out + char_To_bitcode(Huffman.currTree, temp_bit, str.charAt(i));
	}
	
    }
    return out.trim() ;
}



    String output = "not found";
    public String char_To_bitcode(TreeNode tree, String temp_bit, char character) {
	    TreeNode curr_charSet = tree;

		
		if(tree.getItem().getBitcode() != null)
		{
		    if(tree.getItem().getCharacter() == character) { 
        			output = tree.getItem().getBitcode();
        			}  
		    }
	
			if(tree.getLeft() != null) {
				String currBitCode = temp_bit + "0";
				curr_charSet = tree.getLeft();				
				curr_charSet.getItem().setBitcode(currBitCode );
				char_To_bitcode(curr_charSet, currBitCode, character);
				
				
			}
			if(tree.getRight() != null) {
				String currBitCode = temp_bit + "1";
				curr_charSet = tree.getRight();				
				curr_charSet.getItem().setBitcode(currBitCode );				
				char_To_bitcode(curr_charSet, currBitCode, character);
			}
			
		return output.trim();	
	}
	
	
	

	
}

package lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class List_To_Tree {
    
    public TreeNode list_To_Tree(List<TreeNode> node_List){
    	 List<TreeNode> node_LocalNode = new ArrayList<>(node_List); // node_List;
	
		
	Collections.sort(node_LocalNode, new NodeComparator());
	    
	TreeNode root = new TreeNode(node_LocalNode.get(0).getItem())  ;
	
	while  (node_LocalNode.size() > 1) {			    
	Collections.sort(node_LocalNode, new NodeComparator());
	    
	    int left_Frequency = node_LocalNode.get(0).getItem().getFrequency();
	    int right_Frequency = node_LocalNode.get(1).getItem().getFrequency();
	    
	    Char_Set temp = new Char_Set(' ', left_Frequency + right_Frequency);
	    
	    root = new TreeNode(temp, node_LocalNode.get(0), node_LocalNode.get(1));
	    
	    node_LocalNode.remove(0);
	    node_LocalNode.remove(0);
	    node_LocalNode.add(root);

	}
	
	
	
	
	
	
	return root;
	
	
    }

}

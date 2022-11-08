package struct;

import java.util.Comparator;

public class NodeComparator implements Comparator<TreeNode>{
    
    @Override
    public int compare(TreeNode o1, TreeNode o2) {
        
        return ((Char_Set)(o1.getItem())).getFrequency() - ((Char_Set)(o2.getItem())).getFrequency();
    }

}

import java.util.PriorityQueue;

public class HuffmanCode {
    private HuffmanNode tree;

    public HuffmanTree buildTree(int[] charFreqs) {
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<>();
        for (int i = 0; i < charFreqs.length; i++)
            if (charFreqs[i] > 0)
                trees.offer(new HuffmanLeaf(charFreqs[i], (char) i));

        assert trees.size() > 0;
        while (trees.size() > 1) {
            HuffmanTree a = trees.poll();
            HuffmanTree b = trees.poll();
            trees.offer(new HuffmanNode(a, b));
        }
        this.tree = (HuffmanNode) trees.peek();
        return trees.poll();
    }

    public void printCodes(HuffmanTree tree, StringBuffer prefix) {
        assert tree != null;
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf) tree;
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);

        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode) tree;

            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length() - 1);

            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public String decrypt(String s){
        // 110 100 111 00 101 01
        StringBuffer stringBuffer = new StringBuffer();
        char[] numArray = s.toCharArray();
        HuffmanNode tree = this.tree;
        for (int i = 0; i < numArray.length; i++){
            boolean flag = false;
            while (!flag){
                if (numArray[i] == '0'){
                    if (tree.left.getClass().equals(HuffmanLeaf.class)){
                        HuffmanLeaf same = (HuffmanLeaf) tree.left;
                        stringBuffer.append(same.value);
                        flag = true;
                    } else{
                        tree = (HuffmanNode) tree.left;
                        i++;
                    }
                } else if (numArray[i] == '1'){
                    if (tree.right.getClass().equals(HuffmanLeaf.class)){
                        HuffmanLeaf same = (HuffmanLeaf) tree.right;
                        stringBuffer.append(same.value);
                        flag = true;
                    } else{
                        tree = (HuffmanNode) tree.right;
                        i++;
                    }

                }
            }
            tree = this.tree;

        }


return stringBuffer.toString();
    }
}

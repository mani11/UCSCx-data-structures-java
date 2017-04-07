/** 
 *  Data Structures and Algorithms Using Java
 *  Final Project - Hauffman
 *  @author: Wan-Shan Liao
 *  @date: 2017/03/20
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Hauffman {
    /** Unique id for each node */
	private int IdCounter = 0;
    /** Count of Word Frequence {character : frequence} */
    public HashMap<Character, Integer> freqTable;
    /** Code Table {character : hauffman code} */
    public HashMap<Character, String> codeTable;
    /** DeCode Table {hauffman code : character} */
    public HashMap<String, Character> decodeTable;
    /** Root node of the Hauffman Tree */
    private Node root;
    /** Original String */
	private String string;
    /** Encoded String */
    private String encodedString;
    /** Decoded String */
    private String decodedString;
    /** Whether display on screen */
	private boolean show;
    /** output path of dot file */
	private String dotfilename;

    /** Constructor */
	public Hauffman(String s, boolean show, String dotfilename){
	    this.string = s;
	    this.show = show;
	    this.dotfilename = dotfilename;
        this.freqTable = new HashMap<Character, Integer>();
        this.codeTable = new HashMap<Character, String>();
        this.decodeTable = new HashMap<String, Character>();

        getFreq();
		buildTree();
        buildCodeTable(root, "");
        writeDot();

        if (show) {
            System.out.println("============ Code for each character in " + string + "============");
            for (Map.Entry<Character, String> entry : codeTable.entrySet()) {
				System.out.printf("%c Has Code  %s\n", entry.getKey(), entry.getValue());
            }
            System.out.println("============ Original String ===========");
            System.out.println(string);
            System.out.println("============ Decoded String ===========");
            decode();
            System.out.println(decodedString);
            System.out.println("============ Recovered String ===========");
            encode();
            System.out.println(encodedString);
        } 
	}

	/** Hauffman Tree' Node */
    private class Node implements Comparable<Node> {
		private final int uid;
        private final char ch;
        private final int wordCount;
        private final Node left, right;

		/** Constructor for class node */
		private Node(char ch, int wordCount, Node left, Node right) {
			this.uid = ++IdCounter;
			this.wordCount = wordCount;
			this.ch = ch;
			this.left = left;
			this.right = right;

            if (show) {
                if (isLeaf())
                    System.out.printf("Leaf     node %d char is %c Weight is %d\n",
                                      uid, ch, wordCount);
                else
                    System.out.printf("Internal node %d : Left %c(%d) Right %c(%d) Weight = %d\n",
                                      uid, left.ch, left.wordCount, right.ch, right.wordCount, wordCount);
            }
		}	

        /** Return True if the node is a leaf */
        private boolean isLeaf() {
            return (left == null && right == null);
        }

        /** Compare two nodes base on wordCountuency */
        public int compareTo(Node other) {
            return this.wordCount - other.wordCount;
        }
    }

    /** Count Word Frequence */
    private void getFreq() {
        char ch;
        for (int i = 0; i < string.length(); i++) {
            ch = string.charAt(i);
            Integer count = freqTable.get(ch);
            freqTable.put(ch, count == null ? 1 : (count + 1));
        }

        if (show) {
            System.out.println("============" + string + "++++++++++++");
            for (Map.Entry<Character, Integer> entry : freqTable.entrySet()) {
                System.out.printf("%c occurs %3d times\n", entry.getKey(), entry.getValue());
            }
        }
    }

    /** Build Hauffman Tree base on word frequence */
    private void buildTree() {
        if (show)
            System.out.println("==== Tree built in this order ===========");

        // Create a min heap contains all characters of string
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for (Map.Entry<Character, Integer> entry : freqTable.entrySet()) {
            pq.offer(new Node(entry.getKey(), entry.getValue(), null, null));
        }

        // Iterate merge two smallest tree
        while (pq.size() > 1) {
            Node left  = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.wordCount + right.wordCount, left, right);
            pq.offer(parent);
        }

        root = pq.poll();
    }

    /** Build Code Table and Reverse Code Table */
    private void buildCodeTable(Node node, String s) {
        if (!node.isLeaf()) {
            buildCodeTable(node.left,  s + '0');
            buildCodeTable(node.right, s + '1');
        }
        else {
            if (s == "") s = "0";
            codeTable.put(node.ch, s);
            decodeTable.put(s, node.ch);
        }
    }

    /** Write dotfile via level order traverse*/
    private void writeDot() {
        try {
            // String dotfile = dotfilename.replace("C:\\work\\java\\fig\\", "");
	        // PrintWriter output = new PrintWriter(dotfile);
	        PrintWriter output = new PrintWriter(dotfilename);
            String pdffilename = dotfilename.replace("dot", "pdf");
            output.println("## Wanshan Liao ####");
            output.printf ("## dot -Tpdf %s -o %s\n", dotfilename, pdffilename);
            output.printf ("digraph g {\n");
            output.printf (" label = \"%s\"\n", string);
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node curr = queue.poll();
                if (curr.left != null) {
                    queue.add(curr.left);
                    output.printf(" \"%d\\n%d\" ->\"%d\\n%d%s\" [color=red]\n", 
                                  curr.uid, curr.wordCount, curr.left.uid, curr.left.wordCount,
                                  (curr.left.isLeaf() ? "\\n" + curr.left.ch : ""));
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                    output.printf(" \"%d\\n%d\" ->\"%d\\n%d%s\" [color=blue]\n", 
                                  curr.uid, curr.wordCount, curr.right.uid, curr.right.wordCount,
                                  (curr.right.isLeaf() ? "\\n" + curr.right.ch : ""));
                }
            }
	        output.flush();
	        output.close();
        }
        catch(IOException e) {
            System.out.println("Error writing into output file: " + dotfilename);
            System.exit(2);
	    }
    }

	public String decode() {
        decodedString = "";
        char ch;
        for (int i = 0; i < string.length(); ++i) {
            ch = string.charAt(i);
            decodedString += codeTable.get(ch);
        }
        return decodedString;
	}

    public String encode() {
        encodedString = "";
        String tmp = "";
		for (int i = 0; i < decodedString.length(); ++i){
            tmp += decodedString.charAt(i);
			if (decodeTable.containsKey(tmp)) {
                encodedString += decodeTable.get(tmp);
                tmp = "";
			}
		}
        return encodedString;
    }

}

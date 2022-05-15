import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCode {
    private HuffmanNode huffmanNode;
    private PrintStream output;
    private boolean b = false;

    public HuffmanCode(int[] frequencies) {
        /* TESTER CODE - USED TO CHECK FREQUENCIES
        System.out.println();
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i]>0)
                System.out.println(i + " " + frequencies[i]);
        }
        System.out.println();
        */
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                pq.add(new HuffmanNode(frequencies[i], (char) i));
            }
        }
        while (pq.size() > 1) {
            pq.add(new HuffmanNode(pq.poll(), pq.poll()));
        }
        huffmanNode = pq.poll();
    }

    public HuffmanCode(Scanner input) {
        while (input.hasNextLine()) {
            Character c = (char) Integer.parseInt(input.nextLine());
            String binary = input.nextLine();
            huffmanNode = scanDive(c, binary, huffmanNode);
        }
    }

    /*
    Recursive diver that generates HuffmanNode and looks prettier than non-recursive method
     */
    private HuffmanNode scanDive(Character c, String binary, HuffmanNode curr) {
        if (binary.length() == 0) return new HuffmanNode(c);
        else {
            if (curr == null) curr = new HuffmanNode();
            if (binary.substring(0, 1).equals("0")) curr.LEFT = scanDive(c, binary.substring(1), curr.LEFT);
            else {
                curr.RIGHT = scanDive(c, binary.substring(1), curr.RIGHT);
            }
            return curr;
        }
    }

    public void save(PrintStream output) {
        this.output = output;
        printer(huffmanNode);
    }

    /*
    Public function to run recursive translateDiver
     */
    public void translate(BitInputStream input, PrintStream output) {
        while (input.hasNextBit()) {
            this.output = output;
            translateDiver(input, huffmanNode);
        }
    }

    /*
    Recursive diver to translate bits
     */
    private void translateDiver(BitInputStream input, HuffmanNode node) {
        if (node.LEFT != null && node.RIGHT != null) {
            if (input.nextBit() == 0) {
                translateDiver(input, node.LEFT);
            } else {
                translateDiver(input, node.RIGHT);
            }
        } else {
            output.write(node.ASCII);
        }
    }

    /*
    Private method to find the depth of a HuffmanNode
     */
    private int depthFinder(HuffmanNode node) {
        if (node != null) {
            int left = depthFinder(node.LEFT) + 1;
            int right = depthFinder(node.RIGHT) + 1;
            return Math.max(left, right);
        } else {
            return 0;
        }
    }

    /*
    Private method to print values in HuffmanNode. Calls diver
     */
    private void printer(HuffmanNode node) {
        int depth = depthFinder(node);
        for (int i = 1; i < depth; i++) {
            diver(node, i, "");
        }
    }

    /*
    Private method to find and print values in HuffmanNode
     */
    private void diver(HuffmanNode node, int depth, String curr) {
        if (depth == 0 && node.ASCII != null) {
            if (!b) {
                String str = ((int) node.ASCII) + "";
                output.append(str);
                output.append("\n");
                output.append(curr);
                b = true;
            } else {
                String str = ((int) node.ASCII) + "";
                output.append("\n");
                output.append(str);
                output.append("\n");
                output.append(curr);
            }
        } else {
            if (node.LEFT != null) {
                diver(node.LEFT, depth - 1, curr + "0");
            }
            if (node.RIGHT != null) {
                diver(node.RIGHT, depth - 1, curr + "1");
            }
        }
    }

    /*
    This class handles nodes, where the ASCII value and frequency are stored
     */
    private static class HuffmanNode implements Comparable<HuffmanNode> {
        public final int frequency;
        public final Character ASCII;
        public HuffmanNode LEFT, RIGHT;

        /*
        Constructor for leaves
         */
        public HuffmanNode(int frequency, Character ASCII) {
            this.frequency = frequency;
            this.ASCII = ASCII;
            this.LEFT = null;
            this.RIGHT = null;
        }

        /*
        Constructor for alr-made branches
         */
        public HuffmanNode(HuffmanNode LEFT, HuffmanNode RIGHT) {
            this.frequency = LEFT.frequency + RIGHT.frequency;
            this.ASCII = null;
            this.LEFT = LEFT;
            this.RIGHT = RIGHT;
        }

        /*
        Constructor for leaves - Scanner HuffmanCode constructor
         */
        public HuffmanNode(Character ASCII) {
            frequency = 1;
            this.ASCII = ASCII;
        }

        /*
        Constructor for branches - Scanner HuffmanCode constructor
         */
        public HuffmanNode() {
            frequency = 1;
            this.ASCII = 00;
        }

        /*
        Compares, with higher frequencies being higher on list
         */
        @Override
        public int compareTo(HuffmanNode huffmanNode) {
            return frequency - huffmanNode.frequency;
        }
    }
}
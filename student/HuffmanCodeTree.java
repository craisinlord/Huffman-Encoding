package student;

import provided.BinarySequence;
/**
 * Max Locketz.
 * CSCI 1913
 */
public class HuffmanCodeTree {
    /**
     * A given trees root node.
     */
    private HuffmanNode root;

    /**
     * Constructs a new HuffmanCode tree with a given root.
     * @param root - root of the tree.
     */
    public HuffmanCodeTree(HuffmanNode root) {
        this.root = root;
    }

    /**
     * Constructs a HuffmanNode tree with a given code book.
     * Starts with a null root, then uses the "put" method to add
     * each element of the codebook to the tree.
     * @param codebook - HuffmanCodeBook to be converted to a tree.
     */
    public HuffmanCodeTree(HuffmanCodeBook codebook) {
        root = new HuffmanNode(null, null);
        for (int i = 0; i < codebook.getLen(); i++) {
            put(codebook.getKey(i), codebook.getData(i));
        }
    }

    /**
     * Tests the validity of the root and all it's descendants.
     * This therefore tests the entire tree for validity.
     * @return boolean indicating if a tree is valid.
     */
    public boolean isValid() {
        return root.isValidTree();
    }

    /**
     * Iterates through each boolean in the BinarySequence, finding the corresponding nodes.
     * If a node does not exist, makes new nodes along the way.
     * Sets the final nodes data to the char "letter".
     * @param seq - BinarySequence code that represents the node's place in the tree.
     * @param letter - char to put in the tree.
     */
    public void put(BinarySequence seq, char letter) {
        HuffmanNode node = root;
        HuffmanNode newNode;
        for (int i = 0; i < seq.size(); i++) {
            if (seq.get(i)) {
                newNode = node.getOne();
                if (newNode == null) {
                    newNode = new HuffmanNode(null, null);
                    node.setOne(newNode);
                }
            } else {
                newNode = node.getZero();
                if (newNode == null) {
                    newNode = new HuffmanNode(null, null);
                    node.setZero(newNode);
                }
            }

            node = newNode;
        }
        node.setData(letter);
    }

    /**
     * Decodes a given binary sequence, outputting a string.
     * Starts at the root and goes down the tree until it finds a char than matches the sequence.
     * Then, adds that char to the string and starts at the top again.
     * Uses a StringBuilder to save resources.
     * @param s - binary sequence to decode.
     * @return - string composed of the characters represented by the binary sequence.
     */
    public String decode(BinarySequence s) {
        HuffmanNode node = root;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.size(); i++) {
            if (!isValid()) {
                break;
            }
            if (s.get(i)) {
                node = node.getOne();
            } else {
                node = node.getZero();
            }
            if (node.getOne() == null && node.getZero() == null) {
                sb.append(node.getData());
                node = root;
            }
        }
        return sb.toString();
    }
}

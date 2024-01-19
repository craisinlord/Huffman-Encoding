package student;
/**
 * Max Locketz.
 * CSCI 1913
 */
public class HuffmanNode {
    /**
     * A given node's zero child.
     */
    private HuffmanNode zero;
    /**
     * A given node's one child.
     */
    private HuffmanNode one;
    /**
     * Private variable containing the node's data.
     */
    private Character data;

    /**
     * Constructs a HuffmanNode with both children and no data.
     * @param zero child 0.
     * @param one child 1.
     */
    public HuffmanNode(HuffmanNode zero, HuffmanNode one) {
        this.zero = zero;
        this.one = one;
    }

    /**
     * Constructs a HuffmanNode with data and no children.
     * Leaf Node Constructor
     * @param data - data to add to the node.
     */
    public HuffmanNode(char data) {
        this.data = data;
    }

    /**
     * Boolean indicating if a given tree is valid.
     * Starts from the root node, and evaluate its children
     * ONLY returns true if all the node's descendants are valid.
     * @return boolean
     */
    public boolean isValidTree() {
        if (isValidNode()) {
            if (isLeaf()) {
                return true;
            } else {
                return one.isValidTree() && zero.isValidTree();
            }
        }
        return false;
    }

    /**
     * Boolean indicating if a given node is valid.
     * A node is valid if it has data and no children,
     * or if it has no data and both children.
     * @return boolean
     */
    public boolean isValidNode() {
        if (data == null) {
            return zero != null && one != null;
        } else {
            return zero == null && one == null;
        }
    }

    /**
     * Boolean indicating if a given Huffman node is a leaf.
     * A node is a leaf if it has data, and no children.
     * @return boolean
     */
    public boolean isLeaf() {
        return data != null;
    }

    /**
     * Sets the Zero child of a huffman node.
     * @param zero node.
     */
    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    /**
     * Sets the One child of a huffman node.
     * @param one node.
     */
    public void setOne(HuffmanNode one) {
        this.one = one;
    }

    /**
     * Sets the data of a HuffmanNode.
     * @param data char
     */
    public void setData(char data) {
        this.data = data;
    }

    /**
     * Gets the Zero child of a node.
     * @return zero HuffmanNode.
     */
    public HuffmanNode getZero() {
        return zero;
    }

    /**
     * Gets the One child of a node.
     * @return one HuffmanNode.
     */
    public HuffmanNode getOne() {
        return one;
    }

    /**
     * Gets the data of a node.
     * @return char data.
     */
    public Character getData() {
        return data;
    }

    /**
     * Overrides toString method to print Huffman nodes data, zero, and one children.
     * Used for debugging.
     * @return string representation.
     */
    @Override
    public String toString() {
        return "Data: " + data + " Zero Child: " + zero + " One Child: " + one;
    }
}

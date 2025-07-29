package AVLTreeTSpack;
import Nodepack.Node;
import Symbolpack.Symbol;
public class AVLTreeTS {
    public Node root;

    private int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    // Get balance factor of a node
    private int getBalanceFactor(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    public Node insertNode(Node node, Symbol item) {

        // Find the position and insert the node
        if (node == null)
            return (new Node(item));
        if (item.name.compareTo(node.item.name) < 0)
            node.left = insertNode(node.left, item);
        else if (item.name.compareTo(node.item.name) > 0)
            node.right = insertNode(node.right, item);
        else
            return node;

        // Update the balance factor of each node
        // And, balance the tree
        node.height = 1 + max(height(node.left), height(node.right));
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (item.name.compareTo(node.left.item.name) < 0) {
                return rightRotate(node);
            } else if (item.name.compareTo(node.left.item.name) > 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (balanceFactor < -1) {
            if (item.name.compareTo(node.right.item.name) > 0) {
                return leftRotate(node);
            } else if (item.name.compareTo(node.right.item.name) < 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }
    public Node searchNode(Node root, String name)
    {
    
        if (root == null || root.item.name.compareTo(name) == 0)
            return root;

        if (root.item.name.compareTo(name) < 0)
            return searchNode(root.right, name);

        return searchNode(root.left, name);
    	
    }

    private Node nodeWithMinimumValue(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    public Node deleteNode(Node root, String name) {

        // Find the node to be deleted and remove it
        if (root == null)
            return root;
        if (name.compareTo(root.item.name) < 0)
            root.left = deleteNode(root.left, name);
        else if (name.compareTo(root.item.name) > 0)
            root.right = deleteNode(root.right, name);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = nodeWithMinimumValue(root.right);
                root.item = temp.item;
                root.right = deleteNode(root.right, temp.item.name);
            }
        }
        if (root == null)
            return root;

        // Update the balance factor of each node and balance the tree
        root.height = max(height(root.left), height(root.right)) + 1;
        int balanceFactor = getBalanceFactor(root);
        if (balanceFactor > 1) {
            if (getBalanceFactor(root.left) >= 0) {
                return rightRotate(root);
            } else {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(root.right) <= 0) {
                return leftRotate(root);
            } else {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }
        return root;
    }

    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.item.name + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void printTree(Node currPtr, String indent, boolean last) {
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(currPtr.item.name);
            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }
}
    

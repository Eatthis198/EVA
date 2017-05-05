package socket.tcp.binarytree;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by David on 02.05.2017. this is a search tree
 */
public class BinaryTree implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static void main(String args[])
    {

        BinaryTree tree = new BinaryTree(0);

        tree.append(-1);
        tree.append(2);

        // for (int i = -5; i < 6; i++) {
        // tree.append(i);
        // }

        tree.printLevelOrder(tree.root);
    }

    public BinaryTreeNode getRoot()
    {
        return root;
    }

    public void setRoot(BinaryTreeNode root)
    {
        this.root = root;
    }

    private BinaryTreeNode root;

    private Queue<BinaryTreeNode> queue = new LinkedList<>();

    public BinaryTree(int startValue)
    {
        root = new BinaryTreeNode(startValue);
    }

    public void append(int value)
    {

        BinaryTreeNode current = root;

        while (true)
        {
            if (value <= current.getValue())
            {
                if (current.getChildren()[0] == null)
                {
                    current.getChildren()[0] = new BinaryTreeNode(value);
                    return;
                }
                else
                {
                    current = current.getChildren()[0];
                }
            }
            else
            {
                if (current.getChildren()[1] == null)
                {
                    current.getChildren()[1] = new BinaryTreeNode(value);
                    return;
                }
                else
                {
                    current = current.getChildren()[1];
                }
            }
        }

    }

    public void printLevelOrder(BinaryTreeNode start)
    {

        queue.add(start);

        while (!queue.isEmpty())
        {
            levelOrderStep(queue.poll());
        }

    }

    private void levelOrderStep(BinaryTreeNode node)
    {
        for (BinaryTreeNode child : node.getChildren())
        {
            if (child != null)
            {
                queue.add(child);
            }
        }
        System.out.println(" " + node.getValue() + " ");
    }

}

class BinaryTreeNode implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int value;

    private BinaryTreeNode children[];

    public BinaryTreeNode(int val)
    {
        children = new BinaryTreeNode[2];
        value = val;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public BinaryTreeNode[] getChildren()
    {
        return children;
    }

    public void setChildren(BinaryTreeNode[] children)
    {
        this.children = children;
    }

    public boolean isEmpty()
    {
        for (int i = 0; i < 2; i++)
        {
            if (children[i] != null)
            {
                return false;
            }
        }
        return true;
    }

}
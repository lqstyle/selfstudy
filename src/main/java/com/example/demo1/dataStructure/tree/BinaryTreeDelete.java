package com.example.demo1.dataStructure.tree;

/**
 * @author liangqing
 * @since 2021/2/5 15:36
 */
public class BinaryTreeDelete {

    public static void main(String[] args) {
        BinaryTreeDelete binaryTreeDelete = new BinaryTreeDelete();
        BinaryTree.TreeNode binaryTree = binaryTreeDelete.buildBinaryTree();
        System.out.println("删除前");
        binaryTree.preIter();
        binaryTree.deleteNode(2);
        System.out.println("删除后");
        binaryTree.preIter();

    }

    private static BinaryTree.TreeNode buildBinaryTree() {
        BinaryTree.TreeNode treeNode1 = new BinaryTree.TreeNode(1, "1");
        BinaryTree.TreeNode treeNode2 = new BinaryTree.TreeNode(2, "2");
        BinaryTree.TreeNode treeNode3 = new BinaryTree.TreeNode(3, "3");
        BinaryTree.TreeNode treeNode4 = new BinaryTree.TreeNode(4, "4");
        BinaryTree.TreeNode treeNode5 = new BinaryTree.TreeNode(5, "5");
        BinaryTree.TreeNode treeNode6 = new BinaryTree.TreeNode(6, "6");
        BinaryTree.TreeNode treeNode7 = new BinaryTree.TreeNode(7, "7");

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        return treeNode1;
    }

    //二叉树
    static class BinaryTree {

        public BinaryTree(BinaryTree.TreeNode root) {
            this.root = root;
        }

        //先序遍历
        public void preOrder() {
            if (this.root != null) {
                this.root.preIter();
            } else {
                System.out.println("节点为空");
            }
        }

        //根节点
        private BinaryTree.TreeNode root;

        public void deleteNode(Integer no) {
            if (this.root == null) {
                return;
            } else {
                root.deleteNode(no);
            }

        }

        //树节点
        static class TreeNode {

            private final int no;

            private final String name;

            private BinaryTree.TreeNode left;

            private BinaryTree.TreeNode right;

            public TreeNode(int no, String name) {
                this.no = no;
                this.name = name;
            }

            /**
             * 先序遍历   根左右
             */
            private void preIter() {
                System.out.printf("当前编号 %d, 当前名称 %s \n", this.no, this.name);
                if (this.left != null) {
                    this.left.preIter();
                }
                if (this.right != null) {
                    this.right.preIter();
                }
            }

            @Override
            public String toString() {
                return "TreeNode{" +
                        "no=" + no +
                        ", name='" + name + '\'' +
                        '}';
            }

            private void deleteNode(int no) {
            /*
            1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点
            而不能去判断 当前这个结点是不是需要删除结点.
            2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点
            就将 this.left = null; 并且就返回 (结束递归删除)
            3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点
            就将 this.right= null ;并且就返回 (结束递归删除)
            4. 如果第 2 和第 3 步没有删除结点，那么我们就需要向左子树进行递归删除
            5. 如果第 4 步也没有删除结点，则应当向右子树进行递归删除
             */

                if (this.left != null && this.left.no == no) {
                    this.left = null;
                    return;
                }
                if (this.right != null && this.right.no == no) {
                    this.right = null;
                    return;
                }

                if (this.left != null) {
                    this.left.deleteNode(no);
                }

                if (this.right != null) {
                    this.right.deleteNode(no);
                }

            }

        }

    }


}

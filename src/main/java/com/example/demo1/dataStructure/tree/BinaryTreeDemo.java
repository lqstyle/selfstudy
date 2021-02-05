package com.example.demo1.dataStructure.tree;

/**
 * @author liangqing
 * @since 2021/2/5 10:50
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
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

        BinaryTree binaryTree = new BinaryTree(treeNode1);
        System.out.println("先序遍历输出结果");
        binaryTree.preOrder();

        System.out.println("中序遍历输出结果");
        binaryTree.middleOrder();

        System.out.println("后序遍历输出结果");
        binaryTree.postOrder();

    }

    //二叉树
    static class BinaryTree {

        public BinaryTree(TreeNode root) {
            this.root = root;
        }

        //根节点
        private TreeNode root;

        //先序遍历
        public void preOrder() {
            if (this.root != null) {
                this.root.preIter();
            } else {
                System.out.println("节点为空");
            }
        }

        //中序遍历
        public void middleOrder() {
            if (this.root != null) {
                this.root.middleIter();
            } else {
                System.out.println("节点为空");
            }
        }

        //先序遍历
        public void postOrder() {
            if (this.root != null) {
                this.root.postdleIter();
            } else {
                System.out.println("节点为空");
            }
        }

        //树节点
        static class TreeNode {

            private final int no;

            private final String name;

            private TreeNode left;

            private TreeNode right;

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

            //中序遍历  左根右
            private void middleIter() {
                if (this.left != null) {
                    this.left.middleIter();
                }

                System.out.printf("当前编号 %d, 当前名称 %s \n", this.no, this.name);

                if (this.right != null) {
                    this.right.middleIter();
                }
            }

            //后序遍历  左右根
            private void postdleIter() {
                if (this.left != null) {
                    this.left.postdleIter();
                }

                if (this.right != null) {
                    this.right.postdleIter();
                }
                System.out.printf("当前编号 %d, 当前名称 %s \n", this.no, this.name);

            }

            @Override
            public String toString() {
                return "TreeNode{" +
                        "no=" + no +
                        ", name='" + name + '\'' +
                        '}';
            }
        }

    }

}





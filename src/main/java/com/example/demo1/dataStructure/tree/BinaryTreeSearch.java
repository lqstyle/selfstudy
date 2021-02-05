package com.example.demo1.dataStructure.tree;

/**
 * @author liangqing
 * @since 2021/2/5 14:03
 */
public class BinaryTreeSearch {

    public static void main(String[] args) {
        final BinaryTree.TreeNode treeNode = buildBinaryTree();
        treeNode.preSearch(5);
        treeNode.middleSearch(5);
        treeNode.postSearch(5);
    }

    //二叉树
    static class BinaryTree {

        public BinaryTree(BinaryTree.TreeNode root) {
            this.root = root;
        }

        //根节点
        private BinaryTree.TreeNode root;

        //先序查找
        public void preSearchTree(Integer num) {
            if (this.root != null) {
                this.root.preSearch(num);
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


            //先序查找
            private TreeNode preSearch(Integer no) {
                System.out.println("进入先序查找");
                //先判断当前节点是否相等
                if (this.no == no) {
                    return this;
                }
                //如果当前节点没有查找到，则查找左子树
                TreeNode result = null;
                if (this.left != null) {
                    result = this.left.preSearch(no);
                }
                if (result != null) {
                    return result;
                }
                //如果左子树也没查找到，则查找右子树
                //判断当前节点的右子树是否为空，若不为空，则继续查找
                if (this.right != null) {
                    result = this.right.preSearch(no);
                }
                return result;

            }

            //中序查找
            private TreeNode middleSearch(Integer no) {
                //先查找左节点，如果不为空，则查找
                TreeNode result = null;
                if (this.left != null) {
                    result = this.left.middleSearch(no);
                }
                if (result != null) {
                    return result;
                }
                System.out.println("进入中序查找");
                //判断当前节点是否相等,若相等则返回
                if (this.no == no) {
                    return this;
                }
                //判断当前节点的右节点是否为空，若不为空，则继续查找
                if (this.right != null) {
                    result = this.right.middleSearch(no);
                }
                return result;

            }

            //后序查找
            private TreeNode postSearch(Integer no) {

                //先判断左节点
                TreeNode result = null;
                if (this.left != null) {
                    result = this.left.postSearch(no);
                }
                if (result != null) {
                    return result;
                }
                //如果左子树也没查找到，则查找右子树
                //判断当前节点的右子树是否为空，若不为空，则继续查找
                if (this.right != null) {
                    result = this.right.postSearch(no);
                }
                if (result != null) {
                    return result;
                }
                System.out.println("进入后序查找");
                //先判断当前节点是否相等
                if (this.no == no) {
                    return this;
                }
                return result;

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

    private static  BinaryTree.TreeNode buildBinaryTree() {
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
}

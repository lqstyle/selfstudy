package com.example.demo1.queue;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * @author liangqing
 * @since 2020/11/19 9:17
 * <p>
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * <p>
 * 有两种通用的遍历树的策略：
 * <p>
 * 深度优先搜索（DFS）
 * 在这个策略中，我们采用深度作为优先级，以便从跟开始一直到达某个确定的叶子，然后再返回根到达另一个分支。
 * <p>
 * 深度优先搜索策略又可以根据根节点、左孩子和右孩子的相对顺序被细分为先序遍历，中序遍历和后序遍历。
 * <p>
 * 宽度优先搜索（BFS）
 * 我们按照高度顺序一层一层的访问整棵树，高层次的节点将会比低层次的节点先被访问到。
 */


class TreeNode {

    public int value;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int value) {
        this.value = value;
    }
}

public class BinaryTree {


    public static void main(String[] args) {
        buildTreeNode();
    }
    public static void buildTreeNode(){
        TreeNode treeNode  = new TreeNode(1);

        TreeNode treeNodeLeft  = new TreeNode(2);

        TreeNode treeNodeRight = new TreeNode(3);

        treeNode.left = treeNodeLeft;
        treeNode.right = treeNodeRight;

        TreeNode treeNodeLeft1  = new TreeNode(4);
        treeNodeLeft.left=treeNodeLeft1;

        TreeNode treeNodeRight1 = new TreeNode(5);
        treeNodeLeft.right=treeNodeRight1;
        //广度遍历
        getLevelNumber(treeNode);

        //深度遍历
        getDfsLevelNumber(treeNode);

    }

    //广度遍历,从根节点开始遍历
    public static List<List<Integer>> getLevelNumber(TreeNode root) {
        if (Objects.isNull(root)) {
            return new LinkedList<>();
        }
        return bfs(root);

    }

    //深度遍历,从根节点开始遍历
    public static List<List<Integer>> getDfsLevelNumber(TreeNode root) {
        if (Objects.isNull(root)) {
            return new LinkedList<>();
        }
        List<List<Integer>> result = new LinkedList<>();
        //从第一层开始遍历
        dfs(root, 1, result);
        System.out.println("深度遍历"+result.toString());
        return result;

    }

    private static void dfs(TreeNode node, int level, List<List<Integer>> result) {

        //如果当前list的size 小于当前树的层级，则添加一个新的list
        if (result.size() < level) {
            result.add(new LinkedList<>());
        }
        //获取当前层的存储list，添加元素
        result.get(level - 1).add(node.value);
        //递归遍历左孩子
        if (Objects.nonNull(node.left)) {
            dfs(node.left, level + 1, result);
        }
        //递归遍历右孩子
        if (Objects.nonNull(node.right)) {
            dfs(node.right, level + 1, result);
        }


    }

    /**
     * 广度优先遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levels = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (Objects.nonNull(poll)) {
                    levels.add(poll.value);
                    if (Objects.nonNull(poll.left)) {
                        queue.offer(poll.left);
                    }
                    if (Objects.nonNull(poll.right)) {
                        queue.offer(poll.right);
                    }
                }
            }

            result.add(levels);
        }
        System.out.println("广度遍历"+result.toString());
        return result;

    }


}

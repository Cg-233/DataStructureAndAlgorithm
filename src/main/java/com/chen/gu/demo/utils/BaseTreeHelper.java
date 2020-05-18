package com.chen.gu.demo.utils;

import java.util.HashMap;
import java.util.List;

import com.chen.gu.demo.utils.constant.BusinessConstant;

public abstract class BaseTreeHelper<T> {

    private TreeNode<T> root;
    private List<TreeNode<T>> tempNodeList;
    private boolean isValidTree = true;

    public BaseTreeHelper(List<T> entityList) {
        tempNodeList = changeEnititiesToTreeNodes(entityList);
        generateTree();
    }

    /**
     * 获取泛型T的实例
     * @return T 实例对象
     */
    protected abstract T getInstance();

    /**
     * adapt the entities to the corresponding treeNode
     * @author bobbrown.yjt
     * @since 2018/3/27 下午1:59
     */
    protected abstract List<TreeNode<T>> changeEnititiesToTreeNodes(List<T> entityList);

    public static TreeNode getTreeNodeById(TreeNode tree, Long id) {
        if (tree == null) {
            return null;
        }
        return tree.findTreeNodeById(id);
    }

    /** generate a tree from the given treeNode or entity list */
    private void generateTree() {
        HashMap<String, TreeNode<T>> nodeMap = putNodesIntoMap();
        putChildIntoParent(nodeMap);
    }

    /**
     * put all the treeNodes into a hash table by its id as the key
     *
     * @return hashmap that contains the treenodes
     */
    private HashMap<String, TreeNode<T>> putNodesIntoMap() {
        HashMap<String, TreeNode<T>> nodeMap = new HashMap<>();
        root = new TreeNode<T>(getInstance());
        root.setId(-1L);
        nodeMap.put("-1", root);
        for (TreeNode<T> treeNode : tempNodeList) {
            Long id = treeNode.getId();
            String keyId = String.valueOf(id);
            nodeMap.put(keyId, treeNode);
        }
        return nodeMap;
    }

    /**
     * set the parent nodes point to the child nodes
     * @author bobbrown.yjt
     * @since 2018/3/27 下午2:02
    */
    private void putChildIntoParent(HashMap<String, TreeNode<T>> nodeMap) {
        for (TreeNode<T> treeNode : nodeMap.values()) {
            Long parentId = treeNode.getpId();
            String parentKeyId = String.valueOf(parentId);
            TreeNode<T> parentNode = nodeMap.get(parentKeyId);
            if (parentNode == null) {
                if (!BusinessConstant.ROOT_ID.equals(treeNode.getId())) {
                    nodeMap.get(String.valueOf(BusinessConstant.ROOT_ID)).addChildNode(treeNode);
                }
            } else {
                parentNode.addChildNode(treeNode);
            }
        }
    }

    /**
     * add a tree node to the tempNodeList
     * @author bobbrown.yjt
     * @since 2018/3/27 下午2:00
    */
    public void addTreeNode(TreeNode<T> treeNode) {
        this.tempNodeList.add(treeNode);
    }

    /**
     * create a tree node to the tree generated already
     * @author bobbrown.yjt
     * @since 2018/3/27 下午2:00
    */
    public boolean insertTreeNode(TreeNode treeNode) {
        return root.insertNodes(treeNode);
    }

    public boolean isValidTree() {
        return this.isValidTree;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public List<TreeNode<T>> getTempNodeList() {
        return tempNodeList;
    }

    public void setTempNodeList(List<TreeNode<T>> tempNodeList) {
        this.tempNodeList = tempNodeList;
    }

}

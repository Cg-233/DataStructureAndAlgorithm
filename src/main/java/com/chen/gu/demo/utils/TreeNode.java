package com.chen.gu.demo.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TreeNode<T> implements Serializable {

    private static final long serialVersionUID = 2909261020874701965L;

    private Long id;

    private String name;

    private Long pId;

    // 树的唯一标识
    private T node;

    // 父id
    private TreeNode<T> pNode;

    // 使用一个集合来记录该树里的所有子节点
    private List<TreeNode<T>> childNodes;

    public TreeNode(T entity) {
        node = entity;
    }

    public void addChildNode(TreeNode<T> childNode){
        childNode.setpNode(this);
        if ( this.childNodes==null){
            this.childNodes = new ArrayList<>();
        }
        this.childNodes.add(childNode);
    }

    public void removeChildNode(TreeNode<T> childNode){
        if (this.childNodes!=null){
            Iterator<TreeNode<T>> it = childNodes.iterator();
            while(it.hasNext()){
                TreeNode<T> node = it.next();
                if(node.getId().equals(childNode.getId())){
                    childNodes.remove(node);
                }
            }
        }
    }

    /**
     * 根据节点id，查找以该节点id作为根节点的树，没有返回null
     * @param nodeId 节点id
     * @author bobbrown.yjt
     * @since 2018/3/27 下午1:04
    */
    public TreeNode findTreeNodeById(Long nodeId) {
        if (this.id.equals(nodeId)) {
            return this;
        }
        if (childNodes == null || childNodes.isEmpty()) {
            return null;
        } else {
            for (TreeNode<T> childNode : childNodes) {
                TreeNode result = childNode.findTreeNodeById(nodeId);
                if (result != null) {
                    return result;
                }
            }
            return null;
        }
    }

    /**
     * 查询树下的自身及其子节点的idList
     * @author bobbrown.yjt
     * @since 2018/3/27 下午1:05
    */
    public List<Long> getIdList(){
        if (childNodes == null) {
            return Collections.singletonList(id);
        } else {
            List<Long> idList = new ArrayList<>();
            idList.add(id);
            childNodes.forEach(e -> {
                idList.addAll(e.getIdList());
            });
            return idList;
        }
    }

    /**
     * 判断当前节点是否是叶子节点
     * @author bobbrown.yjt
     * @since 2018/3/27 下午1:08
    */
    public boolean isLeaf() {
        return childNodes == null || childNodes.isEmpty();
    }

    /**
     * 删除节点和它下面的所有子节点
     * @author bobbrown.yjt
     * @since 2018/3/27 下午1:20
    */
    public void deleteNode() {
        TreeNode<T> parentNode = this.getpNode();
        if (parentNode != null) {
            parentNode.deleteChildNode(id);
        }
    }

    /**
     * 删除当前节点的某个子节点
     * @author bobbrown.yjt
     * @since 2018/3/27 下午1:15
    */
    public void deleteChildNode(Long childId) {
        List<TreeNode<T>> childList = this.getChildNodes();
        for (TreeNode<T> child : childList) {
            if (child.getId().equals(childId)) {
                childList.remove(child);
                return;
            }
        }
    }

    /**1
     * 动态的插入一个新的节点到当前树中
     * @author bobbrown.yjt
     * @since 2018/3/27 下午1:21
    */
    public boolean insertNodes(TreeNode treeNode) {
        Long parentId = treeNode.getpId();
        if (this.id.equals(parentId)) {
            addChildNode(treeNode);
            return true;
        } else {
            boolean insertFlag;
            for (TreeNode<T> childNode : childNodes) {
                insertFlag = childNode.insertNodes(treeNode);
                if (insertFlag) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
      * 返回当前树的叶子节点集合
      * @author bobbrown.yjt
      * @since 2018/3/27 下午1:11
    */
    public List<TreeNode<T>> getLeafNodes() {
        List<TreeNode<T>> childList = new ArrayList<>();
        if (childNodes == null) {
            childList.add(this);
        } else {
            for (TreeNode<T> childNode : childNodes) {
                childList.addAll(childNode.getLeafNodes());
            }
        }
        return childList;
    }

    /**
     * 返回当前树节点的父辈节点集合
     */
    public List<TreeNode<T>> getParents() {
        List<TreeNode<T>> elderList = new ArrayList<>();
        TreeNode<T> parentNode = this.getpNode();
        if (parentNode == null) {
            return elderList;
        } else {
            elderList.add(parentNode);
            elderList.addAll(parentNode.getParents());
            return elderList;
        }
    }

    /**
     * 树打印
     * @author bobbrown.yjt
     * @since 2018/3/27 下午1:06
    */
    public void printTree(){
        printTree(this);
    }

    private void printTree(TreeNode<T> node){
        for (TreeNode<T> childNode: node.getChildNodes()) {
            System.out.println(childNode.getNode().toString());
            if (childNode.getChildNodes()!=null){
                printTree(childNode);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }

    public TreeNode<T> getpNode() {
        return pNode;
    }

    public void setpNode(TreeNode<T> pNode) {
        this.pNode = pNode;
    }

    public List<TreeNode<T>> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<TreeNode<T>> childNodes) {
        this.childNodes = childNodes;
    }

}
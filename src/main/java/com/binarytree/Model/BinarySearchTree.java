package com.binarytree.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BinarySearchTree {

    @Id
    @SequenceGenerator(name = "binarySearchTree_sequence", sequenceName = "binarySearchTree_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "binarySearchTree_sequence")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private BinaryNode root;

    @ElementCollection
    private List<Integer> userNumber;


    @Transient
    private String userNumberString;


    public BinarySearchTree() {};

    public BinarySearchTree(BinaryNode root) {
        this.root = root;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BinaryNode getRoot() {
        return root;
    }

    public void setRoot(BinaryNode root) {
        this.root = root;
    }

    public List<Integer> getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(List<Integer> userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserNumberString() {
        return userNumberString;
    }

    public void setUserNumberString(String userNumberString) {
        this.userNumberString = userNumberString;
    }





    public void insert(int value) {
        root = insertRecursion(root, value);
    }

    private BinaryNode insertRecursion(BinaryNode node, int value) {
        if (node == null) {
            node = new BinaryNode(value);
            return node;
        }

        if (value <= node.getValue()) {
            node.setLeft(insertRecursion(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(insertRecursion(node.getRight(), value));
        }
        return node;
    }

    public void preorder() {
        preorderRecursion(root);
    }

    private void preorderRecursion(BinaryNode node) {
        if (node != null) {
            System.out.print(node.getValue() + " ");
            preorderRecursion(node.getLeft());
            preorderRecursion(node.getRight());
        }
    }

    @Override
    public String toString() {
        return "{" + "\n" +
                "\"root\": " + root + "\n" +
                '}';
    }

    public static void main(String[] args) {

    }
}

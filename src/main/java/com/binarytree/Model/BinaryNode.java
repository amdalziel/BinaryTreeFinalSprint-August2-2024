package com.binarytree.Model;

import jakarta.persistence.*;

@Entity
public class BinaryNode {

    @Id
    @SequenceGenerator(name = "binaryNode_sequence", sequenceName = "binaryNode_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "binaryNode_sequence")
    private Long id;

    private int value;

    @ManyToOne(cascade = CascadeType.ALL)
    private BinaryNode left;

    @ManyToOne(cascade = CascadeType.ALL)
    private BinaryNode right;


    public BinaryNode() {}

    public BinaryNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }


    @Override
    public String toString() {
        return "{ value=" + value + "\n" +
                ", left=" + left + "\n" +
                ", right=" + right + "\n" +
                '}';
    }

}

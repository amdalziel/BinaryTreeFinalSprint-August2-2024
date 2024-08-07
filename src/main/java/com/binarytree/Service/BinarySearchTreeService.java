package com.binarytree.Service;

import com.binarytree.Model.BinaryNode;
import com.binarytree.Model.BinarySearchTree;
import com.binarytree.Repository.BinaryNodeRepository;
import com.binarytree.Repository.BinarySearchTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BinarySearchTreeService {

    @Autowired
    BinarySearchTreeRepository binarySearchTreeRepository;

    @Autowired
    BinaryNodeRepository binaryNodeRepository;

    public List<BinarySearchTree> getAllBinarySearchTrees() {
        return (List<BinarySearchTree>) binarySearchTreeRepository.findAll();
    }

    public BinarySearchTree addBinarySearchTree(List<Integer> userNumbers) {
        BinarySearchTree newBST = new BinarySearchTree();
        for (int i = 0; i < userNumbers.size(); i++) {
            newBST.insert(userNumbers.get(i));
        }

        saveNodes(newBST.getRoot());

        return binarySearchTreeRepository.save(newBST);
    }

    private void saveNodes(BinaryNode node) {
        if (node == null) {
            return;
        }

        binaryNodeRepository.save(node);

        saveNodes(node.getLeft());
        saveNodes(node.getRight());
    }

    public BinarySearchTree getLastBinarySearchTree() {
        List<BinarySearchTree> listBST = (List<BinarySearchTree>) binarySearchTreeRepository.findAll();
        if (listBST.isEmpty()) {
            return null;
        }
        return listBST.get(listBST.size() - 1);

    }
}

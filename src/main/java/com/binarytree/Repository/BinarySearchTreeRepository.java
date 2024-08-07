package com.binarytree.Repository;

import com.binarytree.Model.BinarySearchTree;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinarySearchTreeRepository extends CrudRepository<BinarySearchTree, Long> {
}

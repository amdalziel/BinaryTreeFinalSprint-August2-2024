package com.binarytree.Repository;

import com.binarytree.Model.BinaryNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinaryNodeRepository extends CrudRepository<BinaryNode, Long> {
}

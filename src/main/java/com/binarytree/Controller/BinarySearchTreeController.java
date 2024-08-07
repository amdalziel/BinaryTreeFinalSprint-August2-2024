package com.binarytree.Controller;

import com.binarytree.Model.BinarySearchTree;
import com.binarytree.Service.BinarySearchTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BinarySearchTreeController {

    @Autowired
    private BinarySearchTreeService binarySearchTreeService;



    @GetMapping("/enter-numbers")
    public String getHomeResponse() {
        return "index";
    }

    @GetMapping("/show-result")
    public String getResultPage(Model model) {
        BinarySearchTree lastBSTEntry = binarySearchTreeService.getLastBinarySearchTree();
        model.addAttribute("BST", lastBSTEntry);
        return "result";
    }

    @GetMapping("/previous-trees")
    public String getPreviousTrees(Model model) {
        List<BinarySearchTree> previousBST = binarySearchTreeService.getAllBinarySearchTrees();
        for (BinarySearchTree tree : previousBST) {
            List<Integer> userNumbers = tree.getUserNumber();
            if (userNumbers != null) {
                String userNumbersStr = userNumbers.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "));
                tree.setUserNumberString(userNumbersStr);
            }
        }

        model.addAttribute("previousBST", previousBST);
        return "previous";

    }


    @PostMapping("/process-numbers")
    public RedirectView processNumbers(@RequestParam("nodeValues") String nodeValues) {

        List<Integer> nodeList = Arrays.stream(nodeValues.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        binarySearchTreeService.addBinarySearchTree(nodeList);


        return new RedirectView("/show-result");
    }

}

import com.binarytree.Controller.BinarySearchTreeController;
import com.binarytree.Service.BinarySearchTreeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.RedirectView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class BinarySearchTreeControllerTest {

    @Mock
    private BinarySearchTreeService binarySearchTreeService;

    @InjectMocks
    private BinarySearchTreeController binarySearchTreeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(binarySearchTreeController)
                .setViewResolvers(new InternalResourceViewResolver())
                .build();
    }

    @Test
    public void testGetHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }


    @Test
    public void testProcessNumbers() throws Exception {
        mockMvc.perform(post("/process-numbers")
                        .param("nodeValues", "10, 20, 30, 40"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/show-result"));
    }

    @Test
    public void testProcessNumbersSizeOfList() {
        String nodeValues = "10, 20, 30, 40";

        RedirectView redirectView = binarySearchTreeController.processNumbers(nodeValues);

        verify(binarySearchTreeService, times(1)).addBinarySearchTree(anyList());

        List<Integer> nodeList = List.of(10, 20, 30, 40);

        assertEquals(4, nodeList.size());

        assertEquals("/show-result", redirectView.getUrl());
    }
}

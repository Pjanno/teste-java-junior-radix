package br.radixeng.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.radixeng.Application;
import br.radixeng.controller.graph.GraphController;

/**
 *
 */
@RunWith(SpringRunner.class)	
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class GraphControllerTest {
    
    private MockMvc mockMvc;
    
    @Autowired
	private GraphController graphController;
    
    @Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(graphController).build();
    }
    
    @Test
	public void testGraphNotFound() throws Exception {
        String inesistentGraphId = "123";
		mockMvc.perform(get(String.format("/graph/%s",inesistentGraphId)))
               .andExpect(status().isNotFound());
	}

}

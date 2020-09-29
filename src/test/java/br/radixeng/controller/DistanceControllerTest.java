package br.radixeng.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.radixeng.Application;
import br.radixeng.controller.distance.DistanceController;
import br.radixeng.controller.graph.GraphController;

@RunWith(SpringRunner.class)	
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class DistanceControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private DistanceController distanceController;
	
	@Autowired
	private GraphController graphController;
	
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(distanceController, graphController).build();
    }
	
	@Test
	public void distanceOriginAtoC() throws Exception {
		
		String jsonGraph = "{\"data\":[{\"source\":\"A\",\"target\":\"B\",\"distance\":5},{\"source\":\"B\",\"target\":\"C\",\"distance\":4},{\"source\":\"C\",\"target\":\"D\",\"distance\":8},{\"source\":\"D\",\"target\":\"C\",\"distance\":8},{\"source\":\"D\",\"target\":\"E\",\"distance\":6},{\"source\":\"A\",\"target\":\"D\",\"distance\":5},{\"source\":\"C\",\"target\":\"E\",\"distance\":2},{\"source\":\"E\",\"target\":\"B\",\"distance\":3},{\"source\":\"A\",\"target\":\"E\",\"distance\":7}]}";
		mockMvc.perform(post("/graph")
				.accept(MediaType.APPLICATION_JSON)
			.content(jsonGraph)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
		
        Integer graphId = 1;
        String town1 = "A";
        String town2 = "C";
        String jsonResponse = "{\"distance\":9,\"path\":[\"A\",\"B\",\"C\"]}";
        
        mockMvc
        .perform(post(String.format("/distance/%d/from/%s/to/%s", graphId, town1, town2))
        .accept(MediaType.APPLICATION_JSON_UTF8))
        	.andDo(print())
        	.andExpect(status().isOk())
        	.andExpect(content().json(jsonResponse));
        
	}	

	@Test
	public void distanceOriginBtoB() throws Exception {
		
		String jsonGraph = "{\"data\":[{\"source\":\"A\",\"target\":\"B\",\"distance\":5},{\"source\":\"B\",\"target\":\"C\",\"distance\":4},{\"source\":\"C\",\"target\":\"D\",\"distance\":8},{\"source\":\"D\",\"target\":\"C\",\"distance\":8},{\"source\":\"D\",\"target\":\"E\",\"distance\":6},{\"source\":\"A\",\"target\":\"D\",\"distance\":5},{\"source\":\"C\",\"target\":\"E\",\"distance\":2},{\"source\":\"E\",\"target\":\"B\",\"distance\":3},{\"source\":\"A\",\"target\":\"E\",\"distance\":7}]}";
		mockMvc.perform(post("/graph")
				.accept(MediaType.APPLICATION_JSON)
			.content(jsonGraph)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
		
        Integer graphId = 1;
        String town1 = "B";
        String town2 = "B";
        String jsonResponse = "{\"distance\":0,\"path\":[\"B\"]}";
        
        mockMvc
        .perform(post(String.format("/distance/%d/from/%s/to/%s", graphId, town1, town2))
        .accept(MediaType.APPLICATION_JSON_UTF8))
        	.andDo(print())
        	.andExpect(status().isOk())
        	.andExpect(content().json(jsonResponse));
        
	}	
	
	



}

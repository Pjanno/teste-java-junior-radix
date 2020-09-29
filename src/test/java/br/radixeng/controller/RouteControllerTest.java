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
import br.radixeng.controller.graph.GraphController;
import br.radixeng.controller.route.RouteController;

@RunWith(SpringRunner.class)	
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class RouteControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private RouteController routeController;
	
	@Autowired
	private GraphController graphController;
	
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(routeController, graphController).build();
		String jsonGraph = "{\"data\":[{\"source\":\"A\",\"target\":\"B\",\"distance\":5},{\"source\":\"B\",\"target\":\"C\",\"distance\":4},{\"source\":\"C\",\"target\":\"D\",\"distance\":8},{\"source\":\"D\",\"target\":\"C\",\"distance\":8},{\"source\":\"D\",\"target\":\"E\",\"distance\":6},{\"source\":\"A\",\"target\":\"D\",\"distance\":5},{\"source\":\"C\",\"target\":\"E\",\"distance\":2},{\"source\":\"E\",\"target\":\"B\",\"distance\":3},{\"source\":\"A\",\"target\":\"E\",\"distance\":7}]}";
		mockMvc.perform(post("/graph")
				.accept(MediaType.APPLICATION_JSON)
			.content(jsonGraph)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
    }
	
//	1.Rotas de origem C e destino C com um maximo de 3 paradas:- C (0 paradas)
	@Ignore
	@Test
	public void routeOriginCtoC() throws Exception {
        Integer graphId = 1;
        String town1 = "C";
        String town2 = "C";
        Integer maxStop = 3;
        String jsonResponse = "{\"routes\":[{\"route\":\"C\",\"stops\":0}]}";
        
        mockMvc
        .perform(post(String.format("/routes/%d/from/%s/to/%s?maxStops=%d", graphId, town1, town2, maxStop))
        .accept(MediaType.APPLICATION_JSON_UTF8))
        	.andDo(print())
        	.andExpect(status().isOk())
        	.andExpect(content().json(jsonResponse));
        
	}	

//	2.Rotas de origem A e destino C com um maximo de 4 paradas:- ABC (2 paradas)- ADC (2 paradas)- AEBC (3 paradas)- ADEBC (4 paradas)
	@Ignore
	@Test
	public void routeOriginAtoC() throws Exception {		
        Integer graphId = 1;
        String town1 = "A";
        String town2 = "C";
        Integer maxStop = 4; 
        
        mockMvc
        .perform(post(String.format("/routes/%d/from/%s/to/%s?maxStops=%d", graphId, town1, town2, maxStop))
        .accept(MediaType.APPLICATION_JSON_UTF8))
        	.andDo(print())
        	.andExpect(status().isOk());
//        	.andExpect(jsonPath("$.[0]").isArray());
//          .andExpect(jsonPath("$.routes", hasSize(4)));
	}	
	



}

package br.radixeng.controller.graph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.radixeng.model.graph.Graph;
import br.radixeng.service.graph.GraphService;

@Controller
@RequestMapping("/graph")
public class GraphController {
	
	@Autowired
	GraphService graphService;
	
	@PostMapping
	public ResponseEntity<?> postGraph(@RequestBody Graph graph) {
		try {
			return new ResponseEntity<Graph>(graphService.save(graph), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getGraph(@PathVariable Integer id) {
    	if (graphService.find(id) != null) {
    		return new ResponseEntity<Graph>(graphService.find(id), HttpStatus.OK);
    	}
    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);    	
    }  
    
}

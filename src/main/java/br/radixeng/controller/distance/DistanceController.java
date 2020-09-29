package br.radixeng.controller.distance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.radixeng.model.graph.Graph;
import br.radixeng.service.distance.DistanceService;
import br.radixeng.service.graph.GraphService;

@Controller
@RequestMapping("/distance")
public class DistanceController {
	
	@Autowired
	GraphService graphService;
	@Autowired
	DistanceService distanceService;
	
	@PostMapping("/{graphId}/from/{town1}/to/{town2}")
	public ResponseEntity<?> lowestDistance(@PathVariable(required = true) Integer graphId, @PathVariable(required = true) String town1, @PathVariable(required = true) String town2) {
		Graph g = graphService.find(graphId);
		if (g != null) {
			return new ResponseEntity<>(distanceService.findMinimumDistance(g, town1, town2), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}		
	}

}

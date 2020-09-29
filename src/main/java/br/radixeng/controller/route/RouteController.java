package br.radixeng.controller.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.radixeng.model.graph.Graph;
import br.radixeng.model.route.dto.RouteDTO;
import br.radixeng.service.graph.GraphService;
import br.radixeng.service.route.RouteService;

@Controller
@RequestMapping("/routes")
public class RouteController {
	
	@Autowired
	RouteService routeService;
	@Autowired
	GraphService graphService; 
	
    @PostMapping("/{graphId}/from/{town1}/to/{town2}")
	public ResponseEntity<?> findRoutesMaxStop(@PathVariable Integer graphId,@PathVariable String town1, @PathVariable String town2, @RequestParam(required = false) Integer maxStops) {
		try {
			Graph g = graphService.find(graphId);
			if (g != null) {
				return new ResponseEntity<RouteDTO>(routeService.findRoutesWithMaxStops(g, town1, town2, maxStops), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

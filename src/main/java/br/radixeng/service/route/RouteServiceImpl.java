package br.radixeng.service.route;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.radixeng.model.graph.Graph;
import br.radixeng.model.route.dto.RouteDTO;
import br.radixeng.model.route.dto.RoutesDTO;

@Service
public class RouteServiceImpl implements RouteService {

	@Override
	public RouteDTO findRoutesWithMaxStops(Graph graph, String town1, String town2, Integer maxStops) {	
		RouteDTO r = new RouteDTO();
		if (town1.equals(town2)) {
			RoutesDTO routes = new RoutesDTO();
			routes.setRoute(town1);
			routes.setStops(0);
			r.getRoutes().add(routes);
			return r;
		}
		
		String path = "";
		List<RoutesDTO> routes = new ArrayList<RoutesDTO>();
		this.findRoutes(town1, graph, path, town2, routes, maxStops);
				
		r.setRoutes(routes);		
		return r;
	}
	
	private void findRoutes(String node, Graph graph, String path, String target, List<RoutesDTO> possibleRoutes, Integer maxStops) {
		path = path + node;
		List<String> adj = new ArrayList<String>(); 
		// Listo os vizinhos
		for (int i = 0; i < graph.getData().size(); i++) {
			if (graph.getData().get(i).getSource().equals(node) && !path.contains(graph.getData().get(i).getTarget())) {
				adj.add(graph.getData().get(i).getTarget());
			}
		}
		// Verifico o target com base nos vizinhos
		for (int j = 0; j < adj.size(); j++) {
			if (adj.get(j).equals(target)) {
				RoutesDTO r = new RoutesDTO();
				r.setRoute(path + target);
				r.setStops(path.length());
				if (maxStops != null) {
					if (r.getStops() <= maxStops) {
						possibleRoutes.add(r);
					}
				} else {
					possibleRoutes.add(r);
				}
			} else {
				this.findRoutes(adj.get(j), graph, path, target, possibleRoutes, maxStops);
			}
		}
	}
	
}

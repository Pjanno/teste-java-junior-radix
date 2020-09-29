package br.radixeng.service.route;

import org.springframework.stereotype.Service;

import br.radixeng.model.graph.Graph;
import br.radixeng.model.route.dto.RouteDTO;

@Service
public interface RouteService {

	RouteDTO findRoutesWithMaxStops(Graph graph, String town1, String town2, Integer maxStops);

}

package br.radixeng.service.graph;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.radixeng.model.graph.Graph;
import br.radixeng.model.route.Route;
import br.radixeng.repository.GraphRepository;

@Service
public class GraphServiceImpl implements GraphService {
	
	@Autowired
	GraphRepository graphRepository;
	
	@Override
	public Graph save(Graph graph) {
		Graph newGraph = new Graph();
		List<Route> routes = new ArrayList<Route>();
		graph.getData().forEach(e -> {
			routes.add(e);
		});			
		newGraph.setData(routes);
		return graphRepository.save(newGraph);
	}
	
	@Override
	public Graph find(Integer id) {
		return graphRepository.findOne(id);
	}
		
}

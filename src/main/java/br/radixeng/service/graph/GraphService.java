package br.radixeng.service.graph;

import org.springframework.stereotype.Service;

import br.radixeng.model.graph.Graph;

@Service
public interface GraphService {
    
    Graph save(Graph graph);
	Graph find(Integer id);
    
}

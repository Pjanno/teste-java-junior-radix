package br.radixeng.service.distance;

import org.springframework.stereotype.Service;

import br.radixeng.model.distance.dto.DistanceDTO;
import br.radixeng.model.graph.Graph;

@Service
public interface DistanceService {

	DistanceDTO findMinimumDistance(Graph graph, String town1, String town2);

}

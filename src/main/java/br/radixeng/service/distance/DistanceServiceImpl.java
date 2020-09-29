package br.radixeng.service.distance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.radixeng.model.distance.dto.DistanceDTO;
import br.radixeng.model.graph.Graph;
import br.radixeng.util.comparator.DistanceComparator;

@Service
public class DistanceServiceImpl implements DistanceService {
	
	@Override
	public DistanceDTO findMinimumDistance(Graph graph, String town1, String town2) {	
		if (town1.equals(town2)) {
			DistanceDTO d = new DistanceDTO();
			d.setDistance(0l);
			d.getPath().add(town1);
			return d;
		}
		
		String path = "";
		List<DistanceDTO> allRoutes = new ArrayList<>();
		this.findRoutesWithDistance(town1, graph, path, town2, allRoutes, 0l, 0l);
		allRoutes.sort(new DistanceComparator());
		DistanceDTO minimum = new DistanceDTO();
		minimum = allRoutes.get(0);
		
		return minimum;
	}
	
	private void findRoutesWithDistance(String node, Graph graph, String path, String target, List<DistanceDTO> allRoutes, Long distance, Long distanceRun) {
		path = path + node;
		distanceRun = distanceRun + distance;
		List<String> adj = new ArrayList<>();
		List<Long> adjDistance = new ArrayList<>();
		// Listo os vizinhos
		for (int i = 0; i < graph.getData().size(); i++) {
			if (graph.getData().get(i).getSource().equals(node) && !path.contains(graph.getData().get(i).getTarget())) {
				adj.add(graph.getData().get(i).getTarget());
				adjDistance.add(graph.getData().get(i).getDistance());
			}
		}
		// Verifico o target com base nos vizinhos
		for (int j = 0; j < adj.size(); j++) {
			if (adj.get(j).equals(target)) {
			// Caso seja o target
				DistanceDTO d = new DistanceDTO();
				path = path + target;
				List<String> p = new ArrayList<>();
				d.setDistance(distanceRun + adjDistance.get(j));
				for (int k = 0; k < path.length(); k++) {
					p.add(String.valueOf(path.charAt(k)));
				}
				d.setPath(p);
				allRoutes.add(d);
			} else {
			// Caso não seja
				this.findRoutesWithDistance(adj.get(j), graph, path, target, allRoutes, adjDistance.get(j), distanceRun);
			}
		}
	}

}

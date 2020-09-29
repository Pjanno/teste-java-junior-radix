package br.radixeng.util.comparator;

import java.util.Comparator;

import br.radixeng.model.distance.dto.DistanceDTO;

public class DistanceComparator implements Comparator<DistanceDTO>{

	@Override
	public int compare(DistanceDTO o1, DistanceDTO o2) {
		return o1.getDistance().compareTo(o2.getDistance());
	}

}

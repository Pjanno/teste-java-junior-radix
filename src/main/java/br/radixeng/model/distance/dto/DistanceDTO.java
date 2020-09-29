package br.radixeng.model.distance.dto;

import java.util.ArrayList;
import java.util.List;

public class DistanceDTO {
	
	private Long distance;
	private List<String> path = new ArrayList<String>();

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}
	
}

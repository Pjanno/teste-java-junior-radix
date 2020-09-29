package br.radixeng.model.route.dto;

import java.util.ArrayList;
import java.util.List;

public class RouteDTO {
	
	private List<RoutesDTO> routes = new ArrayList<RoutesDTO>();

	public List<RoutesDTO> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RoutesDTO> routes) {
		this.routes = routes;
	}
	
}

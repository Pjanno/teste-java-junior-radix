package br.radixeng.model.route.dto;

public class RoutesDTO {
	
	private String route;
	private Integer stops;
	
	public RoutesDTO(String route, Integer stops) {
		super();
		this.route = route;
		this.stops = stops;
	}	
	
	public RoutesDTO() {
		super();
	}
	
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public Integer getStops() {
		return stops;
	}
	public void setStops(Integer stops) {
		this.stops = stops;
	}
	
}

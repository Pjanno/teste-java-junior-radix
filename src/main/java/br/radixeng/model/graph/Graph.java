package br.radixeng.model.graph;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.radixeng.model.route.Route;

@Entity
public class Graph {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "route_id")
    private List<Route> data = new ArrayList<Route>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Route> getData() {
		return data;
	}

	public void setData(List<Route> data) {
		this.data = data;
	}
	
}

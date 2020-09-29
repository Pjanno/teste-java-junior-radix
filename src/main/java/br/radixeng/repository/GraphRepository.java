package br.radixeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.radixeng.model.graph.Graph;

@Repository
public interface GraphRepository extends JpaRepository<Graph, Integer>{
    
}

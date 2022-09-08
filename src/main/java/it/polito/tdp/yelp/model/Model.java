package it.polito.tdp.yelp.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	YelpDao dao; 
	List<User> vertici;
	List<Adiacenza> archi; 
	Graph<User, DefaultWeightedEdge> grafo;
	Map<String, User> idMap;
	double max;
	
	
	
	public Model() {
		this.dao= new YelpDao(); 
	}
	
	public String creaGrafo(int rec, int anno) {
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class); 
		idMap= new HashMap<>(); 
		vertici= dao.getAllUsers(rec,idMap);
		
		
		Graphs.addAllVertices(this.grafo, vertici);
		archi=dao.getArchi(anno, idMap);
		
		for(Adiacenza a: dao.getArchi(anno, idMap)) {
			if(grafo.containsVertex(a.getU1()) && grafo.containsVertex(a.getU2())) {
				Graphs.addEdge(this.grafo, a.getU1(), a.getU2(), a.getSim());
			}
		}
		
		return "Grafo creato!\n# Vertici:"+grafo.vertexSet().size()+ "\n# Archi: "+grafo.edgeSet().size();	
	}

	public List<User> getVertici() {
		return vertici;
	}

	public List<User> getUtenteSimile(User u) {
		max=0;
		List <User> result= new LinkedList<>(); 
		for (DefaultWeightedEdge e: grafo.edgesOf(u)) { //ciclo sugli archi
			double p= grafo.getEdgeWeight(e); //peso= peso arco
			if(p>max) {
				max=p; 
			}
		}
			for (DefaultWeightedEdge e: grafo.edgesOf(u)) {
				double p= grafo.getEdgeWeight(e);
				if(p==max) { //archi con peso uguale al peso max, li aggiungo alla lista result
					User u2= Graphs.getOppositeVertex(this.grafo, e, u);
					 result.add(u2);
				}
		}
		
	return result; 

	}

	public List<Adiacenza> getArchi() {
		return archi;
	}
	
	
	
}

import java.util.ArrayList;

public class Greedy {
    
	public Greedy() {
	}

	public ArrayList<Arco<Integer>> greedy(GrafoNoDirigido<Integer> grafo){
		
		ArrayList<Arco<Integer>> solucion = new ArrayList<Arco<Integer>>();
		
		ArrayList<Arco<Integer>> arcos = new ArrayList<Arco<Integer>>();//sort ascend
		
		UnionFind conjuntos = new UnionFind(grafo.cantidadVertices());
		
		while((conjuntos.count() > 1) && (!arcos.isEmpty())) {
			Arco<Integer> primero = arcos.get(0);
			arcos.remove(0);
			
			int repOrigen = conjuntos.find(primero.getVerticeOrigen());
			int repDestino = conjuntos.find(primero.getVerticeDestino());
			
			if(repOrigen != repDestino) {
				solucion.add(primero);
				conjuntos.union(repOrigen, repDestino);
			}
		}
		if(conjuntos.count() == 1) {
			return solucion;
		}
		return null;

		
	}
}

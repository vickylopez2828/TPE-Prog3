import java.util.ArrayList;
import java.util.Iterator;

public class Greedy {
    ComparadorArco comp = new ComparadorArco();

	public Greedy() {
	}

	public ArrayList<Arco<Integer>> greedy(GrafoNoDirigido<Integer> grafo){
		
		ArrayList<Arco<Integer>> solucion = new ArrayList<Arco<Integer>>();
		
		ArrayList<Arco<Integer>> arcos = ordenarArcos(grafo);

		System.out.println("arcos2: "+arcos);
		UnionFind conjuntos = new UnionFind(grafo.cantidadVertices());
		
		while((conjuntos.count() > 1) && (!arcos.isEmpty())) {
			Arco<Integer> primero = arcos.get(0);
			arcos.remove(0);
			
			int repOrigen = conjuntos.find(primero.getVerticeOrigen());
			int repDestino = conjuntos.find(primero.getVerticeDestino());
			System.out.println("repOrigen: " + repOrigen + " repDestino: "+ repDestino);
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
	
	public ArrayList<Arco<Integer>> ordenarArcos(GrafoNoDirigido<Integer> grafo){
		ArrayList<Arco<Integer>> arcos = new ArrayList<Arco<Integer>>();
		Iterator<Arco<Integer>> it = grafo.obtenerArcos();

		while(it.hasNext()){
			Arco<Integer> arco = it.next();
			arcos.add(arco);
		}
		arcos.sort(comp);
		return arcos;
	}
		
}

import java.util.ArrayList;
import java.util.Iterator;

public class Greedy {
    ComparadorArco comp = new ComparadorArco();
	private int metrica = 0;

	public Greedy() {
	}

	public String greedy(GrafoNoDirigido<Integer> grafo){
		
		ArrayList<Arco<Integer>> solucion = new ArrayList<Arco<Integer>>();
		ArrayList<Arco<Integer>> arcos = ordenarArcos(grafo);
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
			metrica++;
		}
		if(conjuntos.count() == 1) {
			return mostrarSolucion(solucion);
		}
		return "no es conexo";
	}	
	
	private String mostrarSolucion(ArrayList<Arco<Integer>> solucion){
		return "Greddy\n"+ this.mostrarEstaciones(solucion) 
		+ "\n"+ this.calcularKm(solucion)
		+ "  kms" +"\n"+ "métrica: " +metrica ;
	}

	private String mostrarEstaciones(ArrayList<Arco<Integer>> solucion){
		String rdo = "";
		for(Arco<Integer> e: solucion){
			rdo += "E"+e.getVerticeOrigen() +"-"+"E"+e.getVerticeDestino()+", ";
		}
		return rdo;
	}

	private int calcularKm(ArrayList<Arco<Integer>> solucion){
		int rdo = 0;
		for(Arco<Integer> e: solucion){
			rdo += e.getEtiqueta(); 
		}
		return rdo;
	}

	private ArrayList<Arco<Integer>> ordenarArcos(GrafoNoDirigido<Integer> grafo){
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

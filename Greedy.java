import java.util.ArrayList;
import java.util.Iterator;

public class Greedy {
    ComparadorArco comp = new ComparadorArco();
	private int metrica;
	private int mejorKm;

	public Greedy() {
		this.metrica = 0;
		this.mejorKm = 0;
	}
	

	public int getMetrica() {
		return metrica;
	}


	public void setMetrica(int metrica) {
		this.metrica = metrica;
	}


	public int getMejorKm() {
		return mejorKm;
	}


	public void setMejorKm(int mejorKm) {
		this.mejorKm = mejorKm;
	}

	public ArrayList<Arco<Integer>> greedy(GrafoNoDirigido<Integer> grafo){
		
		ArrayList<Arco<Integer>> solucion = new ArrayList<Arco<Integer>>();
		ArrayList<Arco<Integer>> arcos = ordenarArcos(grafo);

		ArrayList<Integer> vertices = obtenerVertices(grafo);
		
		UnionFind conjuntos = new UnionFind(grafo.cantidadVertices());
		
		while((conjuntos.count() > 1) && (!arcos.isEmpty())) {

			Arco<Integer> primero = arcos.get(0);
			arcos.remove(0);
			
			int repOrigen = conjuntos.find(vertices.indexOf(primero.getVerticeOrigen()));
			int repDestino = conjuntos.find(vertices.indexOf(primero.getVerticeDestino()));
			if(repOrigen != repDestino) {
				solucion.add(primero);
				conjuntos.union(repOrigen, repDestino);
			}
			metrica++;
		}
		if(conjuntos.count() == 1) {
			return solucion;
		}
		return null;
	}

	public String mostrarEstaciones(ArrayList<Arco<Integer>> solucion){
		String rdo = "";
		for(Arco<Integer> e: solucion){
			rdo += "E"+e.getVerticeOrigen() +"-"+"E"+e.getVerticeDestino()+", ";
		}
		return rdo;
	}

	public int calcularKm(ArrayList<Arco<Integer>> solucion){
		int rdo = 0;
		for(Arco<Integer> e: solucion){
			rdo += e.getEtiqueta(); 
		}
		this.setMejorKm(rdo);
		return rdo;
	}

	private ArrayList<Integer> obtenerVertices(GrafoNoDirigido<Integer> grafo){
		ArrayList<Integer> vertices = new ArrayList<>();
		Iterator<Integer> it = grafo.obtenerVertices();
		while(it.hasNext()){
			Integer v = it.next();
			if(!vertices.contains(v)){
				vertices.add(v);
			}
		}
		return vertices;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Backtracking {
    private ArrayList<Arco<Integer>> solucion;
	private ArrayList<Arco<Integer>> arcos;
	private int mejorDistancia;
	private int contador;
	

	public Backtracking(int limiteGreedy) {
		this.arcos = new ArrayList<>();
		this.solucion = new ArrayList<>();
		this.mejorDistancia = limiteGreedy;
		this.contador = 0;
	}
	
	public int getMejorDistancia() {
		return mejorDistancia;
	}
	
	public int getContador() {
		return contador;
	}
	
	public ArrayList<Arco<Integer>> backtracking(GrafoNoDirigido<Integer>g){
		this.arcos = obtenerArcos(g);
		int sumaParcial = 0;
		ArrayList<Arco<Integer>> solucionParcial = new ArrayList<>();
		backtracking(solucionParcial, g, arcos, sumaParcial);
		return solucion;
	}		
	
	private void backtracking(ArrayList<Arco<Integer>> solucionParcial, GrafoNoDirigido<Integer> g, 
    ArrayList<Arco<Integer>> arcos, int sumaParcial) {
		contador++;
		if (arcos.isEmpty()) {
            if(!solucionParcial.isEmpty()){
                if(this.esConexo2(g, solucionParcial)){//chequeo sea conexo
                    if(sumaParcial <= this.mejorDistancia) {
                        this.mejorDistancia = sumaParcial;
                        this.solucion.clear();
                    	this.solucion.addAll(solucionParcial);
                    } 
                }
            }
        }
		else {
			//LLAMADA A BACK SIN AGREGAR ARCO
			Arco<Integer> aux = arcos.get(0);
			arcos.remove(aux); 
			//PODA
			if(sumaParcial <= mejorDistancia){
				backtracking(solucionParcial, g, arcos, sumaParcial);
			}
			arcos.add(0, aux);
			//LLAMADA A BACK 
			aux = arcos.get(0);
			arcos.remove(aux);
			if(!solucionParcial.contains(aux)){
				solucionParcial.add(aux);
				sumaParcial += aux.getEtiqueta();
				//PODA
				if(sumaParcial <= mejorDistancia){
					backtracking(solucionParcial, g, arcos, sumaParcial);
				}
				solucionParcial.remove(aux);
				sumaParcial -= aux.getEtiqueta();
			}
			arcos.add(0, aux);    
        }
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
		return rdo;
	}
	
	public ArrayList<Arco<Integer>> obtenerArcos(GrafoDirigido<Integer> grafo){
		ArrayList<Arco<Integer>> arcos = new ArrayList<>();
		Iterator<Arco<Integer>> it = grafo.obtenerArcos();
		while(it.hasNext()){
			Arco<Integer> arco = it.next();
			arcos.add(arco);
		}
		return arcos;
	}
	public boolean esConexo2(Grafo<Integer> grafo, ArrayList<Arco<Integer>> solucionParcial) {
		UnionFind componentes = new UnionFind(grafo.cantidadVertices());
		ArrayList<Integer> vertices = this.obtenerVertices(grafo);
		for(Arco<Integer> a: solucionParcial){
			int u = a.getVerticeOrigen();
			int v = a.getVerticeDestino();
			int representanteU = componentes.find(vertices.indexOf(u)); 
			int representanteV = componentes.find(vertices.indexOf(v));
			if(representanteU != representanteV) {
				componentes.union(representanteU, representanteV);
			}
		}
		return componentes.count() == 1;
	}
	private ArrayList<Integer> obtenerVertices(Grafo<Integer> grafo){
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


    public boolean esConexo(Grafo<Integer> grafo, ArrayList<Arco<Integer>> solucionParcial){

		GrafoNoDirigido<Integer> nuevoGrafo = armarGrafo(solucionParcial);

        HashMap<Integer, Boolean> visitados = new HashMap<>(); 
        Iterator<Integer> iter1 = grafo.obtenerVertices(); 
		while(iter1.hasNext()) {
			int v = iter1.next();
			visitados.put(v, false);
		}
        Iterator<Integer> iter2 = nuevoGrafo.obtenerVertices(); 
        Integer primerVertice = iter2.next();
        dfs(nuevoGrafo, primerVertice, visitados);
        Iterator<Integer> iter3 = grafo.obtenerVertices(); 
		while(iter3.hasNext()) {
			int v = iter3.next();
            if(visitados.get(v) == false){
                return false;
            } 
		}
        return true;

    }

    private void dfs(Grafo<Integer> grafo, Integer primerVertice, HashMap<Integer, Boolean> visitados){
        visitados.put(primerVertice, true);
        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(primerVertice);
		if(adyacentes!=null) {
			while(adyacentes.hasNext()) {
				int ady = adyacentes.next();
				if(visitados.get(ady) == false) {
					dfs(grafo, ady, visitados);
				}
			}
		}
    }
        

    private GrafoNoDirigido<Integer> armarGrafo(ArrayList<Arco<Integer>> solucionParcial){
        GrafoNoDirigido<Integer> g = new GrafoNoDirigido<>();

        for(Arco<Integer> a: solucionParcial){
            g.agregarVertice(a.getVerticeOrigen());
            g.agregarVertice(a.getVerticeDestino());
            g.agregarArco(a.getVerticeOrigen(), a.getVerticeDestino(), a.getEtiqueta());
        }

        return g;
    }
	 
	
}

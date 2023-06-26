import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class GrafoDirigido<T> implements Grafo<T> {
    private HashMap<Integer, ArrayList<Arco<T>>> vertices;
	private int cantidadVertices;
	private int cantidadArcos;
	
	public GrafoDirigido() {
		this.vertices = new HashMap<>();
		this.cantidadVertices = 0;
		this.cantidadArcos = 0;
	}

	@Override
	public void agregarVertice(int verticeId) {
		if(!this.contieneVertice(verticeId)) {
			vertices.put(verticeId, new ArrayList<Arco<T>>());
			cantidadVertices++;			
		}
	}
	/**
	* Complejidad: O(n)^2 = O|V|*O|A| donde V es cantidad de vertices y |A| es la cantidad de arcos, debido a 
	* que debe realizar un primer recorrido por sus vertices y por cada vertice luego recorre cada uno
	* de sus arcos para poder borrar un vertice.
	*/

	@Override
	public void borrarVertice(int verticeId) {
		//Borrar los arcos del vertice
		int aux = 0;
		boolean existe = false;
		for (int v : vertices.keySet()) {
			for (Arco<T> a : vertices.get(v)) {
				if(a.getVerticeDestino() == verticeId) {
					aux = a.getVerticeOrigen();
					existe = true;
				}
			}
			if (existe)
				this.borrarArco(aux, verticeId);
		}
		
		//Borrar el vertice (aca ya se borran mis arcos)
		cantidadArcos-= vertices.get(verticeId).size();
		vertices.remove(verticeId);
		cantidadVertices--;
	}
	/**
	* Complejidad: O1, ya que se supone una complejidad O1 para todos los metodos que implementan 
	* HashMap, para agregar un arco solo se hacen llamados a metodos de HashMap(contains y get)
	* .
	*/

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if(vertices.containsKey(verticeId1)&&(vertices.containsKey(verticeId2))) {
			vertices.get(verticeId1).add(new Arco<>(verticeId1, verticeId2, etiqueta));
			cantidadArcos++;
		}
	}
	/**
	* Complejidad: O(n), por un lado utiliza el metodo existeArco que tiene una complejidad O(n)
	* y luego usa el remove de ArrayList, que tambien tiene complejidad de O(n)donde n es el número 
	* de elementos en la lista.
	* Entonces O(n) + O(n) = O(n)
	*/
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if (this.existeArco(verticeId1, verticeId2)) {
			ArrayList<Arco<T>> arcos = vertices.get(verticeId1);
			arcos.remove(this.obtenerArco(verticeId1, verticeId2));
			cantidadArcos--;
		}
	}
	/**
	* Complejidad: O1, ya que se supone una complejidad O1 para todos los metodos de HashMap, 
	* en este caso se usa el contains. 
	*/
	@Override
	public boolean contieneVertice(int verticeId) {
		return vertices.containsKey(verticeId);
	}
	/**
	* Complejidad: O(n), donde n son todos los Arcos.
	*/
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if (this.contieneVertice(verticeId1) && (this.contieneVertice(verticeId2))) {
			ArrayList<Arco<T>> arcos = vertices.get(verticeId1);//O1
			if (!arcos.isEmpty()) { //O1
				for (Arco <T> a : arcos) { //O(a)
					if(a.getVerticeDestino()==verticeId2) //O1
						return true;
				}
			}
		}
		return false;
	}
	/**
	* Complejidad: O(n), donde n son todos los Arcos.
	*/
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if (this.contieneVertice(verticeId1) && (this.contieneVertice(verticeId2))) {//O1
			ArrayList<Arco<T>> arcos = vertices.get(verticeId1);//O1
			if (!arcos.isEmpty()) {//O1
				for (Arco <T> a : arcos) {//On
					if(a.getVerticeDestino()==verticeId2)//O1
						return a;
				}
			}
		}
		return null;
	}
	/**
	* Complejidad: O1, ya que simplemente se hace un acceso a la variable de instancia.
	*/
	@Override
	public int cantidadVertices() {
		return this.cantidadVertices;
	}
	/**
	* Complejidad: O1, ya que simplemente se hace un acceso a la variable de instancia.
	*/
	@Override
	public int cantidadArcos() {
		return this.cantidadArcos;
	}
	/**
	* Complejidad: O1, ya que se implementa un metodo de HashMap que suponemos todos con
	* complejidad O1
	*/
	@Override
	public Iterator<Integer> obtenerVertices() {
		return vertices.keySet().iterator();
	}
	
	/**
	* Complejidad: On, donde n son la cantidad de arcos
	*/
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		ArrayList<Arco<T>> lista = this.vertices.get(verticeId);
		ArrayList<Integer> adyacentes = new ArrayList<>();
		for (Arco<T> elem: lista) {
			adyacentes.add(elem.getVerticeDestino());
		}
		return adyacentes.iterator();
	}
	/**
	* Complejidad: On, donde n son la cantidad de arcos
	*/
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		Set<Map.Entry<Integer, ArrayList<Arco<T>>>> lista = vertices.entrySet();
		if(!lista.isEmpty()) {
			ArrayList<Arco<T>> arcos = new ArrayList<>();
			for (Map.Entry<Integer, ArrayList<Arco<T>>> elem : lista) {
				arcos.addAll(elem.getValue());
			}
			return arcos.iterator();
		}
		return null;
		//La recorremos y a cada lista la guardamos una una lista auxiliar addAll y a esa lista la iterator
	}
	/**
	* Complejidad: O1
	*/
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		ArrayList<Arco<T>> arcos = this.vertices.get(verticeId);
		return arcos.iterator();
	}
	
		public void imprimirGrafo() {
			Iterator<Integer> it = this.obtenerVertices();
			while(it.hasNext()) {
				Integer key = it.next();
				System.out.println("Vertice" + key + "--> Arcos: "+ vertices.get(key) );
			}
		}
	
	public void imprimirEtiquetaArco() {
		Iterator<Arco<T>> it = this.obtenerArcos();
		while(it.hasNext()) {
			Arco<T> key = it.next();
			System.out.println("Arco" + key + "--> etiqueta: "+key.getEtiqueta() );
		}
	}
	public String toString() {
		return this.vertices.toString() ;
	}

	public boolean isConexo() {
		UnionFind componentes = new UnionFind(this.cantidadVertices());
		Iterator<Arco<T>> it = this.obtenerArcos();
		if(it != null) {
			while(it.hasNext()) {
				Arco<T> arco = it.next();
				int u = arco.getVerticeOrigen();
				int v = arco.getVerticeDestino();
				int representanteU = componentes.find(u); 
				int representanteV = componentes.find(v);
				
				if(representanteU != representanteV) {
					componentes.union(representanteU, representanteV);
				}
			}
		}
		return componentes.count() == 1;
	}

}

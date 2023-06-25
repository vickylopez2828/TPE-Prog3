import java.util.ArrayList;

public class UnionFind {
    private ArrayList<Integer> representantes;
	 private int count;
	 public UnionFind(int cantVertices) {//inicializo todos los vertices en -1, cada uno es un conjunto
		 this.representantes = new ArrayList<>();
		 for(int i = 0; i < cantVertices; i++) {
			 representantes.add(i, i);
		 }
		// System.out.println(vertices);
		 this.count = cantVertices;
	 }

	 public int count() {
		 return this.count;
	 }
	 
	 
	 public void union(int i, int j) {
		 int repI = this.find(i);
		 int repJ = this.find(j);
		 
		 if(repI != repJ) {
			 this.representantes.set(repI, repJ);
		 }
		 
		 this.count --;
		 //System.out.println("union"+vertices);
	 }
	 
	 public int find(int i) {
		 if(i == representantes.get(i)) {
			 return i;
		 }
		 int aux = this.find(representantes.get(i));
		 return representantes.set(i, aux);
	 }
	
}

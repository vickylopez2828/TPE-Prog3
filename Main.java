import java.util.HashSet;
import java.util.Set;
public class Main {
    
    public static void main(String[] args) {
		
		Set s = new HashSet<>();
		
		
		
		GrafoNoDirigido<Integer> g2 = new GrafoNoDirigido<>();
		
		GrafoDirigido<Integer> g = new GrafoDirigido<>();
		g.agregarVertice(8);
		g.agregarVertice(7);
		g.agregarVertice(5);
		
		g.agregarArco(8, 7, null);
		g.agregarArco(7, 5, null);
		g.agregarArco(5, 7, null);

		
		

		String path = "C:/Users/Tomy/Desktop/datasets/dataset1.txt";
		CSVReader reader = new CSVReader(path);
		reader.read(g2);
		
		//System.out.println(g);
		
		g2.imprimirGrafo();
		//g.imprimirEtiquetaArco();
		//System.out.println(g2.isConexo());
		//System.out.println(g.isConexo());
		//Greedy greedy = new Greedy();
		
		//System.out.println(greedy.greedy(g2));
		
	}
}


public class Main {
    
    public static void main(String[] args) {
		GrafoNoDirigido<Integer> g = new GrafoNoDirigido<>();
		GrafoNoDirigido<Integer> g2 = new GrafoNoDirigido<>();
		GrafoNoDirigido<Integer> g3 = new GrafoNoDirigido<>();
		
		String path1 = "C:/Users/Tomy/Desktop/datasets/dataset1.txt";
		String path2 = "C:/Users/Tomy/Desktop/datasets/dataset2.txt";
		String path3 = "C:/Users/Tomy/Desktop/datasets/dataset3.txt";

		CSVReader reader = new CSVReader(path1);
		reader.read(g);
		CSVReader reader2 = new CSVReader(path2);
		reader2.read(g2);
		CSVReader reader3 = new CSVReader(path3);
		reader3.read(g3);
		
		g.imprimirGrafo();
		Greedy greedy = new Greedy();
		greedy.greedy(g);
		System.out.println("Dataset 1");
		System.out.println(greedy.greedy(g));
		System.out.println("Dataset 2");
		System.out.println(greedy.greedy(g2));
		System.out.println("Dataset 3");
		System.out.println(greedy.greedy(g3));

		GrafoNoDirigido<Integer> g4 = new GrafoNoDirigido<>();

		g4.agregarVertice(1);
		g4.agregarVertice(2);
		g4.agregarVertice(3);
		g4.agregarVertice(4);

		g4.agregarArco(1, 2, 20);
		g4.agregarArco(1, 3, 5);
		//g4.agregarArco(1, 4,25);
		g4.agregarArco(2, 3, 35);
		//g4.agregarArco(2, 4, 10);
		//g4.agregarArco(3, 4, 30);
		

		
		System.out.println("es conexo:"+g4.isConexo());
		
	}
}

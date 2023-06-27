import java.util.ArrayList;

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
		
		Greedy greedy = new Greedy();
		System.out.println("Dataset 1");
		ArrayList<Arco<Integer>> solGreedy1 = greedy.greedy(g);
		System.out.println( "Greedy\n"+ greedy.mostrarEstaciones(solGreedy1)
		+ "\n"+ greedy.calcularKm(solGreedy1)
		+ "  kms" +"\n"+ "métrica: " + greedy.getMetrica());
		//System.out.println(greedy.greedy(g));
		
		System.out.println("----------------------------------------------------------------------------");
		Greedy greedy2 = new Greedy();
		System.out.println("Dataset 2");
		ArrayList<Arco<Integer>> solGreedy2 = greedy2.greedy(g2);
		System.out.println( "Greedy\n"+ greedy2.mostrarEstaciones(solGreedy2)
		+ "\n"+ greedy2.calcularKm(solGreedy2)
		+ "  kms" +"\n"+ "métrica: " + greedy2.getMetrica());
		//System.out.println(greedy2.greedy(g2));
		System.out.println("----------------------------------------------------------------------------");
		Greedy greedy3 = new Greedy();
		System.out.println("Dataset 3");
		ArrayList<Arco<Integer>> solGreedy3 = greedy3.greedy(g3);
		System.out.println( "Greedy\n"+ greedy3.mostrarEstaciones(solGreedy3)
		+ "\n"+ greedy3.calcularKm(solGreedy3)
		+ "  kms" +"\n"+ "métrica: " + greedy3.getMetrica());
		//System.out.println(greedy3.greedy(g3));
		System.out.println("----------------------------------------------------------------------------");

		GrafoNoDirigido<Integer> g4 = new GrafoNoDirigido<>();

		g4.agregarVertice(11);
		g4.agregarVertice(12);
		g4.agregarVertice(13);
		g4.agregarVertice(14);

		g4.agregarArco(11, 12, 20);
		g4.agregarArco(11, 13, 5);
		//g4.agregarArco(11, 14,25);
		g4.agregarArco(12, 13, 35);
		//g4.agregarArco(12, 14, 10);
		//g4.agregarArco(13, 14, 30);
		System.out.println("g4:"+greedy.greedy(g4));
		Greedy greedy4 = new Greedy();
		System.out.println("grafo4");
		ArrayList<Arco<Integer>> solGreedy4 = greedy4.greedy(g4);
		if(solGreedy4 != null){
			System.out.println( "Greedy\n"+ greedy4.mostrarEstaciones(solGreedy4)
			+ "\n"+ greedy4.calcularKm(solGreedy4)
			+ "  kms" +"\n"+ "métrica: " + greedy4.getMetrica());
		} else {
			System.out.println("No hay solucion porque el grafo no es conexo");
		}
		

		System.out.println("----------------------------------------------------------------------------");

		//lo uso para que back tenga como referencia una solucion con bajo costo
		//que Greedy ya encontro, lo uso para inicializar mi mejorDistancia
		int kmGreedy1 = greedy.getMejorKm();
		int kmGreedy2 = greedy2.getMejorKm();
		int kmGreedy3 = greedy3.getMejorKm();

		Backtracking back = new Backtracking(kmGreedy1);
		ArrayList<Arco<Integer>> solBack1 = back.backtracking(g);
		System.out.println("Dataset 1");
		System.out.println( "Backtracking\n"+ back.mostrarEstaciones(solBack1)
		+ "\n"+ back.calcularKm(solBack1)
		+ "  kms" +"\n"+ "métrica: " + back.getContador());
		System.out.println("----------------------------------------------------------------------------");
		//System.out.println(back.backtracking(g));
		//System.out.println("contador: "+back.getContador());
		Backtracking back2 = new Backtracking(kmGreedy2);
		//System.out.println(back2.backtracking(g2));
		
		ArrayList<Arco<Integer>> solBack2 = back2.backtracking(g2);
		System.out.println("Dataset 2");
		System.out.println( "Backtracking\n"+ back2.mostrarEstaciones(solBack2)
		+ "\n"+ back2.calcularKm(solBack2)
		+ "  kms" +"\n"+ "métrica: " + back2.getContador());
		System.out.println("----------------------------------------------------------------------------");

		//Backtracking back3 = new Backtracking(kmGreedy3);
		//System.out.println(back3.backtracking(g3));


	}
}
/* 
 "Backtracking\n"+ this.mostrarEstaciones() 
		+ "\n"+ this.calcularKm()
		+ "  kms" +"\n"+ "métrica: " + this.getContador() ;

		GrafoNoDirigido<Integer> g4 = new GrafoNoDirigido<>();

		g4.agregarVertice(11);
		g4.agregarVertice(12);
		g4.agregarVertice(13);
		g4.agregarVertice(14);

		g4.agregarArco(11, 12, 20);
		g4.agregarArco(11, 13, 5);
		g4.agregarArco(11, 14,25);
		g4.agregarArco(12, 13, 35);
		g4.agregarArco(12, 14, 10);
		g4.agregarArco(13, 14, 30);
		System.out.println("g4:"+greedy.greedy(g4));
		esConexo conex = new esConexo();
		System.out.println(conex.esConexo(g4));
		*/
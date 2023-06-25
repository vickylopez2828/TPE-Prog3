import java.util.Comparator;

public class ComparadorArco implements Comparator<Arco<Integer>> {

    @Override
    public int compare(Arco<Integer> arco1, Arco<Integer> arco2) {
        return arco1.getEtiqueta() - arco2.getEtiqueta();
    }

    
}

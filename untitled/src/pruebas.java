import java.util.ArrayList;
import java.util.*;
public class pruebas {
    public static void main(String[] args){
        List<Integer> lista=new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            lista.add(i);
        }
        System.out.println(lista);
        lista.remove((Integer) 8);
        System.out.println(lista);
    }
}

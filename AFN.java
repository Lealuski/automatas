package automatas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author David Leal & Daniel Villa
 */
public class AFN {

    final private char[] alfabeto;
    final private String[] estados;
    final private int[] estadosFinales;
    final private int estadoInicial;
    final private List<String>[][] transiciones;
    private final HashMap<Character, Integer> indicesAlf;
    private final HashMap<String, Integer> indicesEst;
    private List<Integer> estadosActuales;
    private Set<Integer> estadosPosteriores;

    public AFN(char[] alfabeto, String[] estados, String[] estadosFinales, String estadoInicial, List<String>[][] transiciones) {
        this.transiciones = transiciones;
        this.alfabeto = alfabeto;
        this.estados = estados;
        this.indicesAlf = new HashMap<Character, Integer>();
        this.indicesEst = new HashMap<String, Integer>();
        for (int i = 0; i < alfabeto.length; i++) {
            indicesAlf.put(alfabeto[i], i);
        }
        for (int i = 0; i < estados.length; i++) {
            indicesEst.put(estados[i], i);
        }
        this.estadosFinales = new int[estadosFinales.length];
        for (int i = 0; i < estadosFinales.length; i++) {
            this.estadosFinales[i] = indicesEst.get(estadosFinales[i]);
        }
        quicksort(this.estadosFinales, 0, this.estadosFinales.length - 1);
        this.estadoInicial = indicesEst.get(estadoInicial);
    }

    public boolean esAceptado(String palabra) {
        estadosActuales = new ArrayList<Integer>();
        estadosPosteriores = new TreeSet<Integer>();
        estadosActuales.add(this.estadoInicial);
        for (char letra : palabra.toCharArray()) {
            for (int estadoActual : estadosActuales) {
                List<String> trans = transiciones[estadoActual][indicesAlf.get(letra)];
                if (trans != null) {
                    for (String tr : trans) {
                        estadosPosteriores.add(indicesEst.get(tr));
                    }
                }
            }
            if (estadosPosteriores.isEmpty()) {
                return false;
            }
            estadosActuales.clear();
            for (Integer e : estadosPosteriores) {
                estadosActuales.add(e);
            }
            estadosPosteriores.clear();
        }
        boolean b = false;
        System.out.print("Los estados finales con la palabra " + palabra + " son: ");
        for (Integer e : estadosActuales) {
            if (binsearch(this.estadosFinales, e) != -1) {
                b = true; //indicesEst.get(this.estados[e]))
            }
            System.out.print(estados[e] + "  ");
        }
        System.out.println();
        return b;
    }

    private static int partition(int[] A, int ini, int fin) {
        int pos_pivot = ini + (int) (Math.random() * (fin - ini + 1));
        int pivot = A[pos_pivot];
        A[pos_pivot] = A[ini];
        A[ini] = pivot;
        int i = ini + 1;
        int j = fin;
        while (i <= j) {
            while (i <= j && A[i] <= pivot) {
                i++;
            }
            while (i <= j && A[j] > pivot) {
                j--;
            }
            if (i < j) {
                int aux = A[i];
                A[i] = A[j];
                A[j] = aux;
            }
        }
        A[ini] = A[j];
        A[j] = pivot;
        return j;
    }

    private static void quicksort(int[] A, int ini, int fin) {
        if (ini < fin) {
            int pos_pivot = partition(A, ini, fin);
            quicksort(A, ini, pos_pivot - 1);
            quicksort(A, pos_pivot + 1, fin);
        }
    }

    private static int binsearch(int[] A, int x) {
        int p = 0, q = A.length - 1;
        while (p <= q) {
            int m = (p + q) / 2;
            if (A[m] == x) {
                return m;
            } else if (A[m] > x) {
                q = m - 1;
            } else {
                p = m + 1;
            }
        }
        return -1;
    }

    public void imprimirTabla() {
        System.out.println("E: " + Arrays.toString(this.alfabeto));
        for (int i = 0; i < this.estados.length; i++) {
            System.out.println((i == this.estadoInicial ? "->" : "")
                    + estados[i] + (binsearch(estadosFinales, i) != -1 ? "*" : "")
                    + ": " + transicionEstado(i));
        }
    }

    private String transicionEstado(int estado) {
        String out = " ";
        int letra = 0;
        for (List<String> trs : this.transiciones[estado]) {
            out += alfabeto[letra];
            if (trs != null) {
                out += "->{";
                for (String tr : trs) {
                    out += tr + " ";
                }
                out += "}  ";
            } else {
                out += "-> null  ";
            }
            letra++;
        }
        return out;
    }

}

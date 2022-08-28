package automatas;

import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author David Leal
 */
public class AFD {

    private char[] alfabeto;
    private String[] estados;
    private int[] estadosFinales;
    private int estadoInicial;
    private String[][] transiciones;
    private HashMap<Character, Integer> indicesAlf;
    private HashMap<String, Integer> indicesEst;

    public AFD(char[] alfabeto, String[] estados, String[] estadosFinales, String estadoInicial, String[][] transiciones) {
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
        int indiceActual = this.estadoInicial;
        for (char letra : palabra.toCharArray()) {
            indiceActual = indicesEst.get(transiciones[indiceActual][indicesAlf.get(letra)]);
        }
        return binsearch(this.estadosFinales, indiceActual) != -1;
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
            System.out.println((i == this.estadoInicial ? "->" : "") + estados[i] + (binsearch(estadosFinales, i) != -1 ? "*" : "") + ": " + Arrays.toString(transiciones[i]));
        }
    }

    public char[] getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(char[] alfabeto) {
        this.alfabeto = alfabeto;
    }

    public String[] getEstados() {
        return estados;
    }

    public void setEstados(String[] estados) {
        this.estados = estados;
    }

    public int[] getEstadosFinales() {
        return estadosFinales;
    }

    public void setEstadosFinales(int[] estadosFinales) {
        this.estadosFinales = estadosFinales;
    }

    public int getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(int estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public String[][] getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(String[][] transiciones) {
        this.transiciones = transiciones;
    }

    public HashMap<Character, Integer> getIndicesAlf() {
        return indicesAlf;
    }

    public void setIndicesAlf(HashMap<Character, Integer> indicesAlf) {
        this.indicesAlf = indicesAlf;
    }

    public HashMap<String, Integer> getIndicesEst() {
        return indicesEst;
    }

    public void setIndicesEst(HashMap<String, Integer> indicesEst) {
        this.indicesEst = indicesEst;
    }

}

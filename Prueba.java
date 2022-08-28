
package automatas;

/**
 *
 * @author David Leal
 */
public class Prueba {
    public static void main(String[] args) {
        char[] alf = {'a','b','c'};
        String[] est = {"q0","S","T","R"};
        String[] estFinales = {"T"};
        String estIni = "q0";
        String[][] trans = new String[est.length][alf.length];
        trans[0][0] = "S";
        trans[0][1] = "T";
        trans[0][2] = "R";
        trans[1][0] = "S";
        trans[1][1] = "R";
        trans[1][2] = "T";
        trans[2][0] = "T";
        trans[2][1] = "R";
        trans[2][2] = "S";
        trans[3][0] = "R";
        trans[3][1] = "R";
        trans[3][2] = "T";
        AFD afd = new AFD(alf, est, estFinales, estIni, trans);
        String s1 = "baa";
        String s2 = "cca";
        String s3 = "aca";
        String s4 = "aaaaa";
        String s5 = "bca";
        String s6 = "baba";
        afd.imprimirTabla();
        System.out.println(s1+" es:"+afd.esAceptado(s1));
        System.out.println(s2+" es:"+afd.esAceptado(s2));
        System.out.println(s3+" es:"+afd.esAceptado(s3));
        System.out.println(s4+" es:"+afd.esAceptado(s4));
        System.out.println(s5+" es:"+afd.esAceptado(s5));
        System.out.println(s6+" es:"+afd.esAceptado(s6));
    }
}

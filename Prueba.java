package automatas;

import java.util.Scanner;

/**
 *
 * @author David Leal
 */
public class Prueba {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Letras del alfabeto: ");
        String[] alfStr = in.nextLine().split(" ");
        char[] alf = new char[alfStr.length];
        for (int i = 0; i < alf.length; i++) {
            alf[i] = alfStr[i].charAt(0);
        }
        System.out.println("Estados: ");
        String[] estados = in.nextLine().split(" ");
        System.out.println("Estados finales: ");
        String[] estadosFinales = in.nextLine().split(" ");
        System.out.print("Estado Inicial: \n");
        String estIni = in.nextLine();
        String[][] trans = new String[estados.length][alf.length];
        for (int i = 0; i < estados.length; i++) {
            for (int j = 0; j < alf.length; j++) {
                System.out.println(estados[i] + " x " + alf[j] + " : ");
                trans[i][j] = in.nextLine();
            }
        }
        AFD afd = new AFD(alf, estados, estadosFinales, estIni, trans);
        System.out.println("Escribe las palabras: ");
        String[] palabras = in.nextLine().split(" ");
        for (String pal : palabras) {
            System.out.println(pal + (afd.esAceptado(pal) ? " es aceptado." : " no es aceptado."));
        }
//        System.out.println("Ejemplo 1:");
//        ej1();
//        System.out.println();
//        System.out.println("Ejemplo 2:");
//        ej2();
    }

    static void ej1() {
        char[] alf = {'a', 'b', 'c'};
        String[] est = {"q0", "S", "T", "R"};
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
        System.out.println(s1 + " es:" + afd.esAceptado(s1));
        System.out.println(s2 + " es:" + afd.esAceptado(s2));
        System.out.println(s3 + " es:" + afd.esAceptado(s3));
        System.out.println(s4 + " es:" + afd.esAceptado(s4));
        System.out.println(s5 + " es:" + afd.esAceptado(s5));
        System.out.println(s6 + " es:" + afd.esAceptado(s6));
    }

    static void ej2() {
        char[] alf = {'0', '1'};
        String[] est = {"q0", "q1", "q2"};
        String[] estFinales = {"q2"};
        String estIni = "q0";
        String[][] trans = new String[est.length][alf.length];
        trans[0][0] = "q1";
        trans[0][1] = "q2";
        trans[1][0] = "q0";
        trans[1][1] = "q1";
        trans[2][0] = "q1";
        trans[2][1] = "q0";
        AFD afd = new AFD(alf, est, estFinales, estIni, trans);
        String s1 = "1";
        String s2 = "111";
        String s3 = "1001";
        String s4 = "01";
        String s5 = "0111";
        String s6 = "101";
        afd.imprimirTabla();
        System.out.println(s1 + " es:" + afd.esAceptado(s1));
        System.out.println(s2 + " es:" + afd.esAceptado(s2));
        System.out.println(s3 + " es:" + afd.esAceptado(s3));
        System.out.println(s4 + " es:" + afd.esAceptado(s4));
        System.out.println(s5 + " es:" + afd.esAceptado(s5));
        System.out.println(s6 + " es:" + afd.esAceptado(s6));
    }
}

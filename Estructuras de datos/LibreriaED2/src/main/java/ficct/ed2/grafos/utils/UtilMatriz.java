/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.utils;

/**
 *
 * @author OJavierHR
 */
public class UtilMatriz {

    public static String toString(float[][] matriz) {
        String sMatriz = "";
        if (matriz == null || matriz.length == 0) {
            return sMatriz;
        }
        for (float[] fila : matriz) {
            String sFila = "[";
            for (float b : fila) {
                sFila += b + ", ";

            }
            sFila = sFila.substring(0, sFila.length() - 2);
            sFila += "]";
            sMatriz += sFila + '\n';
        }
        return sMatriz.substring(0, sMatriz.length() - 1);
    }

    public static String toString(boolean[][] matriz) {
        String sMatriz = "";
        if (matriz == null || matriz.length == 0) {
            return sMatriz;
        }
        for (boolean[] fila : matriz) {
            String sFila = "[";
            for (boolean b : fila) {
                if (b) {
                    sFila += 1 + ", ";
                } else {
                    sFila += 0 + ", ";
                }
            }
            sFila = sFila.substring(0, sFila.length() - 2);
            sFila += "]";
            sMatriz += sFila + '\n';
        }
        return sMatriz.substring(0, sMatriz.length() - 1);
    }

    public static boolean[][] copy(boolean[][] matriz) {
        int fil = matriz.length;
        int col = matriz[0].length;
        boolean[][] matrizR = new boolean[fil][col];
        for (int i = 0; i < fil; i++) {
            System.arraycopy(matriz[i], 0, matrizR[i], 0, col);
        }
        return matrizR;
    }
}

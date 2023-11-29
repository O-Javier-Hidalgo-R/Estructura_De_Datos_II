package edu.uagrm.ficct.ed1.proyecto1.utils;

import edu.uagrm.ficct.ed1.proyecto1.app.models.MBook;
import edu.uagrm.ficct.ed1.proyecto1.arbolbusqueda.IArbolBusqueda;
import java.awt.Image;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class appUtils {

    public static final List<Character> SEPARADORES = Arrays.asList(' ',
            ',', '[', ']');

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(ahora);
    }

    //Diferencias entre dos fechas
    //@param fechaInicial La fecha de inicio
    //@param fechaFinal  La fecha de fin
    //@return Retorna el numero de dias entre dos fechas
    public static synchronized int diferenciasDeFechas(Date fechaInicial, Date fechaFinal) throws ParseException {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        try {
            fechaInicial = df.parse(fechaInicioString);
        } catch (ParseException ex) {
        }

        String fechaFinalString = df.format(fechaFinal);
        fechaFinal = df.parse(fechaFinalString);

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }

    //Devuele un java.util.Date desde un String en formato dd-MM-yyyy
    //@param La fecha a convertir a formato date
    //@return Retorna la fecha en formato Date
    public static synchronized java.util.Date stringToDate(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaEnviar = null;
        try {
            fechaEnviar = formatoDelTexto.parse(fecha);
            return fechaEnviar;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void setImageLabel(JLabel jLabel, String name) {
        ImageIcon imagen = new ImageIcon("C:\\Users\\OJavierHR\\Documents\\NetBeansProjects\\iLib-main\\src\\main\\resources\\images\\" + name);
        int w = jLabel.getSize().width;
        int h = jLabel.getSize().height;
        Icon icon = new ImageIcon(imagen.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
        jLabel.setIcon(icon);
        jLabel.repaint();
    }

    public static void librosDefault(IArbolBusqueda<Integer, MBook> a) {
        //forma de "Inicializar una lista con operador doble llave.
        /*List<String> generos = new ArrayList<String>() {
        {
        add("Novela");
        }
        };*/
        a.insertar(13,
                new MBook("Don Quijote de la Mancha",
                        "2008-01-16",
                        "Miguel De Cervantes Saavedra",
                        new ArrayList<String>() {
                    {
                        add("Ficcion");
                        add("Parodia");
                        add("Satira");
                    }
                },
                        "pluton ediciones 4ta edicion",
                        "Don Quijote de la Mancha "
                        + "narra las aventuras de Alonso Quijano, "
                        + "un hidalgo pobre que de tanto leer novelas "
                        + "de caballería acaba enloqueciendo y creyendo"
                        + " ser un caballero andante, nombrándose a sí "
                        + "mismo como don Quijote de la Mancha.",
                        "Español",
                        936,
                        30,
                        30));
        a.insertar(6, 
                new MBook("La vuela al mundo en ochenta dias", 
                        "2008-12-22", 
                        "Julio Verne",
                        new ArrayList<String>() {
                    {
                        add("Ciencia ficcion");
                        add("Aventura");
                    }
                }, 
                        "Anaya", 
                        "Phileas Fogg, un flemático inglés, ha "
                                + "apostado su fortuna a que dará la vuelta al "
                                + "mundo en 80 días, y empleará todos los "
                                + "medios de locomoción a su alcance: trenes, "
                                + "barcos, coches, y hasta un elefante y un "
                                + "trineo.", 
                        "Castellano", 
                        304, 
                        20, 
                        20));
        a.insertar(17, 
                new MBook("Lo que el viento se llevo", 
                        "1936-1-16", 
                        "Margaret Mitchell", 
                        new ArrayList<String>() {
                    {
                        add("Novela");
                        add("Romance");
                    }
                }, 
                        "b de bolsillo",
                        "Scarlett O'Hara vive en Tara, una gran "
                                + "plantación del estado sureño de  Georgia, y "
                                + "está enamorada de Ashley Wilkes, que en "
                                + "breve contraerá  matrimonio con Melanie "
                                + "Hamilton. Estamos en 1861, en los "
                                + "prolegómenos de  la guerra de Secesión, y "
                                + "todos los jóvenes sureños muestran "
                                + "entusiasmo  por entrar en combate, excepto "
                                + "el atractivo aventurero Rhett Butler.", 
                        "Ingles",
                        992,
                        20, 
                        20));
        a.insertar(4, 
                new MBook("Estudio en escarlata", 
                        "2000-11-21", 
                        "Arthur Conan Doyle", 
                        new ArrayList<String>() {
                    {
                        add("ficcion");
                        add("Policial");
                        add("Misterio");
                    }
                }, 
                        "Anaya",
                        "La historia comienza en 1878, cuando el Dr. "
                                + "John Watson se encuentra con un viejo amigo,"
                                + " Stamford. Watson se vio obligado a "
                                + "retirarse por una herida de guerra y, al "
                                + "recuperarse, fue víctima del tifus. A partir"
                                + " de ese momento comienza a buscar un lugar"
                                + " para vivir, porque no puede seguir con ese"
                                + " estilo de vida. Stamford revela que un "
                                + "onocido suyo, Sherlock Holmes, está buscando"
                                + " a alguien para compartir el alquiler en un"
                                + " piso en la calle Baker, en el 221 B.", 
                        "Español",
                        192,
                        20, 
                        20));
        a.insertar(9, 
                new MBook("El signo de los cuatro", 
                        "1936", 
                        "Arthur Conan Doyle", 
                        new ArrayList<String>() {
                    {
                        add("Ficcion");
                        add("Policial");
                        add("Misterio");
                    }
                }, 
                        "valdemar",
                        "La petición de una mujer a Sherlock Holmes "
                                + "para acompañarla a visitar a un hombre y "
                                + "la muerte del hermano del mismo, lo lleva "
                                + "a descubrir, junto al doctor Watson, el "
                                + "secreto que hay tras un tesoro encontrado "
                                + "en la India, un juramento entre tres "
                                + "indios, un blanco y una enloquecedora "
                                + "sed de venganza.", 
                        "Español",
                        264,
                        20, 
                        20));
        a.insertar(23,
                new MBook("Don Quijote de la Mancha",
                        "2016-01-16",
                        "Miguel De Cervantes Saavedra",
                        new ArrayList<String>() {
                    {
                        add("Ficcion");
                        add("Parodia");
                        add("Satira");
                    }
                },
                        "pluton ediciones 1ra edicion",
                        "Don Quijote de la Mancha "
                        + "narra las aventuras de Alonso Quijano, "
                        + "un hidalgo pobre que de tanto leer novelas "
                        + "de caballería acaba enloqueciendo y creyendo"
                        + " ser un caballero andante, nombrándose a sí "
                        + "mismo como don Quijote de la Mancha.",
                        "Español",
                        924,
                        30,
                        30));
        a.insertar(33, 
                new MBook("La vuela al mundo en ochenta dias", 
                        "2008-12-22", 
                        "Julio Verne",
                        new ArrayList<String>() {
                    {
                        add("Ciencia ficcion");
                        add("Aventura");
                    }
                }, 
                        "anaya", 
                        "Phileas Fogg, un flemático inglés, ha "
                                + "apostado su fortuna a que dará la vuelta al "
                                + "mundo en 80 días, y empleará todos los "
                                + "medios de locomoción a su alcance: trenes, "
                                + "barcos, coches, y hasta un elefante y un "
                                + "trineo.", 
                        "Ingles", 
                        304, 
                        20, 
                        20));
        a.insertar(5, 
                new MBook("Lo que el viento se llevo", 
                        "2004-1-16", 
                        "Margaret Mitchell", 
                        new ArrayList<String>() {
                    {
                        add("Novela");
                        add("Romance");
                    }
                }, 
                        "O'Hellgins",
                        "Scarlett O'Hara vive en Tara, una gran "
                                + "plantación del estado sureño de  Georgia, y "
                                + "está enamorada de Ashley Wilkes, que en "
                                + "breve contraerá  matrimonio con Melanie "
                                + "Hamilton. Estamos en 1861, en los "
                                + "prolegómenos de  la guerra de Secesión, y "
                                + "todos los jóvenes sureños muestran "
                                + "entusiasmo  por entrar en combate, excepto "
                                + "el atractivo aventurero Rhett Butler.", 
                        "Ingles",
                        874,
                        20, 
                        20));
        a.insertar(55, 
                new MBook("Estudio en escarlata", 
                        "2000-11-21", 
                        "Arthur Conan Doyle", 
                        new ArrayList<String>() {
                    {
                        add("ficcion");
                        add("Policial");
                        add("Misterio");
                    }
                }, 
                        "Anaya",
                        "La historia comienza en 1878, cuando el Dr. "
                                + "John Watson se encuentra con un viejo amigo,"
                                + " Stamford. Watson se vio obligado a "
                                + "retirarse por una herida de guerra y, al "
                                + "recuperarse, fue víctima del tifus. A partir"
                                + " de ese momento comienza a buscar un lugar"
                                + " para vivir, porque no puede seguir con ese"
                                + " estilo de vida. Stamford revela que un "
                                + "onocido suyo, Sherlock Holmes, está buscando"
                                + " a alguien para compartir el alquiler en un"
                                + " piso en la calle Baker, en el 221 B.", 
                        "Ingles",
                        192,
                        20, 
                        20));
    }

    public static List<String> stringToList(String text) {
        List<String> result;
        return stringToList(text, "", new ArrayList<String>());
    }

    private static List<String> stringToList(String text, String palabra, ArrayList<String> listaDePalabras) {
        if(text.equals("")){
            return listaDePalabras;
        }
        if(SEPARADORES.contains(text.charAt(0))){
            if(palabra.equals("")){
                return stringToList(text.substring(1, text.length()), palabra, listaDePalabras);
            }else{
                listaDePalabras.add(palabra);
                return stringToList(text.substring(1, text.length()), "", listaDePalabras);
            }
            
        }
        return stringToList(text.substring(1, text.length()), palabra + text.charAt(0), listaDePalabras);
    }
}

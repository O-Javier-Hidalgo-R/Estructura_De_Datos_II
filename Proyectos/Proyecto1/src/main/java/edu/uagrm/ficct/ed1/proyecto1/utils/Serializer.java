/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uagrm.ficct.ed1.proyecto1.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.uagrm.ficct.ed1.proyecto1.app.models.MBook;
import edu.uagrm.ficct.ed1.proyecto1.app.models.User;
import edu.uagrm.ficct.ed1.proyecto1.arbolbusqueda.IArbolBusqueda;
import edu.uagrm.ficct.ed1.proyecto1.ui.views.Lendings;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OJavierHR
 */

public class Serializer {

    public static final String DEFAULT_ROOT = "src//main//resources//serialized//";
    private static final String FORMAT = ".json";

    public static void serialize(Object object, String path) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String stringJson = gson.toJson(object, object.getClass());
        try (PrintWriter printWriter = new PrintWriter(path + FORMAT)) {
            printWriter.write(stringJson);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serializer.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    public static Object deserialize(Type type, String path) {
        try (Reader reader = 
                Files.newBufferedReader(Paths.get(path + FORMAT))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.fromJson(reader, type);
        } catch (IOException ex) {
            Logger.getLogger(Serializer.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void deserializeLendingsSearchTree(IArbolBusqueda<Long, Lendings> aBLending, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class TreeElement<K extends Comparable<K>, V> {

        private final K key;
        private final V valor;

        public TreeElement(K key, V valor) {
            this.key = key;
            this.valor = valor;
        }

        public K getKey() {
            return key;
        }

        public V getValor() {
            return valor;
        }

    }

    public static void serializeSearchTree(IArbolBusqueda ab, String path) {
        if (ab == null) {
            throw new IllegalArgumentException("the search tree is null");
        }
        List<Comparable> clavesPorNivel = ab.recorridoPorNiveles();
        List<TreeElement> elements = new ArrayList<>();
        for (Comparable key : clavesPorNivel) {
            elements.add(new TreeElement(key, ab.buscar(key)));
        }
        serialize(elements, path);
    }

    public static void deserializeBookSearchTree(IArbolBusqueda ab, String path) {
        Type type = new TypeToken<List<TreeElement<Long, MBook>>>() {
        }.getType();
        List<TreeElement<Long, MBook>> elements
                = (List<TreeElement<Long, MBook>>) deserialize(type, path);

        for (TreeElement<Long, MBook> element : elements) {
            ab.insertar(element.key, element.valor);
        }
    }
    
    public static void deserializeUsersSearchTree(IArbolBusqueda ab, String path) {
        Type type = new TypeToken<List<TreeElement<Long, User>>>() {
        }.getType();
        List<TreeElement<Long, User>> elements
                = (List<TreeElement<Long, User>>) deserialize(type, path);

        for (TreeElement<Long, User> element : elements) {
            ab.insertar(element.key, element.valor);
        }
    }
}


package edu.uagrm.ficct.ed1.proyecto1.app.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para manipular un objeto libro.
 * @author OJavierHR
 */
public class MBook {

    //Atributo para la primary key de la tabla books cuando se usa base de datos
    //private String id;
    private String title;
    private String date;
    private String author;
    private List<String> categoriesList;
    private String publisher;
    private String description;
    private String language;
    private int pages;
    private int stock;
    private int available;

    /**
     * Constructor por defecto (parametros vacios).
     */
    public MBook() {
        categoriesList = new ArrayList<>();
    }

    /**
     * 
     * @param title titulo.
     * @param date fecha de publicacion.
     * @param author autor.
     * @param categoriesList lista de categorias.
     * @param publisher editorial.
     * @param description deescripcion.
     * @param language lenjuage.
     * @param pages paginas.
     * @param stock stock.
     * @param available ejemplares disponibles.
     */
    public MBook(String title, String date, String author, 
            List<String> categoriesList, String publisher, String description, 
            String language, int pages, int stock, int available) {
        this.title = title;
        this.date = date;
        this.author = author;
        this.categoriesList = categoriesList;
        this.publisher = publisher;
        this.description = description;
        this.language = language;
        this.pages = pages;
        this.stock = stock;
        this.available = available;
    }

    /**
     * Obtener el valor de la editorial.
     * @return valor de la editorial.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Insertar valor de la editorial.
     * @param publisher valor de la editorial a insertar.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Obtiene el valor del lenguaje.
     * @return valor del lengueje.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Inserta valor del lenguaje.
     * @param language valor del lenguaje a insertar.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Muestra el valor del titulo.
     * @return valor del titulo.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Insertar el valor del titulo.
     * @param title valor del titulo a insertar.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene el valor de la fecha de publicacion.
     * @return valor de la fecha de publicacion.
     */
    public String getDate() {
        return date;
    }

    /**
     * Inserta el valor de la fecha de publicacion.
     * @param date valor de la fecha de publicacion a insertar.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Obtener el valor del autor.
     * @return valor del autor.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Inserta el valor del autor.
     * @param author valor del autor a insertar.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Obtiene los valores de la lista de categorias.
     * @return lista con las categorias.
     */
    public List<String> getCategoriesList() {
        List<String> categoryListValues = new ArrayList<>();
        for (String category : this.categoriesList) {
            categoryListValues.add(category);
        }
        return categoryListValues;
    }

    /**
     * Inserta los valores de la lista de categorias.
     * @param categoryListValues lista con valores a insertar en la lista de 
     * categorias.
     */
    public void setCategoriesList(List<String> categoryListValues) {
        this.categoriesList = new ArrayList<>();
        for (String category : categoryListValues) {
            this.categoriesList.add(category);
        }
    }
    
    /**
     * Obtiene el valor de las paginas. 
     * @return valor de las paginas.
     */
    public int getPages() {
        return pages;
    }

    /**
     * Inserta el valor de las paginas.
     * @param pages valor de las paginas a insertar.
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * Obtiene el valor de la descripcion.
     * @return valor de la descripcion.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Inserta el valor de la descripcion.
     * @param description valor de la descripcion a insertar.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtener el valor del stock.
     * @return valor del stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Inserta el valor del stock.
     * @param stock valor del stock a insertar.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Obtener el valor de ejemplares disponibles.
     * @return valor de ejemplares disponibles.
     */
    public int getAvailable() {
        return available;
    }

    /**
     * Insertar el valor de ejemplares disponibles.
     * @param available valor de ejemplares disponibles a insertar.
     */
    public void setAvailable(int available) {
        this.available = available;
    }
}

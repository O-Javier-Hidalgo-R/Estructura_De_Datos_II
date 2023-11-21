package edu.uagrm.ficct.ed1.proyecto1.app.interfaces;

import edu.uagrm.ficct.ed1.proyecto1.app.models.MBook;
import java.util.List;

public interface DAOBooks {
    public void registrar(MBook book) throws Exception;
    public void modificar(MBook book) throws Exception;
    public void eliminar(int bookId) throws Exception;
    public List<MBook> listar(String title) throws Exception;
    public MBook getBookById(int bookId) throws Exception;
}

package edu.uagrm.ficct.ed1.proyecto1.app.interfaces;

import edu.uagrm.ficct.ed1.proyecto1.app.models.MBook;
import edu.uagrm.ficct.ed1.proyecto1.app.models.Lending;
import edu.uagrm.ficct.ed1.proyecto1.app.models.User;
import java.util.List;

public interface DAOLendings {
    public void registrar(Lending lending) throws Exception;
    public void modificar(Lending lending) throws Exception;
    public Lending getLending(User user, MBook book) throws Exception;
    // public void eliminar(Lending user) throws Exception;
    public List<Lending> listar() throws Exception;
}

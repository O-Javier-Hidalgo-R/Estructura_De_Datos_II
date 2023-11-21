package edu.uagrm.ficct.ed1.proyecto1.app.interfaces;

import edu.uagrm.ficct.ed1.proyecto1.app.models.User;
import java.util.List;

public interface DAOUsers {
    public void registrar(User user) throws Exception;
    public void modificar(User user) throws Exception;
    public void sancionar(User user) throws Exception;
    public void eliminar(int userId) throws Exception;
    public List<User> listar(String name) throws Exception;
    public User getUserById(int userId) throws Exception;
}
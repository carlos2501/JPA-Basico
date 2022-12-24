import Entidades.Usuario;
import Utilidades.JPAem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import javax.swing.*;
import java.util.List;

public class JPAResultList {
    public static void main(String[] args) {
        // Parte fija de la query
        String qry = "select u from Usuario u";

        String perfil = JOptionPane.showInputDialog("Indique el perfil a buscar");
        /* Parte variable de la query
           La partícula ?1 indica que es un dato que se proporciona como parámetro:
               - el 1 indica que es el primer (y en este caso único) parámetro.
               - Si hubiera más, se indicarían con ?2, ?3 ...
         */
        qry += (perfil.equals("*")) ? "" : " where u.perfil=?1";
        // Abrimos la conexión
        EntityManager em = JPAem.getEntityManager();

        Query qryUsu  = em.createQuery(qry, Usuario.class);
        if (!perfil.equals("*")) {
            qryUsu.setParameter(1, perfil); // el uno indica el ordinal del parámetro proporcionado
        }
        List<Usuario> listaUsu = qryUsu.getResultList();

        if (listaUsu.size() > 0) {
            listaUsu.forEach(System.out::println);
        } else {
            System.out.println("No hay usuarios con ese perfil.");
        }

        // FUNDAMENTAL. No olvidar
        em.close();

    }
}

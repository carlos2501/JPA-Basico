import Entidades.Usuario;
import Utilidades.JPAem;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.util.List;

public class JPAResultList {
    public static void main(String[] args) {
        String perfil = JOptionPane.showInputDialog("Indique el perfil a buscar");
        // Abrimos la conexión
        EntityManager em = JPAem.getEntityManager();
        /* Creamos la consulta de tipo Query
           La partícula ?1 indica que es un dato que se proporciona como parámetro:
               - el 1 indica que es el primer (y en este caso único) parámetro.
               - Si hubiera más, se indicarían con ?2, ?3 ...
         */
        List<Usuario> listaUsu  = em.createQuery("select u from Usuario u where u.perfil=?1", Usuario.class)
                .setParameter(1, perfil)    // el uno indica el ordinal del parámetro proporcionado
                .getResultList();

        if (listaUsu.size() > 0) {
            listaUsu.forEach(System.out::println);
        } else {
            System.out.println("No hay usuarios con ese perfil.");
        }

        // FUNDAMENTAL. No olvidar
        em.close();

    }
}

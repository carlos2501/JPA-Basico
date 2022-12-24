import Entidades.Usuario;
import Utilidades.JPAem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import javax.swing.*;

public class JPAsingleResultUsu {
    public static void main(String[] args) {
        String perfil = JOptionPane.showInputDialog("Indique el perfil a buscar");
        // Abrimos la conexión
        EntityManager em = JPAem.getEntityManager();
        /* Creamos la consulta de tipo Query
            La partícula ?1 indica que es un dato que se proporciona como parámetro:
                - el 1 indica que es el primer (y en este caso único) parámetro.
                - Si hubiera más, se indicarían con ?2, ?3 ...
         */
        Query qry = em.createQuery("select u from Usuario u where u.perfil=?1", Usuario.class);
        qry.setParameter(1, perfil);    // el uno indica el ordinal del parámetro proporcionado
        /*  La siguiente sentencia funciona con "admin" porque solo hay un registro con ese perfil, pero
            no funciona con "usuario" porque hay más de un registro con ese perfil y devuelve el error
            NonUniqueResultException: query did not return a unique result
         */
        // Utilizamos setMaxResult() para obligar a devolver SOLO UN registro y que no se produzca el error.
        // Esto devuelve el primer registro encontrado
        qry.setMaxResults(1);
        // Si no utilizamos el cast -(Usuario)- no funciona porque getSingleResult() devuelve un objeto genérico
        Usuario usu = (Usuario) qry.getSingleResult();
        System.out.println(usu);

        // FUNDAMENTAL. No olvidar
        em.close();
    }
}

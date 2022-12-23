import Entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class JPAListar {
    public static void main(String[] args) {
        /* La primera operación a realizar es conectar con la BBDD.
            Para ello invocamos la EntityManagerFactory para que se cree la conexión con la BBDD
         */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemploJPA");
        /*
            conectamos con la BBDD y obtenemos una instancia de EntityManager (el canuto de conexión)
         */
        EntityManager em = emf.createEntityManager();
        List<Usuario> listaUsuarios = em.createQuery("select usu from Usuario usu", Usuario.class).getResultList();

        listaUsuarios.forEach(System.out::println);
        System.out.println();

        // Una vez hecho, ES FUNDAMENTAL CERRAR LA CONEXIÓN A LA BBDD
        em.close();
    }
}

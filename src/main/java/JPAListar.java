import Entidades.Usuario;
import Utilidades.JPAem;
import jakarta.persistence.EntityManager;

import java.util.List;

public class JPAListar {
    public static void main(String[] args) {
        /* Vamos a listar los contenidos de la tabla de usuarios

            La primera operación a realizar es conectar con la BBDD.
            Para ello invocamos la EntityManagerFactory para que se cree la conexión con la BB y se cree una
            instancia (EntityManager) para manejar la consulta.
         */
        EntityManager em = JPAem.getEntityManager();  // conectamos con la BBDD y obtenemos una instancia de EntityManager
        // Ahora em contiene una lista de entidades (usuarios)
        //List<Usuario> usuarios = (List<Usuario>) em.createQuery("select usu from Usuario usu");
        List<Usuario> usuarios = em.createQuery("select usu from Usuario usu", Usuario.class).getResultList();

        // Listamos los usuarios
        usuarios.forEach(System.out::println);
        System.out.println();
        System.out.println();

        // Una vez hecho, ES FUNDAMENTAL CERRAR LA CONEXIÓN A LA BBDD
        em.close();
    }
}

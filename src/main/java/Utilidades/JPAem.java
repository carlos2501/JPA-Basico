package Utilidades;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAem {
    /* La variable emFactoryManager es final porque puede haber solo una emFactoryManager en la aplicación, y esta será
        la encargada de crear la conexión con la BBDD  UNA SOLA VEZ en la aplicación.

        Cuando se utiliza esta cale por primera vez, se crea una emFactoryManager y se abre la conexión.

        Posteriormente, con cada petición a la BBDD, la emFactoryManager crea una em (entity manager) para gestionar
        la petición.
     */
    private static final  EntityManagerFactory emf = buildEntManagerFactory() ;

    // El nombre es el que se indica en el archivo persistence.xml
    private static EntityManagerFactory buildEntManagerFactory(){
        return Persistence.createEntityManagerFactory("ejemploJPA");
    }

    // En cada llamada, la emf crea una em para gestionar la petición a la BBDD
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
import Entidades.Usuario;
import Utilidades.JPAem;
import jakarta.persistence.EntityManager;

import javax.swing.*;

public class JPAleeId {
    public static void main(String[] args) {
        // Preguntamos el id
        Long id = Long.valueOf(JOptionPane.showInputDialog("ID a buscar"));
        // Abrimos la conexión
        EntityManager em = JPAem.getEntityManager();
        // Usamos la función find de JPA que busca por la PK de la entidad. Si no se encuentra, find() devuelve null
        Usuario usu = em.find(Usuario.class, id);
        if (usu != null) {
            System.out.println(usu);
        } else {
            System.out.println("El usuario no existe.");
        }
        /* Si lo ejecutamos una segunda vez, se puede observar en la consola que no se ejecuta una
            segunda consulta SQL en la BBDD, ya que JPA metió los resultados de la primera en la caché.
            Esta segunda vez, al tratarse de la misma consulta, JPA busca los resultados en su caché y ahorra
            una llamada al servidor de BBDD.
         */
        Usuario usu2 = em.find(Usuario.class, id);
        if (usu2 != null) {
            System.out.println(usu2);
        } else {
            System.out.println("El usuario 2 no existe.");
        }
        // FUNDAMENTAL: cerramos la conexión
        em.close();
    }
}

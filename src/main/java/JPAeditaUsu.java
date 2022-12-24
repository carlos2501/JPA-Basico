import Entidades.Usuario;
import Utilidades.JPAem;
import jakarta.persistence.EntityManager;

import javax.swing.*;

public class JPAeditaUsu {
    public static void main(String[] args) {
        // Preguntamos el id
        Long id = Long.valueOf(JOptionPane.showInputDialog("ID a buscar"));
        // Abrimos la conexión
        EntityManager em = JPAem.getEntityManager();
        try {
            em.getTransaction().begin();
            // Usamos la función find de JPA que busca por la PK de la entidad. Si no se encuentra, find() devuelve null
            Usuario usu = em.find(Usuario.class, id);
            if (usu != null) {
                // editamos los datos
                usu.setUsuario(JOptionPane.showInputDialog("Usuario", usu.getUsuario()));
                usu.setClave((JOptionPane.showInputDialog("Clave", usu.getClave())));
                usu.setNombre(JOptionPane.showInputDialog("Nombre", usu.getNombre()));
                usu.setApellidos(JOptionPane.showInputDialog("Apellidos", usu.getApellidos()));
                usu.setPerfil(JOptionPane.showInputDialog("Perfil", (usu.getPerfil() == null || usu.getPerfil().length() == 0)?"usuario":usu.getPerfil()));
                em.persist(usu);
                em.getTransaction().commit();
                // comprobamos los cambios
                usu = em.find(Usuario.class, id);
                System.out.println(usu);
            } else {
                System.out.println("El usuario no existe.");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // FUNDAMENTAL: cerramos la conexión
            em.close();
        }
    }
}

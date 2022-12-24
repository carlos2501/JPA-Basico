import Entidades.Usuario;
import Utilidades.JPAem;
import jakarta.persistence.EntityManager;

import javax.swing.*;

public class JPANuevoUsuRapido {
    public static void main(String[] args) {
        EntityManager em = JPAem.getEntityManager();
        // Usamos un bloque try..catch para capturar los errores de BBDD
        try {
            // preguntamos solo el usuario y la clave
            String usuario = JOptionPane.showInputDialog("Usuario");
            String clave = JOptionPane.showInputDialog("clave");
            // para asegurar el éxito de la operación, usamos una transacción
            em.getTransaction().begin();

            Usuario usu = new Usuario(usuario, clave);
            em.persist(usu);
            em.getTransaction().commit();

            System.out.println("El id del nuevo usuario es: " + usu.getId());
            // comprobamos el nuevo registro
            usu = em.find(Usuario.class, usu.getId());
            System.out.println(usu);
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}

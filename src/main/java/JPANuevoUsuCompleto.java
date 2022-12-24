import Entidades.Usuario;
import Utilidades.JPAem;
import jakarta.persistence.EntityManager;

import javax.swing.*;

public class JPANuevoUsuCompleto {
    public static void main(String[] args) {
        EntityManager em = JPAem.getEntityManager();
        // Usamos un bloque try..catch para capturar los errores de BBDD
        try {
            // preguntamos solo el usuario y la clave
            String usuario = JOptionPane.showInputDialog("Usuario");
            String clave = JOptionPane.showInputDialog("clave");
            String nombre = JOptionPane.showInputDialog("Nombre");
            String apellidos = JOptionPane.showInputDialog("Apellidos");
            String perfil = JOptionPane.showInputDialog("Perfil");
            // para asegurar el éxito de la operación, usamos una transacción
            em.getTransaction().begin();

            Usuario usu = new Usuario();
            usu.setUsuario(usuario);
            usu.setClave(clave);
            usu.setNombre(nombre);
            usu.setApellidos(apellidos);
            usu.setPerfil(perfil);
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

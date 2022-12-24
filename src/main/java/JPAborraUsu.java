import Entidades.Usuario;
import Utilidades.JPAem;
import jakarta.persistence.EntityManager;

import javax.swing.*;

public class JPAborraUsu {

    public static void main(String[] args) {
        EntityManager em = JPAem.getEntityManager();
        Long id = Long.valueOf(JOptionPane.showInputDialog("Id del usuario a borrar"));
        // pedimos confirmación
        int Confirm = JOptionPane.showConfirmDialog(null,
                "Esta acción no se puede deshacer.\n¿Está seguro de querer borrar definitivamente el usuario?",
                "Confirme el borrado",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        try {
            if (Confirm == JOptionPane.YES_OPTION) {
                // borrar el registro
                Usuario u = em.find(Usuario.class, id);
                em.getTransaction().begin();
                em.remove(u);
                em.getTransaction().commit();
            } else {
                System.out.println("Ningún usuario eliminado.");
            }
        } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
        } finally {
                em.close();
        }
        //System.exit(0);
    }
}

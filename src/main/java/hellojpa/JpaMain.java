package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //영속
            Member member1 = new Member();
            Member member2 = new Member();
            member1.setName("A");
            member1.setRoleType(RoleType.ADMIN);

            member2.setName("B");
            member2.setRoleType(RoleType.USER);

            em.persist(member1);
            em.persist(member2);
            System.out.println("member1.getId() = " + member1.getId());
            System.out.println("===================================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

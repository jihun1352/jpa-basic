package jpabook.jpashop;

import hellojpa.RoleType;
import jpabook.jpashop.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Child child1 = new Child();
            Child child2 = new Child();
            child1.setName("323");
            child2.setName("chil2323d2");

            Parent parent = new Parent();
            parent.setName("parent!");
            parent.addChild(child1);
            parent.addChild(child2);
            // Parent엔티티에 cascade가 걸려있으므로 한번만 해주면된다.
            em.persist(parent);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

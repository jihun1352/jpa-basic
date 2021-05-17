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

            // 임베디드 타입인 주소
            Address address = new Address("city","street","1000");
            Address address2 = new Address("city","street","1000");

            System.out.println("address2 = " + (address.equals(address2)));

            Member member = new Member();
            member.setUsername("jpa");
            member.setAddress(address);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("jpa2");
            member2.setAddress(new Address("newCity","street","1000"));
            em.persist(member2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

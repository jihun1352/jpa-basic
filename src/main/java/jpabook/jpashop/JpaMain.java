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

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("jpa");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("===========================");
            // 위에서 영속성컨텍스트를 초기화 해주었고(em.clear), Member 엔티티에 지연로딩을 걸어놔서
            // findTeam.getClass()를 하면 프록시 값이 출력이 된다.
            System.out.println("team.getClass() = " + findTeam.getClass());
            findTeam.getName();   // 직접 team내의 값을 조회할때 select 쿼리가 실행됨.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

package jpabook.jpashop;

import hellojpa.RoleType;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Team;

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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            // Member이 주인이기 때문에 setTeam(team)을 넣어준다.
            Member member = new Member();
            member.setUsername("jpa");
            member.changeTeam(team);
            em.persist(member);

            //연관관계 편의 메소드로 member.setTeam(team);에서 team에도 같이 들어가기 때문에
            //아래 코드는 없어도 된다.
            //team.getMembers().add(member);

            //em.flush();
            //em.clear();

            // flush, clear을 안하면 em.find시 위의 em.persist로 인해 1차 캐쉬에 저장되어 있어서
            // 메모리상에서 조회를 하기 때문에 값이 없다.
            // member1의 사이즈가 0이 된다.
            Team team1 = em.find(Team.class, team.getId());
            List<Member> member1 = team1.getMembers();
            System.out.println("member1.size() = " + member1.size());
            for (Member m : member1) {
                m.getUsername();
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

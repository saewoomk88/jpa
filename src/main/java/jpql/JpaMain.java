package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setName("A");
            em.persist(team);
            Team team2 = new Team();
            team2.setName("A");
            em.persist(team2);
            Team team3 = new Team();
            team3.setName("B");
            em.persist(team3);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(team2);
            Member member3 = new Member();
            member3.setUsername("member3");
            member3.setTeam(team3);
            em.persist(member);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();
            System.out.println("----------------------------------------");
            int i = em.createQuery("update Member m set m.age =20")
                    .executeUpdate();

            System.out.println("resultCount = " + i);

            System.out.println("member.getAge() = " + member.getAge());
            System.out.println("member2.getAge() = " + member2.getAge());
            System.out.println("member3.getAge() = " + member3.getAge());


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
//        Member member = em.find(Member.class, 1L);
        emf.close();


    }

}

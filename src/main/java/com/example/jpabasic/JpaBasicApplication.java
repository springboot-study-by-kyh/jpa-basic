package com.example.jpabasic;

import com.example.jpabasic.domain.Book;
import com.example.jpabasic.jpql.Member;
import com.example.jpabasic.jpql.MemberDTO;
import com.example.jpabasic.jpql.Team;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class JpaBasicApplication {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest"); // 로딩 시점에 하나만 생성해줘야함.
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		// 트랜잭션 시작
		tx.begin();

		try {

			Team teamA = new Team();
			teamA.setName("teamA");
			em.persist(teamA);

			Team teamB = new Team();
			teamB.setName("teamB");
			em.persist(teamB);

			Member member1 = new Member();
			member1.setUsername("member1");
			member1.setTeam(teamA);
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("member2");
			member2.setTeam(teamA);
			em.persist(member2);

			Member member3 = new Member();
			member3.setUsername("member3");
			member3.setTeam(teamB);
			em.persist(member3);

			em.flush();
			em.clear();

			em.createNamedQuery("Member.findByUsername", Member.class)
				.setParameter("username", "회원1")
				.getResultList();

			// DB 쿼리가 날라가는 시점
			tx.commit();
		} catch (Exception e) {
			// 문제 발생 시 rollback 처리
			tx.rollback();
		} finally {
			// 작업 완료 후 닫아주기
			em.close();
		}
		emf.close();
	}
}
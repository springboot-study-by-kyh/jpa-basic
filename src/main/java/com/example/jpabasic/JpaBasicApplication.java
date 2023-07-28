package com.example.jpabasic;

import com.example.jpabasic.domain.Book;
import com.example.jpabasic.jpql.Member;
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

			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);

			Member res = em.createQuery("select m from Member m.username = : username", Member.class)
				.setParameter("username", "member1")
					.getSingleResult();

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
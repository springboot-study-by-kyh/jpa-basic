package com.example.jpabasic;

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

			// MemberEntity가 비영속 상태
			MemberEntity member = new MemberEntity();
			member.setId(100L);
			member.setName("HELLO JPA");

			// MemberEntity가 영속 상태 (Entity 수정시에 persist 호출을 하지 않는것이 맞음)
			em.persist(member);
			// em.detach() : 영속 상태에서 영구 제거

			// 1차 캐시에서 조회
			MemberEntity findMember = em.find(MemberEntity.class, 100L);

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
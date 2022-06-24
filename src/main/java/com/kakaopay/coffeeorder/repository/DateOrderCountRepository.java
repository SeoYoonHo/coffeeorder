package com.kakaopay.coffeeorder.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kakaopay.coffeeorder.domain.DateOrderCount;
import com.kakaopay.coffeeorder.dto.MostMenuDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional
public class DateOrderCountRepository {

	private final EntityManager em;

	public void save(DateOrderCount dateOrderCount) {
		em.persist(dateOrderCount);
	}

	public DateOrderCount findByMenuAndDate(Long menuId, LocalDate date) {
		List<DateOrderCount> dateOrderCountList;

		dateOrderCountList = em
				.createQuery("select d from DateOrderCount d where d.menu.id = :menuid and d.date = :date",
						DateOrderCount.class)
				.setParameter("menuid", menuId).setParameter("date", date).getResultList();

		if (dateOrderCountList == null || dateOrderCountList.size() == 0) {
			return null;
		} else {
			return dateOrderCountList.get(0);
		}
	}

	public List<MostMenuDTO> findMostCntMenuByDate(LocalDate date) {
		List<MostMenuDTO> dateOrderCountList;

		dateOrderCountList = em.createQuery(
				"select new com.kakaopay.coffeeorder.dto.MostMenuDTO(d.menu.id, SUM(d.orderCount)) from DateOrderCount d where d.date > :date group by d.menu.id order by 2 desc",
				MostMenuDTO.class).setParameter("date", date).getResultList();

		return dateOrderCountList;
	}

	public int deleteByMenuAndDate(Long menuId, LocalDate date) {
		int deleteCnt = em.createQuery("delete from DateOrderCount d where d.menu.id = :menuid and d.date = :date")
				.setParameter("menuid", menuId).setParameter("date", date).executeUpdate();

		return deleteCnt;
	}

}

package com.choongang.gb2023501.ybRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.domain.EduMaterials;

@Repository
public interface EduRepository extends JpaRepository<EduMaterials, Long> {

	void deleteByEmNum(int emNum);

	
}

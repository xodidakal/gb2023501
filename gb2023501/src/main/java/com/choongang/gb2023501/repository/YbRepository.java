package com.choongang.gb2023501.repository;

import java.util.Optional;

import com.choongang.gb2023501.domain.EduMaterials;

public interface YbRepository {

	Optional<EduMaterials> 		findByEduMaterials(int em_num);

	int 						updateByEduMaterials(EduMaterials eduMaterials);


	
}

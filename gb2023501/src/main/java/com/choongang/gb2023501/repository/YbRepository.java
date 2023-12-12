package com.choongang.gb2023501.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.choongang.gb2023501.domain.EduMaterials;
import com.choongang.gb2023501.domain.GameOrder;

public interface YbRepository {

	Optional<EduMaterials> 		findByEduMaterials(int em_num);

	int 						updateByEduMaterials(EduMaterials eduMaterials);

	List<EduMaterials> 			findAll();

	List<GameOrder> 			findAllSales();

	List<EduMaterials> 			findByEduMaterialsContaining(String keyword, String type);
	
}

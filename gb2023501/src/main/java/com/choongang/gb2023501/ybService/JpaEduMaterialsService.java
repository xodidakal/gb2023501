package com.choongang.gb2023501.ybService;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.choongang.gb2023501.domain.EduMaterials;
import com.choongang.gb2023501.repository.YbRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional  // -> 원자성,commit or rollback이 되어야함
@RequiredArgsConstructor
public class JpaEduMaterialsService {
	private final YbRepository yr;
	
	public Optional<EduMaterials> findByEduMaterials(int em_num) {
		System.out.println("YbJpaEduMaterialsService findByEduMaterials start...");
		System.out.println("YbJpaEduMaterialsService findByEduMaterials em_num -> " + em_num);
			
		Optional<EduMaterials> findByEduMaterials =  yr.findByEduMaterials(em_num);
		return findByEduMaterials;
	}

	public void updateByEduMaterials(EduMaterials eduMaterials) {
		System.out.println("YbJpaEduMaterialsService updateByEduMaterials start...");
		System.out.println("YbJpaEduMaterialsService updateByEduMaterials em_num -> " + eduMaterials);
		
		yr.updateByEduMaterials(eduMaterials);
		return;
	}

}

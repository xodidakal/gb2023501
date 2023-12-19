package com.choongang.gb2023501.ybRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.domain.EduMaterials;
import com.choongang.gb2023501.domain.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {


	
}

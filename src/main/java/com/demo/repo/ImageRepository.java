package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.ImgData;

@Repository
public interface ImageRepository extends JpaRepository<ImgData, Long> {
	
	
}

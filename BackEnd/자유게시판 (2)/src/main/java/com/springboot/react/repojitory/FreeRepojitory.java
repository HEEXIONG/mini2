package com.springboot.react.repojitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.react.model.Free;

@Repository
public interface FreeRepojitory extends JpaRepository<Free, Long>{

	

	
}

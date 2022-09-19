package com.springboot.react.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.react.model.Free;
import com.springboot.react.repojitory.FreeRepojitory;

@Service
public class FreeServiceImpl implements FreeService{

	@Autowired
	private FreeRepojitory dao;
	
	
	
	  @Override//리스트 
	  public List<Free> findAll() { 
		  return (List<Free>) dao.findAll(); 
		  }



	@Override//글작성
	public Free save(Free free) {
		
		return dao.save(free);
	}


	@Transactional(readOnly = true)
	@Override//상세보기
	public Free select(Long id) {
		
		return dao.findById(id)
				.orElseThrow(null);
	}


	
	@Override//삭제
	public String delete(Long id) {
		dao.deleteById(id);
		return "ok";
	}


	@Transactional
	@Override
	public Free update(Long id, Free free) {
		Free free1 = dao.findById(id)
				.orElseThrow(null);
		
		free1.setTitle(free.getTitle());
		free1.setWriter(free.getWriter());
		free1.setContent(free.getContent());
		
		return free1;
		
	}
	 
	  
	  
	
	

	


	
	

	
	
	
}

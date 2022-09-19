package com.springboot.react.service;

import java.util.List;

import com.springboot.react.model.Free;

public interface FreeService {

	

	 List<Free> findAll(); //글목록
	
	public Free save(Free free);//글쓰기
	
	public Free select(Long id);//상세보기
	
	public String delete(Long id);//글 삭제
	
	public Free update(Long id,Free free);//글수정
}

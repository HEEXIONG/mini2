package com.springboot.react.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.react.model.Free;
import com.springboot.react.service.FreeService;
import com.sun.org.apache.regexp.internal.recompile;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class FreeController {

	@Autowired
	private FreeService freeService;
	
	
	
	
	@RequestMapping(value = "/hellospring", method = RequestMethod.GET)
    public String helloSpring() {
        return "Hello Spring";
    }
	
	
	
	  @RequestMapping(value = "/findAll", method = RequestMethod.GET) public
	  List<Free> findAll(){ return freeService.findAll(); }
	 
	  
	  
	 @PostMapping("/insert")//글쓰기
	  public ResponseEntity<?> save(@RequestBody Free free){
		 return new ResponseEntity<>(freeService.save(free), HttpStatus.CREATED);
		  
	  }
	 
	 @GetMapping("/view/{id}")//상세보기
	 public ResponseEntity<?> findById(@PathVariable Long id) {
		 
		 return new ResponseEntity<>(freeService.select(id),HttpStatus.OK);
	 }
	 
	 
	@DeleteMapping("/delete/{id}")//글삭제
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		freeService.delete(id);
		return new ResponseEntity<>(freeService.delete(id),HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")//글수정
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Free free){
		freeService.update(id, free);
		return new ResponseEntity<>(freeService.update(id, free),HttpStatus.OK);
	}
	
	
}

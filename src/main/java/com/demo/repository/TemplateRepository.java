package com.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

	public Page<Template> findByTemplateName( String templateName,Pageable pageable);	
	Template findByTemplateName(String templateName);


}

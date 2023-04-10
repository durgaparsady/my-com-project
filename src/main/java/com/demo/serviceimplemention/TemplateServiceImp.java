package com.demo.serviceimplemention;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.dto.AddTemplateDTO;
import com.demo.dto.UpdateTemplateDTO;
import com.demo.model.Template;
import com.demo.repository.TemplateRepository;
import com.demo.service.TemplateService;

@Service
@Transactional
public class TemplateServiceImp implements TemplateService {

	@Autowired
	TemplateRepository templateRepository;

	@Override
	public Template addTemplate(AddTemplateDTO request) {
		Template template = new Template();
		template.setTemplateName(request.getTemplateName());
		template.setContent(request.getContent());
		template.setHeader(request.getHeader());
		template.setFooter(request.getFooter());
		template.setCreatedBy("0");
		template.setDateCreated(new Timestamp(System.currentTimeMillis()));
		template.setDateUpdated(new Timestamp(System.currentTimeMillis()));
		return templateRepository.save(template);
		
		
	}

	@Override
	public Page<Template> getTemplateList(Integer pageNo, Integer pageSize, String sortBy, String keyword) {
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
		if (!keyword.isBlank()) {
			return templateRepository.findByTemplateName(keyword, pageable);
		}
		return templateRepository.findAll(pageable);
	}

	@Override
	public void deleteTemplate(Long id) {
		templateRepository.deleteById(id);
	}

	@Override
	public Template getTemplateName(String templateName) {
		return templateRepository.findByTemplateName(templateName);	
	}

	@Override
	public void editTemplate(UpdateTemplateDTO request) {
		Template template = templateRepository.findById(request.getId()).orElse(null);
		if(template!=null) {
		template.setContent(request.getContent());
		template.setHeader(request.getHeader());
		template.setFooter(request.getFooter());
		template.setCreatedBy("0");
		template.setDateUpdated(new Timestamp(System.currentTimeMillis()));
		templateRepository.save(template);
		}
	}

	@Override
	public Template getById(Long id) {
		return templateRepository.findById(id).orElse(null);
	}

}

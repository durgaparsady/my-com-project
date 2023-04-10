package com.demo.service;

import org.springframework.data.domain.Page;

import com.demo.dto.AddTemplateDTO;
import com.demo.dto.UpdateTemplateDTO;
import com.demo.model.Template;

public interface TemplateService {

	Template addTemplate(AddTemplateDTO request);

	Page<Template> getTemplateList(Integer pageNo, Integer pageSize, String sortBy, String keyword);

	void deleteTemplate(Long id);

	void editTemplate(UpdateTemplateDTO request);

	Template getTemplateName(String templateName);

	Template getById(Long id);
}

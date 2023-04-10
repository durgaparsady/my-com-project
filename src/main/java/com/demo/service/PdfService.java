package com.demo.service;

import java.util.List;
import java.util.Map;

import com.demo.dto.PdfDTO;

public interface PdfService  {

	List<Map<String, Object>> generatePdf(PdfDTO request) throws Exception ;

	String getTemplateName(Long id);


}

//package com.demo.dto;
//
//
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.demo.dto.PdfDTO;
//import com.demo.model.Template;
//import com.demo.repository.TemplateRepository;
//import com.demo.service.PdfService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.pd4ml.PD4ML;
//
//@Service
//public class pdfServiceBackup implements PdfService {
//
//	@Value("${file-upload-location}")
//	private String fileUploadLocation;
//
//	@Autowired
//	TemplateRepository templateRepository;
//
//	@Override
//	public List<Map<String, Object>> generatePdf(PdfDTO request) throws Exception {
//		Object pdfName = "";
//		Long timestamp = System.currentTimeMillis();
//		Template templateData = templateRepository.findByTemplateName(request.getTitle());
//		String htmlContentBefore = templateData.getContent();
//		String htmlContentAfter = htmlContentBefore.replace("$[break]", "<pd4ml:page.break>");
//		String jsonText = request.getJsonText().toString();
//		List<Map<String, Object>> templateList = new ObjectMapper().readValue(jsonText, List.class);
//
//		for (Map<String, Object> templateObj : templateList) {
//			PD4ML pd4ml = new PD4ML();
//			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(htmlContentAfter.getBytes());
//
//			HashMap<String, String> hashMap = new HashMap<>();
//			Set<String> keySet = templateObj.keySet();
//			Iterator<String> k = keySet.iterator();
//			pdfName = templateObj.get(k.next());
//			for (String data : keySet) {
//				hashMap.put(data, templateObj.get(data).toString());
//			}
//			File file = new File(
//					fileUploadLocation + pdfName + timestamp + "_" + RandomStringUtils.randomAlphabetic(4) + ".pdf");
//			FileOutputStream fileOutputStream = new FileOutputStream(file);
//			pd4ml.useTTF("c:/Windows/Fonts/", "Mangal,times,courier");
//			pd4ml.setPageHeader(templateData.getHeader(), 30, "1+");
//			pd4ml.setPageFooter(templateData.getFooter(), 30, "1+");
//			pd4ml.setDynamicData(hashMap);
//			pd4ml.readHTML(byteArrayInputStream);
//			pd4ml.writePDF(fileOutputStream);
//		}
//		return templateList;
//	}
//
//	@Override
//	public String getTemplateName(Long id) {
//		Template template = templateRepository.findById(id).orElse(null);
//		return template.getTemplateName();
//	}
//
//	@Override
//	public List<Map<String, Object>> generatePdfPostman(PdfDTO request) throws Exception {
//		Object pdfName = "";
//		Long timestamp = System.currentTimeMillis();
//		Template templateData = templateRepository.findByTemplateName(request.getTitle());
//		String htmlContentBefore = templateData.getContent();
//		String htmlContentAfter = htmlContentBefore.replace("$[break]", "<pd4ml:page.break>");
//
//		List<Map<String, Object>> templateList = (List<Map<String, Object>>) request.getJsonText();
//
//		for (Map<String, Object> templateObj : templateList) {
//			PD4ML pd4ml = new PD4ML();
//			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(htmlContentAfter.getBytes());
//
//			HashMap<String, String> hashMap = new HashMap<>();
//			Set<String> keySet = templateObj.keySet();
//			Iterator<String> k = keySet.iterator();
//			pdfName = templateObj.get(k.next());
//			for (String data : keySet) {
//				hashMap.put(data, templateObj.get(data).toString());
//			}
//			File file = new File(
//					fileUploadLocation + pdfName + timestamp + "_" + RandomStringUtils.randomAlphabetic(4) + ".pdf");
//			FileOutputStream fileOutputStream = new FileOutputStream(file);
//			pd4ml.useTTF("c:/Windows/Fonts/", "Mangal,times,courier");
//			pd4ml.setPageHeader(templateData.getHeader(), 30, "1+");
//			pd4ml.setPageFooter(templateData.getFooter(), 30, "1+");
//			pd4ml.setDynamicData(hashMap);
//			pd4ml.readHTML(byteArrayInputStream);
//			pd4ml.writePDF(fileOutputStream);
//		}
//		return templateList;
//	}
//
//}
//
////package com.demo.serviceimplemention;
////
////import java.io.ByteArrayInputStream;
////import java.io.File;
////import java.io.FileOutputStream;
////import java.util.HashMap;
////import java.util.Iterator;
////import java.util.List;
////import java.util.Map;
////import java.util.Set;
////
////import org.apache.commons.lang3.RandomStringUtils;
////import org.json.JSONObject;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.stereotype.Service;
////
////import com.demo.dto.PdfDTO;
////import com.demo.model.Template;
////import com.demo.repository.TemplateRepository;
////import com.demo.service.PdfService;
////import com.pd4ml.PD4ML;
////
////@Service
////public class PdfServiceImp implements PdfService {
////
////	@Value("${file-upload-location}")
////	private String fileUploadLocation;
////
////	@Autowired
////	TemplateRepository templateRepository;
////
////	@Override
////	public List<Map<String, Object>> generatePdf(PdfDTO request) throws Exception {
////		Object pdfName = "";
////	
////		Long timestamp = System.currentTimeMillis();
////		Template templateData = templateRepository.findByTemplateName(request.getTitle());
////		String htmlContentBefore = templateData.getContent();
////		String htmlContentAfter = htmlContentBefore.replace("break", "<pd4ml:page.break>");
////		
////
////		List<Map<String, Object>> templateList =  (List<Map<String, Object>>) request.getJsonText();//(List<Map<String, Object>>) request.getJsonText();
////
////		for (Map<String, Object> templateObj : templateList) {
////			PD4ML pd4ml = new PD4ML();
////			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(htmlContentAfter.getBytes());
////
////			HashMap<String, String> hashMap = new HashMap<>();
////			Set<String> keySet = templateObj.keySet();
////			Iterator<String> k = keySet.iterator();
////			pdfName = templateObj.get(k.next());
////			for (String data : keySet) {
////				hashMap.put(data, templateObj.get(data).toString());
////			}
////			File file = new File(
////					fileUploadLocation + pdfName + timestamp + "_" + RandomStringUtils.randomAlphabetic(4) + ".pdf");
////			FileOutputStream fileOutputStream = new FileOutputStream(file);
////			pd4ml.useTTF("c:/Windows/Fonts/", "Mangal,times,courier");
////			pd4ml.setPageHeader(templateData.getHeader(), 30, "1+");
////    		pd4ml.setPageFooter(templateData.getFooter(), 30, "1+");
////			pd4ml.setDynamicData(hashMap);
////			pd4ml.readHTML(byteArrayInputStream);
////			pd4ml.writePDF(fileOutputStream);
////		}
////		return templateList;
////	}
////
////	@Override
////	public String getTemplateName(Long id) {
////		Template template = templateRepository.findById(id).orElse(null);
////		return template.getTemplateName();
////	}
////
////}
//
////package com.demo.serviceimplemention;
////
////import java.io.ByteArrayInputStream;
////import java.io.File;
////import java.io.FileOutputStream;
////import java.util.HashMap;
////import java.util.Iterator;
////import java.util.List;
////import java.util.Map;
////import java.util.Set;
////
////import org.apache.commons.lang3.RandomStringUtils;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.stereotype.Service;
////
////import com.demo.dto.PdfDTO;
////import com.demo.model.Template;
////import com.demo.repository.TemplateRepository;
////import com.demo.service.PdfService;
////import com.fasterxml.jackson.databind.ObjectMapper;
////import com.pd4ml.PD4ML;
////
////@Service
////public class PdfServiceImp implements PdfService {
////
////	@Value("${file-upload-location}")
////	private String fileUploadLocation;
////
////	@Autowired
////	TemplateRepository templateRepository;
////
////	@Override
////	public List<Map<String, Object>> generatePdf(PdfDTO request) throws Exception {
////		Object pdfName = "";
////		ObjectMapper mapper = new ObjectMapper();
//////		System.out.println("header : "+request.getHeader());
////		String jsonText = request.getJsonText();
////		Long timestamp = System.currentTimeMillis();
////		Template templateData = templateRepository.findByTemplateName(request.getTitle());
////		String htmlContentBefore = templateData.getContent();
////		String htmlContentAfter = htmlContentBefore.replace("$[break]", "<pd4ml:page.break>");
////
////		Map<String, List<Map<String, Object>>> templateMap = mapper.readValue(jsonText, HashMap.class);
////		List<Map<String, Object>> templateList = templateMap.get(templateData.getTemplateName());
////
////		for (Map<String, Object> templateObj : templateList) {
////			PD4ML pd4ml = new PD4ML();
////			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(htmlContentAfter.getBytes());
////
////			HashMap<String, String> hashMap = new HashMap<>();
////			Set<String> keySet = templateObj.keySet();
////			Iterator<String> k = keySet.iterator();
////			pdfName = templateObj.get(k.next());
////			for (String data : keySet) {
////				hashMap.put(data, templateObj.get(data).toString());
////			}
////			File file = new File(
////					fileUploadLocation + pdfName + timestamp + "_" + RandomStringUtils.randomAlphabetic(4) + ".pdf");
////			FileOutputStream fileOutputStream = new FileOutputStream(file);
////			pd4ml.useTTF("c:/Windows/Fonts/", "Mangal,times,courier");
//// 		pd4ml.setPageHeader(templateData.getHeader(), 30, "1+");
//// 			pd4ml.setPageFooter(templateData.getFooter(), 30, "1+");
////			pd4ml.setDynamicData(hashMap);
////			pd4ml.readHTML(byteArrayInputStream);
////			pd4ml.writePDF(fileOutputStream);
////		}
////		return templateList;
////	}
////
////	@Override
////	public String getTemplateName(Long id) {
////		Template template = templateRepository.findById(id).orElse(null);
////		return template.getTemplateName();
////	}
////
////}

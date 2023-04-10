package com.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.dto.PdfDTO;
import com.demo.service.PdfService;

@RestController
public class PdfController {

	@Autowired
	PdfService pdfService;

	@RequestMapping("/pdfTemplatePage/{id}")
	public ModelAndView getPDFTemplate(@PathVariable(value = "id") Long id, Model model) {
		PdfDTO pdfRequest = new PdfDTO();
		pdfRequest.setTemplateName(pdfService.getTemplateName(id));
		model.addAttribute("pdfRequest", pdfRequest);
		return new ModelAndView("pdf_template");
	}

	@PostMapping("/pdfGenerate")	
	public ModelAndView pdfGenerateCode(@ModelAttribute PdfDTO pdfData, Model model, HttpSession session) {
		try {
			if (pdfData.getJsonText()==null) {
				session.setAttribute("msg", "Please Enter Some Json array ");
				model.addAttribute("pdfRequest", pdfData);
				return new ModelAndView("pdf_template");
			}			
			model.addAttribute("mapList", pdfService.generatePdf(pdfData));
			session.setAttribute("msg", "PDF Created ");
			return new ModelAndView("pdf_data_list");			
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("msg", "Please enter valid json array ");
			model.addAttribute("pdfRequest", pdfData);
			return new ModelAndView("pdf_template");
		}
	}
	
	//----------------------Postman Api--------------------------------
	@PostMapping("/generatePdf")	
	public ModelAndView generatePdf(@RequestBody PdfDTO pdfData, Model model, HttpSession session) {
		try {
			if (pdfData.getJsonText()==null) {
				session.setAttribute("msg", "Please Enter Some Json array ");
				model.addAttribute("pdfRequest", pdfData);
				return new ModelAndView("pdf_template");
			}			
			model.addAttribute("mapList",pdfService.generatePdf(pdfData));
			session.setAttribute("msg", "PDF Created ");
			return new ModelAndView("pdf_data_list");			
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("msg", "Please enter valid json array ");
			model.addAttribute("pdfRequest", pdfData);
			return new ModelAndView("pdf_template");
		}
	}	
}

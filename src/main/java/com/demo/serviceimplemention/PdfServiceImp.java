package com.demo.serviceimplemention;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.demo.dto.PdfDTO;
import com.demo.model.Template;
import com.demo.repository.TemplateRepository;
import com.demo.service.PdfService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pd4ml.PD4ML;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Service
public class PdfServiceImp implements PdfService {

	@Value("${file-upload-location}")
	private String fileUploadLocation;

	@Autowired
	TemplateRepository templateRepository;

	@Override
	public List<Map<String, Object>> generatePdf(PdfDTO request) throws Exception {
		String jsonText = "";
		Object pdfName = "";
		Long timestamp = System.currentTimeMillis();
		Template templateData = templateRepository.findByTemplateName(request.getTemplateName());
		String htmlContentBefore = templateData.getContent();
		String htmlContentAfter = htmlContentBefore.replace("$[break]", "<pd4ml:page.break>");

		if (!isJSONValid(request.getJsonText().toString())) {
			jsonText = new ObjectMapper().writeValueAsString(request.getJsonText());
		} else {
			jsonText = request.getJsonText().toString();
		}

		List<Map<String, Object>> jsonTextList = new ObjectMapper().readValue(jsonText, List.class);
		for (Map<String, Object> jsonTextMap : jsonTextList) {
			Document document = Jsoup.parse(htmlContentAfter);
			PD4ML pd4ml = new PD4ML();

			HashMap<String, String> hashMap = new HashMap<>();
			Set<String> keySet = jsonTextMap.keySet();
			Iterator<String> keyItr = keySet.iterator();
			pdfName = (String) jsonTextMap.get(keyItr.next());
			int index = 0;
			for (String key : keySet) {

				if (jsonTextMap.get(key) instanceof ArrayList) {
					List<Map<String, Object>> listData = (List<Map<String, Object>>) jsonTextMap.get(key);
					for (Map<String, Object> listObject : listData) {

						Set<String> listKeySet = listObject.keySet();
						Element table = document.select("table").get(index);
						Elements rows = table.select("tr");
						Element lastRow = rows.last();
						Element newRow = new Element("tr");

						for (String listKey : listKeySet) {
							Element idCell = new Element("td").text(listObject.get(listKey).toString());
							newRow.appendChild(idCell);
							lastRow.after(newRow);						}
						lastRow.after(newRow);					}
					index++;
				}
				hashMap.put(key, jsonTextMap.get(key).toString());
			}

			try (FileOutputStream fileOutputStream = new FileOutputStream(new File(
					fileUploadLocation + pdfName + timestamp + "_" + RandomStringUtils.randomAlphabetic(4) + ".pdf"))) {
				pd4ml.useTTF("c:/Windows/Fonts/", "Mangal,times,courier");
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(document.toString().getBytes());
				pd4ml.setPageHeader(templateData.getHeader(), 30, "1+");
     			pd4ml.setPageFooter(templateData.getFooter(), 30, "1+");
				pd4ml.setDynamicData(hashMap);
				pd4ml.readHTML(byteArrayInputStream);
				pd4ml.writePDF(fileOutputStream);
			

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonTextList;

	}

	@Override
	public String getTemplateName(Long id) {
		Template template = templateRepository.findById(id).orElse(null);
		return template.getTemplateName();
	}

	public static boolean isJSONValid(String jsonInString) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.readTree(jsonInString);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}

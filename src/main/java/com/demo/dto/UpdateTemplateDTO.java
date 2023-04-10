package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTemplateDTO {
	private Long id;
	private String templateName;
	private String content;
	private String header;
	private String footer;
}
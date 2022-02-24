package com.example.demo.info;

import java.sql.Date;

import lombok.Data;

@Data
public class Study {

	private int idx;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
}

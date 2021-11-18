package com.example.demo.controller;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.form.LoginForm;
@Controller
public class toukouController {
	@Autowired
	   JdbcTemplate jdbcTemplate;
	//URLは@RequstMappingのpathで決定する
	//例えば「http://localhost:8080/sample2」というURLにしたいなら
	//@RequestMapping(path = "/sample2", method = RequestMethod.GET)
	//と打つ
	@RequestMapping(path = "/toukou", method = RequestMethod.GET)
	public String viewLogin(HttpSession session) {
		List<Map<String,Object>> list;
	       list = jdbcTemplate.queryForList("select * from toukou");
	       session.setAttribute("tokoList", list);
	       list = jdbcTemplate.queryForList("select IMGPATH from user_image where NAME = ?",session.getAttribute("NAME"));
	       session.setAttribute("gazou", list);
	       //model
	       return "session/top";
	}
		@RequestMapping(path = "/toukou", method = RequestMethod.POST)
		public String insert(String toko,HttpSession session) {
			if(toko.isEmpty()) {
				return "redirect:/toukou";
			}
			else {
			jdbcTemplate.update("INSERT INTO toukou(NAME,TOKO) VALUES(?,?)",session.getAttribute("NAME"),toko);
			return "redirect:/toukou";
			}
		}
	}

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
public class foroController {
	@Autowired
	   JdbcTemplate jdbcTemplate;
	//URLは@RequstMappingのpathで決定する
	//例えば「http://localhost:8080/sample2」というURLにしたいなら
	//@RequestMapping(path = "/sample2", method = RequestMethod.GET)
	//と打つ
	@RequestMapping(path = "/foro", method = RequestMethod.GET)
	public String viewLogin(HttpSession session) {
		List<Map<String,Object>> list;
	       list = jdbcTemplate.queryForList("select AITE from foro where JIBUN = ?",session.getAttribute("NAME"));
	       session.setAttribute("foroList", list);
	       //model
	       return "session/foro";
	}
		@RequestMapping(path = "/foro", method = RequestMethod.POST)
		public String insert(HttpSession session) {
			List<Map<String,Object>> list;
		       list = jdbcTemplate.queryForList("select * from foro where JIBUN = ? and AITE = ?",session.getAttribute("NAME"),session.getAttribute("NAME2"));
		       if(list.isEmpty()) {
			jdbcTemplate.update("INSERT INTO foro(JIBUN,AITE) VALUES(?,?)",session.getAttribute("NAME"),session.getAttribute("NAME2"));
			session.setAttribute("error","追加しました");
		       }else {
		    	   session.setAttribute("error","すでについかしています。");
		       }
			return "session/top2";
	}
}

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
public class foroController2 {
	@Autowired
	   JdbcTemplate jdbcTemplate;
	//URLは@RequstMappingのpathで決定する
	//例えば「http://localhost:8080/sample2」というURLにしたいなら
	//@RequestMapping(path = "/sample2", method = RequestMethod.GET)
	//と打つ
	@RequestMapping(path = "/foro2", method = RequestMethod.GET)
	public String viewLogin(HttpSession session) {
		List<Map<String,Object>> list;
	       list = jdbcTemplate.queryForList("select AITE from foro where JIBUN = ?",session.getAttribute("NAME"));
	       session.setAttribute("foroList", list);
	       //model
	       return "session/foro";
	}
		@RequestMapping(path = "/foro2", method = RequestMethod.POST)
		public String insert(HttpSession session) {
			List<Map<String,Object>> list;
		       list = jdbcTemplate.queryForList("select * from foro where JIBUN = ? and AITE = ?",session.getAttribute("NAME"),session.getAttribute("NAME2"));
		       if(list.isEmpty()) {
			session.setAttribute("error","フォローしてません");
		       }else {
		    	   jdbcTemplate.update("DELETE FROM `foro` WHERE AITE = ?",session.getAttribute("NAME2"));
		    	   session.setAttribute("error","解除しました");
		       }
			return "session/top2";
	}
}

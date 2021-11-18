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
public class DmController {
	@Autowired
	   JdbcTemplate jdbcTemplate;
	//URLは@RequstMappingのpathで決定する
	//例えば「http://localhost:8080/sample2」というURLにしたいなら
	//@RequestMapping(path = "/sample2", method = RequestMethod.GET)
	//と打つ
	@RequestMapping(path = "/dm", method = RequestMethod.GET)
	public String viewLogin(HttpSession session) {
		List<Map<String,Object>> list;
	       list = jdbcTemplate.queryForList("select NAME from toukou where NAME = ?",session.getAttribute("NAME2"));
	       session.setAttribute("dmList", list);
	       list = jdbcTemplate.queryForList("select * from user_image where NAME = ?",session.getAttribute("NAME2"));
	       session.setAttribute("gazou2", list);
	       list = jdbcTemplate.queryForList("select * from dm where (JIBUN = ? and AITE = ?) or (JIBUN = ? and AITE = ?)",session.getAttribute("NAME"),session.getAttribute("NAME2"),session.getAttribute("NAME2"),session.getAttribute("NAME"));
	       session.setAttribute("dmList2", list);
	       System.out.println("1");
	       //model
	       return "session/dm";
	}
		@RequestMapping(path = "/dm", method = RequestMethod.POST)
		public String insert(String dmnaiyou,HttpSession session) {
			jdbcTemplate.update("INSERT INTO dm(JIBUN,AITE,NAIYOU) VALUES(?,?,?)",session.getAttribute("NAME"),session.getAttribute("NAME2"),dmnaiyou);
			return "redirect:/dm";
	}
}

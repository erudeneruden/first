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
public class toukou2Controller {
	@Autowired
	   JdbcTemplate jdbcTemplate;
	//URLは@RequstMappingのpathで決定する
	//例えば「http://localhost:8080/sample2」というURLにしたいなら
	//@RequestMapping(path = "/sample2", method = RequestMethod.GET)
	//と打つ
		@RequestMapping(path = "/toukou2", method = RequestMethod.POST)
		public String insert(String kennsaku,HttpSession session) {
			List<Map<String,Object>> list;
			if(kennsaku.isEmpty()) {
				return "redirect:/toukou";
			}else {
		       list = jdbcTemplate.queryForList("select * from toukou where NAME = ?",kennsaku);
		       session.setAttribute("tokoList2", list);
		       session.setAttribute("NAME2", list.get(0).get("NAME"));
		       session.setAttribute("error","");
			return "session/top2";
	}
		}
}

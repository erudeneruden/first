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
public class LoginController {
	@Autowired
	   JdbcTemplate jdbcTemplate;
	//URLは@RequstMappingのpathで決定する
	//例えば「http://localhost:8080/sample2」というURLにしたいなら
	//@RequestMapping(path = "/sample2", method = RequestMethod.GET)
	//と打つ
	@RequestMapping(path = "/aiueo", method = RequestMethod.GET)
	public String viewLogin(HttpSession session) {
		//表示するHTMLはreturnの後ろに書く。
		session.setAttribute("Id", "");
		session.setAttribute("NAME", "");
		session.setAttribute("NAME2", "");
		session.setAttribute("foroList", "");
		session.setAttribute("dmList", "");
		session.setAttribute("dmList2", "");
		session.setAttribute("tokoList", "");
		session.setAttribute("tokoList2", "");
		return "/session/zenki";
	}
		@RequestMapping(path = "/login", method = RequestMethod.POST)
		public String doLogin(LoginForm loginForm,HttpSession session) {
			List<Map<String, Object>> list;
		       list = jdbcTemplate.queryForList("select * from user where ID= ? and PASSWORD = ?",loginForm.getID(),loginForm.getPASSWORD());
			if(list.isEmpty()) {
				return "redirect:/login";
			}
			else {
			session.setAttribute("Id", list);
			session.setAttribute("NAME", list.get(0).get("NAME"));
			return "redirect:/toukou";
			}
			
	}
}

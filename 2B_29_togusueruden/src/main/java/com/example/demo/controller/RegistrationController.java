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
public class RegistrationController {
	@Autowired
	   JdbcTemplate jdbcTemplate;
	//URLは@RequstMappingのpathで決定する
	//例えば「http://localhost:8080/sample2」というURLにしたいなら
	//@RequestMapping(path = "/sample2", method = RequestMethod.GET)
	//と打つ
	@RequestMapping(path = "/registration", method = RequestMethod.GET)
	public String viewLogin() {
		//表示するHTMLはreturnの後ろに書く。
		return "/session/registration";
	}
		@RequestMapping(path = "/registration", method = RequestMethod.POST)
		public String insert(String userId2 , String name2, String password2,String jikosyoukai,HttpSession session) {
			List<Map<String, Object>> list;
		       list = jdbcTemplate.queryForList("select * from user where ID= ? and NAME = ?",userId2,name2);
		       if(list.isEmpty()) {
		    	   jdbcTemplate.update("INSERT INTO user(ID, NAME, PASSWORD, PROFILE) VALUES(?,?,?,?)", userId2, name2,password2,jikosyoukai);
					return "redirect:/login";
		    	   
		       }else {
		    	   return "redirect:/login";
		       }
	}
}

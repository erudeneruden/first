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
public class ProfileController {
	@Autowired
	   JdbcTemplate jdbcTemplate;
	//URLは@RequstMappingのpathで決定する
	//例えば「http://localhost:8080/sample2」というURLにしたいなら
	//@RequestMapping(path = "/sample2", method = RequestMethod.GET)
	//と打つ
	@RequestMapping(path = "/profile", method = RequestMethod.GET)
	public String viewLogin() {
		//表示するHTMLはreturnの後ろに書く。
		return "/session/profile";
	}
		@RequestMapping(path = "/profile", method = RequestMethod.POST)
		public String insert(String imgpath, HttpSession session) {
			String imgpath2=("img/"+imgpath+".jpg");
			System.out.println(imgpath2);
			List<Map<String,Object>> list;
		       list = jdbcTemplate.queryForList("select NAME from user_image where NAME =?",session.getAttribute("NAME"));
			if(list.isEmpty()) {
			jdbcTemplate.update("INSERT INTO user_image(NAME, IMGPATH) VALUES(?,?)", session.getAttribute("NAME"),imgpath2);
			}
	    	   else {
	    		   jdbcTemplate.update("UPDATE user_image SET NAME=?,IMGPATH=?",session.getAttribute("NAME"),imgpath2);
	    	   }
			return "redirect:/toukou";
	}
}

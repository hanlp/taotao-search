package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import com.taotao.util.ExceptionUtil;
import com.taotao.util.TaotaoResult;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;

	@RequestMapping("/query")
	@ResponseBody
	public TaotaoResult getItemSearch(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "60") int rows, @RequestParam(value = "q") String queryString) {
		try {
			// 解决乱码
			queryString = new String(queryString.getBytes("ISO8859-1"), "UTF-8");
			SearchResult result = searchService.search(queryString, page, rows);
			return TaotaoResult.ok(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}

package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.search.service.ItemService;
import com.taotao.util.TaotaoResult;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/manager/importall")
	@ResponseBody
	public TaotaoResult importItemAll() {
		return itemService.importItemAll();// 调用service方法
	}
}

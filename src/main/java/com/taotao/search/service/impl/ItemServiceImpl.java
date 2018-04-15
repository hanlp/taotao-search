package com.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;
import com.taotao.util.ExceptionUtil;
import com.taotao.util.TaotaoResult;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;

	@Override
	public TaotaoResult importItemAll() {

		try {
			// 从数据库里查询出数据
			List<Item> list = itemMapper.getItemList();
			// 遍历list,把数据插入到索引库中
			// 需要solrServer
			for (Item item : list) {
				// 作用域中添加数据
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", item.getId());
				document.addField("item_title", item.getTitle());
				document.addField("item_sell_point", item.getSell_point());
				document.addField("item_price", item.getPrice());
				document.addField("item_image", item.getImage());
				document.addField("item_desc", item.getItem_desc());
				document.addField("item_category_name", item.getName());// 分类名称
				solrServer.add(document);// 添加索引库
			}
			solrServer.commit();// 提交事务
		} catch (Exception e) {
			e.printStackTrace();
			// 捕获异常
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}

		return TaotaoResult.ok();
	}

}

package com.taotao.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolr {

	public static void main(String[] args) throws SolrServerException, IOException {
		/*
		 * ApplicationContext context = new ClassPathXmlApplicationContext(
		 * "classpath:spring/applicationContext-*.xml"); ItemMapper mapper =
		 * context.getBean(ItemMapper.class);
		 * System.out.println(mapper.getItemList().size());
		 */

		SolrServer server = new HttpSolrServer("http://172.18.12.151:8080/solr");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "100000");
		document.addField("item_title", "测试数据!!");
		server.add(document);
		server.commit();
	}

	@Test
	public void searchDocument() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://172.18.12.151:8080/solr");
		// 创建一个查询对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery("*:*");
		// 设置分页，默认是显示前十条
		query.setStart(20);
		query.setRows(50);
		// 执行查询
		QueryResponse response = solrServer.query(query);
		// 取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		System.out.println("共查询到记录：" + solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));

		}
	}
}

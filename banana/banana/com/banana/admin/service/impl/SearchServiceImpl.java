package com.banana.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banana.admin.bean.DateRange;
import com.banana.admin.bean.Rules;
import com.banana.admin.bean.Sentiment;
import com.banana.admin.bean.TimeRules;
import com.banana.admin.dao.SearchDao;
import com.banana.admin.service.SearchService;
import com.ht.sys.service.impl.BaseServiceImpl;
import com.ht.sys.util.ElasticsearchUtil;

/**
 * 索引业务层
 * @author dell
 * @data 2016-09-14
 * @version 1.0
 */
@Service("SearchService")
public class SearchServiceImpl extends BaseServiceImpl <Sentiment> implements SearchService {

	
	private SearchDao searchDao;
	
    @Autowired
	public void setCommodityRecordDao(SearchDao searchDao){
		super.setBaseDao(searchDao);
        this.searchDao = searchDao;
    }
    
    public SearchDao getSearchDao(){
        return searchDao;
    }
	
	
	/* 
	 * 索引前十条数据
	 */
	@Override
	public String queryList(JSONObject dataJsonObject) {
		
		String responseStr = "";
		JSONArray ruleList = dataJsonObject.getJSONArray("timeRules");
		String type = dataJsonObject.getString("type");
		String sentiment = dataJsonObject.getString("sentiment");
	
		String[] validResult = validEmpty(ruleList, type, sentiment);
		if ("no".equals(validResult[0])) {
			responseStr = validResult[1];
			return responseStr;
		}
		
		Object[] rulesArray = getTimeRules(true, ruleList, false);
		
		SearchResponse response= searchData(Integer.parseInt(sentiment), Integer.parseInt(type), rulesArray);
		
		System.out.println(response);
		
		return "";
	}
	
	
	/**
	 * 组装数据
	 * @return
	 */
	public SearchResponse searchData(int sentiment, int type, Object[] rulesArray) {

		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		
		if (rulesArray.length >= 0 && rulesArray != null) {
			
			TimeRules timeRues = null;
			
			for (Object object: rulesArray) {
				timeRues = (TimeRules) object;
				List<Rules> list = timeRues.getRulesList();
				
				for (Rules rules: list) {
					String [] name = rules.getName().split(";");
					for (String toplic : name) {
						queryBuilder.should(QueryBuilders.matchQuery("title", toplic));		
						queryBuilder.should(QueryBuilders.matchQuery("content", toplic));
					}
				}
			}
			
		}
		
/*		queryBuilder.should(QueryBuilders.matchQuery("content", "华为手机"));
		QueryBuilders.rangeQuery("artype").from(1).to(9);*/
		// SearchResponse response =
		// client.prepareSearch().execute().actionGet();
		
		SearchRequestBuilder sbuilder = ElasticsearchUtil.client
				.prepareSearch("sentiment")
			    .setTypes("dataType") 
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(queryBuilder) // Query
//				.setPostFilter(QueryBuilders.rangeQuery("artype").from(1).to(9)) // Filter
				.setFrom(0).setSize(10);
		System.out.println(sbuilder.toString());
		SearchResponse responses = sbuilder.execute().actionGet();
		return responses;
	}
	
	
	
	
	/*
	 * 校验客户端json数据合法
	 * @param ruleList
	 * @param type
	 * @param sentiment
	 * @return
	 */
	private String[] validEmpty(JSONArray ruleList, String type,
			String sentiment) {
		String[] result = new String[2];
		JSONObject responseStr = new JSONObject();
		if (ruleList.size() == 0) {
			responseStr.put("code", "100");
			responseStr.put("message", "rulelist is empty");
			result[0] = "no";
			result[1] = responseStr.toString();
			return result;
		}
		if (type == null || "".equals(type)) {
			responseStr.put("code", "100");
			responseStr.put("message", "type is empty");
			result[0] = "no";
			result[1] = responseStr.toString();
			return result;
		}
		if (sentiment == null || "".equals(sentiment)) {
			responseStr.put("code", "100");
			responseStr.put("message", "sentiment is empty");
			result[0] = "no";
			result[1] = responseStr.toString();
			return result;
		}
		result[0] = "yes";
		result[1] = "";
		return result;
	}
	
	
	
	
	/**
	 * json转化实体数据
	 * @param topicTime
	 * @param timeRulesList
	 * @param timeRange
	 * @return
	 */
	private Object[] getTimeRules(boolean topicTime, JSONArray timeRulesList,
			boolean timeRange) {
		int ruleSize = timeRulesList.size();
		if (timeRange) {
			ruleSize++;
		}
		Object[] rulesArray = new Object[ruleSize];
		List<Rules> rulesList = null;
		Rules rules = null;
		TimeRules timeRules = null;
		JSONObject jsonObject = null;
		JSONArray rulesArrayObj = null;
		DateRange dateRange = null;
		List<DateRange> dateRanges = new ArrayList<DateRange>();
		int timeSize = 0;
		if (timeRange) {
			timeSize = ruleSize - 1;
		} else {
			timeSize = ruleSize;
		}
		for (int i = 0; i < timeSize; i++) {
			jsonObject = timeRulesList.getJSONObject(i);
			rulesList = new ArrayList<Rules>();
			timeRules = new TimeRules();
			if (topicTime) {
				timeRules.setDateStart(jsonObject.getString("startDay"));
				timeRules.setDateEnd(jsonObject.getString("endDay"));
			}
			if (timeRange) {
				dateRange = new DateRange();
				dateRange.setDateStart(jsonObject.getString("startDay"));
				dateRange.setDateEnd(jsonObject.getString("endDay"));
				dateRanges.add(dateRange);
			}
			rulesArrayObj = jsonObject.getJSONArray("rulelist");
			int topicSize = rulesArrayObj.size();
			for (int j = 0; j < topicSize; j++) {
				rules = new Rules();
				rules.setName(rulesArrayObj.getJSONObject(j).getString("name"));
				rules.setTopic_id(rulesArrayObj.getJSONObject(j).containsKey("topic_id")? rulesArrayObj.getJSONObject(j).getInt("topic_id"): 0);
				rules.setTypeid(rulesArrayObj.getJSONObject(j).containsKey("typeid")? rulesArrayObj.getJSONObject(j).getInt("typeid"): 0);
				rulesList.add(rules);
			}
			timeRules.setRulesList(rulesList);
			rulesArray[i] = timeRules;
		}
		if (timeRange) {
			rulesArray[rulesArray.length - 1] = dateRanges;
		}
		return rulesArray;
	}
  
}

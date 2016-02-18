package com.darcytech.training.web;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

public class EsMain {

    public static void query(String userId, String hostName, String index) throws IOException {

        System.out.println("search begin: " + new Date());

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.termsQuery("userId", userId));

        BoolQueryBuilder queryBuilder2 = QueryBuilders.boolQuery();

        BoolQueryBuilder nestQuery = QueryBuilders.boolQuery();
        nestQuery.must(QueryBuilders.rangeQuery("trades.payTime").gte(1438358400000L).lte(1451577540000L));
        nestQuery.must(QueryBuilders.rangeQuery("trades.payment").gte(1).lte(5000));
        NestedQueryBuilder nest = QueryBuilders.nestedQuery("trades", nestQuery);
        //        nest.scoreMode("none");

        queryBuilder2.must(nest);
        queryBuilder.must(queryBuilder2);

        String port = "9300";
        TransportClient client = TransportClient.builder().settings(org.elasticsearch.common.settings.Settings.settingsBuilder()
                .put("cluster.name", "hermes")).build();
        client.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(hostName, Integer.valueOf(port))));

        SearchRequestBuilder builder = client.prepareSearch(index).setScroll(TimeValue.timeValueMinutes(1)).addFields("nick",
                "email",
                "mobile",
                "realName",
                "receiveSms",
                "inBlacklist");
        builder.setQuery(queryBuilder);
        builder.setSize(200);
//        builder.setSearchType(SearchType.SCAN);
        //        builder.setFetchSource(true);
//        builder.addSort(SortBuilders.fieldSort("_doc"));
        SearchResponse response = builder.execute().actionGet();
        String scrollId = response.getScrollId();
        int count = 0;
        SearchHit[] searchHits;
        do {
            SearchScrollRequestBuilder scrollSearch = client.prepareSearchScroll(scrollId).setScroll(TimeValue.timeValueMinutes(1));
            SearchResponse scrollResponse = scrollSearch.execute().actionGet();
            searchHits = scrollResponse.getHits().getHits();
            count += searchHits.length;
            scrollId = scrollResponse.getScrollId();
        } while (searchHits.length > 0);

        System.out.println("search end: " + new Date());
        //        List<List<String>> parts = Lists.partition(ids, 100);
        //        for (List<String> p : parts) {
        //            ListenableActionFuture<MultiGetResponse> multiGetResponse = client.prepareMultiGet().add("customer_29", null, p).execute();
        //            System.out.println("multiGet1: " + new Date());
        //        }

        System.out.println("multiGet end: " + new Date());

    }

    public static void main(String[] args) throws IOException {
        query("1770002254", "127.0.0.1", "customer_29");
    }

}

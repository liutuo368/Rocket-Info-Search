package com.example.a6442_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.a6442_group_project.Parser.Parser;
import com.example.a6442_group_project.Parser.SearchCondition;
import com.example.a6442_group_project.Parser.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchResultActivity extends AppCompatActivity {

    private ListView listView;
    private List<Map<String, Object>> list;
    private RocketListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        String text = "";
        Intent intent = getIntent();
        if(intent.getAction().equals("search")){
            text = intent.getStringExtra("text");
        }
        Tokenizer tokenizer = new Tokenizer(text);
        Parser parser = new Parser(tokenizer);
        SearchCondition searchCondition = parser.parseSearchInfo();
        listView = findViewById(R.id.rocketList);
        list = getData(searchCondition);
        adapter = new RocketListAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchResultActivity.this, RocketDetailActivity.class);
                intent.setAction("rocketInfo");
                intent.putExtra("index", list.get(position).get("index").toString());
                startActivity(intent);
            }
        });

    }

    private List<Map<String, Object>> getData(SearchCondition searchCondition) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Set<Integer> by_company = new HashSet<>();
        Set<Integer> by_country = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        if(searchCondition.company != null) {
            by_company = findIndex(MainActivity.companies, searchCondition.company);
            MainActivity.companies = LoadAndSave.loadCompanyFromXMLFile(LoadAndSave.getXML(this, "company.xml"));
        }
        if(searchCondition.country != null) {
            by_country = findIndex(MainActivity.countries, searchCondition.country);
            MainActivity.countries = LoadAndSave.loadCountryFromXMLFile(LoadAndSave.getXML(this, "country.xml"));
        }
        if(searchCondition.company != null && searchCondition.country != null) {
            result.addAll(by_company);
            result.retainAll(by_country);
        }
        if(searchCondition.company != null && searchCondition.country == null) {
            result.addAll(by_company);
        }
        if(searchCondition.company == null && searchCondition.country != null) {
            result.addAll(by_country);
        }
        for(Integer i: result) {
            Map<String, Object> map = new HashMap<>();
            map.put("company", MainActivity.rockets.get(i).getCompany());
            map.put("detail", MainActivity.rockets.get(i).getDetail());
            map.put("index", i);
            list.add(map);
        }
        return list;
    }

    private Set<Integer> findIndex(RBTree tree, String value) {
        Set<Integer> list = new HashSet<>();
        RBTree.RBTNode node;
        while ((node = tree.search(value)) != null) {
            list.add(node.getIndex());
            tree.remove(value);
        }
        return list;
    }
}
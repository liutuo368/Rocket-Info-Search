package com.example.a6442_group_project;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class LoadAndSave {

    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static InputStream getXML(Context context, String fileName) {
        InputStream in = null;
        try {
            in = context.getResources().getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    public static List<Space> loadRocketFromJsonFile(String file) {
        List<Space> list = new ArrayList<>();
        JSONArray array = (JSONArray) JSONValue.parse(file);
        for(int i = 0; i < array.size(); i++) {
            JSONObject obj = (JSONObject) array.get(i);
            String company = (String) obj.get("company");
            String location = (String) obj.get("location");
            String datum = (String) obj.get("datum");
            String detail = (String) obj.get("detail");
            String statusRocket = (String) obj.get("status_rocket");
            String rocket = (String)  obj.get("rocket");
            String statusMission = (String) obj.get("status_mission");
            Space s = new Space(company, location, datum, detail, statusRocket, rocket, statusMission);
            list.add(s);
        }
        return list;
    }


    public static RBTree loadCompanyFromXMLFile(InputStream file) {
        RBTree<String> tree = new RBTree<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            Node root = doc.getFirstChild();
            NodeList nl = root.getChildNodes();

            for(int i = 0; i < nl.getLength(); i++) {
                Node companyNode = nl.item(i);
                if(companyNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList companyAttr = companyNode.getChildNodes();
                    String companyName = "";
                    int index = 0;
                    for(int j = 0; j < companyAttr.getLength(); j++) {
                        Node n = companyAttr.item(j);
                        String nodeName = n.getNodeName();
                        if(nodeName.equals("companyName")) {
                            Node contentNode = n.getFirstChild();
                            companyName = contentNode.getTextContent().toLowerCase();
                        } else if(nodeName.equals("indexNo")) {
                            Node contentNode = n.getFirstChild();
                            index = Integer.parseInt(contentNode.getTextContent());
                        }
                    }
                    tree.insert(companyName, index);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tree;
    }

    public static RBTree loadCountryFromXMLFile(InputStream file) {
        RBTree<String> tree = new RBTree<String>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            Node root = doc.getFirstChild();
            NodeList nl = root.getChildNodes();

            for(int i = 0; i < nl.getLength(); i++) {
                Node countryNode = nl.item(i);
                if(countryNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList countryAttr = countryNode.getChildNodes();
                    String countryName = "";
                    int index = 0;
                    for(int j = 0; j < countryAttr.getLength(); j++) {
                        Node n = countryAttr.item(j);
                        String nodeName = n.getNodeName();
                        if(nodeName.equals("countryName")) {
                            Node contentNode = n.getFirstChild();
                            countryName = contentNode.getTextContent().toLowerCase();
                        } else if(nodeName.equals("indexNo")) {
                            Node contentNode = n.getFirstChild();
                            index = Integer.parseInt(contentNode.getTextContent());
                        }
                    }
                    tree.insert(countryName, index);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tree;
    }


}

package json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.BaseUser;
import bean.PHR;

public class JsonToObject {
	
	public BaseUser jsonToBaseUser(String jsonStr) throws JSONException{
		
		BaseUser user = null;
		JSONObject json = new JSONObject(jsonStr);
		user.setId(String.valueOf(UUID.randomUUID()));
		user.setHead(json.getString("head"));
		user.setName(json.getString("name"));
		user.setUserName(json.getString("userName"));
		user.setPassword(json.getString("password"));
		user.setIDCard(json.getString("IDCard"));
		user.setTelephone(json.getString("telephone"));
		user.setSex(json.getString("sex"));
		user.setAge(json.getInt("age"));
		
		return user;
	}
	
	public Map<String,Object> jsonToMap(String jsonStr) throws JSONException{
		Map<String,Object> map = new HashMap<String,Object>();
		JSONObject json = new JSONObject(jsonStr);
		Iterator<String> iterator = json.keys();
		List<String> keys = new ArrayList<String>();
		while(iterator.hasNext()){
			String key = iterator.next();
			keys.add(key);
		}
		for(String key : keys){
			map.put(key, json.get(key));
		}
		return map;
		
	}
	
	public List<Map<String,Object>> jsonToList(String jsonStr) throws JSONException{
		System.out.println(jsonStr);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		JSONArray jsonArray = new JSONArray(jsonStr);
		for(int i = 0;i<jsonArray.length();i++){
			JSONObject json = jsonArray.getJSONObject(i);
			Iterator<String> iterator = json.keys();
			List<String> keys = new ArrayList<String>();
			while(iterator.hasNext()){
				String key = iterator.next();
				keys.add(key);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			for(String key : keys){
				map.put(key, json.get(key));
			}
			list.add(map);
		}
		return list;
		
	}
	
	public List<String> jsonToPHR(String jsonStr) throws JSONException{
		
		if (jsonStr == null)
			System.out.println("×Ö·û´®Îª¿Õ£¡£¡£¡£¡");
		List<String> list = new ArrayList<String>();
		JSONObject json = new JSONObject(jsonStr);
		list.add((String) json.get("userID"));
		list.add((String) json.get("height"));
		list.add((String) json.get("weight"));
		list.add((String) json.get("blood_pressure"));
		list.add((String) json.get("vital_capacity"));
		list.add((String) json.get("vision_left"));
		list.add((String) json.get("vision_right"));
		list.add((String) json.get("heart_rate"));
		list.add((String) json.get("temperature"));
		list.add((String) json.get("hemoglobin"));
		list.add((String) json.get("platelet"));
		list.add((String) json.get("urine"));
		list.add((String) json.get("urine_red_blood_cell"));
		list.add((String) json.get("blood_type"));
		list.add((String) json.get("Measurements_1"));
		list.add((String) json.get("Measurements_2"));
		list.add((String) json.get("Measurements_3"));
		list.add((String) json.get("score"));
		
		return list;
	}

}

package json;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.BaseUser;
import bean.Information;
import bean.PHR;

public class InfoToJson {

	public InfoToJson() {
	}

	//一般List<Map<String,Object>>集合转化为JSONArray字符串
	public String listToJson(List<Map<String,Object>> list) throws JSONException {

		JSONArray jsonList = new JSONArray();

		if (list == null)
			System.out.println("数组为空");
		else {
//			System.out.println("准备进入循环");
			for (int i = 0; i < list.size(); i++) {
//				System.out.println("进入循环" + i);
				JSONObject json = new JSONObject();
				
				for(Map.Entry<String, Object> entry : list.get(i).entrySet()){
//					System.out.println("进入子循环");
					json.put(entry.getKey(), entry.getValue());
				}

				jsonList.put(json);
			}
		}
		if (jsonList.toString() != null)
			return jsonList.toString();
		else{
			System.out.println("InfoToJson().listToJson方法最后结果为空！");
			return null;
		}
	}

	//一般Map<String,Object>集合转化为JSONObject字符串
	public String mapToJson(Map<String,Object> map) throws JSONException {

		if (map == null)
			System.out.println("InfoToJson:数组为空");
		else {
				JSONObject json = new JSONObject();
				
				for(Map.Entry<String, Object> entry : map.entrySet())
					json.put(entry.getKey(), entry.getValue());

				return json.toString();
		}
		return null;
	}

	public String baseUserToJson(BaseUser user) throws JSONException {

		if (user == null)
			System.out.println("内容为空");
		else {
			JSONObject json = new JSONObject();

			json.put("id", user.getId());
			json.put("head", user.getHead());
			json.put("name", user.getName());
			json.put("userName", user.getUserName());
			json.put("password", user.getPassword());
			json.put("sex", user.getSex());
			json.put("personalID", user.getIDCard());
			json.put("phone", user.getTelephone());
			
			return json.toString();
		}
		return null;
	}

	public String phrToJson(PHR phr) throws JSONException {

		if (phr == null)
			System.out.println("内容为空");
		else {
			JSONObject json = new JSONObject();

			json.put("id", phr.getId());
			json.put("height", phr.getHeight());
			json.put("weight", phr.getWeight());
			json.put("blood_pressure", phr.getBlood_pressure());
			json.put("vital_capacity", phr.getVital_capacity());
			json.put("vision_left", phr.getVision_left());
			json.put("vision_right", phr.getVision_right());
			json.put("heart_rate", phr.getHeart_rate());
			json.put("temperature", phr.getTemperature());
			json.put("hemoglobin", phr.getHemoglobin());
			json.put("platelet", phr.getPlatelet());
			json.put("urine", phr.getUrine());
			json.put("urine_red_blood_cell", phr.getUrine_red_blood_cell());
			json.put("blood_type", phr.getBlood_type());
			json.put("Measurements_1", phr.getMeasurements_1());
			json.put("Measurements_2", phr.getMeasurements_2());
			json.put("Measurements_3", phr.getMeasurements_3());
			json.put("score", phr.getScore());
			
			return json.toString();
		}
		return null;
	}
}

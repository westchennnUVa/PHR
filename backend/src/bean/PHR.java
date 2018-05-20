package bean;

public class PHR {
	
	private String id;
	private String height;
	private String weight;
	private String blood_pressure;
	private String vital_capacity;
	private String vision_left;
	private String vision_right;
	private String heart_rate;
	private String temperature;
	private String hemoglobin;
	private String platelet;
	private String urine;
	private String urine_red_blood_cell;
	private String blood_type;
	private String Measurements_1;
	private String Measurements_2;
	private String Measurements_3;
	private String score;
	
	public PHR(String id, String height, String weight, String blood_pressure,String vital_capacity, String vision_left, String vision_right, String heart_rate, 
			String temperature, String hemoglobin, String platelet, String urine, String urine_red_blood_cell, String blood_type, String Measurements_1,
			String Measurements_2, String Measurements_3, String score){
		
		this.id = id;
		this.height = height;
		this.weight = weight;
		this.blood_pressure = blood_pressure;
		this.vital_capacity = vital_capacity;
		this.vision_left = vision_left;
		this.vision_right = vision_right;
		this.heart_rate = heart_rate;
		this.temperature = temperature;
		this.hemoglobin = hemoglobin;
		this.platelet = platelet;
		this.urine = urine;
		this.urine_red_blood_cell = urine_red_blood_cell;
		this.blood_type = blood_type;
		this.Measurements_1 = Measurements_1;
		this.Measurements_2 = Measurements_2;
		this.Measurements_3 = Measurements_3;
		this.score = score;
	}
	
	public PHR() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBlood_pressure() {
		return blood_pressure;
	}

	public void setBlood_pressure(String blood_pressure) {
		this.blood_pressure = blood_pressure;
	}

	public String getVital_capacity() {
		return vital_capacity;
	}

	public void setVital_capacity(String vital_capacity) {
		this.vital_capacity = vital_capacity;
	}

	public String getVision_left() {
		return vision_left;
	}

	public void setVision_left(String vision_left) {
		this.vision_left = vision_left;
	}

	public String getVision_right() {
		return vision_right;
	}

	public void setVision_right(String vision_right) {
		this.vision_right = vision_right;
	}

	public String getHeart_rate() {
		return heart_rate;
	}

	public void setHeart_rate(String heart_rate) {
		this.heart_rate = heart_rate;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHemoglobin() {
		return hemoglobin;
	}

	public void setHemoglobin(String hemoglobin) {
		this.hemoglobin = hemoglobin;
	}

	public String getPlatelet() {
		return platelet;
	}

	public void setPlatelet(String platelet) {
		this.platelet = platelet;
	}

	public String getUrine() {
		return urine;
	}

	public void setUrine(String urine) {
		this.urine = urine;
	}

	public String getUrine_red_blood_cell() {
		return urine_red_blood_cell;
	}

	public void setUrine_red_blood_cell(String urine_red_blood_cell) {
		this.urine_red_blood_cell = urine_red_blood_cell;
	}

	public String getBlood_type() {
		return blood_type;
	}

	public void setBlood_type(String blood_type) {
		this.blood_type = blood_type;
	}

	public String getMeasurements_1() {
		return Measurements_1;
	}

	public void setMeasurements_1(String measurements_1) {
		Measurements_1 = measurements_1;
	}

	public String getMeasurements_2() {
		return Measurements_2;
	}

	public void setMeasurements_2(String measurements_2) {
		Measurements_2 = measurements_2;
	}

	public String getMeasurements_3() {
		return Measurements_3;
	}

	public void setMeasurements_3(String measurements_3) {
		Measurements_3 = measurements_3;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	
	
}

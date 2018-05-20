package bean;

public class ChatBean {

	private String from;
	private String to;
	private String time;
	private String content;
	
	public ChatBean(String from, String to, String time, String content) {
		this.from = from;
		this.to = to;
		this.time = time;
		this.content = content;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}

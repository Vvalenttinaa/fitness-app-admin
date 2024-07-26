package dto;

import java.io.Serializable;
import java.util.Date;

public class Logger implements Serializable {

	 private Integer id;
	 private String log;
	 private Date date;
	 
	public Logger(Integer id, String log, Date date) {
		super();
		this.id = id;
		this.log = log;
		this.date = date;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}

package br.tech;

import java.util.List;

public class Mensagem {
	private Integer id;
	private String mens;
	private List<String> tag;
	private List<Integer> tag_id;
	public List<String> getTag() {
		return tag;
	}
	public void setTag(List<String> tag) {
		this.tag = tag;
	}
	public List<Integer> getTag_id() {
		return tag_id;
	}
	public void setTag_id(List<Integer> tag_id) {
		this.tag_id = tag_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMens() {
		return mens;
	}
	public void setMens(String mens) {
		this.mens = mens;
	}

}

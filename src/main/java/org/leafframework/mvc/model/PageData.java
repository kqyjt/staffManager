package org.leafframework.mvc.model;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class PageData {
	private HashMap<String, Object> pf = new LinkedHashMap<String, Object>();

	public HashMap<String, Object> getPf() {
		return pf;
	}

	public void setPf(HashMap<String, Object> f) {
		this.pf = f;
	}
}

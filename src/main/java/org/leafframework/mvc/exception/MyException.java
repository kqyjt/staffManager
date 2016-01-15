package org.leafframework.mvc.exception;

import org.leafframework.util.RETURN;

public class MyException extends RuntimeException {
	private static final long serialVersionUID = 1646037390184618524L;
	private RETURN ret;

	public MyException(RETURN ret) {
		super(ret.getErrorCode()+":"+ret.getErrorMsg());
		this.ret = ret;
	}

	public RETURN getRet() {
		return ret;
	}

	public void setRet(RETURN ret) {
		this.ret = ret;
	}
	
}

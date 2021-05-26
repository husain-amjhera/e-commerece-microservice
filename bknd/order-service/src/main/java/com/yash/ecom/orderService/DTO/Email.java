package com.yash.ecom.orderService.DTO;

import java.io.Serializable;

public class Email implements Serializable{

	private static final long serialVersionUID = 1L;

	private String sendTo;

	private String subject;

	private String text;

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Email [sendTo=" + sendTo + ", subject=" + subject + ", text=" + text + "]";
	}

	public Email() {
		super();
	}

}


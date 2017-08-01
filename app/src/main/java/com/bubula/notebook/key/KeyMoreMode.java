package com.bubula.notebook.key;

public class KeyMoreMode {
	private String text;
	private int resID;
	private int type;

	public KeyMoreMode(int type, String text, int resID) {
		super();
		this.text = text;
		this.resID = resID;
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public String getText() {
		return text;
	}

	public int getResID() {
		return resID;
	}

	@Override
	public String toString() {
		return "KeyMoreMode [text=" + text + ", resID=" + resID + ", type="
				+ type + "]";
	}

}

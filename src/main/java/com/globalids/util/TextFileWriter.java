package com.globalids.util;

import java.io.IOException;

public class TextFileWriter {
	private String fileName;
	
	public TextFileWriter setFileName(String name) {
		this.fileName = name;
		return this;
	}
	
	public TextFileWriter write(String text) {
		return this;
	}
	
	public void save() throws IOException {
	
	}
	
	public String getFileName() {
		return fileName;
	}
}

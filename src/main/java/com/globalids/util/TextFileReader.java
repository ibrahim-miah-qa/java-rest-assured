package com.globalids.util;

import java.io.*;

public class TextFileReader {
	protected String fileName;
	
	public TextFileReader setFileName(String name) {
		this.fileName = name;
		return this;
	}
	
	public String read() throws Exception {
		File file = new File(this.fileName);
		
		if (!file.exists()) {
			throw new Exception("input file not present!");
		}
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		StringBuilder buffer = new StringBuilder();
		
		while( (line = reader.readLine()) != null) {
			buffer.append(line);
		}
		
		System.out.println(buffer.toString());
		return buffer.toString();
	}
	
	public String getFileName() {
		return fileName;
	}
}

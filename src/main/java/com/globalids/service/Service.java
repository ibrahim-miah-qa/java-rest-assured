package com.globalids.service;

import com.globalids.Function;

public interface Service {
	public void execute(Function function, String[] arg);
	public void help();
	
	void process(String[] s);
}

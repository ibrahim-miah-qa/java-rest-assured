package com.globalids.service;

import com.globalids.Arguments;
import com.globalids.Function;

public class GlossaryService implements Service {
	@Override
	public void execute(Function function, String[] arguments) {
	
	}
	
	@Override
	public void help() {
		System.out.println("### Glossary help");
	}
	
	@Override
	public void process(String[] s) {
		System.out.println("processing glossary");
	}
}

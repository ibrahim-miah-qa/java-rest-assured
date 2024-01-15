package com.globalids;

import com.globalids.service.AppService;
import com.globalids.service.ConnectionService;
import com.globalids.service.GlossaryService;
import com.globalids.service.Service;

public enum Topic {
	app,
	glossary,
	be,
	object,
	connection;
	
	public Service getService() {
		Service service = null;
		
		switch (this) {
			case app:
				service = new AppService();
				break;
				
			case glossary:
				service = new GlossaryService();
				break;
				
			case connection:
				service = new ConnectionService();
				break;
		}
		
		return service;
	}
	
	public void help() {
		this.getService().help();
	}
	
	public void process(String[] s) {
		this.getService().process(s);
	}
}

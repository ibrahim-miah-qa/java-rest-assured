package jmeter;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import java.io.File;

public class Login {
	
	public static void main(String[] args) {
		// Loop Controller
		LoopController loopController = new LoopController();
		loopController.setContinueForever(false);
		loopController.setLoops(3);
		
		// Thread Group
		ThreadGroup threadGroup = new ThreadGroup();
		threadGroup.setSamplerController(loopController);
		threadGroup.setNumThreads(3);
		threadGroup.setRampUp(1);
		
		// Sampler
		HTTPSampler httpSampler = new HTTPSampler();
		httpSampler.setDomain("127.0.0.1:8000");
		httpSampler.setPort(8000);
		httpSampler.setMethod("GET");
		httpSampler.setPath("/244623663983475834759358754/admin/blogs");
		
		// Test Plan
		TestPlan testPlan = new TestPlan("Login Test Plan");
		
		HashTree hashTree = new HashTree();
		hashTree.add("test_plan", testPlan);
		hashTree.add("loop_controller", loopController);
		hashTree.add("thread_group", threadGroup);
		hashTree.add("sampler", httpSampler);
		
		StandardJMeterEngine jm = new StandardJMeterEngine();
		JMeterUtils.loadProperties("C:\\apps\\apache-jmeter-5.4.3\\bin\\jmeter.properties");
		//JMeterUtils.setJMeterHome(jmeterHome.getPath());
		jm.configure(hashTree);
		jm.run();
	}
}

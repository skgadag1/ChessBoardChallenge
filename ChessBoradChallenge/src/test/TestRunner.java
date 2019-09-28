package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	
	public static void main(String[] args) throws InterruptedException {
		
		Result result = JUnitCore.runClasses(TestCaseChessBoardRepresentation.class);
		
	      for (Failure failure : result.getFailures()) {
	         System.out.println("\t"+failure.toString());
	      }
			
	      System.out.println("\t"+result.wasSuccessful());


	}

}

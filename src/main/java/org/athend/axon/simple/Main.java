
package org.athend.axon.simple;

import java.util.UUID;
import org.athend.axon.simple.service.abstr.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {
	
	@Autowired private UserService userService;
	
	public static void main(String[]args) throws InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		context.setConfigLocations(new String[]{"application-context.xml"});
		context.refresh();
		Main main = context.getBean(Main.class);
		main.createUser();
	}
	
	public void createUser() throws InterruptedException{
		UUID userId = userService.createUser("Some User Name");
		userService.lockuser(userId);
		// sleep to emulate a continuously running app
		Thread.sleep(1000000);
	}
}

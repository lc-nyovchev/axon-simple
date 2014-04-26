
package org.athend.axon.simple.domain;

import lombok.Setter;
import org.athend.axon.simple.commands.UserCreateCommand;
import org.athend.axon.simple.commands.UserLockCommand;
import org.athend.axon.simple.commands.UserUnlockCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserCommandHandler {
	@Setter
	@Autowired
	@Qualifier("userRepository")
	private Repository<User> repository;
	
	@CommandHandler
	public void handle(UserCreateCommand userCreateCommand){
		User user = new User(
			userCreateCommand.getUserId(),
			userCreateCommand.getUsername()
		);
		repository.add(user);
	}
	
	@CommandHandler
	public void handle(UserLockCommand userLockCommand){
		User user = repository.load(userLockCommand.getUserId());
		user.lock();
	}
	
	@CommandHandler
	public void handle(UserUnlockCommand userUnlockCommand){
		User user = repository.load(userUnlockCommand.getUserId());
		user.unlock();
	}
}

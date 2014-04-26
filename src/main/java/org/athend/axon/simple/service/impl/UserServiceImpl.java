
package org.athend.axon.simple.service.impl;

import java.util.UUID;
import org.athend.axon.simple.commands.UserCreateCommand;
import org.athend.axon.simple.commands.UserLockCommand;
import org.athend.axon.simple.service.abstr.UUIDService;
import org.athend.axon.simple.service.abstr.UserService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired private UUIDService uuidService;
	@Autowired private CommandGateway commandGateway;
	
	@Override
	public UUID createUser(String username) {
		UUID userId = uuidService.randomUUID();
		commandGateway.sendAndWait(
			new UserCreateCommand(
				userId, 
				username
		));
		return userId;
	}

	@Override
	public void lockuser(UUID userId) {
		commandGateway.sendAndWait(new UserLockCommand(userId));
	}

}

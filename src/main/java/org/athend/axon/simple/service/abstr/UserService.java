
package org.athend.axon.simple.service.abstr;

import java.util.UUID;
import org.athend.axon.simple.commands.UserCreateCommand;
import org.athend.axon.simple.commands.UserLockCommand;
import org.athend.axon.simple.domain.User;

/**
 * A service for creating {@link User}s
 */
public interface UserService {
	/**
	 * Creates a {@link User} with a random {@link User#id} and the specified username.
	 * Just sends a {@link UserCreateCommand} to the command gateway
	 * @param username
	 * @return 
	 */
	UUID createUser(String username);
	
	/**
	 * Sends a {@link UserLockCommand} to lock the user with the specified id
	 * @param userId 
	 */
	void lockuser(UUID userId);
}

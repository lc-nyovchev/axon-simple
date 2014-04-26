
package org.athend.axon.simple.events;

import java.io.Serializable;
import java.util.UUID;
import lombok.Value;
import org.athend.axon.simple.commands.UserCreateCommand;

/**
 * An event for creating a user
 * @see UserCreateCommand
 */
@Value
public class UserCreatedEvent implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UUID userId;
	private String username;
}


package org.athend.axon.simple.commands;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.athend.axon.simple.events.UserCreatedEvent;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Simple command to create a user
 * @see UserCreatedEvent
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateCommand {
	@NotNull private UUID userId;
	@NotBlank private String username;
}

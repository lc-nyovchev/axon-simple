
package org.athend.axon.simple.commands;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.athend.axon.simple.events.UserLockedEvent;

/**
 * A command to be fired when a user needs to be locked
 * @see UserUnlockCommand
 * @see UserLockedEvent
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLockCommand {
	private UUID userId;
}

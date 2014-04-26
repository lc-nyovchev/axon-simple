
package org.athend.axon.simple.commands;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.athend.axon.simple.events.UserUnlockedEvent;

/**
 * A command to be fired when a user needs to be unlocked
 * @see UserLockCommand
 * @see UserUnlockedEvent
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUnlockCommand {
	private UUID userId;
}

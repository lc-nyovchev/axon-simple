
package org.athend.axon.simple.events.saga;

import java.io.Serializable;
import java.util.UUID;
import lombok.Value;

/**
 * An event that is used only as exchange in between Sagas. This event would be scheduled by a Saga, but wouldn't be stored
 * in the Event Store. See this discussion: https://groups.google.com/forum/#!msg/axonframework/ebNsDPtXjAA/rlLiv7q8X9YJ 
*/
@Value
public class UserUnlockSagaEvent implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UUID userId;
}

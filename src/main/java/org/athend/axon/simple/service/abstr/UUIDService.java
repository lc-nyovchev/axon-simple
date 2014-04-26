
package org.athend.axon.simple.service.abstr;

import java.util.UUID;

/**
 * Common interface for retrieving random {@link UUID}s
 */
public interface UUIDService {
	/**
	 * Retrieve a random UUID
	 * @return a random UUID
	 */
	UUID randomUUID();
}

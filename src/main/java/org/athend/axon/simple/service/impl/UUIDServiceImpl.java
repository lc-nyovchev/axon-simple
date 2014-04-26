
package org.athend.axon.simple.service.impl;

import org.athend.axon.simple.service.abstr.UUIDService;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UUIDServiceImpl implements UUIDService {

	@Override
	public UUID randomUUID() {
		return UUID.randomUUID();
	}

}

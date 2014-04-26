
package org.athend.axon.simple.domain;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.athend.axon.simple.events.UserCreatedEvent;
import org.athend.axon.simple.events.UserLockedEvent;
import org.athend.axon.simple.events.UserUnlockedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

@Getter
@NoArgsConstructor
public class User extends AbstractAnnotatedAggregateRoot<UUID> {
	private static final long serialVersionUID = 1L; 

	@AggregateIdentifier private UUID id;
	
	private boolean locked;
	
	public User(UUID userId, String username){
		if ( userId == null ){
			throw new IllegalArgumentException("UserId must not be null");
		}
		if ( StringUtils.isBlank(username ) ){
			throw new IllegalArgumentException("username must not be blank");
		}
		apply(new UserCreatedEvent(userId, username));
	}
	
	public void lock(){
		if ( !locked ){
			apply(new UserLockedEvent(id));
		}
	}
	
	public void unlock(){
		if ( locked ){
			apply(new UserUnlockedEvent(id));
		}
	}
	
	@EventHandler
	public void handle(UserCreatedEvent userCreatedEvent){
		this.id = userCreatedEvent.getUserId();
	}
	
	@EventHandler
	public void handle(UserLockedEvent userLockedEvent){
		this.locked = true;
	}
	
	@EventHandler
	public void handle(UserUnlockedEvent userUnlockedEvent){
		this.locked = false;
	}
}

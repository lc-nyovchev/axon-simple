
package org.athend.axon.simple.domain;

import lombok.Setter;
import org.athend.axon.simple.commands.UserUnlockCommand;
import org.athend.axon.simple.events.UserLockedEvent;
import org.athend.axon.simple.events.UserUnlockedEvent;
import org.athend.axon.simple.events.saga.UserUnlockSagaEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.annotation.Timestamp;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.ScheduleToken;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;

public class UserLockSaga extends AbstractAnnotatedSaga {
	private static final long serialVersionUID = 1L;

	// unlock after 5 secs
	public static final Long UNLOCK_AFTER_MILLIS = new Long(DateTimeConstants.MILLIS_PER_SECOND * 5); 
	
 	@Autowired @Setter private transient CommandGateway commandGateway;
	@Autowired @Setter private transient EventScheduler eventScheduler;
	
	private ScheduleToken userUnlockToken;
	
	@StartSaga
	@SagaEventHandler(associationProperty = "userId")
	public void handle(UserLockedEvent event, @Timestamp DateTime eventTime) {
		if (userUnlockToken != null) {
			eventScheduler.cancelSchedule(userUnlockToken);
		}
		// scheduling a new event to be fired at the specified time. The event handler for this event is only in 
		// the saga and would then trigger a UserUnlockCommand
		this.userUnlockToken = eventScheduler.schedule(
			getUnlockTime(eventTime), 
			new UserUnlockSagaEvent(event.getUserId())
		);
	}
	
	@SagaEventHandler(associationProperty = "userId")
	public void handle(final UserUnlockSagaEvent unlockSagaEvent) {
		commandGateway.sendAndWait(new UserUnlockCommand(unlockSagaEvent.getUserId()));
		end();
	}
	
	/**
	 * Also, if some external action unlocks the user, the saga should stop.
	 * @param userUnlockedEvent 
	 */
	@SagaEventHandler(associationProperty = "userId")
	public void handle(final UserUnlockedEvent userUnlockedEvent){
		if ( this.userUnlockToken != null ){
			eventScheduler.cancelSchedule(userUnlockToken);
		}
		end();
	}
	
	private DateTime getUnlockTime(DateTime eventTime){
		return new DateTime(eventTime.getMillis() + UNLOCK_AFTER_MILLIS);
	}
}

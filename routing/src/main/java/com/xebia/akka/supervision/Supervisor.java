package com.xebia.akka.supervision;

import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.resume;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.UntypedActor;
import akka.japi.Function;
import akka.util.Duration;

public class Supervisor extends UntypedActor {

	private SupervisorStrategy strategy = new OneForOneStrategy(5,
			Duration.parse("10 seconds"), new Function<Throwable, Directive>() {
				public Directive apply(Throwable t) {
					if (t instanceof ArithmeticException) {
						return resume();
					} else if (t instanceof NullPointerException) {
						return restart();
					} else {
						return escalate();
					}
				}
			});

	@Override
	public SupervisorStrategy supervisorStrategy() {
		return strategy;
	}

	@Override
	public void onReceive(Object message) throws Exception {
		
		if(message instanceof Result){
			getSender().tell(message);
		}
		getContext().actorOf(new Props(Supervisee.class)).forward(message,
				getContext());
	}

}

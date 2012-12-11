package com.xebia.akka.routing;

import akka.actor.UntypedActor;

public class WorkerActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		if(message  instanceof String){
			System.out.println("Message "+message+" Processed by "+getSelf());
		}else{
			throw new IllegalArgumentException("Invalid Message sent!!");
		}
		
	}
}

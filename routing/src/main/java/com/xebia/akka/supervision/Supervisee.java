package com.xebia.akka.supervision;

import scala.Option;
import akka.actor.UntypedActor;

public class Supervisee extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		if (message == null) {
			throw new NullPointerException("Null Value supplied to the Actor");
		}
		if (message instanceof Integer) {
			int value = (Integer) message;
			if (value < 50) {
				throw new ArithmeticException("Value Should be greater than 50");
			}
			System.out.println(" Processed Correct Message Valued " + value);
		} else {
			throw new IllegalArgumentException("Only Integers are supported...");
		}
	}

	@Override
	public void preStart() {
		System.out.println("Started by " + getContext().parent());
	}

	@Override
	public void postRestart(Throwable reason) {
		System.out.println("Restarted Actor " + getSelf());
	}

	@Override
	public void preRestart(Throwable reason, Option<Object> message) {
		System.out.println("Error Occured due to " + reason.getMessage()
				+ " Restarting Actor " + getSelf());
	}

}

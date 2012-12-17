package com.xebia.akka.supervision;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.Await;
import akka.pattern.Patterns;
import akka.util.Duration;
import akka.util.Timeout;

public class SupervisorExampleTest {

	private ActorRef supervisor;

	private Object[] objects = new Object[] { new Integer(1), new Integer(100),
			null, "Sameer", Long.valueOf(4l) };

	@Test
	public void shouldTestRestartability() throws Exception {
		ActorSystem actorSystem = ActorSystem.create("Test");
		supervisor = actorSystem.actorOf(new Props(Supervisor.class));
		
		CountDownLatch countDownLatch = new CountDownLatch(1);
		sendMessages();
		countDownLatch.await(10000, TimeUnit.MILLISECONDS);
	}

	private void sendMessages() {
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
				new Runnable() {
					public void run() {
						int counter = ((int) RandomUtils.nextInt()) % 5;
						supervisor.tell(objects[counter]);
					}
				}, 100, 10000, TimeUnit.MILLISECONDS);
	}

}

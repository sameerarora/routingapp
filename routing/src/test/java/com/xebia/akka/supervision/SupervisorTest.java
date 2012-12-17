package com.xebia.akka.supervision;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.Await;
import akka.pattern.Patterns;
import akka.util.Duration;

public class SupervisorTest {

	@Test
	public void test() throws Exception {
		ActorSystem actorSystem = ActorSystem.create("Test");
		ActorRef supervisor = actorSystem.actorOf(new Props(Supervisor.class));
		supervisor.tell(new Integer(55));

		Object result = Await.result(
				Patterns.ask(supervisor, new Result(), 5000),
				Duration.create(5000, TimeUnit.MILLISECONDS));
		assertTrue(result instanceof Result);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testException() throws Exception {
		ActorSystem actorSystem = ActorSystem.create("Test");
		ActorRef supervisor = actorSystem.actorOf(new Props(Supervisor.class));
		supervisor.tell(new Integer(45));

		Object result = Await.result(
				Patterns.ask(supervisor, new Result(), 5000),
				Duration.create(5000, TimeUnit.MILLISECONDS));
		//assertTrue(result instanceof Result);
	}

}

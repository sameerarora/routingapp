package com.xebia.akka.routing;

import static org.junit.Assert.*;

import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RandomRouter;

public class RoutingTestClient {

	@Test
	public void shouldCallMethodOnWorkerActor() {
		ActorSystem actorSystem=ActorSystem.create("TestActorSystem");
		ActorRef worker = actorSystem.actorOf(new Props(WorkerActor.class).withRouter(new RandomRouter(2)), "worker");
		worker.tell("Sameer Arora");
		worker.tell("Sameer Arora");
		worker.tell("Sameer Arora");
	}

}

package com.xebia.akka.routing.client;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import com.typesafe.config.ConfigFactory;

public class RoutingClient {

	public static void main(String[] args) throws InterruptedException {
		ActorSystem system = ActorSystem.create("RouterClientApplication",
				ConfigFactory.load().getConfig("RouterClientApp"));

		final ActorRef router = system
				.actorFor("akka://RouterApp@127.0.0.1:2552/user/router");
		for (int i = 0; i < 10; i++) {
			router.tell(("Random Message " + i + ""));
		}

		system.shutdown();
	}
}

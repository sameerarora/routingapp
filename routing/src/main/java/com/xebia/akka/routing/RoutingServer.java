package com.xebia.akka.routing;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.kernel.Bootable;
import akka.routing.RandomRouter;
import akka.routing.RoundRobinRouter;

import com.typesafe.config.ConfigFactory;

public class RoutingServer implements Bootable {

	public void shutdown() {

	}

	public void startup() {
		System.out.println("Starting cluster of Workers..");
		ActorSystem system = ActorSystem.create("RouterApp", ConfigFactory
				.load().getConfig("RouterApp"));
		system.actorOf(
				new Props(WorkerActor.class).withRouter(new RandomRouter(5)),"router");
		
	}

}

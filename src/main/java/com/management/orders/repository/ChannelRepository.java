package com.management.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.orders.model.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {

	public Channel findByName(String name);

}

package com.example.demo.resolvers;

import com.example.demo.types.Location;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Subscription implements GraphQLSubscriptionResolver {

    @Autowired
    private LocationPublisher locationPublisher;

    public Publisher<Location> pushLocation() {
        return locationPublisher.getPublisher();
    }
}

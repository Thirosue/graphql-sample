package com.example.demo.resolvers;

import com.example.demo.types.Location;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LocationMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private LocationPublisher locationPublisher;

    public Location addLocation(String locationId, String name, String path) {
        log.info("mutation : " + locationId + ", path : " + path);
        Location location = Location.builder().locationId(locationId).name(name).path(path).build();
        locationPublisher.publish(location);
        return location;
    }
}

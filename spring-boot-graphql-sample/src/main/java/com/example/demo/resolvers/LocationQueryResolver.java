package com.example.demo.resolvers;

import com.example.demo.types.Location;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LocationQueryResolver implements GraphQLQueryResolver {

    public Location getLocation(String id) {
        log.info("query : " + id);
        return Location.builder().locationId(id).name("name" + id).path("path" + id).build();
    }
}

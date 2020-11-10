package com.example.demo.types;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Location {
    private String locationId;
    private String name;
    private String path;
}

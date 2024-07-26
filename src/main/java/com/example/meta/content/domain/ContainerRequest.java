package com.example.meta.content.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContainerRequest {
    private List<IgContainer> containers;
}

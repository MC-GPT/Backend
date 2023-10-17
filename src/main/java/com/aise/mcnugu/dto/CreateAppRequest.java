package com.aise.mcnugu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateAppRequest {

    private String serialNumber;

    private String name;

    private boolean isLight;

    private int type;

    private boolean locked;

    private Long home_id;
}

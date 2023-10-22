package com.aise.mcnugu.dto;

import lombok.Getter;

@Getter
public class CreateAppRequest {

    private String serialNumber;

    private String name;

    private Long home_id;

    private boolean light;

    private int type = 0;

    private boolean locked = false;
}

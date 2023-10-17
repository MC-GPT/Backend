package com.aise.mcnugu.dto;

import com.aise.mcnugu.domain.Appliance;
import com.aise.mcnugu.domain.Game;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MainResponse {

    private String home_name;

    private boolean isOwner;

    private List<Appliance> apps;

    private List<Game> games;
}

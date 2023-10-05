package com.aise.mcnugu.dto;

import com.aise.mcnugu.domain.Appliance;
import com.aise.mcnugu.domain.Game;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MainResponse {

    private String home_name;

    private boolean isOwner;

    private List<Appliance> apps;

    private List<Game> games;
}

package org.airsonic.player.service.playqueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.airsonic.player.domain.MediaFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayStrategyContext {

    private final Map<String, PlayStrategy> strategies = new HashMap<>();

    @Autowired
    public PlayStrategyContext(Map<String, PlayStrategy> strategies) {
        if (strategies != null) {
            this.strategies.putAll(strategies);
        }
    }

    public List<MediaFile> getTracks(String strategyKey, String sourceId) throws Exception {
        PlayStrategy strategy = strategies.get(strategyKey);
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy found for key: " + strategyKey);
        }
        return strategy.getTracks(sourceId);
    }
}
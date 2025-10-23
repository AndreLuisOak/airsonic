package org.airsonic.player.service.playqueue;

import java.util.List;

import org.airsonic.player.domain.MediaFile;

public interface PlayStrategy {
    List<MediaFile> getTracks(String sourceId) throws Exception;
}
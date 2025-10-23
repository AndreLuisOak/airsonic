package org.airsonic.player.service.playqueue;

import java.util.List;

import org.airsonic.player.domain.MediaFile;
import org.airsonic.player.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("playlistPlayStrategy")
public class PlaylistPlayStrategy implements PlayStrategy {

    @Autowired
    private PlaylistService playlistService;

    @Override
    public List<MediaFile> getTracks(String playlistId) throws Exception {
        int id = Integer.parseInt(playlistId);
        return playlistService.getFilesInPlaylist(id, true);
    }
}
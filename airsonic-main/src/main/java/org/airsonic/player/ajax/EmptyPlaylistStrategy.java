package org.airsonic.player.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.airsonic.player.domain.Playlist;
import java.util.Date;

@Service("emptyPlaylistStrategy")
public class EmptyPlaylistStrategy implements PlaylistCreationStrategy {

    @Autowired
    private org.airsonic.player.service.PlaylistService playlistService;

    @Override
    public Playlist createPlaylist(String username) {
        Playlist playlist = new Playlist();
        Date now = new Date();
        playlist.setUsername(username);
        playlist.setCreated(now);
        playlist.setChanged(now);
        playlist.setShared(false);
        playlist.setName("Empty Playlist - " + now);
        playlistService.createPlaylist(playlist);
        return playlist;
    }
}

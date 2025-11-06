package org.airsonic.player.ajax;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.airsonic.player.domain.Player;
import org.airsonic.player.domain.Playlist;
import org.airsonic.player.service.PlayerService;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("playQueuePlaylistStrategy")
public class PlayQueuePlaylistStrategy implements PlaylistCreationStrategy {

    @Autowired
    private org.airsonic.player.service.PlaylistService playlistService;
    @Autowired
    private PlayerService playerService;

    @Override
    public Playlist createPlaylist(String username) {
        HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
        HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
        Player player = null;
        try {
            player = playerService.getPlayer(request, response);
        } catch (Exception ex) {
        }

        Playlist playlist = new Playlist();
        Date now = new Date();
        playlist.setUsername(username);
        playlist.setCreated(now);
        playlist.setChanged(now);
        playlist.setShared(false);
        playlist.setName("PlayQueue - " + now);

        playlistService.createPlaylist(playlist);
        playlistService.setFilesInPlaylist(playlist.getId(), player.getPlayQueue().getFiles());
        return playlist;
    }
}

package org.airsonic.player.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.airsonic.player.domain.Playlist;
import org.airsonic.player.domain.Player;
import org.airsonic.player.service.PlayerService;
import org.directwebremoting.WebContextFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

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
        Player player = playerService.getPlayer(request, response);

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

package org.airsonic.player.ajax;

public interface PlaylistCreationStrategy {
    Playlist createPlaylist(String username);
}

package org.airsonic.player.ajax;

import org.airsonic.player.domain.Playlist;

public interface PlaylistCreationStrategy {
    Playlist createPlaylist(String username);
}

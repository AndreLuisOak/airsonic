/*
 This file is part of Airsonic.

 Airsonic is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Airsonic is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Airsonic.  If not, see <http://www.gnu.org/licenses/>.

 Copyright 2016 (C) Airsonic Authors
 Based upon Subsonic, Copyright 2009 (C) Sindre Mehus
 */
package org.airsonic.player.ajax;

import org.airsonic.player.dao.MediaFileDao;
import org.airsonic.player.domain.MediaFile;
import org.airsonic.player.domain.MusicFolder;
import org.airsonic.player.domain.Player;
import org.airsonic.player.domain.Playlist;
import org.airsonic.player.i18n.LocaleResolver;
import org.airsonic.player.service.MediaFileService;
import org.airsonic.player.service.PlayerService;
import org.airsonic.player.service.SecurityService;
import org.airsonic.player.service.SettingsService;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.DateFormat;
import java.util.*;

/**
 * Provides AJAX-enabled services for manipulating playlists.
 * This class is used by the DWR framework (http://getahead.ltd.uk/dwr/).
 *
 * @author Sindre Mehus
 */
@Service("ajaxPlaylistService")
public class PlaylistService {

    @Autowired
    private SecurityService securityService;

    // Estratégias registradas no contexto Spring
    private final Map<String, PlaylistCreationStrategy> strategies;

    @Autowired
    public PlaylistService(Map<String, PlaylistCreationStrategy> strategies) {
        this.strategies = strategies;
    }

    /**
     * Cria uma playlist com base no tipo informado.
     * Usa o padrão Strategy para delegar o comportamento.
     */
    public int createPlaylist(String type) {
        HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
        String username = securityService.getCurrentUsername(request);

        PlaylistCreationStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de playlist desconhecido: " + type);
        }

        Playlist playlist = strategy.createPlaylist(username);
        return playlist.getId();
    }
}


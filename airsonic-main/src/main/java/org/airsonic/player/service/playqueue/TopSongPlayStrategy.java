package org.airsonic.player.service.playqueue;

import java.util.List;

import org.airsonic.player.domain.MediaFile;
import org.airsonic.player.domain.MusicFolder;
import org.airsonic.player.service.LastFmService;
import org.airsonic.player.service.MediaFileService;
import org.airsonic.player.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("topSongPlayStrategy")
public class TopSongPlayStrategy implements PlayStrategy {

    @Autowired private LastFmService lastFmService;
    @Autowired private SettingsService settingsService;
    @Autowired private MediaFileService mediaFileService;

    @Override
    public List<MediaFile> getTracks(String mediaFileId) throws Exception {
        int id = Integer.parseInt(mediaFileId);
        MediaFile mediaFile = mediaFileService.getMediaFile(id);
        // Usar todos os music folders (como fallback) — mantém compatibilidade com o uso original.
        List<MusicFolder> folders = settingsService.getAllMusicFolders();
        return lastFmService.getTopSongs(mediaFile, 50, folders);
    }
}
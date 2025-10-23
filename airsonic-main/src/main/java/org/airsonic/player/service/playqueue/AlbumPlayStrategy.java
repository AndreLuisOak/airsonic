package org.airsonic.player.service.playqueue;

import java.util.List;

import org.airsonic.player.domain.MediaFile;
import org.airsonic.player.service.MediaFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("albumPlayStrategy")
public class AlbumPlayStrategy implements PlayStrategy {

    @Autowired
    private MediaFileService mediaFileService;

    @Override
    public List<MediaFile> getTracks(String albumId) throws Exception {
        int id = Integer.parseInt(albumId);
        MediaFile album = mediaFileService.getMediaFile(id);
        return mediaFileService.getChildrenOf(album, true, false, true);
    }
}
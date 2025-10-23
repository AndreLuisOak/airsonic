package org.airsonic.player.ajax.command;

import org.airsonic.player.dao.MediaFileDao;

/**
 * Concrete command to unstar a media file.
 */
public class UnstarMediaFileCommand implements StarCommand {

    private final MediaFileDao mediaFileDao;
    private final int mediaFileId;
    private final String username;

    public UnstarMediaFileCommand(MediaFileDao mediaFileDao, int mediaFileId, String username) {
        this.mediaFileDao = mediaFileDao;
        this.mediaFileId = mediaFileId;
        this.username = username;
    }

    @Override
    public void execute() {
        mediaFileDao.unstarMediaFile(mediaFileId, username);
    }
}
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

import java.util.List;
import java.util.Objects;

import org.airsonic.player.util.StringUtil;

/**
 * The playlist of a player.
 *
 * Builder pattern used to construct immutable instances.
 *
 * @author Sindre Mehus (adapted)
 */
public class PlayQueueInfo {

    private final List<Entry> entries;
    private final boolean stopEnabled;
    private final boolean repeatEnabled;
    private final boolean shuffleRadioEnabled;
    private final boolean internetRadioEnabled;
    private final boolean sendM3U;
    private final float gain;
    private final int startPlayerAt;         // -1 means not set
    private final long startPlayerAtPosition; // millis

    private PlayQueueInfo(Builder b) {
        this.entries = b.entries;
        this.stopEnabled = b.stopEnabled;
        this.repeatEnabled = b.repeatEnabled;
        this.shuffleRadioEnabled = b.shuffleRadioEnabled;
        this.internetRadioEnabled = b.internetRadioEnabled;
        this.sendM3U = b.sendM3U;
        this.gain = b.gain;
        this.startPlayerAt = b.startPlayerAt;
        this.startPlayerAtPosition = b.startPlayerAtPosition;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public String getDurationAsString() {
        int durationSeconds = 0;
        if (entries != null) {
            for (Entry entry : entries) {
                if (entry.getDuration() != null) {
                    durationSeconds += entry.getDuration();
                }
            }
        }
        return StringUtil.formatDurationMSS(durationSeconds);
    }

    public boolean isStopEnabled() {
        return stopEnabled;
    }

    public boolean isSendM3U() {
        return sendM3U;
    }

    public boolean isRepeatEnabled() {
        return repeatEnabled;
    }

    public boolean isShuffleRadioEnabled() {
        return shuffleRadioEnabled;
    }

    public boolean isInternetRadioEnabled() {
        return internetRadioEnabled;
    }

    public float getGain() {
        return gain;
    }

    public int getStartPlayerAt() {
        return startPlayerAt;
    }

    public long getStartPlayerAtPosition() {
        return startPlayerAtPosition;
    }

    /**
     * Return a new PlayQueueInfo with updated startPlayerAt (immutably).
     * Keeps all other fields the same.
     */
    public PlayQueueInfo setStartPlayerAt(int startPlayerAt) {
        return new Builder().from(this).startPlayerAt(startPlayerAt).build();
    }

    /**
     * Return a new PlayQueueInfo with updated startPlayerAtPosition (immutably).
     * Keeps all other fields the same.
     */
    public PlayQueueInfo setStartPlayerAtPosition(long startPlayerAtPosition) {
        return new Builder().from(this).startPlayerAtPosition(startPlayerAtPosition).build();
    }

    /**
     * Builder for PlayQueueInfo.
     */
    public static class Builder {
        private List<Entry> entries;
        private boolean stopEnabled;
        private boolean repeatEnabled;
        private boolean shuffleRadioEnabled;
        private boolean internetRadioEnabled;
        private boolean sendM3U;
        private float gain;
        private int startPlayerAt = -1;
        private long startPlayerAtPosition = 0L;

        public Builder entries(List<Entry> entries) {
            this.entries = entries;
            return this;
        }

        public Builder stopEnabled(boolean stopEnabled) {
            this.stopEnabled = stopEnabled;
            return this;
        }

        public Builder repeatEnabled(boolean repeatEnabled) {
            this.repeatEnabled = repeatEnabled;
            return this;
        }

        public Builder shuffleRadioEnabled(boolean shuffleRadioEnabled) {
            this.shuffleRadioEnabled = shuffleRadioEnabled;
            return this;
        }

        public Builder internetRadioEnabled(boolean internetRadioEnabled) {
            this.internetRadioEnabled = internetRadioEnabled;
            return this;
        }

        public Builder sendM3U(boolean sendM3U) {
            this.sendM3U = sendM3U;
            return this;
        }

        public Builder gain(float gain) {
            this.gain = gain;
            return this;
        }

        public Builder startPlayerAt(int startPlayerAt) {
            this.startPlayerAt = startPlayerAt;
            return this;
        }

        public Builder startPlayerAtPosition(long startPlayerAtPosition) {
            this.startPlayerAtPosition = startPlayerAtPosition;
            return this;
        }

        public PlayQueueInfo build() {
            return new PlayQueueInfo(this);
        }

        /**
         * Populate builder fields from an existing PlayQueueInfo (useful for immutable updates).
         */
        public Builder from(PlayQueueInfo info) {
            Objects.requireNonNull(info);
            this.entries = info.entries;
            this.stopEnabled = info.stopEnabled;
            this.repeatEnabled = info.repeatEnabled;
            this.shuffleRadioEnabled = info.shuffleRadioEnabled;
            this.internetRadioEnabled = info.internetRadioEnabled;
            this.sendM3U = info.sendM3U;
            this.gain = info.gain;
            this.startPlayerAt = info.startPlayerAt;
            this.startPlayerAtPosition = info.startPlayerAtPosition;
            return this;
        }
    }

    public static class Entry {
        private final int id;
        private final Integer trackNumber;
        private final String title;
        private final String artist;
        private final String album;
        private final String genre;
        private final Integer year;
        private final String bitRate;
        private final Integer duration;
        private final String durationAsString;
        private final String format;
        private final String contentType;
        private final String fileSize;
        private final boolean starred;
        private final String albumUrl;
        private final String streamUrl;
        private final String remoteStreamUrl;
        private final String coverArtUrl;
        private final String remoteCoverArtUrl;

        public Entry(
                int id,
                Integer trackNumber,
                String title,
                String artist,
                String album,
                String genre,
                Integer year,
                String bitRate,
                Integer duration,
                String durationAsString,
                String format,
                String contentType,
                String fileSize,
                boolean starred,
                String albumUrl,
                String streamUrl,
                String remoteStreamUrl,
                String coverArtUrl,
                String remoteCoverArtUrl) {

            this.id = id;
            this.trackNumber = trackNumber;
            this.title = title;
            this.artist = artist;
            this.album = album;
            this.genre = genre;
            this.year = year;
            this.bitRate = bitRate;
            this.duration = duration;
            this.durationAsString = durationAsString;
            this.format = format;
            this.contentType = contentType;
            this.fileSize = fileSize;
            this.starred = starred;
            this.albumUrl = albumUrl;
            this.streamUrl = streamUrl;
            this.remoteStreamUrl = remoteStreamUrl;
            this.coverArtUrl = coverArtUrl;
            this.remoteCoverArtUrl = remoteCoverArtUrl;
        }

        public int getId() {
            return id;
        }

        public Integer getTrackNumber() {
            return trackNumber;
        }

        public String getTitle() {
            return title;
        }

        public String getArtist() {
            return artist;
        }

        public String getAlbum() {
            return album;
        }

        public String getGenre() {
            return genre;
        }

        public Integer getYear() {
            return year;
        }

        public String getBitRate() {
            return bitRate;
        }

        public String getDurationAsString() {
            return durationAsString;
        }

        public Integer getDuration() {
            return duration;
        }

        public String getFormat() {
            return format;
        }

        public String getContentType() {
            return contentType;
        }

        public String getFileSize() {
            return fileSize;
        }

        public boolean isStarred() {
            return starred;
        }

        public String getAlbumUrl() {
            return albumUrl;
        }

        public String getStreamUrl() {
            return streamUrl;
        }

        public String getRemoteStreamUrl() {
            return remoteStreamUrl;
        }

        public String getCoverArtUrl() {
            return coverArtUrl;
        }

        public String getRemoteCoverArtUrl() {
            return remoteCoverArtUrl;
        }
    }
}

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
package org.airsonic.player.command;

import org.airsonic.player.controller.MusicFolderSettingsController;
import org.airsonic.player.domain.MusicFolder;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Command used in {@link MusicFolderSettingsController}.
 *
 * Refatorado para usar Builder Pattern. Classe imutável.
 */
public class MusicFolderSettingsCommand {

    private final String interval;
    private final String hour;
    private final boolean scanning;
    private final boolean fastCache;
    private final boolean organizeByFolderStructure;
    private final List<MusicFolderInfo> musicFolders;
    private final MusicFolderInfo newMusicFolder;
    private final String excludePatternString;
    private final boolean ignoreSymLinks;

    private MusicFolderSettingsCommand(Builder b) {
        this.interval = b.interval;
        this.hour = b.hour;
        this.scanning = b.scanning;
        this.fastCache = b.fastCache;
        this.organizeByFolderStructure = b.organizeByFolderStructure;
        this.musicFolders = b.musicFolders != null ? new ArrayList<>(b.musicFolders) : new ArrayList<>();
        this.newMusicFolder = b.newMusicFolder;
        this.excludePatternString = b.excludePatternString;
        this.ignoreSymLinks = b.ignoreSymLinks;
    }

    public String getInterval() { return interval; }
    public String getHour() { return hour; }
    public boolean isScanning() { return scanning; }
    public boolean isFastCache() { return fastCache; }
    public boolean isOrganizeByFolderStructure() { return organizeByFolderStructure; }
    public List<MusicFolderInfo> getMusicFolders() { return new ArrayList<>(musicFolders); }
    public MusicFolderInfo getNewMusicFolder() { return newMusicFolder; }
    public String getExcludePatternString() { return excludePatternString; }
    public boolean isIgnoreSymLinks() { return ignoreSymLinks; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String interval;
        private String hour;
        private boolean scanning;
        private boolean fastCache;
        private boolean organizeByFolderStructure;
        private List<MusicFolderInfo> musicFolders;
        private MusicFolderInfo newMusicFolder;
        private String excludePatternString;
        private boolean ignoreSymLinks;

        public Builder interval(String interval) { this.interval = interval; return this; }
        public Builder hour(String hour) { this.hour = hour; return this; }
        public Builder scanning(boolean scanning) { this.scanning = scanning; return this; }
        public Builder fastCache(boolean fastCache) { this.fastCache = fastCache; return this; }
        public Builder organizeByFolderStructure(boolean organize) { this.organizeByFolderStructure = organize; return this; }
        public Builder musicFolders(List<MusicFolderInfo> musicFolders) { this.musicFolders = musicFolders; return this; }
        public Builder addMusicFolder(MusicFolderInfo musicFolderInfo) {
            if (this.musicFolders == null) this.musicFolders = new ArrayList<>();
            this.musicFolders.add(musicFolderInfo);
            return this;
        }
        public Builder newMusicFolder(MusicFolderInfo newMusicFolder) { this.newMusicFolder = newMusicFolder; return this; }
        public Builder excludePatternString(String excludePatternString) { this.excludePatternString = excludePatternString; return this; }
        public Builder ignoreSymLinks(boolean ignoreSymLinks) { this.ignoreSymLinks = ignoreSymLinks; return this; }

        public MusicFolderSettingsCommand build() { return new MusicFolderSettingsCommand(this); }
    }

    public static class MusicFolderInfo {

        private final Integer id;
        private final String path;
        private final String name;
        private final boolean enabled;
        private final boolean delete;
        private final boolean existing;

        private MusicFolderInfo(InfoBuilder b) {
            this.id = b.id;
            this.path = b.path;
            this.name = b.name;
            this.enabled = b.enabled;
            this.delete = b.delete;
            this.existing = b.existing;
        }

        public Integer getId() { return id; }
        public String getPath() { return path; }
        public String getName() { return name; }
        public boolean isEnabled() { return enabled; }
        public boolean isDelete() { return delete; }
        public boolean isExisting() { return existing; }

        public MusicFolder toMusicFolder() {
            String p = StringUtils.trimToNull(this.path);
            if (p == null) {
                return null;
            }
            File file = new File(p);
            String nm = StringUtils.trimToNull(this.name);
            if (nm == null) {
                nm = file.getName();
            }
            return new MusicFolder(id, new File(p), nm, enabled, new Date());
        }

        public static InfoBuilder builder() { return new InfoBuilder(); }

        public static class InfoBuilder {
            private Integer id;
            private String path;
            private String name;
            private boolean enabled = true;
            private boolean delete = false;
            private boolean existing;

            public InfoBuilder id(Integer id) { this.id = id; return this; }
            public InfoBuilder path(String path) { this.path = path; return this; }
            public InfoBuilder name(String name) { this.name = name; return this; }
            public InfoBuilder enabled(boolean enabled) { this.enabled = enabled; return this; }
            public InfoBuilder delete(boolean delete) { this.delete = delete; return this; }
            public InfoBuilder existing(boolean existing) { this.existing = existing; return this; }

            public InfoBuilder from(MusicFolder mf) {
                Objects.requireNonNull(mf);
                this.id = mf.getId();
                this.path = mf.getPath().getPath();
                this.name = mf.getName();
                this.enabled = mf.isEnabled();
                this.existing = mf.getPath().exists() && mf.getPath().isDirectory();
                return this;
            }

            public MusicFolderInfo build() { return new MusicFolderInfo(this); }
        }
    }
}

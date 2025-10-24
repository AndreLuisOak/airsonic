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

import org.airsonic.player.domain.Theme;

/**
 * Command used in {@link org.airsonic.player.controller.GeneralSettingsController}.
 *
 * Refatorado para usar Builder Pattern. Classe imutável.
 */
public class GeneralSettingsCommand {

    private final String playlistFolder;
    private final String musicFileTypes;
    private final String videoFileTypes;
    private final String coverArtFileTypes;
    private final String index;
    private final String ignoredArticles;
    private final String shortcuts;
    private final boolean sortAlbumsByYear;
    private final boolean gettingStartedEnabled;
    private final String welcomeTitle;
    private final String welcomeSubtitle;
    private final String welcomeMessage;
    private final String loginMessage;
    private final String localeIndex;
    private final String[] locales;
    private final String themeIndex;
    private final Theme[] themes;

    private GeneralSettingsCommand(Builder b) {
        this.playlistFolder = b.playlistFolder;
        this.musicFileTypes = b.musicFileTypes;
        this.videoFileTypes = b.videoFileTypes;
        this.coverArtFileTypes = b.coverArtFileTypes;
        this.index = b.index;
        this.ignoredArticles = b.ignoredArticles;
        this.shortcuts = b.shortcuts;
        this.sortAlbumsByYear = b.sortAlbumsByYear;
        this.gettingStartedEnabled = b.gettingStartedEnabled;
        this.welcomeTitle = b.welcomeTitle;
        this.welcomeSubtitle = b.welcomeSubtitle;
        this.welcomeMessage = b.welcomeMessage;
        this.loginMessage = b.loginMessage;
        this.localeIndex = b.localeIndex;
        this.locales = b.locales;
        this.themeIndex = b.themeIndex;
        this.themes = b.themes;
    }

    public String getPlaylistFolder() { return playlistFolder; }
    public String getMusicFileTypes() { return musicFileTypes; }
    public String getVideoFileTypes() { return videoFileTypes; }
    public String getCoverArtFileTypes() { return coverArtFileTypes; }
    public String getIndex() { return index; }
    public String getIgnoredArticles() { return ignoredArticles; }
    public String getShortcuts() { return shortcuts; }
    public String getWelcomeTitle() { return welcomeTitle; }
    public String getWelcomeSubtitle() { return welcomeSubtitle; }
    public String getWelcomeMessage() { return welcomeMessage; }
    public String getLoginMessage() { return loginMessage; }
    public String getLocaleIndex() { return localeIndex; }
    public String[] getLocales() { return locales; }
    public String getThemeIndex() { return themeIndex; }
    public Theme[] getThemes() { return themes; }
    public boolean isSortAlbumsByYear() { return sortAlbumsByYear; }
    public boolean isGettingStartedEnabled() { return gettingStartedEnabled; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String playlistFolder;
        private String musicFileTypes;
        private String videoFileTypes;
        private String coverArtFileTypes;
        private String index;
        private String ignoredArticles;
        private String shortcuts;
        private boolean sortAlbumsByYear;
        private boolean gettingStartedEnabled;
        private String welcomeTitle;
        private String welcomeSubtitle;
        private String welcomeMessage;
        private String loginMessage;
        private String localeIndex;
        private String[] locales;
        private String themeIndex;
        private Theme[] themes;

        public Builder playlistFolder(String v) { this.playlistFolder = v; return this; }
        public Builder musicFileTypes(String v) { this.musicFileTypes = v; return this; }
        public Builder videoFileTypes(String v) { this.videoFileTypes = v; return this; }
        public Builder coverArtFileTypes(String v) { this.coverArtFileTypes = v; return this; }
        public Builder index(String v) { this.index = v; return this; }
        public Builder ignoredArticles(String v) { this.ignoredArticles = v; return this; }
        public Builder shortcuts(String v) { this.shortcuts = v; return this; }
        public Builder sortAlbumsByYear(boolean v) { this.sortAlbumsByYear = v; return this; }
        public Builder gettingStartedEnabled(boolean v) { this.gettingStartedEnabled = v; return this; }
        public Builder welcomeTitle(String v) { this.welcomeTitle = v; return this; }
        public Builder welcomeSubtitle(String v) { this.welcomeSubtitle = v; return this; }
        public Builder welcomeMessage(String v) { this.welcomeMessage = v; return this; }
        public Builder loginMessage(String v) { this.loginMessage = v; return this; }
        public Builder localeIndex(String v) { this.localeIndex = v; return this; }
        public Builder locales(String[] v) { this.locales = v; return this; }
        public Builder themeIndex(String v) { this.themeIndex = v; return this; }
        public Builder themes(Theme[] v) { this.themes = v; return this; }

        public GeneralSettingsCommand build() { return new GeneralSettingsCommand(this); }
    }
}

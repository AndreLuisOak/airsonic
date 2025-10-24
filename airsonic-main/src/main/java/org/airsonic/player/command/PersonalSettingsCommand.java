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
import org.airsonic.player.domain.User;

/**
 * Command used for personal settings pages.
 *
 * Refatorado para Builder Pattern. Classe imutável.
 */
public class PersonalSettingsCommand {
    private final User user;
    private final String localeIndex;
    private final String[] locales;
    private final String themeIndex;
    private final Theme[] themes;
    private final boolean partyModeEnabled;
    private final boolean showNowPlayingEnabled;
    private final boolean showArtistInfoEnabled;
    private final boolean songNotificationEnabled;
    private final int paginationSize;
    private final boolean use24HourFormat;
    private final String avatarUrl;

    private PersonalSettingsCommand(Builder b) {
        this.user = b.user;
        this.localeIndex = b.localeIndex;
        this.locales = b.locales;
        this.themeIndex = b.themeIndex;
        this.themes = b.themes;
        this.partyModeEnabled = b.partyModeEnabled;
        this.showNowPlayingEnabled = b.showNowPlayingEnabled;
        this.showArtistInfoEnabled = b.showArtistInfoEnabled;
        this.songNotificationEnabled = b.songNotificationEnabled;
        this.paginationSize = b.paginationSize;
        this.use24HourFormat = b.use24HourFormat;
        this.avatarUrl = b.avatarUrl;
    }

    public static Builder builder() { return new Builder(); }

    public User getUser() { return user; }
    public String getLocaleIndex() { return localeIndex; }
    public String[] getLocales() { return locales; }
    public String getThemeIndex() { return themeIndex; }
    public Theme[] getThemes() { return themes; }
    public boolean isPartyModeEnabled() { return partyModeEnabled; }
    public boolean isShowNowPlayingEnabled() { return showNowPlayingEnabled; }
    public boolean isShowArtistInfoEnabled() { return showArtistInfoEnabled; }
    public boolean isSongNotificationEnabled() { return songNotificationEnabled; }
    public int getPaginationSize() { return paginationSize; }
    public boolean isUse24HourFormat() { return use24HourFormat; }
    public String getAvatarUrl() { return avatarUrl; }

    public static class Builder {
        private User user;
        private String localeIndex;
        private String[] locales;
        private String themeIndex;
        private Theme[] themes;
        private boolean partyModeEnabled;
        private boolean showNowPlayingEnabled;
        private boolean showArtistInfoEnabled;
        private boolean songNotificationEnabled;
        private int paginationSize = 25;
        private boolean use24HourFormat;
        private String avatarUrl;

        public Builder user(User v) { this.user = v; return this; }
        public Builder localeIndex(String v) { this.localeIndex = v; return this; }
        public Builder locales(String[] v) { this.locales = v; return this; }
        public Builder themeIndex(String v) { this.themeIndex = v; return this; }
        public Builder themes(Theme[] v) { this.themes = v; return this; }
        public Builder partyModeEnabled(boolean v) { this.partyModeEnabled = v; return this; }
        public Builder showNowPlayingEnabled(boolean v) { this.showNowPlayingEnabled = v; return this; }
        public Builder showArtistInfoEnabled(boolean v) { this.showArtistInfoEnabled = v; return this; }
        public Builder songNotificationEnabled(boolean v) { this.songNotificationEnabled = v; return this; }
        public Builder paginationSize(int v) { this.paginationSize = v; return this; }
        public Builder use24HourFormat(boolean v) { this.use24HourFormat = v; return this; }
        public Builder avatarUrl(String v) { this.avatarUrl = v; return this; }

        public PersonalSettingsCommand build() {
            // example validation: must have user
            if (this.user == null) {
                throw new IllegalStateException("user is required");
            }
            return new PersonalSettingsCommand(this);
        }
    }
}

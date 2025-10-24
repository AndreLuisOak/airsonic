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

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.airsonic.player.controller.PlayerSettingsController;
import org.airsonic.player.domain.Player;
import org.airsonic.player.domain.PlayerTechnology;
import org.airsonic.player.domain.TranscodeScheme;
import org.airsonic.player.domain.Transcoding;

/**
 * Command used in {@link PlayerSettingsController}.
 *
 * Refatorado para Builder Pattern. Classe imutável.
 */
public class PlayerSettingsCommand {
    private final Integer playerId;
    private final String name;
    private final String description;
    private final String type;
    private final Date lastSeen;
    private final boolean dynamicIp;
    private final boolean autoControlEnabled;
    private final boolean m3uBomEnabled;
    private final String technologyName;
    private final String transcodeSchemeName;
    private final boolean transcodingSupported;
    private final String transcodeDirectory;
    private final List<Transcoding> allTranscodings;
    private final int[] activeTranscodingIds;
    private final EnumHolder[] technologyHolders;
    private final EnumHolder[] transcodeSchemeHolders;
    private final Player[] players;
    private final boolean admin;
    private final String javaJukeboxMixer;
    private final String[] javaJukeboxMixers;
    private final TranscodingHolder[] transcodingHolders;

    private PlayerSettingsCommand(Builder b) {
        this.playerId = b.playerId;
        this.name = b.name;
        this.description = b.description;
        this.type = b.type;
        this.lastSeen = b.lastSeen;
        this.dynamicIp = b.dynamicIp;
        this.autoControlEnabled = b.autoControlEnabled;
        this.m3uBomEnabled = b.m3uBomEnabled;
        this.technologyName = b.technologyName;
        this.transcodeSchemeName = b.transcodeSchemeName;
        this.transcodingSupported = b.transcodingSupported;
        this.transcodeDirectory = b.transcodeDirectory;
        this.allTranscodings = b.allTranscodings;
        this.activeTranscodingIds = b.activeTranscodingIds;
        this.technologyHolders = b.technologyHolders;
        this.transcodeSchemeHolders = b.transcodeSchemeHolders;
        this.players = b.players;
        this.admin = b.admin;
        this.javaJukeboxMixer = b.javaJukeboxMixer;
        this.javaJukeboxMixers = b.javaJukeboxMixers;
        this.transcodingHolders = b.transcodingHolders;
    }

    public static Builder builder() { return new Builder(); }

    public Integer getPlayerId() { return playerId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public Date getLastSeen() { return lastSeen == null ? null : new Date(lastSeen.getTime()); }
    public boolean isDynamicIp() { return dynamicIp; }
    public boolean isAutoControlEnabled() { return autoControlEnabled; }
    public boolean isM3uBomEnabled() { return m3uBomEnabled; }
    public String getTechnologyName() { return technologyName; }
    public String getTranscodeSchemeName() { return transcodeSchemeName; }
    public boolean isTranscodingSupported() { return transcodingSupported; }
    public String getTranscodeDirectory() { return transcodeDirectory; }
    public List<Transcoding> getAllTranscodings() { return allTranscodings; }
    public int[] getActiveTranscodingIds() { return activeTranscodingIds; }
    public EnumHolder[] getTechnologyHolders() { return technologyHolders; }
    public EnumHolder[] getTranscodeSchemeHolders() { return transcodeSchemeHolders; }
    public Player[] getPlayers() { return players; }
    public boolean isAdmin() { return admin; }
    public String getJavaJukeboxMixer() { return javaJukeboxMixer; }
    public String[] getJavaJukeboxMixers() { return javaJukeboxMixers; }
    public TranscodingHolder[] getTranscodingHolders() { return transcodingHolders; }

    public static class Builder {
        private Integer playerId;
        private String name;
        private String description;
        private String type;
        private Date lastSeen;
        private boolean dynamicIp;
        private boolean autoControlEnabled;
        private boolean m3uBomEnabled;
        private String technologyName;
        private String transcodeSchemeName;
        private boolean transcodingSupported;
        private String transcodeDirectory;
        private List<Transcoding> allTranscodings;
        private int[] activeTranscodingIds;
        private EnumHolder[] technologyHolders;
        private EnumHolder[] transcodeSchemeHolders;
        private Player[] players;
        private boolean admin;
        private String javaJukeboxMixer;
        private String[] javaJukeboxMixers;
        private TranscodingHolder[] transcodingHolders;

        public Builder playerId(Integer v) { this.playerId = v; return this; }
        public Builder name(String v) { this.name = v; return this; }
        public Builder description(String v) { this.description = v; return this; }
        public Builder type(String v) { this.type = v; return this; }
        public Builder lastSeen(Date v) { this.lastSeen = v == null ? null : new Date(v.getTime()); return this; }
        public Builder dynamicIp(boolean v) { this.dynamicIp = v; return this; }
        public Builder autoControlEnabled(boolean v) { this.autoControlEnabled = v; return this; }
        public Builder m3uBomEnabled(boolean v) { this.m3uBomEnabled = v; return this; }
        public Builder technologyName(String v) { this.technologyName = v; return this; }
        public Builder transcodeSchemeName(String v) { this.transcodeSchemeName = v; return this; }
        public Builder transcodingSupported(boolean v) { this.transcodingSupported = v; return this; }
        public Builder transcodeDirectory(String v) { this.transcodeDirectory = v; return this; }
        public Builder allTranscodings(List<Transcoding> v) { this.allTranscodings = v; return this; }
        public Builder activeTranscodingIds(int[] v) { this.activeTranscodingIds = v; return this; }
        public Builder technologyHoldersFromEnums(PlayerTechnology[] technologies) {
            if (technologies != null) {
                EnumHolder[] holders = new EnumHolder[technologies.length];
                for (int i = 0; i < technologies.length; i++) {
                    holders[i] = new EnumHolder(technologies[i].name(), technologies[i].toString());
                }
                this.technologyHolders = holders;
            }
            return this;
        }
        public Builder transcodeSchemeHoldersFromEnums(TranscodeScheme[] schemes) {
            if (schemes != null) {
                EnumHolder[] holders = new EnumHolder[schemes.length];
                for (int i = 0; i < schemes.length; i++) {
                    holders[i] = new EnumHolder(schemes[i].name(), schemes[i].toString());
                }
                this.transcodeSchemeHolders = holders;
            }
            return this;
        }
        public Builder players(Player[] v) { this.players = v; return this; }
        public Builder admin(boolean v) { this.admin = v; return this; }
        public Builder javaJukeboxMixer(String v) { this.javaJukeboxMixer = v; return this; }
        public Builder javaJukeboxMixers(String[] v) { this.javaJukeboxMixers = v; return this; }
        public Builder transcodingHolders(TranscodingHolder[] v) { this.transcodingHolders = v; return this; }

        public PlayerSettingsCommand build() { return new PlayerSettingsCommand(this); }
    }

    /**
     * Holds the transcoding and whether it is active for the given player.
     * Imutável.
     */
    public static class TranscodingHolder {
        private final Transcoding transcoding;
        private final boolean active;

        public TranscodingHolder(Transcoding transcoding, boolean active) {
            this.transcoding = Objects.requireNonNull(transcoding);
            this.active = active;
        }

        public Transcoding getTranscoding() { return transcoding; }
        public boolean isActive() { return active; }
    }
}

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

/**
 * Command used in {@link org.airsonic.player.controller.AdvancedSettingsController}.
 *
 * Refatorado para usar o Builder Pattern. Classe imutável.
 */
public class AdvancedSettingsCommand {

    private final String downloadLimit;
    private final String uploadLimit;
    private final boolean ldapEnabled;
    private final String ldapUrl;
    private final String ldapSearchFilter;
    private final String ldapManagerDn;
    private final String ldapManagerPassword;
    private final boolean ldapAutoShadowing;
    private final String brand;
    private final String smtpServer;
    private final String smtpEncryption;
    private final String smtpPort;
    private final String smtpUser;
    private final String smtpPassword;
    private final String smtpFrom;
    private final boolean captchaEnabled;
    private final String recaptchaSiteKey;
    private final String recaptchaSecretKey;

    private AdvancedSettingsCommand(Builder b) {
        this.downloadLimit = b.downloadLimit;
        this.uploadLimit = b.uploadLimit;
        this.ldapEnabled = b.ldapEnabled;
        this.ldapUrl = b.ldapUrl;
        this.ldapSearchFilter = b.ldapSearchFilter;
        this.ldapManagerDn = b.ldapManagerDn;
        this.ldapManagerPassword = b.ldapManagerPassword;
        this.ldapAutoShadowing = b.ldapAutoShadowing;
        this.brand = b.brand;
        this.smtpServer = b.smtpServer;
        this.smtpEncryption = b.smtpEncryption;
        this.smtpPort = b.smtpPort;
        this.smtpUser = b.smtpUser;
        this.smtpPassword = b.smtpPassword;
        this.smtpFrom = b.smtpFrom;
        this.captchaEnabled = b.captchaEnabled;
        this.recaptchaSiteKey = b.recaptchaSiteKey;
        this.recaptchaSecretKey = b.recaptchaSecretKey;
    }

    public String getDownloadLimit() { return downloadLimit; }
    public String getUploadLimit() { return uploadLimit; }
    public boolean isLdapEnabled() { return ldapEnabled; }
    public String getLdapUrl() { return ldapUrl; }
    public String getLdapSearchFilter() { return ldapSearchFilter; }
    public String getLdapManagerDn() { return ldapManagerDn; }
    public String getLdapManagerPassword() { return ldapManagerPassword; }
    public boolean isLdapAutoShadowing() { return ldapAutoShadowing; }
    public String getBrand() { return brand; }
    public String getSmtpServer() { return smtpServer; }
    public String getSmtpEncryption() { return smtpEncryption; }
    public String getSmtpPort() { return smtpPort; }
    public String getSmtpUser() { return smtpUser; }
    public String getSmtpPassword() { return smtpPassword; }
    public String getSmtpFrom() { return smtpFrom; }
    public boolean isCaptchaEnabled() { return captchaEnabled; }
    public String getRecaptchaSiteKey() { return recaptchaSiteKey; }
    public String getRecaptchaSecretKey() { return recaptchaSecretKey; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String downloadLimit;
        private String uploadLimit;
        private boolean ldapEnabled;
        private String ldapUrl;
        private String ldapSearchFilter;
        private String ldapManagerDn;
        private String ldapManagerPassword;
        private boolean ldapAutoShadowing;
        private String brand;
        private String smtpServer;
        private String smtpEncryption;
        private String smtpPort;
        private String smtpUser;
        private String smtpPassword;
        private String smtpFrom;
        private boolean captchaEnabled;
        private String recaptchaSiteKey;
        private String recaptchaSecretKey;

        public Builder downloadLimit(String v) { this.downloadLimit = v; return this; }
        public Builder uploadLimit(String v) { this.uploadLimit = v; return this; }
        public Builder ldapEnabled(boolean v) { this.ldapEnabled = v; return this; }
        public Builder ldapUrl(String v) { this.ldapUrl = v; return this; }
        public Builder ldapSearchFilter(String v) { this.ldapSearchFilter = v; return this; }
        public Builder ldapManagerDn(String v) { this.ldapManagerDn = v; return this; }
        public Builder ldapManagerPassword(String v) { this.ldapManagerPassword = v; return this; }
        public Builder ldapAutoShadowing(boolean v) { this.ldapAutoShadowing = v; return this; }
        public Builder brand(String v) { this.brand = v; return this; }
        public Builder smtpServer(String v) { this.smtpServer = v; return this; }
        public Builder smtpEncryption(String v) { this.smtpEncryption = v; return this; }
        public Builder smtpPort(String v) { this.smtpPort = v; return this; }
        public Builder smtpUser(String v) { this.smtpUser = v; return this; }
        public Builder smtpPassword(String v) { this.smtpPassword = v; return this; }
        public Builder smtpFrom(String v) { this.smtpFrom = v; return this; }
        public Builder captchaEnabled(boolean v) { this.captchaEnabled = v; return this; }
        public Builder recaptchaSiteKey(String v) { this.recaptchaSiteKey = v; return this; }
        public Builder recaptchaSecretKey(String v) { this.recaptchaSecretKey = v; return this; }

        public AdvancedSettingsCommand build() { return new AdvancedSettingsCommand(this); }
    }
}

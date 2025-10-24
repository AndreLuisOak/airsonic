package org.airsonic.player.command;

import javax.validation.constraints.NotNull;

import org.airsonic.player.spring.DataSourceConfigType;

/**
 * DTO for database configuration. Refatorado para Builder Pattern.
 */
public class DatabaseSettingsCommand {

    @NotNull
    private final DataSourceConfigType configType;
    private final String embedDriver;
    private final String embedPassword;
    private final String embedUrl;
    private final String embedUsername;
    private final String JNDIName;
    private final int mysqlVarcharMaxlength;
    private final String usertableQuote;

    private DatabaseSettingsCommand(Builder b) {
        this.configType = b.configType;
        this.embedDriver = b.embedDriver;
        this.embedPassword = b.embedPassword;
        this.embedUrl = b.embedUrl;
        this.embedUsername = b.embedUsername;
        this.JNDIName = b.JNDIName;
        this.mysqlVarcharMaxlength = b.mysqlVarcharMaxlength;
        this.usertableQuote = b.usertableQuote;
    }

    public DataSourceConfigType getConfigType() { return configType; }
    public String getEmbedDriver() { return embedDriver; }
    public String getEmbedPassword() { return embedPassword; }
    public String getEmbedUrl() { return embedUrl; }
    public String getEmbedUsername() { return embedUsername; }
    public String getJNDIName() { return JNDIName; }
    public int getMysqlVarcharMaxlength() { return mysqlVarcharMaxlength; }
    public String getUsertableQuote() { return usertableQuote; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private DataSourceConfigType configType;
        private String embedDriver;
        private String embedPassword;
        private String embedUrl;
        private String embedUsername;
        private String JNDIName;
        private int mysqlVarcharMaxlength;
        private String usertableQuote;

        public Builder configType(DataSourceConfigType v) { this.configType = v; return this; }
        public Builder embedDriver(String v) { this.embedDriver = v; return this; }
        public Builder embedPassword(String v) { this.embedPassword = v; return this; }
        public Builder embedUrl(String v) { this.embedUrl = v; return this; }
        public Builder embedUsername(String v) { this.embedUsername = v; return this; }
        public Builder JNDIName(String v) { this.JNDIName = v; return this; }
        public Builder mysqlVarcharMaxlength(int v) { this.mysqlVarcharMaxlength = v; return this; }
        public Builder usertableQuote(String v) { this.usertableQuote = v; return this; }

        public DatabaseSettingsCommand build() {
            if (this.configType == null) {
                throw new IllegalStateException("configType is required");
            }
            return new DatabaseSettingsCommand(this);
        }
    }
}

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
package org.airsonic.player.controller;

import org.airsonic.player.command.AdvancedSettingsCommand;
import org.airsonic.player.service.SettingsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for the page used to administrate advanced settings.
 * Atualizado para suportar o Builder Pattern em AdvancedSettingsCommand.
 *
 * @author Sindre Mehus
 */
@Controller
@RequestMapping("/advancedSettings")
public class AdvancedSettingsController {

    @Autowired
    private SettingsService settingsService;

    @GetMapping
    protected String formBackingObject(Model model) {

        AdvancedSettingsCommand command = AdvancedSettingsCommand.builder()
            .downloadLimit(String.valueOf(settingsService.getDownloadBitrateLimit()))
            .uploadLimit(String.valueOf(settingsService.getUploadBitrateLimit()))
            .ldapEnabled(settingsService.isLdapEnabled())
            .ldapUrl(settingsService.getLdapUrl())
            .ldapSearchFilter(settingsService.getLdapSearchFilter())
            .ldapManagerDn(settingsService.getLdapManagerDn())
            .ldapManagerPassword(settingsService.getLdapManagerPassword())
            .ldapAutoShadowing(settingsService.isLdapAutoShadowing())
            .brand(settingsService.getBrand())
            .smtpServer(settingsService.getSmtpServer())
            .smtpEncryption(settingsService.getSmtpEncryption())
            .smtpPort(settingsService.getSmtpPort())
            .smtpUser(settingsService.getSmtpUser())
            .smtpPassword(settingsService.getSmtpPassword())
            .smtpFrom(settingsService.getSmtpFrom())
            .captchaEnabled(settingsService.isCaptchaEnabled())
            .recaptchaSiteKey(settingsService.getRecaptchaSiteKey())
            .recaptchaSecretKey(settingsService.getRecaptchaSecretKey())
            .build();

        model.addAttribute("command", command);
        return "advancedSettings";
    }

    @PostMapping
    protected String doSubmitAction(@ModelAttribute AdvancedSettingsCommand command,
                                    RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("settings_reload", false);
        redirectAttributes.addFlashAttribute("settings_toast", true);

        try {
            settingsService.setDownloadBitrateLimit(Long.parseLong(command.getDownloadLimit()));
        } catch (NumberFormatException ignored) { }

        try {
            settingsService.setUploadBitrateLimit(Long.parseLong(command.getUploadLimit()));
        } catch (NumberFormatException ignored) { }

        settingsService.setLdapEnabled(command.isLdapEnabled());
        settingsService.setLdapUrl(command.getLdapUrl());
        settingsService.setLdapSearchFilter(command.getLdapSearchFilter());
        settingsService.setLdapManagerDn(command.getLdapManagerDn());
        settingsService.setLdapAutoShadowing(command.isLdapAutoShadowing());

        if (StringUtils.isNotEmpty(command.getLdapManagerPassword())) {
            settingsService.setLdapManagerPassword(command.getLdapManagerPassword());
        }

        settingsService.setSmtpServer(command.getSmtpServer());
        settingsService.setSmtpEncryption(command.getSmtpEncryption());
        settingsService.setSmtpPort(command.getSmtpPort());
        settingsService.setSmtpUser(command.getSmtpUser());
        settingsService.setSmtpFrom(command.getSmtpFrom());

        if (StringUtils.isNotEmpty(command.getSmtpPassword())) {
            settingsService.setSmtpPassword(command.getSmtpPassword());
        }

        settingsService.setCaptchaEnabled(command.isCaptchaEnabled());
        settingsService.setRecaptchaSiteKey(command.getRecaptchaSiteKey());

        if (StringUtils.isNotEmpty(command.getRecaptchaSecretKey())) {
            settingsService.setRecaptchaSecretKey(command.getRecaptchaSecretKey());
        }

        settingsService.save();

        return "redirect:advancedSettings.view";
    }
}
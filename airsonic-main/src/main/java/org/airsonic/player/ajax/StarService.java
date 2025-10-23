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

import org.airsonic.player.ajax.command.StarCommandExecutor;
import org.airsonic.player.ajax.command.StarMediaFileCommand;
import org.airsonic.player.ajax.command.UnstarMediaFileCommand;
import org.airsonic.player.dao.MediaFileDao;
import org.airsonic.player.domain.User;
import org.airsonic.player.service.SecurityService;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides AJAX-enabled services for starring.
 * Refactored using the Command pattern to decouple
 * web context logic from star operations.
 */
@Service("ajaxStarService")
public class StarService {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private MediaFileDao mediaFileDao;

    // simple invoker; kept internal for now
    private final StarCommandExecutor executor = new StarCommandExecutor();

    public void star(int id) {
        String user = getUser();
        executor.execute(new StarMediaFileCommand(mediaFileDao, id, user));
    }

    public void unstar(int id) {
        String user = getUser();
        executor.execute(new UnstarMediaFileCommand(mediaFileDao, id, user));
    }

    private String getUser() {
        WebContext webContext = WebContextFactory.get();
        User user = securityService.getCurrentUser(webContext.getHttpServletRequest());
        return user.getUsername();
    }

    // Keep setters for compatibility / tests
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void setMediaFileDao(MediaFileDao mediaFileDao) {
        this.mediaFileDao = mediaFileDao;
    }
}

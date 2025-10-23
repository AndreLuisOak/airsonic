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

import org.airsonic.player.ajax.adapter.TransferStatusAdapter;
import org.airsonic.player.domain.TransferStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides AJAX-enabled services for retrieving the status of ongoing transfers.
 * Refactored using the Adapter pattern to decouple session access logic.
 */
@Service("ajaxTransferService")
public class TransferService {

    private final TransferStatusAdapter transferStatusAdapter;

    @Autowired
    public TransferService(TransferStatusAdapter transferStatusAdapter) {
        this.transferStatusAdapter = transferStatusAdapter;
    }

    /**
     * Returns info about any ongoing upload within the current session.
     * @return Info about ongoing upload.
     */
    public UploadInfo getUploadInfo() {
        TransferStatus status = transferStatusAdapter.getCurrentTransferStatus();

        if (status == null) {
            return new UploadInfo(0L, 0L);
        }
        return new UploadInfo(status.getBytesTransfered(), status.getBytesTotal());
    }
}

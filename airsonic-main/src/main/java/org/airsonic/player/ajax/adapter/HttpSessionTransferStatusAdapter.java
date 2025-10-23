package org.airsonic.player.ajax.adapter;

import javax.servlet.http.HttpSession;

import org.airsonic.player.controller.UploadController;
import org.airsonic.player.domain.TransferStatus;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Component;

/**
 * Adapter that retrieves transfer status from the current HTTP session.
 */
@Component
public class HttpSessionTransferStatusAdapter implements TransferStatusAdapter {

    @Override
    public TransferStatus getCurrentTransferStatus() {
        HttpSession session = WebContextFactory.get().getSession();
        return (TransferStatus) session.getAttribute(UploadController.UPLOAD_STATUS);
    }
}
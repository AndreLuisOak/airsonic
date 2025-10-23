package org.airsonic.player.ajax.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple invoker / executor for StarCommand instances.
 */
public class StarCommandExecutor {
    private static final Logger LOG = LoggerFactory.getLogger(StarCommandExecutor.class);

    public void execute(StarCommand command) {
        try {
            command.execute();
        } catch (RuntimeException e) {
            LOG.error("Failed to execute star command", e);
            throw e;
        } catch (Exception e) {
            LOG.error("Failed to execute star command", e);
            // wrap to runtime to avoid changing caller signatures
            throw new RuntimeException(e);
        }
    }
}
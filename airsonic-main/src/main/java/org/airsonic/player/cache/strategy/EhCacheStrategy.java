package org.airsonic.player.cache.strategy;

import java.io.File;

import org.airsonic.player.service.SettingsService;
import org.springframework.stereotype.Component;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;

/**
 * Estratégia concreta para Ehcache.
 */
@Component("ehCacheStrategy")
public class EhCacheStrategy implements CacheStrategy {
    private final CacheManager cacheManager;

    public EhCacheStrategy() {
        Configuration configuration = ConfigurationFactory.parseConfiguration();
        File cacheDir = new File(SettingsService.getAirsonicHome(), "cache");
        configuration.getDiskStoreConfiguration().setPath(cacheDir.getPath());
        this.cacheManager = CacheManager.create(configuration);
    }

    @Override
    public Ehcache createCache(String name) {
        return cacheManager.getCache(name);
    }
}
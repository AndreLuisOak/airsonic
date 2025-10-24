package org.airsonic.player.cache.strategy;

import net.sf.ehcache.Ehcache;

/**
 * Strategy interface para criação/recuperação de caches.
 */
public interface CacheStrategy {
    Ehcache createCache(String name);
}
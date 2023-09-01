package me.kartdroid.androidkitchen2

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import coil.util.DebugLogger

/**
 * @author [Karthick Chinnathambi]()
 * @since 01/09/23
 */
class AndroidKitchenApp : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        val imageLoader = ImageLoader.Builder(applicationContext)
                .components {
                    add(SvgDecoder.Factory())
                }
                .networkCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .diskCache {
                    coil.disk.DiskCache.Builder()
                            .directory(applicationContext.cacheDir.resolve("image_cache"))
                            .build()
                }
                .respectCacheHeaders(false)

        if (BuildConfig.DEBUG) {
            imageLoader.logger(DebugLogger())
        }
        /*if (isAllowHardWareDisable) {
            imageLoader.allowHardware(false)
        } else if (Canvas().isHardwareAccelerated.not()) {
            imageLoader.allowHardware(false)
            // here logging into firebase to identify the no of devices this feature is disabled
            FirebaseCrashlytics.getInstance().recordException(Throwable("RapidoRiderExt# isHardwareAccelerated is disabled"))
        }*/
        return imageLoader.build()
    }

}
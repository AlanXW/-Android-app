package com.lilei.fitness.image;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;

import com.lilei.fitness.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;


public class ImageLoaderConfig {

	
    public static DisplayImageOptions options;
    public static DisplayImageOptions options_head;
    
    public static DisplayImageOptions getDefaultConfigs(){
    	
        options = new DisplayImageOptions.Builder()
        .showStubImage(R.drawable.default_image)
        .showImageForEmptyUri(R.drawable.default_image)
        .showImageOnFail(R.drawable.default_image)
        .cacheInMemory(true)
        .cacheOnDisc(true)
      //.displayer(new RoundedBitmapDisplayer(20))
        .bitmapConfig(Bitmap.Config.RGB_565)
        .build();
        
        return options;
        
    }

	public static DisplayImageOptions initDisplayOptions(boolean isShowDefault) {
		DisplayImageOptions.Builder displayImageOptionsBuilder = new DisplayImageOptions.Builder();
		
		displayImageOptionsBuilder.imageScaleType(ImageScaleType.EXACTLY);
		if (isShowDefault) {
			displayImageOptionsBuilder.showStubImage(R.drawable.loading_animation);
			displayImageOptionsBuilder
					.showImageForEmptyUri(R.drawable.default_image);
			displayImageOptionsBuilder.showImageOnFail(R.drawable.default_image);
		}
		displayImageOptionsBuilder.cacheInMemory(true);
		displayImageOptionsBuilder.cacheOnDisc(true);
		displayImageOptionsBuilder.bitmapConfig(Bitmap.Config.RGB_565);

		return displayImageOptionsBuilder.build();
	}

	public static DisplayImageOptions initDisplayOptions(int targetWidth,
			boolean isShowDefault) {
		DisplayImageOptions.Builder displayImageOptionsBuilder = new DisplayImageOptions.Builder();
		displayImageOptionsBuilder.imageScaleType(ImageScaleType.EXACTLY);
		if (isShowDefault) {
			displayImageOptionsBuilder.showStubImage(R.drawable.loading_animation);
			displayImageOptionsBuilder
					.showImageForEmptyUri(R.drawable.default_image);
			displayImageOptionsBuilder
					.showImageOnFail(R.drawable.default_image);
		}
		displayImageOptionsBuilder.cacheInMemory(true);
		displayImageOptionsBuilder.cacheOnDisc(true);
		displayImageOptionsBuilder.bitmapConfig(Bitmap.Config.RGB_565);
		displayImageOptionsBuilder.displayer(new SimpleImageDisplayer(
				targetWidth));

		return displayImageOptionsBuilder.build();
	}

	public static void initImageLoader(Context context, String cacheDisc) {

		File cacheDir = StorageUtils.getOwnCacheDirectory(context, cacheDisc);
		ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
				context);
		builder.threadPoolSize(3);
		builder.threadPriority(Thread.NORM_PRIORITY);
		builder.memoryCache(new WeakMemoryCache());
		builder.memoryCacheExtraOptions(480, 800);
		builder.denyCacheImageMultipleSizesInMemory();
		builder.discCache(new UnlimitedDiscCache(cacheDir));
		builder.discCacheFileNameGenerator(new HashCodeFileNameGenerator());
		builder.imageDownloader(new BaseImageDownloader(context, 10000, 60000));
		builder.defaultDisplayImageOptions(initDisplayOptions(true));
		ImageLoader.getInstance().init(builder.build());
	}

}

package com.lilei.fitness.utils;

import java.io.File;
import java.util.List;

import android.os.Environment;

import com.lilei.fitness.entity.User;

public class Constants {

	public static User USER = new User();

	public static List<String> DAILYCHECKEDLIST;

	public static String APP_NAME = "";

	public static String BASE_URL = "http://192.168.0.101:8080/Fit/";

	public static final String IMAGE_URL = "http://58.211.5.34:8080/studioms/staticmedia/images/#";

	public static final String VIDEO_URL = "http://58.211.5.34:8080/studioms/staticmedia/video/#";

	public static final String SHARED_PREFERENCE_NAME = "fitness_prefs";

	public static final String SD_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ File.separator
			+ "com.lilei.fitness";

	public static final String BASE_PATH = SD_PATH + "/images";

	public static final String BASE_IMAGE_CACHE = BASE_PATH + "/cache";

	public static final String SHARE_FILE = BASE_PATH + "/QrShareImage.png";

	public static String IMEI = "";

	public static String TEL = "";

	public static int SCREEN_HEIGHT = 800;

	public static int SCREEN_WIDTH = 480;

	public static float SCREEN_DENSITY = 1.5f;

	public static final int SHARE_SUCCESS = 0X1000;

	public static final int SHARE_CANCEL = 0X2000;

	public static final int SHARE_ERROR = 0X3000;

	public static final int EXECUTE_LOADING = 0X4000;

	public static final int EXECUTE_SUCCESS = 0X5000;

	public static final int EXECUTE_FAILED = 0X6000;

	public static final int LOAD_DATA_SUCCESS = 0X7000;

	public static final int LOAD_DATA_ERROR = 0X8000;

	public static final int SET_DATA = 0X9000;

	public static final int NONE_LOGIN = 0X10000;
}

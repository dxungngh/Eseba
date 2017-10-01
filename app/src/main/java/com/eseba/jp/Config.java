package com.eseba.jp;

/**
 * Created by danielnguyen on 8/21/17.
 */

public class Config {

    public static final class Screen {
        public static final int VIEWPAGER_IMAGE_WIDTH_PIXEL = 3000;
        public static final int VIEWPAGER_IMAGE_HEIGHT_PIXEL = 1936;
    }

    public static final class Extras {
        public static final String PAGE_POSITION = "PAGE_POSITION";
        public static final String PAGE_TITLE = "PAGE_TITLE";
        public static final String PAGE_GENRE = "PAGE_GENRE";
    }

    public static final class Tab {
        public static final int NUMBER_OF_LOOPS = 10000;
    }

    public static final class Network {
        public static final String USERNAME = "xoway";
        public static final String PASSWORD = "159753";
        public static final int HTTP_REQUEST_TIMEOUT_MS = 5000;
        public static final int MAX_RETRY_NUMBER = 3;
        public static final String HOST = "https://eseba.jp/_TEST";
        public static final String SECURE_KEY = "securekey";
        public static final String API_KEY = "xEWL2Zn7AmsWhUKV5MGy2APs";
        public static final String EVENT = "event";
        public static final String SEMINAR = "seminar";
        public static final String GENRE = "genre";
        public static final String AUTHORIZATION = "Authorization";
        public static final String AUTHORIZATION_CODE = "Basic eG93YXk6MTU5NzUz";
    }

    public static final class Database {
        public static final String DATABASE_NAME = "ESEBASE.JP.DATABASE";
        public static final int DATABASE_VERSION = 1;
    }
}

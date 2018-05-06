package com.nanshan.lighteningstorm.pages.utils;

import android.Manifest.permission;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class EnvironmentInfo {
    private EnvironmentInfo() {
    }

    /**
     * 取得网络类型
     */
    public static int getNetWorkType(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkType();
    }

    /**
     * 获取IMEI
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        return imei;
    }

    /**
     * 获取IMSI
     *
     * @param context
     * @return
     */
    public static String getIMSI(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = tm.getSubscriberId();
        return imsi;
    }

    /**
     * 判断外部存储已经挂载(可用)
     */
    private static boolean isExternalStorageMounted() {
        boolean isMounted = Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
        if (!isMounted) {
            LogUtil.i("ExternalStorageState = "
                    + Environment.getExternalStorageState());
        }
        return isMounted;
    }

    /**
     * Check if external storage is built-in or removable.
     * @return True if external storage is removable (like an SD card), false
     * otherwise.
     */
    @TargetApi(9)
    private static boolean isExternalStorageRemovable() {
        if (SdkUtil.hasGingerbread()) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    /**
     * 获取手机内存项目存储根目录 格式(/data/data/项目包名/files/)
     */
    public static File getDataStorageDirectory(Context context) {
        return context.getFilesDir();
    }

    /**
     * 获取手机sdCard根目录 格式(/sdcard/)
     */
    public static File getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory();
    }

    /**
     * Get the external app cache directory.
     * @param context The context to use
     * @return The external cache dir
     */
    @TargetApi(8)
    public static File getExternalCacheDir(Context context) {
        if (SdkUtil.hasFroyo()) {
            return context.getExternalCacheDir();
        }
        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/Android/data/" + context.getPackageName()
                + "/cache/";
        File externalCacheDir = new File(
                Environment.getExternalStorageDirectory(), cacheDir);
        externalCacheDir.mkdirs();
        return externalCacheDir;
    }

    /**
     * Get a usable cache directory (external if available, internal otherwise).
     * @param context    The context to use
     * @param uniqueName A unique directory name to append to the cache dir
     * @return The cache dir
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use
        // external cache dir
        // otherwise use internal cache dir
        final File cachePathFile = isExternalStorageMounted() ? getExternalCacheDir(context)
                : context.getCacheDir();
        File uniqueCacheDir = new File(cachePathFile, uniqueName);
        uniqueCacheDir.mkdirs();
        return uniqueCacheDir;
    }

    /**
     * 获取外部存储目录""/Android/data/"PackageName"/files/""
     * @param context
     * @param uniqueName
     * @return
     */
    @TargetApi(8)
    public static File getExternalFilesDir(Context context, String uniqueName) {
        if (SdkUtil.hasFroyo()) {
            return context.getExternalFilesDir(uniqueName);
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String filesDir = "/Android/data/" + context.getPackageName()
                + "/files/";
        File externalFilesDir = new File(
                Environment.getExternalStorageDirectory(), filesDir);

        if (uniqueName == null) {
            externalFilesDir.mkdirs();
            return externalFilesDir;
        }

        File dir = new File(externalFilesDir, uniqueName);
        dir.mkdirs();
        // IoUtil.createDirector(dir.getAbsolutePath());
        return dir;
    }

    /**
     * 获取设备号
     *
     * @return
     */
    public static String getAndroidId(Context context) {
        return Secure
                .getString(context.getContentResolver(), Secure.ANDROID_ID);
    }

    /**
     * 判断是否有足够的空间
     * @return
     */
    public static boolean hasEnoughSpace(File file) {
        return getUsableSpace(file) > REMAIN_SPACE;
    }

    /**
     * Check how much usable space is available at a given path.
     * @param path The path to check
     * @return The space available in bytes
     */
    @TargetApi(9)
    public static long getUsableSpace(File path) {
        if (SdkUtil.hasGingerbread()) {
            return path.getUsableSpace();
        }
        // final StatFs stats = new StatFs(path.getPath());
        // return (long) stats.getBlockSize() * (long)
        // stats.getAvailableBlocks();
        return getAvailableSize(path);
    }

    private static final long REMAIN_SPACE = 5 * 1024 * 1024;

    /**
     * 获取存储可用内存大小
     */
    private static long getAvailableSize(File path) {
        if (path == null || !path.exists()) {
            return -1;
        }
        try {
            StatFs statFs = new StatFs(path.getPath());
            long availableBlocks = statFs.getAvailableBlocks();// 可用存储块的数量
            long blockSize = statFs.getBlockSize();// 每块存储块的大小
            long availableSize = availableBlocks * blockSize;// 可用容量
            return availableSize;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 获取存储总内存大小
     * @param root
     * @return
     */
    public static long getTotalSize(String root) {
        if (root == null || "".equals(root)) {
            return -1;
        }

        try {
            StatFs statFs = new StatFs(root);
            long blockCount = statFs.getBlockCount();// 总存储块的数量
            long blockSize = statFs.getBlockSize();// 每块存储块的大小
            long totalSize = blockCount * blockSize;// 总存储量
            return totalSize;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 获取已用存储大小
     * @param root
     * @return
     */
    public static long getUsedSize(String root) {
        long size = getTotalSize(root) - getUsableSpace(new File(root));
        if (size >= 0) {
            return size;
        }
        return 0;
    }

    /**
     * 获取IP地址
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            LogUtil.e(ex.toString());
        }
        return null;
    }

    /**
     * 判断是否是平板
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static boolean hasExternalStoragePermission(Context context) {
        return hasPermission(context, permission.WRITE_EXTERNAL_STORAGE);
    }

    public static boolean hasPermission(Context context, String permission) {
        int perm = context.checkCallingOrSelfPermission(permission);
        return perm == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 判断SDK版本
     */
    public static class SdkUtil {
        /**
         * 判断手机系统版本是否为{@link Build.VERSION_CODES#FROYO}以上
         *
         * @return
         */
        public static boolean hasFroyo() {
            // Can use static final constants like FROYO, declared in later
            // versions
            // of the OS since they are inlined at compile time. This is
            // guaranteed
            // behavior.
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
        }

        /**
         * 判断手机系统版本是否为{@link Build.VERSION_CODES#GINGERBREAD}以上
         * @return
         */
        public static boolean hasGingerbread() {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
        }

        /**
         * 判断手机系统版本是否为{@link Build.VERSION_CODES#HONEYCOMB}以上
         *
         * @return
         */
        public static boolean hasHoneycomb() {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
        }

        /**
         * 判断手机系统版本是否为{@link Build.VERSION_CODES#HONEYCOMB_MR1}以上
         * @return
         */
        public static boolean hasHoneycombMR1() {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
        }

    }
}

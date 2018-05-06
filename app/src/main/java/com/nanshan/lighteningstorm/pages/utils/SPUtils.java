package com.nanshan.lighteningstorm.pages.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;

import com.nanshan.lighteningstorm.config.MyApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.Set;

/**
 * usage:
 *  MyPreference.getInstance().read()
 *  MyPreference.getInstance().write()
 *
 */
public class SPUtils {

    private static SPUtils myPreference = null;
    private String packName = "NANSHAN";
    private Context mContext;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    /**
     * 是否是事务模式，在事务模式下，提交的修改都不会写入文件
     */
    private volatile boolean isTransactionMode = false;

    private SPUtils(Context context) {
        mContext = context;
        packName = context.getPackageName();
        sp = mContext.getSharedPreferences(packName, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static SPUtils getInstance() {
        if (myPreference == null) {
            myPreference = new SPUtils(MyApplication.getInstance());
        }
        return myPreference;
    }

    /**
     * 开启一个存储事务，开启后修改操作都不会写入文件，必须通过{@link #commitTransaction()}提交修改
     */
    public void beginTransaction() {
        synchronized (this) {
            isTransactionMode = true;
        }
    }

    /**
     * 提交存储事务
     */
    public void commitTransaction() {
        synchronized (this) {
            isTransactionMode = false;
        }
        editor.commit();
    }

    private boolean commit() {
        synchronized (this) {
            if (!isTransactionMode) {
                return  editor.commit();
            }
        }
        return false;
    }

    public String read(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public boolean read(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public float read(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public int read(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public long read(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public Set<String> read(String key, Set<String> defaultValue) {
        if (!EnvironmentInfo.SdkUtil.hasHoneycomb()) {
            return ObjectUtil.newHashSet();
        }
        return sp.getStringSet(key, defaultValue);
    }

    public <T extends Serializable> T read(Class<T> clazz) {
        String keyName = clazz.getSimpleName();
        return read(keyName, clazz);
    }

    public void write(String key, String value) {
        if (key == null) return;
        editor.putString(key, value);
        commit();
    }

    public void write(String key, boolean value) {
        if (key == null) return;
        editor.putBoolean(key, value);
        commit();
    }

    public void write(String key, float value) {
        if (key == null) return;
        editor.putFloat(key, value);
        commit();
    }

    public void write(String key, int value) {
        if (key == null) return;
        editor.putInt(key, value);
        commit();
    }

    public void write(String key, long value) {
        if (key == null) return;
        editor.putLong(key, value);
        commit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void write(String key, Set<String> value) {
        if (key == null) return;
        if (!EnvironmentInfo.SdkUtil.hasHoneycomb()) {
            commit();
            return;
        }
        editor.putStringSet(key, value);
        commit();
    }

    /**
     * 根据对象名保存对象数据
     */
    public <T extends Serializable>  boolean write(T t) {
        String keyName = t.getClass().getSimpleName();
        return writeObjectByName(keyName, t);
    }

    /**
     * 根据指定的键名保存对象数据
     */
    public <T extends Serializable> boolean writeObjectByName(String key, T t) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(t);
            String base64FromObject = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
            editor.putString(key, base64FromObject);
            return commit();
        } catch (IOException e) {
            return false;
        }
    }

    public <T extends Serializable> T read(String key, Class<T> clazz) {
        T t = null;
        String productBase64 = sp.getString(key, "");
        if (TextUtils.isEmpty(productBase64)) {
            return null;
        }
        byte[] base64 = Base64.decode(productBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64);
        try {
            ObjectInputStream bis = new ObjectInputStream(bais);
            try {
                t = (T) bis.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    public void remove(String key) {
        if (key == null) return;
        editor.remove(key);
        commit();
    }

}
package com.lilei.fitness.utils;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.lilei.fitness.entity.User;

public class SharedPreferencesUtils {

    public static boolean saveUserInfo(Context context, User user) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
            Editor editor = sharedPreferences.edit();
            editor.putInt("userId", user.getUserId());
            editor.putString("username", user.getUsername());
            editor.putString("password", user.getPassword());
            editor.putString("sex", user.getSex());
            editor.putString("height", user.getHeight() + "");
            editor.putString("weight", user.getWeight() + "");
            editor.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Map<String, String> getUserInfo(Context context) {
        HashMap<String, String> hashMap;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
            int userId = sharedPreferences.getInt("userId", 0);
            String password = sharedPreferences.getString("password", "");
            String username = sharedPreferences.getString("username", "");
            String sex = sharedPreferences.getString("sex", "male");
            String height = sharedPreferences.getString("height", "");
            String weight = sharedPreferences.getString("weight", "");

            hashMap = new HashMap<String, String>();
            hashMap.put("userId", userId + "");
            hashMap.put("password", password);
            hashMap.put("username", username);
            hashMap.put("sex", sex);
            hashMap.put("height", height);
            hashMap.put("weight", weight);
            return hashMap;

        } catch (Exception e) {
            e.printStackTrace();
            hashMap = null;
        }
        return hashMap;
    }

    public static boolean saveIPConfig(Context context, String ip, String port) {
        try {
            SharedPreferences preferences = context.getSharedPreferences("serverConnect", Context.MODE_PRIVATE);
            Editor edit = preferences.edit();
            edit.putString("ip", ip);
            edit.putString("port", port);
            edit.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Map<String, String> getIPConfig(Context context) {
        Map<String, String> map = new HashMap<String, String>();
        SharedPreferences preferences = context.getSharedPreferences("serverConnect", Context.MODE_PRIVATE);
        map.put("ip", preferences.getString("ip", ""));
        map.put("port", preferences.getString("port", ""));
        return map;
    }
}

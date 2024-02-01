package com.parmhannong;

import android.widget.Toast;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;   

import java.util.HashMap;
import java.util.Map;

public class ToastModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;
    ToastModule(ReactApplicationContext context){
        super(context);
        this.reactContext = context;
    }


    @Override
    public String getName() {
        return "ToastModule";
    }


    @ReactMethod
    public void show(String message, int duration) {
        ReactApplicationContext context = getReactApplicationContext();
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();

    }


    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("SHORT", Toast.LENGTH_SHORT);
        constants.put("LONG", Toast.LENGTH_LONG);

        return super.getConstants();
    }
}

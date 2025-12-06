package org.example.view.screen;

import java.util.HashMap;

public abstract class Screen {

    private HashMap<String, String> result = new HashMap<>();

    public boolean hasResult(String key) {
        return this.result.containsKey(key);
    }

    public String getResult(String key) {
        return this.result.get(key);
    }

    public void setResult(String key, String result) {
        this.result.put(key, result);
    }

    public String removeResult(String key) {
        return this.result.remove(key);
    }

    public abstract void action();
}

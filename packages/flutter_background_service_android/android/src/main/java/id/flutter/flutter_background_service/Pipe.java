package id.flutter.flutter_background_service;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Pipe {
    public interface  PipeListener {
        public void onReceived(JSONObject object);
    }

    private final List<PipeListener> listeners = new ArrayList<>();
    public boolean hasListener() {
        return !listeners.isEmpty();
    }

    public synchronized void addListener(PipeListener listener){
        this.listeners.add(listener);
    }

    public synchronized void removeListener(PipeListener listener){
        this.listeners.remove(listener);
    }

    public synchronized void invoke(JSONObject object){
        if (!listeners.isEmpty()) {
            for (PipeListener listener : listeners) {
                listener.onReceived(object);
            }
        }
    }
}

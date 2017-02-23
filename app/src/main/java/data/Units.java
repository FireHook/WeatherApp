package data;

import org.json.JSONObject;

/**
 * Created by User on 19.02.2017.
 */

public class Units implements JSONPopulator {

    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
    }
}

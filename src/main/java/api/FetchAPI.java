package api;

import api.menu.MainMenu;
import api.utils.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class FetchAPI {
    private ObjectMapper map;
    private static final FetchAPI instance = new FetchAPI();

    public static void main(String[] args) throws IOException, InterruptedException {
        MainMenu.show();
    }

    public static FetchAPI getInstance(){return instance;}
    public ObjectMapper getMap(){
        if(map == null) {
            map = new ObjectMapper();
            map.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return map;
    }
}

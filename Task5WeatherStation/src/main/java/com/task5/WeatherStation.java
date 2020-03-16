package com.task5;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


public class WeatherStation {

   public static Map<String, Object> jsonToMap(String str) {
            Map<String, Object> map = new Gson().fromJson(
                    str, new TypeToken<HashMap<String, Object>>() {}.getType()
            );
            return map;
        }

        public static void main(String[] args) {
            String apiKey = "cddeaefca430afebe4fd6735fcfb6ccf";
            String city = "Kiev, UA";
            String endpoint = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(endpoint);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line= reader.readLine()) != null) {
                result .append(line);
            }
            reader.close();
            System.out.println(result);

            Map<String, Object> resMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(resMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(resMap.get("wind").toString());
            Map<String, Object> cloudsMap = jsonToMap(resMap.get("clouds").toString());

            System.out.println("Current temperature: " + mainMap.get("temp"));
            System.out.println("Current humidity: " + mainMap.get("humidity"));
            System.out.println("Wind speed: " + windMap.get("speed"));
            System.out.println("Clouds destiny: " + cloudsMap.get("all"));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
   }
}

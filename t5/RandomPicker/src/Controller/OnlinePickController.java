package Controller;

import Model.NameList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class OnlinePickController implements Controller {


    @Override
    public String pick(NameList nameList) {
        return nameList.pick();
    }

    @Override
    public boolean shuffle(NameList nameList) {
        try {
            if (nameList.isEmpty()) {
                return false;
            }

            String urlstr = "https://www.random.org/lists/?mode=advanced";
            URL url = new URL(urlstr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setDoOutput(true);
            String data = getFormattedData(nameList.getNames());
            con.getOutputStream().write(data.getBytes("UTF-8"));

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            if (con.getResponseCode() != 200) return false;

            String responseLine;
            for (int i = 0; i < nameList.getNames().size(); i++) {
                responseLine = in.readLine();
                nameList.getNames().set(i, responseLine);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    private String getFormattedData(ArrayList<String> names) {
        String temp = "list=";

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            name = name + "%0D%0A";
            temp = temp + name;
        }

        temp = temp + "&format=plain&rnd=new";

        return temp;
    }

}

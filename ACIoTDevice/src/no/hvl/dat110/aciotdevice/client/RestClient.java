package no.hvl.dat110.aciotdevice.client;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class RestClient {

    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final String URL = "http://" + Configuration.host + ":" + Configuration.port;

    public RestClient() {
        // TODO Auto-generated constructor stub
    }

    private static String logpath = "/accessdevice/log/";

    public void doPostAccessEntry(String message) {
        // TODO: implement a HTTP POST on the service to post the message
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(new AccessMessage(message)));

        Request request = new Request.Builder().url(URL + logpath).post(body).build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String codepath = "/accessdevice/code";

    public AccessCode doGetAccessCode() {

        AccessCode code = null;

        // TODO: implement a HTTP GET on the service to get current access code

        Request request = new Request.Builder()
                .url(URL + codepath)
                .get()
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            code = new Gson().fromJson(response.body().string(), AccessCode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return code;
    }
}

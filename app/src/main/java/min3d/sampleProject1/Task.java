package min3d.sampleProject1;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by LeeDoBin on 2018-02-27.
 */

public class Task extends AsyncTask <String, Void, String>{
    private String str, receiveMsg;

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        String sendMsg;

        try {
            url = new URL("http://52.79.111.106:8080/study.jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "euc_kr");
            sendMsg = "mode="+params[0];
            osw.write(sendMsg);
            Log.i("tagtt" , String.valueOf(osw.toString()));
            osw.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                Log.i("tagtt" , "secces");
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "euc_kr");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str + "\n");
                    Log.i("tagtt" , "전송중");
                }
                receiveMsg = buffer.toString();
                Log.i("tagtt","receiveMs : " + receiveMsg);

                reader.close();

            } else {
                Log.i("tagtt", conn.getResponseCode() + "error");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("tagtt","1");
        receiveMsg = doJSONParser(receiveMsg);
        return receiveMsg;
    }

    String doJSONParser(String str) {
        StringBuffer sb = new StringBuffer();
        try {
            JSONArray jarray = new JSONArray(str);   // JSONArray 생성
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                String img = jObject.getString("img");
                String text = jObject.getString("text");
                String page = jObject.getString("page");

                sb.append(text+"&"+img+"&");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

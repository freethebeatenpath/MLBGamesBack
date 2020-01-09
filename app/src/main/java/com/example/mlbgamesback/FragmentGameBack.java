package com.example.mlbgamesback;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mlbgamesback.data.MLBObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FragmentGameBack extends Fragment {
    public final static String AMERICAN_LEAGUE_EAST = "America League East";
    public final static String AMERICAN_LEAGUE_CENTRAL = "America League Central";
    public final static String AMERICAN_LEAGUE_WEST = "America League West";
    public final static String NATIONAL_LEAGUE_EAST = "National League East";
    public final static String NATIONAL_LEAGUE_CENTRAL = "National League Central";
    public final static String NATIONAL_LEAGUE_WEST = "National League West";
    public final static String GAMEBACK = "Games Back";
    public final static String AMERICAN_LEAGUE_EAST_NUM = "201";
    public final static String AMERICAN_LEAGUE_CENTRAL_NUM = "202";
    public final static String AMERICAN_LEAGUE_WEST_NUM = "200";
    public final static String NATIONAL_LEAGUE_EAST_NUM = "204";
    public final static String NATIONAL_LEAGUE_CENTRAL_NUM = "205";
    public final static String NATIONAL_LEAGUE_WEST_NUM = "203";
    public final static String DATA_RETRIEVAL_RECORDS = "records";
    public final static String DATA_RETRIEVAL_DIVISION = "division";
    public final static String DATA_RETRIEVAL_ID = "id";
    public final static String DATA_RETRIEVAL_TEAMRECORDS = "teamRecords";
    public final static String DATA_RETRIEVAL_TEAM = "team";
    public final static String DATA_RETRIEVAL_NAME = "name";
    public final static String DATA_RETRIEVAL_GAMESBACK = "gamesBack";

    public final static String URL = "https://storage.mobileqa.mlbinfra.com/bpteam/interview/standings.json";


    private JSONObject jsonObject;
    private ListView userList;
    private HashMap<String, ArrayList<MLBObject>> objectList;
    private ArrayList<MLBObject> listObject;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gameback, parent, false);

    }

    // THe onViewCreated() event is triggered soon after onCreateView().
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    private void getService() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(URL).addHeader("User-Agent", "OkHttp").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(getContext(), call.toString() + " - FragmentGameBack - Failure !", Toast.LENGTH_SHORT);
                        toast.show();

                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) {
                try {
                    jsonObject = new JSONObject(response.body().string());
                    populate(jsonObject);
                } catch (JSONException e) {
                    Toast toast = Toast.makeText(getContext(), call.toString() + " - FragmentGameBack - Error during function!", Toast.LENGTH_SHORT);
                    toast.show();
                } catch (IOException e) {
                    Toast toast = Toast.makeText(getContext(), call.toString() + " - FragmentGameBack - Error during function!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private void populate(JSONObject json) throws JSONException {
        JSONArray recordsArray = json.getJSONArray(DATA_RETRIEVAL_RECORDS);
        for (int i = 0; i < recordsArray.length(); i++) {
            ArrayList<MLBObject> leagues = new ArrayList<>();
            JSONObject jo_inside = recordsArray.getJSONObject(i);
            JSONObject jo_insidea = jo_inside.getJSONObject(DATA_RETRIEVAL_DIVISION);
            String division_id = jo_insidea.getString(DATA_RETRIEVAL_ID);
            JSONArray teamRecordsArry = jo_inside.getJSONArray(DATA_RETRIEVAL_TEAMRECORDS);
            for (int j = 0; j < teamRecordsArry.length(); j++) {
                MLBObject mlbObject = new MLBObject(division_id);
                JSONObject teamRecordsArry_in = teamRecordsArry.getJSONObject(j);
                JSONObject teamRecordsArry_in_team = teamRecordsArry_in.getJSONObject(DATA_RETRIEVAL_TEAM);
                Log.d("NIKO_team_id-->", teamRecordsArry_in_team.getString(DATA_RETRIEVAL_ID));
                Log.d("NIKO_team_name-->", teamRecordsArry_in_team.getString(DATA_RETRIEVAL_NAME));
                Log.d("NIKO_gamesBack-->", teamRecordsArry_in.getString(DATA_RETRIEVAL_GAMESBACK));
                mlbObject.setTeamId(teamRecordsArry_in_team.getString(DATA_RETRIEVAL_ID));
                mlbObject.setDivisionName(getDivisionName(division_id));
                mlbObject.setTeamName(teamRecordsArry_in_team.getString(DATA_RETRIEVAL_NAME));
                mlbObject.setGameBack(teamRecordsArry_in.getString(DATA_RETRIEVAL_GAMESBACK));
                leagues.add(mlbObject);
            }
            objectList.put(division_id, leagues);
        }
    }


    //we know for this case that it will not return a Null. If not I would put in safeguards.
    private String getDivisionName(String s) {
        if (s.equals(AMERICAN_LEAGUE_WEST_NUM)) return AMERICAN_LEAGUE_WEST;
        if (s.equals(AMERICAN_LEAGUE_EAST_NUM)) return AMERICAN_LEAGUE_EAST;
        if (s.equals(AMERICAN_LEAGUE_CENTRAL_NUM)) return AMERICAN_LEAGUE_CENTRAL;
        if (s.equals(NATIONAL_LEAGUE_WEST_NUM)) return NATIONAL_LEAGUE_WEST;
        if (s.equals(NATIONAL_LEAGUE_EAST_NUM)) return NATIONAL_LEAGUE_EAST;
        else return NATIONAL_LEAGUE_CENTRAL;
    }

    private void displayData() {
        listObject = new ArrayList<>();
        listObject.add(new MLBObject(AMERICAN_LEAGUE_EAST, GAMEBACK));
        listObject.addAll(objectList.get(AMERICAN_LEAGUE_EAST_NUM));
        listObject.add(new MLBObject(AMERICAN_LEAGUE_CENTRAL, GAMEBACK));
        listObject.addAll(objectList.get(AMERICAN_LEAGUE_CENTRAL_NUM));
        listObject.add(new MLBObject(AMERICAN_LEAGUE_WEST, GAMEBACK));
        listObject.addAll(objectList.get(AMERICAN_LEAGUE_WEST_NUM));
        listObject.add(new MLBObject(NATIONAL_LEAGUE_EAST, GAMEBACK));
        listObject.addAll(objectList.get(NATIONAL_LEAGUE_EAST_NUM));
        listObject.add(new MLBObject(NATIONAL_LEAGUE_CENTRAL, GAMEBACK));
        listObject.addAll(objectList.get(NATIONAL_LEAGUE_CENTRAL_NUM));
        listObject.add(new MLBObject(NATIONAL_LEAGUE_WEST, GAMEBACK));
        listObject.addAll(objectList.get(NATIONAL_LEAGUE_WEST_NUM));

        //TODO Need to initialize adapter here

    }
}

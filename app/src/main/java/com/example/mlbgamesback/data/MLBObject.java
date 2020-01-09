package com.example.mlbgamesback.data;

import android.os.Parcel;
import android.os.Parcelable;


public class MLBObject implements Parcelable {
    public static final Creator<MLBObject> CREATOR = new Creator<MLBObject>() {
        @Override
        public MLBObject createFromParcel(Parcel in) {
            return new MLBObject(in);
        }

        @Override
        public MLBObject[] newArray(int size) {
            return new MLBObject[size];
        }
    };
    private String id_division;
    private String id_team;
    private String division_name;
    private String team_name;
    private String game_back;

    public MLBObject() {
    }

    public MLBObject(String id_division) {
        this.id_division = id_division;

    }

    public MLBObject(String division_name, String game_back) {
        this.id_division = null;
        this.division_name = division_name;
        this.id_team = null;
        this.team_name = null;
        this.game_back = game_back;
    }

    public MLBObject(String id_division, String division_name, String id_team, String team_name, String game_back) {
        this.id_division = id_division;
        this.division_name = division_name;
        this.id_team = id_team;
        this.team_name = team_name;
        this.game_back = game_back;
    }

    private MLBObject(Parcel in) {
        id_division = in.readString();
        id_team = in.readString();
        division_name = in.readString();
        team_name = in.readString();
        game_back = in.readString();
    }

    public String getDivisionId() {
        return id_division;
    }

    public void setDivisionId(String id_division) {
        this.id_division = id_division;
    }

    public String getTeamId() {
        return id_team;
    }

    public void setTeamId(String id_team) {
        this.id_team = id_team;
    }

    public String getDivisionName() {
        return division_name;
    }

    public void setDivisionName(String name) {
        division_name = name;
    }

    public String getTeamName() {
        return team_name;
    }

    public void setTeamName(String name) {
        team_name = name;
    }

    public String getGameBack() {
        return game_back;
    }

    public void setGameBack(String name) {
        game_back = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_division);
        dest.writeString(id_team);
        dest.writeString(division_name);
        dest.writeString(game_back);
    }
}



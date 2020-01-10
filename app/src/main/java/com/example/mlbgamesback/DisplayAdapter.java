package com.example.mlbgamesback;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mlbgamesback.data.MLBObject;

import java.util.ArrayList;

public class DisplayAdapter extends BaseAdapter {

    private final static String HOUSTON_ASTROS = "117";
    private final static String OAKLAND_ATHLETICS = "133";
    private final static String TEXAS_RANGERS = "140";
    private final static String LOS_ANGELES_ANGELS = "108";
    private final static String SEATTLE_MARINERS = "136";
    private final static String NEW_YORK_YANKEES = "147";
    private final static String TAMPA_BAY_RAYS = "139";
    private final static String BOSTON_RED_SOX = "111";
    private final static String TORONTO_BLUE_JAYS = "141";
    private final static String BALTIMORE_ORIOLES = "110";
    private final static String MINNESOTA_TWINS = "142";
    private final static String CLEVELAND_INDIANS = "114";
    private final static String CHICAGO_WHITE_SOX = "145";
    private final static String KANSAS_CITY_ROYALS = "118";
    private final static String DETROIT_TIGERS = "116";
    private final static String LOUIS_CARDINALS = "138";
    private final static String CHICAGO_CUBS = "112";
    private final static String MILWAUKEE_BREWERS = "158";
    private final static String CINCINNATI_REDS = "113";
    private final static String PITTSBURGH_PIRATES = "134";
    private final static String LOS_ANGELES_DODGERS = "119";
    private final static String ARIZONA_DIAMONDBACKS = "109";
    private final static String SAN_FRANCISCO_GIANTS = "137";
    private final static String SAN_DIEGO_PADRES = "135";
    private final static String COLORADO_ROCKIES = "115";
    private final static String ATLANTA_BRAVES = "144";
    private final static String WASHINGTON_NATIONALS = "120";
    private final static String NEW_YORK_METS = "121";
    private final static String PHILADELPHIA_PHILLIES = "143";
    private final static String MIAMI_MARLINS = "146";

    private static final int ARI = R.drawable.ic_ari;
    private static final int ATL = R.drawable.ic_atl;
    private static final int BAL = R.drawable.ic_bal;
    private static final int BOS = R.drawable.ic_bos;
    private static final int CHC = R.drawable.ic_chc;
    private static final int CIN = R.drawable.ic_cin;
    private static final int CLE = R.drawable.ic_cle;
    private static final int COL = R.drawable.ic_col;
    private static final int CWS = R.drawable.ic_cws;
    private static final int DET = R.drawable.ic_det;
    private static final int HOU = R.drawable.ic_hou;
    private static final int KC = R.drawable.ic_kc;
    private static final int LAA = R.drawable.ic_laa;
    private static final int LAD = R.drawable.ic_lad;
    private static final int MIA = R.drawable.ic_mia;
    private static final int MIL = R.drawable.ic_mil;
    private static final int MIN = R.drawable.ic_min;
    private static final int NYM = R.drawable.ic_nym;
    private static final int NYY = R.drawable.ic_nyy;
    private static final int OAK = R.drawable.ic_oak;
    private static final int PHI = R.drawable.ic_phi;
    private static final int PIT = R.drawable.ic_pit;
    private static final int SD = R.drawable.ic_sd;
    private static final int SEA = R.drawable.ic_sea;
    private static final int SF = R.drawable.ic_sf;
    private static final int STL = R.drawable.ic_stl;
    private static final int TB = R.drawable.ic_tb;
    private static final int TEX = R.drawable.ic_tex;
    private static final int TOR = R.drawable.ic_tor;
    private static final int WSH = R.drawable.ic_wsh;
    private static final int RND = R.drawable.ic_mlb_default;


    private Context mContext;
    private ArrayList<MLBObject> mlbObject;


    public DisplayAdapter(Context fragmentGameBack, ArrayList<MLBObject> mlbObject) {
        this.mContext = fragmentGameBack;
        this.mlbObject = mlbObject;
    }

    public int getCount() {
        return mlbObject.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    public View getView(int pos, View v, ViewGroup parent) {
        Holder mHolder;
        LayoutInflater layoutInflater;
        if (v == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.list_cell, null);
            mHolder = new Holder();

            mHolder.mainLL = v.findViewById(R.id.main_ll);
            //link to TextView
            mHolder.imgcol = v.findViewById(R.id.imgcol);
            mHolder.txtcola = v.findViewById(R.id.txtcola);
            mHolder.txtcolb = v.findViewById(R.id.txtcolb);
            v.setTag(mHolder);
        } else {
            mHolder = (Holder) v.getTag();
        }

        //transfer to views in screen
        if (mlbObject.get(pos).getDivisionId() == null && mlbObject.get(pos).getTeamName() == null) {
            //this area is for the titles of the division name (ie American League East, American League North, etc)
            mHolder.mainLL.setBackgroundColor(mContext.getResources().getColor(R.color.l_gray));
            mHolder.imgcol.setVisibility(View.GONE);
            mHolder.txtcola.setTypeface(Typeface.DEFAULT_BOLD);
            mHolder.txtcolb.setTypeface(Typeface.DEFAULT_BOLD);
            mHolder.txtcola.setText(mlbObject.get(pos).getDivisionName());
        } else {
            // incorporates the values from MLBObject under each section division name.
            mHolder.mainLL.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
            mHolder.imgcol.setVisibility(View.VISIBLE);
            mHolder.imgcol.setImageResource(setTeamImage(mlbObject.get(pos).getTeamId()));
            mHolder.txtcola.setText(mlbObject.get(pos).getTeamName());
            mHolder.txtcola.setTypeface(Typeface.DEFAULT);
            mHolder.txtcolb.setTypeface(Typeface.DEFAULT);
        }
        mHolder.txtcolb.setText(mlbObject.get(pos).getGameBack());
        return v;
    }

    private class Holder {
        LinearLayout mainLL;
        ImageView imgcol;
        TextView txtcola;
        TextView txtcolb;
    }


    //fuctions sets the correct image based on the input string (team ID).
    private int setTeamImage(String s) {
        int result = RND;
        switch (s) {
            case ARIZONA_DIAMONDBACKS:
                result = ARI;
                break;
            case ATLANTA_BRAVES:
                result = ATL;
                break;
            case BALTIMORE_ORIOLES:
                result = BAL;
                break;
            case BOSTON_RED_SOX:
                result = BOS;
                break;
            case CHICAGO_CUBS:
                result = CHC;
                break;
            case CINCINNATI_REDS:
                result = CIN;
                break;
            case CLEVELAND_INDIANS:
                result = CLE;
                break;
            case COLORADO_ROCKIES:
                result = COL;
                break;
            case CHICAGO_WHITE_SOX:
                result = CWS;
                break;
            case DETROIT_TIGERS:
                result = DET;
                break;
            case HOUSTON_ASTROS:
                result = HOU;
                break;
            case KANSAS_CITY_ROYALS:
                result = KC;
                break;
            case LOS_ANGELES_ANGELS:
                result = LAA;
                break;
            case LOS_ANGELES_DODGERS:
                result = LAD;
                break;
            case MIAMI_MARLINS:
                result = MIA;
                break;
            case MILWAUKEE_BREWERS:
                result = MIL;
                break;
            case MINNESOTA_TWINS:
                result = MIN;
                break;
            case NEW_YORK_METS:
                result = NYM;
                break;
            case NEW_YORK_YANKEES:
                result = NYY;
                break;
            case OAKLAND_ATHLETICS:
                result = OAK;
                break;
            case PHILADELPHIA_PHILLIES:
                result = PHI;
                break;
            case PITTSBURGH_PIRATES:
                result = PIT;
                break;
            case SAN_DIEGO_PADRES:
                result = SD;
                break;
            case SEATTLE_MARINERS:
                result = SEA;
                break;
            case SAN_FRANCISCO_GIANTS:
                result = SF;
                break;
            case LOUIS_CARDINALS:
                result = STL;
                break;
            case TAMPA_BAY_RAYS:
                result = TB;
                break;
            case TEXAS_RANGERS:
                result = TEX;
                break;
            case TORONTO_BLUE_JAYS:
                result = TOR;
                break;
            case WASHINGTON_NATIONALS:
                result = WSH;
                break;
        }
        return result;
    }
}


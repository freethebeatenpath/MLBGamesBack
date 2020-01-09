# MLBGamesBack
Provided in the json was:

```
"division" : {
      "id" : 200,
      "link" : "/api/v1/divisions/200"
    },
```

and 

```
"divisionRecords" : [ {
          "wins" : 47,
          "losses" : 18,
          "pct" : ".723",
          "division" : {
            "id" : 200,
            "name" : "American League West",
            "link" : "/api/v1/divisions/200"
          }
        }, {
          "wins" : 19,
          "losses" : 13,
          "pct" : ".594",
          "division" : {
            "id" : 201,
            "name" : "American League East",
            "link" : "/api/v1/divisions/201"
          }
        }, {
          "wins" : 18,
          "losses" : 13,
          "pct" : ".581",
          "division" : {
            "id" : 202,
            "name" : "American League Central",
            "link" : "/api/v1/divisions/202"
          }
        } ],
```

I could assume that because the “divisionRecords” showing the American League (West/East/Central) and more games in the American League that the Houston Astros are in the American League. Furthermore, we can look at the number of games between the American League (West/East/Central) and we can conclude that the Houston Astros are in the American League West. I was unable to conclude that that was 100% accurate form the provided JSON so I took another approach.


I could run separate API calls to like to get the appropriate data to fill in the blanks.
https://statsapi.mlb.com/api/v1/divisions/200
https://statsapi.mlb.com/api/v1/divisions/201
https://statsapi.mlb.com/api/v1/divisions/202
https://statsapi.mlb.com/api/v1/divisions/203
https://statsapi.mlb.com/api/v1/divisions/204
https://statsapi.mlb.com/api/v1/divisions/205


I instead hard code a little the division id with the division name.

Created a MLBObject (to contain the values form the request) that could be expanded on later. Used Parcelable over Serial because it is faster.

I have never used OKHTTP, but I used it because I think that’s what used at MLB.

Tested on emulator and physical Samsung device.

I will probably work on this some more later today tonight but what is currently provided is a working example and within the Acceptance Criteria.



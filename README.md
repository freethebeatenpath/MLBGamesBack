# MLBGamesBack

I could have parse down to the `divisionRecords` and compare it to the first record in `division` to associate the team with the division.

```
          "division" : {
            "id" : 200,
            "link" : "/api/v1/divisions/200"
          }
```

and

```
          "division" : {
            "id" : 200,
            "name" : "American League West",
            "link" : "/api/v1/divisions/200"
          }
```


I also could run separate API calls to get the appropriate data to fill in the blanks.
- https://statsapi.mlb.com/api/v1/divisions/200
- https://statsapi.mlb.com/api/v1/divisions/201
- https://statsapi.mlb.com/api/v1/divisions/202
- https://statsapi.mlb.com/api/v1/divisions/203
- https://statsapi.mlb.com/api/v1/divisions/204
- https://statsapi.mlb.com/api/v1/divisions/205


Instead I hard coded a little the division id with the division name.

Created a `MLBObject` (to contain the values from the request) so that it could be expanded on later. Used Parcelable over Serial because it is faster.

I have never used OKHTTP, but I used it because I think that is what is used at MLB. I found it was easy and Simple to use. From my understanding OKHTTP is thread safe.

Tested on emulator and physical Samsung device.

Currently provided is a working example and within the Acceptance Criteria.

Thanks
Nick

# PLAN OF ACTION

## TODO

- [x] RecyclerView on main activity
  - [ ] Each row of list represents a flight that is a departure from that airport that was searched.
  - [ ] Each row has a button which will send to details page
- [x] EditText for entering airport 
- [x] Button for filtering RecyclerView
  - [ ] Click event
- [x] Button for saved flight activity
  - [ ] Database operational
  - [ ] Fragment
- [x] Implement sharedPreferences to editText in first pages
- [ ] Details page for Flight object
   - [ ] +button1: send Flight details to database
   - [ ] +button2: delete  Flight details from databse
   - [ ] +button3: navigation button (back)
- [x] Get code from https://aviationstack.com/product
- [x] Store code here: **code** = 8e4f8a6f95d24ccff2a43f8f7a05546c

## CHECKLIST

- [x] RecyclerView
- [x] Toast
- [x] SnackBar
- [x] AlertDialog
- [x] EditText
- [x] Button
- [x] SharedPreferences
- [ ] (All) Toolbar 
- [ ] (All) 4 images on Toolbar
- [ ] (All) Click events on 4 images on toolbar
- [x] Help menu item
- [x] AlertDialog on help menu item
- [ ] Language support
- [x] Volley via Executor or AsyncTask
- [x] Fragment (link to Recycler)
- [x] Database
    - [x] Insert operation (send to Recycler)
    - [x] Delete operation
- [ ] Javadoc folder

### Notes

SharedPreferences in week 4

The URL for searching is “http://api.aviationstack.com/v1/flights?access_key=YOUR_ACCESS_KEY&dep_iata=THE_AIRPORT_CODE” where YOUR_ACCESS_KEY is something you must get from this website: https://aviationstack.com/product and THE_AIRPORT_CODE is a 3-letter code representing the airport. For instance, YOW is for Ottawa, YYZ is Toronto, YUL is Montreal. 

Your application must show a list of results in a RecyclerView, with each row representing a flight that is a departure from that airport that was searched.
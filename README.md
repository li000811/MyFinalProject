
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
- [ ] Implement sharedPreferences to editText in first pages
- [ ] Details page for Flight object
   - [ ] +button1: send Flight details to database
   - [ ] +button2: delete  Flight details from databse
   - [ ] +button3: navigation button (back)
   - [ ] Get code from https://aviationstack.com/product
   - [ ] Store code here: **code** = 
   - [ ] Get API key: https://currency.getgeoapi.com/
   - [ ] Store API key here: **key** = 

## CHECKLIST

- [x] RecyclerView
- [ ] Toast
- [ ] SnackBar
- [ ] AlertDialog
- [x] EditText
- [x] Button
- [ ] SharedPreferences
- [ ] (All) Toolbar 
- [ ] (All) 4 images on Toolbar
- [ ] (All) Click events on 4 images on toolbar
- [ ] Help menu item
- [ ] AlertDialog on help menu item
- [ ] Language support
- [ ] Volley via Executor or AsyncTask
- [ ] Fragment (link to Recycler)
- [ ] Database
    - [ ] Insert operation (send to Recycler)
    - [ ] Delete operation
- [ ] Javadoc folder

### Notes

SharedPreferences in week 4

The URL for searching is “http://api.aviationstack.com/v1/flights?access_key=YOUR_ACCESS_KEY?dep_iata=THE_AIRPORT_CODE” where YOUR_ACCESS_KEY is something you must get from this website: https://aviationstack.com/product and THE_AIRPORT_CODE is a 3-letter code representing the airport. For instance, YOW is for Ottawa, YYZ is Toronto, YUL is Montreal. 

Your application must show a list of results in a RecyclerView, with each row representing a flight that is a departure from that airport that was searched.
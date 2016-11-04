# Waterfall API documentation
## Get all users
**Url:** /Waterfall/api/users  
**Method:** `GET`  
**Success Response:** [200 OK]  
**Example content:**  
`[  
  {  
    "birthdate": "1989-09-06T00:00:00+02:00",  
    "city": "Gothenburg",  
    "country": ”Sweden”,  
    "email": ”sigrid@mail.com",  
    "firstName": ”Sigrid”,  
    "gender": "Female",  
    "lastName": ”Gunnarsson”,  
    "links": [  
      {  
        "relation": "Self",  
        "uri": "http://localhost:8080/Waterfall/api/users/1”  
      },  
      {  
        "relation": "Drops",  
        "uri": "http://localhost:8080/Waterfall/api/users/1/drops"  
      }  
    ],  
    "userid": 1,  
    "username": ”Sigge”  
  },  
  {  
    "birthdate": "1902-09-08T00:00:00+01:00",  
    "city": ”Halmstad”,  
    "country": ”Sweden”,  
    "email": ”joel@mail.com”,  
    "firstName": ”Joel”,  
    "gender": "Male",  
    "lastName": ”Nilsson”,  
    "links": [  
      {  
        "relation": "Self",  
        "uri": "http://localhost:8080/Waterfall/api/users/2”  
      },  
      {  
        "relation": "Drops",  
        "uri": "http://localhost:8080/Waterfall/api/users/2/drops"  
      }  
    ],  
    "userid": 2,  
    "username": ”Parab00la”  
  },  
]`  
**Error response:** [// todo lägg in något här]  
## Get user by ID
**URL:** /Waterfall/api/users/:id  
**URL params:** id=[Long]  
**Method:** `GET`  
**Success Response:** [200 OK]  
**Example content:**  
`{  
  "birthdate": "1989-09-06T00:00:00+02:00",  
  "city": "Gothenburg",  
  "country": ”Sweden”,  
  "email": ”sigrid@mail.com",  
  "firstName": ”Sigrid”,  
  "gender": "Female",  
  "lastName": ”Gunnarsson”,  
  "links": [  
    {  
      "relation": "Self",  
      "uri": "http://localhost:8080/Waterfall/api/users/1”  
    },  
    {  
      "relation": "Drops",  
      "uri": "http://localhost:8080/Waterfall/api/users/1/drops"  
    }  
  ],  
  "userid": 1,  
  "username": ”Sigge”  
}`  
**Error response:** [// todo lägg in något här]  
## Get all drops by user-ID  
**URL:** /Waterfall/api/users/:id/drops  
**URL params:** id=[Long]  
**Method:** `GET`  
**Success Response:** [200 OK]  
**Example content:**  
`[  
  {  
    "dropid": 6,  
    "content": "Hi everyone, what are you doing?”,  
    "creationDate": "2016-10-27T18:40:06.532",  
    "links": [],  
    "dropowner": {  
      "birthdate": "1989-09-06T00:00:00+02:00",  
      "city": "Gothenburg",  
      "country": ”Sweden”,  
      "email": ”sigrid@mail.com",  
      "firstName": ”Sigrid”,  
      "gender": "Female",  
      "lastName": ”Gunnarsson”,  
      "links": [],  
      "userid": 1,  
      "username": ”Sigge”  
    },  
    "comments": [  
      {  
        "commentid": 5,  
        "content": ”Nothing much, just browsing away..”,  
        "creationdate": "2016-10-27T18:40:15.868"  
      },  
      {  
        "commentid": 7,  
        "content": ”Eating lunch!”,  
        "creationdate": "2016-10-31T13:46:49.383"  
      }  
    ]  
  }  
]`  
**Error response:** [// todo lägg in något här]  
## Update user  
**URL:** /Waterfall/api/users/  
**Method:** `PUT`  
**Content type:** Json  
**Success Response:** [200 OK]  
**Example request:**  
`{  
    "birthdate": "1989-09-06T00:00:00+02:00",  
    "city": "Gothenburg",  
    "country": "Sweden",  
    "email": ”sigrid@mail.com",  
    "firstName": ”Anna”,  
    "gender": "Female",  
    "lastName": ”Gunnarsson”,  
    "userid": 1,  
    "username": ”Siggis”  
}`  
**Example response:**  
{  
  "birthdate": "1989-09-06T00:00:00+02:00",  
  "city": "Gothenburg",  
  "country": "Sweden",  
  "email": "sigrid@mail.com",  
  "firstName": ”Anna”,  
  "gender": "Female",  
  "lastName": "Gunnarsson",  
  "links": [],  
  "userid": 1,  
  "username": "Siggis"  
}
**Error response:** [// todo lägg in något här]  
## Create user  
**URL:** /Waterfall/api/users/  
**Method:** `POST`  
**Content type:** Json  
**Example request:**  
`{  
  "birthdate": "1970-11-20T00:00:00+02:00",  
  "city": ”Uddevalla”,  
  "country": "Sweden",  
  "email": ”arne@gmail.com",  
  "firstName": ”Arne”,  
  "gender": ”Other”,  
  "lastName": ”Arnesson”,  
  "password": "123",  
  "username": ”ArneMannen”  
}`  
**Success Response:** [201 CREATED]  
**Example response:**  
`{  
  "birthdate": "1970-11-20T00:00:00+02:00",  
  "city": ”Uddevalla”,  
  "country": "Sweden",  
  "email": "arne@gmail.com",  
  "firstName": ”Arne”,  
  "gender": ”Other”,  
  "lastName": ”Arnesson”,  
  "links": [],  
  "username": ”ArneMannen”  
}`  
**Error response:** [204 NO CONTENT]  
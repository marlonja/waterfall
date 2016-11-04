# Waterfall API documentation
## Get all users
**Url:** /Waterfall/api/users  
**Method:** GET  
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
**Method:** GET  
**Success Response:** [200 OK]  
**Example content:**  
`{  
  "birthdate": "1989-09-06T00:00:00+02:00",  
  "city": "Gothenburg",  
  "country": "American Samoa",  
  "email": "lena@gmail.com",  
  "firstName": "Lena",  
  "gender": "Female",  
  "lastName": "Stridsberg",  
  "links": [  
    {  
      "relation": "Self",  
      "uri": "http://localhost:8080/Waterfall/api/users/7"  
    },  
    {  
      "relation": "Drops",  
      "uri": "http://localhost:8080/Waterfall/api/users/7/drops"  
    }  
  ],  
  "userid": 7,  
  "username": "Lenaspena"  
}`
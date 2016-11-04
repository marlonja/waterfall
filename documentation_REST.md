# Waterfall API documentation
## Show all users
**Url:** /Waterfall/api/users  
**Method:** GET  
**Success Response:** [200 OK]  
**Example content:** `[  
{  
"birthdate": "1989-09-06T00:00:00+02:00",  
"city": "Gothenburg",  
"country": "Sweden",  
"email": "lena@gmail.com",  
"firstName": "Lena",  
"gender": "Female",  
"lastName": "Stridsberg",  
"links": [  
      {  
        "relation": "Self",  
        "uri": "http://localhost:8080/Waterfall/api/users/1"  
      },  
      {  
        "relation": "Drops",  
        "uri": "http://localhost:8080/Waterfall/api/users/1/drops"  
      }  
    ],  
    "userid": 1,  
    "username": "Lenaspena"  
},  
{  
"birthdate": "1986-07-29T00:00:00+02:00",  
"city": "Gothenburg",  
"country": "Sweden",  
"email": "joel@gmail.com",  
"firstName": "Joel",  
"gender": "Male",  
"lastName": "Nilsson",  
"links": [  
      {  
        "relation": "Self",  
        "uri": "http://localhost:8080/Waterfall/api/users/8"  
      },  
      {  
        "relation": "Drops",  
        "uri": "http://localhost:8080/Waterfall/api/users/8/drops"  
      }  
    ],  
    "userid": 8,  
    "username": "Fi"  
}  
]`  
**Error response:** [// todo lägg in något här]
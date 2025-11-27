Statement of the technical exercise to complete:
You are a new Developer in an online product sales company. The company sells
three types of products: high-end phones, mid-range phones, and laptops.
Your Product Manager asks you to develop a program that calculates a consumer’s
shopping cart total.
There are two types of clients:
1. Individual clients, identified by a client ID, first name, and last name
2. Professional clients, identified by a client ID, company name, intra-
community VAT number (optional), business registration number, and annual
revenue
For individuals, the high-end phone model costs €1500, the mid-range model costs
€800, and the laptop costs €1200.
For professional clients with annual revenue greater than €10 million, the high-end
phone costs €1000, the mid-range phone costs €550, and the laptop costs €900.
For professional clients with annual revenue below €10 million, the high-end
phone costs €1150, the mid-range phone costs €600, and the laptop costs €1000.
Calculate, for a given client, the total cost of their shopping cart, knowing that it may
contain multiple quantities of each of the three products.

Make sure to implement tests — failing to do so will result in disqualification

Testing the application for IndividualClient and ProfessionalClient
--------------------------------------------------------------------
1.IndividualClient
  POST URL:http://localhost:8080/api/cart/totals
  Request Body:
  {
  "firstName": "Jitendra",
  "lastName": "Mishra",
  "companyName": "",
  "vatNumber": "",
  "registrationNumber": "",
  "annualRevenue": 0,
  "clientType": "individual",
  "clientId": "ABCD01",
  "quantities": {
    "high_end_phone": 1,
    "mid_range_phone": 1,
    "laptop": 1
  }
}

2.ProfessionalClient
 POST URL:http://localhost:8080/api/cart/totals
 Request Body: {
  "firstName": "",
  "lastName": "",
  "companyName": "ABCD01",
  "vatNumber": "123",
  "registrationNumber": "AS3458N",
  "annualRevenue": 100000000,
  "clientType": "professional",
  "clientId": "ABCD01",
  "quantities": {
    "high_end_phone": 1,
    "mid_range_phone": 1,
    "laptop": 1
  }
}

Swagger URL:http://localhost:8080/swagger-ui/index.html#/

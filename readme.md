# Country Info API

A Spring Boot project that fetches country data from the **[REST Countries API](https://restcountries.com/)**, stores it in a relational database, and provides a RESTful API to retrieve the data.

## Endpoints
**Endpoint**:
`GET /api/v1/countries/{country_code}`

**Description**:  
Fetch country data by ISO 3166-1 alpha-3 code.

**Example request**:  
```bash
curl -X POST http://localhost:8080/api/v1/countries/POL
```

**Example response**
```json
{
  "cca3": "POL",
  "commonName": "Poland",
  "officialName": "Republic of Poland",
  "capitals": [
    "Warsaw"
  ],
  "region": "Europe",
  "subregion": "Central Europe",
  "population": 37950802,
  "borders": [
    "BLR",
    "CZE",
    "DEU",
    "LTU",
    "RUS",
    "SVK",
    "UKR"
  ],
  "timezones": [
    "UTC+01:00"
  ],
  "languages": {
    "pol": "Polish"
  },
  "currencies": [
    {
      "code": "PLN",
      "name": "Polish złoty",
      "symbol": "zł"
    }
  ]
}
```
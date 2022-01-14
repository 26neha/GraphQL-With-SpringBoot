# GraphQL-With-SpringBoot

GRAPHQL:- 
GraphQL is an open-source data query and manipulation language for APIs, and a runtime for fulfilling queries with existing data.
GraphQL endpoint is located at http://localhost:8096/rest/books
POST(METHOD) i/p in postman
{
  allBooks {
      title
      authors
  }
  book(id: "123"){
    title
    authors
    publisher

  }
}

& RESULT:-

{
    "errors": [],
    "data": {
        "allBooks": [
            {
                "title": "Cloud Computing",
                "authors": [
                    "Venkat"
                ]
            }
        ],
        "book": {
            "title": "Cloud Computing",
            "authors": [
                "Venkat"
            ],
            "publisher": "Kindle"
        }
    },
    "extensions": null
}

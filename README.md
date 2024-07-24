# URL Shortener

A simple URL shortener written in Clojure.

### Tech Used

- **Clojure**: The primary programming language for this project.
- **Reitit**: Routing library for handling HTTP requests.
- **Muuntaja**: Handle encoding/decoding of HTTP request and response.
- **Ring**: A Clojure web applications library.
- **Carmine**: Redis client for Clojure, used for storing shortened URLs.
- **org.clojure/data.codec**: Used in base64 encoding for URL hashes.

### Prerequisites

- [Leiningen](https://leiningen.org/) 
- [Redis](https://redis.io/)

### Usage

1. To shorten, send a POST request to `/shorten` with the long URL in the request body.

### Example

1. Shorten a URL:
   ```sh
   curl -X POST -d '{"url": "http://example.com"}' -H "Content-Type: application/json" http://localhost:3000/shorten
   ```

### TODO

- Implement the front end using ClojureScript.

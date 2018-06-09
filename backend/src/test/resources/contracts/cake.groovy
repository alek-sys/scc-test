package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method "GET"
        urlPath("/cake/1") {
//            queryParameters {
//                parameter 'limit': 100
//            }
        }
    }

    response {
        status OK()
        body(["name": "Cake is a lie"])
        bodyMatchers {
            jsonPath('$.name', byRegex(".+"))
        }
        headers {
            contentType("application/json")
        }
    }
}
package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method "GET"
        urlPath "/stock/5b48bb1aa873178c033a3c07"
    }

    response {
        status OK()
        body(value(stub("2"), test(regex("[0-9]+"))))
        headers {
            contentType("application/json")
        }
    }
}
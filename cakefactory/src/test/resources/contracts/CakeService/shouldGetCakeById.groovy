package contracts.CakeService

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method "GET"
        urlPath "/cake/5b48bb1aa873178c033a3c07"
    }

    response {
        status OK()
        body(["name": anyNonEmptyString(), "qty": anyPositiveInt()])
        headers {
            contentType(applicationJson())
        }
    }
}
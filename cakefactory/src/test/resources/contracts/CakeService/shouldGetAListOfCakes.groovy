package contracts.CakeService

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method "GET"
        urlPath "/cake"
    }

    response {
        status OK()
        body([
            ["name": anyNonEmptyString(), "qty": anyPositiveInt()],
            ["name": anyNonEmptyString(), "qty": anyPositiveInt()],
            ["name": anyNonEmptyString(), "qty": anyPositiveInt()],
        ])
        headers {
            contentType(applicationJson())
        }
    }
}
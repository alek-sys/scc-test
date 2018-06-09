package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method "GET"
        url "/cake"
    }

    response {
        status OK()
        body([
            ["name": "Fluffy Lizard"],
            ["name": "Sweety November"],
            ["name": "Sugarmode"],
            ["name": "Veryberry"],
        ])
        headers {
            contentType("application/json")
        }
    }
}
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
            ["name": "Sweety November"],
            ["name": "Sugarbomb"],
            ["name": "Veryberry"],
        ])
        headers {
            contentType("application/json")
        }
    }
}
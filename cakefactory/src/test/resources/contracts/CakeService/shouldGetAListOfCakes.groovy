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
            ["name": "Curly Whirly"],
            ["name": "Passion Fizz"],
            ["name": "Red Velvet"],
        ])
        headers {
            contentType("application/json")
        }
    }
}
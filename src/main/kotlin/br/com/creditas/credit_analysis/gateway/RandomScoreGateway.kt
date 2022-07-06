package br.com.creditas.credit_analysis.gateway

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus

@FeignClient(
    value = "randomClient",
    url = "https://www.random.org",
    decode404 = false
)
interface RandomScoreGateway {
    @GetMapping(
        path = ["/integers/?num=1&min=0&max=1000&col=1&base=10&format=plain&rnd=new"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun getScore(): String
}
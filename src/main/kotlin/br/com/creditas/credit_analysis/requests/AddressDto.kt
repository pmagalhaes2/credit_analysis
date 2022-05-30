package br.com.creditas.credit_analysis.requests

import javax.validation.constraints.Size

data class AddressDto(
    val street: String,
    val city: String,
    @field:Size(min = 2, max = 2)
    val state: String,
    val cep: String
)
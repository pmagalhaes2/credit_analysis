package br.com.creditas.credit_analysis.requests

import java.util.Date

data class ClientUpdateRequest(
    val cpf: String,
    val name: String,
    val lastName: String,
    val birthDate: Date,
    val address: AddressDto? = null,
    val contact: ContactDto? = null
)
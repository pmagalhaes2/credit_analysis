package br.com.creditas.credit_analysis.requests

import java.util.*

data class ClientCreationRequest(
    val cpf: String,
    val name: String,
    val lastName: String,
    val birthDate: Date,
    val address: AddressDto? = null
)
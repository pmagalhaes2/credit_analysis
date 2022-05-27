package br.com.creditas.credit_analysis.responses

import br.com.creditas.credit_analysis.requests.AddressDto
import java.util.*

data class ClientCreationResponse(
    val id: UUID,
    val cpf: String,
    val name: String,
    val lastName: String,
    val birthDate: Date,
    val address: AddressDto?
)
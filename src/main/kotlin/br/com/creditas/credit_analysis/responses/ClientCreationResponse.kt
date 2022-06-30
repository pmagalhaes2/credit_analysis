package br.com.creditas.credit_analysis.responses

import br.com.creditas.credit_analysis.requests.AddressDto
import br.com.creditas.credit_analysis.requests.ContactDto
import java.time.LocalDate
import java.util.UUID

data class ClientCreationResponse(
    val id: UUID,
    val cpf: String,
    val name: String,
    val lastName: String,
    val birthDate: LocalDate,
    val score: Int,
    val address: AddressDto?,
    val contacts: List<ContactDto>?
)
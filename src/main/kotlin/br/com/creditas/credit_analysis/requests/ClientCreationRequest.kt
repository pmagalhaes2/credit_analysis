package br.com.creditas.credit_analysis.requests

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.validator.constraints.br.CPF
import java.util.Date
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern

data class ClientCreationRequest(
    @field:NotEmpty
    @field:CPF
    val cpf: String,

    @field:NotEmpty
    @field:Pattern(regexp = "^[\\pL\\pM\\p{Zs}]+\$")
    val name: String,

    @field:NotEmpty
    @field:Pattern(regexp = "^[\\pL\\pM\\p{Zs}]+\$")
    val lastName: String,

    @JsonFormat(pattern= ("dd/MM/yyyy"), locale = "pt-BR", timezone = "UTC")
    val birthDate: Date,

    @field:Valid
    val address: AddressDto? = null,

    @field:Valid
    val contacts: List<ContactDto>? = null
)
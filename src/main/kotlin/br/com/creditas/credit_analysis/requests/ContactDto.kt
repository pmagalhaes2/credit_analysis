package br.com.creditas.credit_analysis.requests

import br.com.creditas.credit_analysis.models.PhoneType
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class ContactDto(
    val type: PhoneType,

    @field:Size(min = 11, max = 12)
    @field:Pattern(
        regexp = "^\\d*\$",
        message="must match only numbers"
    )
    val phoneNumber: String,

    @field:Email
    @field:Size(max = 255)
    val email: String
)
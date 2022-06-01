package br.com.creditas.credit_analysis.requests

import br.com.creditas.credit_analysis.models.PhoneType
import javax.validation.constraints.Email
import javax.validation.constraints.Size

data class ContactDto(
    val type: PhoneType,
    @Size(min = 11, max = 12)
    val phoneNumber: String,
    @Email
    @Size(max = 255)
    val email: String
)
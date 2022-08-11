package br.com.creditas.credit_analysis.requests

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.Pattern

data class ClientUpdateRequest(
    // regexp = "^[\\pL\\pM\\p{Zs}]+\$" -> validates if string matches anything in the Unicode letter category, matching diacritics and whitespace separators
    @field:CPF
    @ApiModelProperty(example = "12345678900")
    val cpf: String,

    @field:Pattern(regexp = "^[\\pL\\pM\\p{Zs}]+\$")
    @ApiModelProperty(example = "Fulano")
    val name: String,

    @field:Pattern(regexp = "^[\\pL\\pM\\p{Zs}]+\$")
    @ApiModelProperty(example = "De Tal")
    val lastName: String,

    @JsonFormat(pattern= ("dd/MM/yyyy"), locale = "pt-BR", timezone = "UTC")
    @ApiModelProperty(example = "01/01/2000")
    val birthDate: LocalDate,

    @field:Valid
    val address: AddressDto? = null,

    @field:Valid
    val contacts: List<ContactDto>? = ArrayList()
)
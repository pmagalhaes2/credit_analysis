package br.com.creditas.credit_analysis.requests

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern

data class ClientCreationRequest(
    // regexp = "^[\\pL\\pM\\p{Zs}]+\$" -> validates if string matches anything in the Unicode letter category, matching diacritics and whitespace separators //
    @field:NotEmpty
    @field:CPF
    @ApiModelProperty(example = "12345678900")
    val cpf: String,

    @field:NotEmpty
    @field:Pattern(
        regexp = "^[\\pL\\pM\\p{Zs}]+\$",
        message = "must match only letters, diacritics, and whitespace separators"
    )
    @ApiModelProperty(example = "Fulano")
    val name: String,

    @field:NotEmpty
    @field:Pattern(
        regexp = "^[\\pL\\pM\\p{Zs}]+\$",
        message = "must match only letters, diacritics, and whitespace separators"
    )
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
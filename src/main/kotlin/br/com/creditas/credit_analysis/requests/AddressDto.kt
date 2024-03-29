package br.com.creditas.credit_analysis.requests

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class AddressDto(
    // regexp = "^[\\pL\\pM\\p{Zs}]+\$" -> validates if string matches anything in the Unicode letter category, matching diacritics and whitespace separators
    @field:Pattern(
        regexp = "^[\\pL\\pM\\p{Zs}]+\$",
        message = "must match only letters, diacritics, and whitespace separators"
    )
    @ApiModelProperty(example = "Rua Canário")
    val street: String,

    @field:Pattern(
        regexp = "^[\\pL\\pM\\p{Zs}]+\$",
        message = "must match only letters, diacritics, and whitespace separators"
    )
    @ApiModelProperty(example = "São Paulo")
    val city: String,

    @field:Size(min = 2, max = 2, message = "size must be 2")
    @field:Pattern(
        regexp = "(?i)(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)",
        message = "must match to an acronym of the Brazilian states"
    )
    @ApiModelProperty(example = "SP")
    val state: String,

    @field:Size(min = 8, max = 8, message = "size must be 8")
    @field:Pattern(regexp = "^\\d*\$")
    @ApiModelProperty(example = "08373650")
    val cep: String,
)
package br.com.creditas.credit_analysis.responses

import br.com.creditas.credit_analysis.requests.AddressDto
import br.com.creditas.credit_analysis.requests.ContactDto
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import java.util.UUID

data class ClientCreationResponse(
    @ApiModelProperty(example = "a24eef38-16a2-47ba-97dc-c0cb77d14e34")
    val id: UUID,

    @ApiModelProperty(example = "12345678900")
    val cpf: String,

    @ApiModelProperty(example = "Fulano")
    val name: String,

    @ApiModelProperty(example = "De Tal")
    val lastName: String,

    @ApiModelProperty(example = "01/01/2000")
    val birthDate: LocalDate,

    @ApiModelProperty(example = "1000")
    val score: Int,

    val address: AddressDto?,

    val contacts: List<ContactDto>?
)
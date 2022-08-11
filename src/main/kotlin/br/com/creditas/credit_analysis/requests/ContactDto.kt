package br.com.creditas.credit_analysis.requests

import br.com.creditas.credit_analysis.models.PhoneType
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class ContactDto(
    @ApiModelProperty(example = "CELL_PHONE")
    val type: PhoneType,

    @field:Size(min = 11, max = 12)
    @field:Pattern(
        regexp = "^\\d*\$",
        message="must match only numbers"
    )
    @ApiModelProperty(example = "11944015362")
    val phoneNumber: String,

    @field:Email
    @field:Size(max = 255)
    @ApiModelProperty(example = "teste@teste.com")
    val email: String
)
package br.com.creditas.credit_analysis.models

import com.fasterxml.jackson.annotation.JsonBackReference
import io.swagger.annotations.ApiModelProperty
import javax.persistence.Entity
import javax.persistence.Id
import java.util.UUID
import javax.persistence.EnumType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Enumerated
import javax.persistence.Column

@Entity
data class Contact(
    @Id
    @ApiModelProperty(example = "a24eef38-16a2-47ba-97dc-c0cb77d14e36")
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonBackReference
    val client: ClientPF,

    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(example = "CELL_PHONE")
    val type: PhoneType = PhoneType.CELL_PHONE,

    @Column(name = "phone_number")
    @ApiModelProperty(example = "11944015362")
    val phoneNumber: String,

    @Column(name = "email_address")
    @ApiModelProperty(example = "teste@teste.com")
    val emailAddress: String
)

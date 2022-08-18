package br.com.creditas.credit_analysis.models

import com.fasterxml.jackson.annotation.JsonBackReference
import io.swagger.annotations.ApiModelProperty
import org.hibernate.annotations.DynamicUpdate
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.JoinColumn
import javax.persistence.Column

@Entity
@DynamicUpdate
data class Address(
    @Id
    @ApiModelProperty(example = "a24eef38-16a2-47ba-97dc-c0cb77d14e35")
    val id: UUID = UUID.randomUUID(),

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonBackReference
    val client: ClientPF,

    @Column(name = "street")
    @ApiModelProperty(example = "Rua Canário")
    val street: String,

    @Column(name = "city")
    @ApiModelProperty(example = "São Paulo")
    val city: String,

    @Column(name = "state")
    @ApiModelProperty(example = "SP")
    val state: String,

    @Column(name = "cep")
    @ApiModelProperty(example = "08373650")
    val cep: String
)

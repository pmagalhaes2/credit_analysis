package br.com.creditas.credit_analysis.models

import com.fasterxml.jackson.annotation.JsonManagedReference
import io.swagger.annotations.ApiModelProperty
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.OneToOne
import javax.persistence.OneToMany
import kotlin.collections.ArrayList


@Entity
@Table(name = "client_pf")
@DynamicUpdate
data class ClientPF(
    @Id
    @ApiModelProperty(example = "a24eef38-16a2-47ba-97dc-c0cb77d14e34")
    val id: UUID = UUID.randomUUID(),

    @Column(name = "cpf")
    @ApiModelProperty(example = "12345678900")
    val cpf: String,

    @Column(name = "name")
    @ApiModelProperty(example = "Fulano")
    val name: String,

    @Column(name = "last_name")
    @ApiModelProperty(example = "De Tal")
    val lastName: String,

    @Column(name = "birth_date")
    @ApiModelProperty(example = "01/01/2000")
    val birthDate: LocalDate,

    @Column(name = "score")
    @ApiModelProperty(example = "1000")
    val score: Int,

    @OneToOne(mappedBy = "client")
    @JsonManagedReference
    val address: Address? = null,

    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    val contacts: List<Contact> = ArrayList()
)
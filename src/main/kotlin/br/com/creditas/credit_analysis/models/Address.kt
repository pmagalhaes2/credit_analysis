package br.com.creditas.credit_analysis.models

import org.hibernate.annotations.DynamicUpdate
import java.util.*
import javax.persistence.*

@Entity
@DynamicUpdate
data class Address(
    @Id
    val id: UUID = UUID.randomUUID(),

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    val client: ClientPF,

    @Column(name = "street")
    val street: String,

    @Column(name = "city")
    val city: String,

    @Column(name = "state")
    val state: String,

    @Column(name = "cep")
    val cep: String
)

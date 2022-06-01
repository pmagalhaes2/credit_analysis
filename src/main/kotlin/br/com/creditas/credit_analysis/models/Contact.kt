package br.com.creditas.credit_analysis.models

import com.fasterxml.jackson.annotation.JsonBackReference
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
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonBackReference
    val client: ClientPF,

    @Enumerated(value = EnumType.STRING)
    val type: PhoneType = PhoneType.CELL_PHONE,

    @Column(name = "phone_number")
    val phoneNumber: String,

    @Column(name = "email_address")
    val emailAddress: String
)

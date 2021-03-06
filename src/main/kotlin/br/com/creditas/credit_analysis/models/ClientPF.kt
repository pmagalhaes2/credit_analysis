package br.com.creditas.credit_analysis.models

import com.fasterxml.jackson.annotation.JsonManagedReference
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
    val id: UUID = UUID.randomUUID(),

    @Column(name = "cpf")
    val cpf: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "last_name")
    val lastName: String,

    @Column(name = "birth_date")
    val birthDate: LocalDate,

    @Column(name = "score")
    val score: Int,

    @OneToOne(mappedBy = "client")
    @JsonManagedReference
    val address: Address? = null,

    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    val contacts: List<Contact> = ArrayList()
)
package br.com.creditas.credit_analysis.models

import java.time.LocalDate

object ClientPFTest {
    fun build(
            address: Address? = null,
            contacts: List<Contact> = ArrayList()
    ) = ClientPF(
            cpf = "88202971080",
            name = "Rúbia",
            lastName = "Gonçalves",
            birthDate = LocalDate.now(),
            score = 1000,
            address = address,
            contacts = contacts
    )
}
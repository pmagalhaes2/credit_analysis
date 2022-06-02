package br.com.creditas.credit_analysis.repositories

import br.com.creditas.credit_analysis.exceptions.NotFoundException
import br.com.creditas.credit_analysis.models.ClientPF
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@Repository
interface ClientRepository: JpaRepository<ClientPF, UUID> {
    fun findByCpf(cpf: String): ClientPF?

    fun findByClientId(id: UUID): ClientPF?

    fun deleteByCpf(cpf: String)
}
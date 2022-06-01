package br.com.creditas.credit_analysis.repositories

import br.com.creditas.credit_analysis.models.ClientPF
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ClientRepository: JpaRepository<ClientPF, UUID> {
    fun findByCpf(cpf: String): ClientPF
}
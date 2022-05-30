package br.com.creditas.credit_analysis.repositories

import br.com.creditas.credit_analysis.models.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AddressRepository : JpaRepository<Address, UUID>
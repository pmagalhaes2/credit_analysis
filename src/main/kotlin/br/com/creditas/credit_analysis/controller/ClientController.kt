package br.com.creditas.credit_analysis.controller

import br.com.creditas.credit_analysis.models.Address
import br.com.creditas.credit_analysis.models.ClientPF
import br.com.creditas.credit_analysis.repositories.AddressRepository
import br.com.creditas.credit_analysis.repositories.ClientRepository
import br.com.creditas.credit_analysis.requests.AddressDto
import br.com.creditas.credit_analysis.requests.ClientCreationRequest
import br.com.creditas.credit_analysis.responses.ClientCreationResponse
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.http.ResponseEntity
import java.util.UUID
import java.util.Optional

@RestController
@RequestMapping("/accounts")
class ClientController(
    private val clientRepository: ClientRepository,
    private val addressRepository: AddressRepository
) {

    @PostMapping
    @Transactional
    fun create(@RequestBody clientRequest: ClientCreationRequest): ClientCreationResponse {
        val clientEntity = ClientPF(
            cpf = clientRequest.cpf,
            name = clientRequest.name,
            lastName = clientRequest.lastName,
            birthDate = clientRequest.birthDate
        )

        val savedClient = clientRepository.save(clientEntity)

        val addressEntity = if (clientRequest.address != null) addressRepository.save(
            Address(
                street = clientRequest.address.street,
                city = clientRequest.address.city,
                state = clientRequest.address.state,
                cep = clientRequest.address.cep,
                client = clientEntity
            )
        ) else null

        val response = ClientCreationResponse(
            id = savedClient.id,
            cpf = savedClient.cpf,
            name = savedClient.name,
            lastName = savedClient.lastName,
            birthDate = savedClient.birthDate,
            address = if (addressEntity != null) AddressDto(
                cep = addressEntity.cep,
                street = addressEntity.street,
                city = addressEntity.city,
                state = addressEntity.state
            ) else null

        )
        return response
    }

    @GetMapping
    fun read() = ResponseEntity.ok(clientRepository.findAll())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<Optional<ClientPF>> {
        return ResponseEntity.ok(clientRepository.findById(id))
    }

    @GetMapping("/find")
    fun getByCpf(@RequestParam cpf: String): ResponseEntity<Optional<ClientPF>> {
        return ResponseEntity.ok(clientRepository.findByCpf(cpf))
    }
}
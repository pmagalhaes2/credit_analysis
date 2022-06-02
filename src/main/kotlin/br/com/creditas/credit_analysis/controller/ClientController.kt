package br.com.creditas.credit_analysis.controller

import br.com.creditas.credit_analysis.models.Address
import br.com.creditas.credit_analysis.models.ClientPF
import br.com.creditas.credit_analysis.models.Contact
import br.com.creditas.credit_analysis.repositories.AddressRepository
import br.com.creditas.credit_analysis.repositories.ClientRepository
import br.com.creditas.credit_analysis.repositories.ContactRepository
import br.com.creditas.credit_analysis.requests.AddressDto
import br.com.creditas.credit_analysis.requests.ClientCreationRequest
import br.com.creditas.credit_analysis.requests.ContactDto
import br.com.creditas.credit_analysis.responses.ClientCreationResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.UUID

@RestController
@RequestMapping("/accounts")
class ClientController(
    private val clientRepository: ClientRepository,
    private val addressRepository: AddressRepository,
    private val contactRepository: ContactRepository
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

        val contactEntity = if (clientRequest.contact != null) contactRepository.save(
            Contact(
                type = clientRequest.contact.type,
                phoneNumber = clientRequest.contact.phoneNumber,
                emailAddress = clientRequest.contact.email,
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
            ) else null,
            contact = if (contactEntity != null) ContactDto(
                type = contactEntity.type,
                phoneNumber = contactEntity.phoneNumber,
                email = contactEntity.emailAddress
            ) else null

        )
        return response
    }

    @GetMapping
    fun read() = ResponseEntity.ok(clientRepository.findAll())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<ClientPF> {
        return ResponseEntity.ok(clientRepository.findByClientId(id))
    }

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    fun getByCpf(@RequestParam cpf: String): ClientPF? {
        return clientRepository.findByCpf(cpf)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deleteClient(@PathVariable id: UUID) {
        clientRepository.deleteById(id)
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deleteClientByCpf(@RequestParam cpf: String) {
        clientRepository.deleteByCpf(cpf)
    }
}
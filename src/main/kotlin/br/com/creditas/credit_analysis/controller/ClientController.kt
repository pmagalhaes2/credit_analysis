package br.com.creditas.credit_analysis.controller

import br.com.creditas.credit_analysis.exceptions.NotFoundException
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
import org.springframework.web.bind.annotation.ControllerAdvice
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
import java.util.Optional

@RestController
@RequestMapping("/accounts")
@ControllerAdvice
class ClientController(
    private val clientRepository: ClientRepository,
    private val addressRepository: AddressRepository,
    private val contactRepository: ContactRepository,
    private val notFoundMessage: String = "Cliente não encontrado!"
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

        val addressEntity = clientRequest.address?. let { address ->
            addressRepository.save(
                Address(
                    street = clientRequest.address.street,
                    city = clientRequest.address.city,
                    state = clientRequest.address.state,
                    cep = clientRequest.address.cep,
                    client = clientEntity
                )
            )
        }

        val contactEntity = clientRequest.contact?. let { contact ->
            contactRepository.save(
                Contact(
                    type = clientRequest.contact.type,
                    phoneNumber = clientRequest.contact.phoneNumber,
                    emailAddress = clientRequest.contact.email,
                    client = clientEntity
                )
            )
        }

        val response = ClientCreationResponse(
            id = savedClient.id,
            cpf = savedClient.cpf,
            name = savedClient.name,
            lastName = savedClient.lastName,
            birthDate = savedClient.birthDate,
            address = addressEntity?. let { address ->
                AddressDto(
                    cep = addressEntity.cep,
                    street = addressEntity.street,
                    city = addressEntity.city,
                    state = addressEntity.state
                ) },
            contact = contactEntity?. let { contact ->
                ContactDto(
                    type = contactEntity.type,
                    phoneNumber = contactEntity.phoneNumber,
                    email = contactEntity.emailAddress
                )
            }
        )
        return response
    }

    @GetMapping
    fun read() = ResponseEntity.ok(clientRepository.findAll())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<ClientPF> {
        return ResponseEntity.ok(clientRepository.findByid(id))
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
        clientRepository.findByid(id)?. let {
            clientRepository.deleteById(id)
        } ?: throw NotFoundException(notFoundMessage)
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deleteClientByCpf(@RequestParam cpf: String) {
        clientRepository.findByCpf(cpf) ?.let {
            clientRepository.deleteByCpf(cpf)
        } ?: throw NotFoundException(notFoundMessage)
    }
}
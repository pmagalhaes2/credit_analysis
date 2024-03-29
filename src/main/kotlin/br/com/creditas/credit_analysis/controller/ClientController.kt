package br.com.creditas.credit_analysis.controller

import br.com.creditas.credit_analysis.exceptions.ClientNotFoundException
import br.com.creditas.credit_analysis.gateway.RandomScoreGateway
import br.com.creditas.credit_analysis.models.Address
import br.com.creditas.credit_analysis.models.ClientPF
import br.com.creditas.credit_analysis.models.Contact
import br.com.creditas.credit_analysis.repositories.AddressRepository
import br.com.creditas.credit_analysis.repositories.ClientRepository
import br.com.creditas.credit_analysis.repositories.ContactRepository
import br.com.creditas.credit_analysis.requests.AddressDto
import br.com.creditas.credit_analysis.requests.ClientCreationRequest
import br.com.creditas.credit_analysis.requests.ClientUpdateRequest
import br.com.creditas.credit_analysis.requests.ContactDto
import br.com.creditas.credit_analysis.responses.ClientCreationResponse
import io.swagger.annotations.ApiOperation
import org.springframework.context.annotation.Description
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
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/accounts")
@ControllerAdvice
class ClientController(
    private val clientRepository: ClientRepository,
    private val addressRepository: AddressRepository,
    private val contactRepository: ContactRepository,
    private val randomScoreGateway: RandomScoreGateway
) {

    private var message = "client not found"

    @ApiOperation(value = "Register a new customer",
            notes = "This method creates a new customer")
    @PostMapping
    @Transactional
    fun create(@RequestBody @Valid clientRequest: ClientCreationRequest): ClientCreationResponse {
        val clientEntity = ClientPF(
            cpf = clientRequest.cpf,
            name = clientRequest.name,
            lastName = clientRequest.lastName,
            birthDate = clientRequest.birthDate,
            score = randomScoreGateway.getScore().trim().toInt()
        )

        val savedClient = clientRepository.save(clientEntity)

        val addressEntity = clientRequest.address?.let { address ->
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

        val contactEntities = clientRequest.contacts?.map { contact ->
            contactRepository.save(
                Contact(
                    type = contact.type,
                    phoneNumber = contact.phoneNumber,
                    emailAddress = contact.email,
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
            score = savedClient.score,
            address = addressEntity?.let { address ->
                AddressDto(
                    cep = addressEntity.cep,
                    street = addressEntity.street,
                    city = addressEntity.city,
                    state = addressEntity.state.uppercase()
                )
            },
            contacts = contactEntities?.map { contact ->
                ContactDto(
                    type = contact.type,
                    phoneNumber = contact.phoneNumber,
                    email = contact.emailAddress
                )
            }
        )
        return response
    }

    @ApiOperation(value = "Get customers",
            notes = "This method return a list of registered customers")
    @GetMapping
    fun read() = ResponseEntity.ok(clientRepository.findAll())

    @ApiOperation(value = "Get client by id",
            notes = "This method return a client by its id")
    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID)  : ResponseEntity<ClientPF> {
        return ResponseEntity.ok(clientRepository.findByid(id)
                ?: throw ClientNotFoundException(message))
    }

    @ApiOperation(value = "Get client by cpf",
            notes = "This method return a client by its cpf")
    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    fun getByCpf(@RequestParam cpf: String): ClientPF? {
        return clientRepository.findByCpf(cpf)
                ?: throw ClientNotFoundException(message)
    }

    @ApiOperation(value = "Delete client by id",
            notes = "This method delete a client by its id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deleteClient(@PathVariable id: UUID) {
        clientRepository.findByid(id)?.let {
            clientRepository.deleteById(id)
        } ?: throw ClientNotFoundException(message)
    }

    @ApiOperation(value = "Delete client by cpf",
            notes = "This method delete a client by its cpf")
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deleteClientByCpf(@RequestParam cpf: String) {
        clientRepository.findByCpf(cpf)?.let {
            clientRepository.deleteByCpf(cpf)
        } ?: throw ClientNotFoundException(message)
    }

    @ApiOperation(value = "Update client by id",
            notes = "This method update a client by its id")
    @PutMapping("/{id}")
    @Transactional
    fun updateClientById(
        @PathVariable id: UUID,
        @RequestBody @Valid clientRequest: ClientUpdateRequest
    ): ClientPF {

        clientRepository.findByid(id)?.let {

            val client = it

            val clientEntity = ClientPF(
                id = client.id,
                cpf = clientRequest.cpf,
                name = clientRequest.name,
                lastName = clientRequest.lastName,
                birthDate = clientRequest.birthDate,
                score = client.score
            )

            client.address?.let { address -> addressRepository.delete(address) }

            val addressEntity = clientRequest.address?.let {
                addressRepository.save(
                    Address(
                        street = clientRequest.address.street,
                        city = clientRequest.address.city,
                        state = clientRequest.address.state.uppercase(),
                        cep = clientRequest.address.cep,
                        client = client
                    )
                )
            }

            client.contacts?.map { contact -> contactRepository.delete(contact) }

            val contactEntities = clientRequest.contacts?.map { contact ->
                contactRepository.save(
                    Contact(
                        type = contact.type,
                        phoneNumber = contact.phoneNumber,
                        emailAddress = contact.email,
                        client = client
                    )
                )
            }

            return clientRepository.save(clientEntity)

        } ?: throw ClientNotFoundException(message)
    }


    @ApiOperation(value = "Update client by cpf",
            notes = "This method update a client by its cpf")
    @PutMapping("/update")
    @Transactional
    fun updateClientByCpf(
        @RequestParam cpf: String,
        @RequestBody @Valid clientRequest: ClientUpdateRequest
    ): ClientPF {

        clientRepository.findByCpf(cpf)?.let {

            val client = it

            val clientEntity = ClientPF(
                id = client.id,
                cpf = clientRequest.cpf,
                name = clientRequest.name,
                lastName = clientRequest.lastName,
                birthDate = clientRequest.birthDate,
                score = client.score
            )

            client.address?.let { address -> addressRepository.delete(address) }

            val addressEntity = clientRequest.address?.let {
                addressRepository.save(
                    Address(
                        street = clientRequest.address.street,
                        city = clientRequest.address.city,
                        state = clientRequest.address.state.uppercase(),
                        cep = clientRequest.address.cep,
                        client = client
                    )
                )
            }

            client.contacts?.map { contact -> contactRepository.delete(contact) }

            val contactEntities = clientRequest.contacts?.map { contact ->
                contactRepository.save(
                    Contact(
                        type = contact.type,
                        phoneNumber = contact.phoneNumber,
                        emailAddress = contact.email,
                        client = client
                    )
                )
            }

            return clientRepository.save(clientEntity)

        } ?: throw ClientNotFoundException(message)
    }
}
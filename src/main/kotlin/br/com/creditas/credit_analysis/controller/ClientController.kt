package br.com.creditas.credit_analysis.controller

import br.com.creditas.credit_analysis.models.Address
import br.com.creditas.credit_analysis.models.ClientPF
import br.com.creditas.credit_analysis.repositories.AddressRepository
import br.com.creditas.credit_analysis.repositories.ClientRepository
import br.com.creditas.credit_analysis.requests.AddressDto
import br.com.creditas.credit_analysis.requests.ClientCreationRequest
import br.com.creditas.credit_analysis.responses.ClientCreationResponse
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}
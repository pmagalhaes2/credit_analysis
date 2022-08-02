package br.com.creditas.credit_analysis.controller

import br.com.creditas.credit_analysis.exceptions.ClientNotFoundException
import br.com.creditas.credit_analysis.gateway.RandomScoreGateway
import br.com.creditas.credit_analysis.models.ClientPFTest
import br.com.creditas.credit_analysis.repositories.AddressRepository
import br.com.creditas.credit_analysis.repositories.ClientRepository
import br.com.creditas.credit_analysis.repositories.ContactRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class ClientControllerTest {

    private val client = ClientPFTest.build()

    @MockK
    lateinit var clientRepository: ClientRepository

    @MockK
    lateinit var addressRepository: AddressRepository

    @MockK
    lateinit var contactRepository: ContactRepository

    @MockK
    lateinit var randomScoreGateway: RandomScoreGateway

    @InjectMockKs
    private lateinit var clientController: ClientController

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should return client if registered cpf is passed`() {

        every { clientRepository.findByCpf(any()) } returns client

        clientController.getByCpf(cpf = "40723058040")

        verify(exactly = 1) { clientRepository.findByCpf(any()) }
    }

    @Test
    fun `should throw client not found exception if cpf is empty`() {

        every { clientRepository.findByCpf("") } returns null

        val currentError = assertThrows<ClientNotFoundException> {
            clientController.getByCpf(cpf = "")
        }

        assertThat(currentError.message).isEqualTo("client not found")

    }

    @Test
    fun `should throw client not found exception if id does not exist`() {

        every { clientRepository.findByid(any()) } returns null

        val currentError = assertThrows<ClientNotFoundException> {
            clientController.getById(id = UUID.randomUUID())
        }

        assertThat(currentError.message).isEqualTo("client not found")

    }
}

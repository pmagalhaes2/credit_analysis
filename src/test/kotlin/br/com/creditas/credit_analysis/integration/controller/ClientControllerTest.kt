package br.com.creditas.credit_analysis.integration.controller

import br.com.creditas.credit_analysis.models.ClientPFTest
import br.com.creditas.credit_analysis.repositories.ClientRepository
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldBeNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity.status
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class ClientControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    private val client = ClientPFTest.build()

    @Autowired
    private lateinit var clientRepository: ClientRepository

    companion object {
        private const val RESOURCE = "/accounts"
        private const val RESOURCE_FIND_CPF = RESOURCE.plus("/find/%s")
    }

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun `should return status 200 when calling endpoint accounts`() {
        mockMvc.get(RESOURCE)
                .andExpect { status { isOk() } }
    }

    @Test
    fun `should return status 404 if an unregistered cpf is passed`() {
        mockMvc.get(RESOURCE_FIND_CPF.format(client.cpf))
                .andExpect { status { isNotFound() } }
    }

    @Test
    fun `should register the customer if the data is correct`() {

        val request = """
            {
                "cpf": "79306071000",
                "name": "Rúbia",
                "lastName": "Gonçalves",
                "birthDate": "31/01/1989",
                       "address": {
                            "street": "Rua Canário",
                            "city": "São Paulo",
                           "state": "SP",
                           "cep": "08373650"
                       }
            }
                """.trimIndent()

        mockMvc.perform(MockMvcRequestBuilders
                .post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect { request }

        clientRepository.findAll().first().cpf `should be equal to` "79306071000"

    }

    @Test
    fun `should delete the customer if a registered cpf is passed`() {

        val request = """
            {
                "cpf": "66290420003",
                "name": "Bruno",
                "lastName": "Fernando",
                "birthDate": "05/07/1980",
                       "address": {
                            "street": "Rua Quintino Bocaiúva",
                            "city": "Itacoatiara",
                           "state": "AM",
                           "cep": "69100069"
                       }
            }
                """.trimIndent()

        mockMvc.perform(MockMvcRequestBuilders
                .post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect { request }
                .also { clientRepository.findAll().last().cpf `should be equal to` "66290420003" }

        mockMvc.perform(MockMvcRequestBuilders
                .delete(RESOURCE.plus("/delete?cpf=66290420003"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect { status(HttpStatus.OK) }

        clientRepository.findByCpf("66290420003").shouldBeNull()
    }
}




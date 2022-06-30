package br.com.creditas.credit_analysis

import br.com.creditas.credit_analysis.models.PhoneType
import br.com.creditas.credit_analysis.requests.AddressDto
import br.com.creditas.credit_analysis.requests.ClientCreationRequest
import br.com.creditas.credit_analysis.requests.ContactDto
import org.amshove.kluent.invoking
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.shouldContainSome
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import javax.validation.Validation
import javax.validation.Validator


@SpringBootTest
class ClientCreationRequestTest {
    private lateinit var validator: Validator

    @BeforeEach
    fun setUp() {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Test
    fun `should not create a client if the cpf is invalid`() {
        val newClient = ClientCreationRequest(
            cpf = "33333333333",
            name = "Rúbia",
            lastName = "Gonçalves",
            birthDate = LocalDate.now(),
            score = 0,
            address = AddressDto(
                street = "Rua Canário",
                city = "São Paulo",
                state = "SP",
                cep = "08373650"
            ),
            contacts = null
        )
        val violations = validator.validate(newClient)
        violations.size `should be equal to` 1
        violations.first().message `should be equal to` "invalid Brazilian individual taxpayer registry number (CPF)"
    }

    @Test
    fun `should not create a client if the cpf is empty`() {
        val newClient = ClientCreationRequest(
            cpf = "",
            name = "Rúbia",
            lastName = "Gonçalves",
            birthDate = LocalDate.now(),
            score = 0,
            address = AddressDto(
                street = "Rua Canário",
                city = "São Paulo",
                state = "SP",
                cep = "08373650"
            ),
            contacts = null
        )
        val violations = validator.validate(newClient)
        violations.size `should be equal to` 2
        violations.map {
            it.message.shouldContainSome(
                "must not be empty",
                "invalid Brazilian individual taxpayer registry number (CPF)"
            )
        }
    }

    @Test
    fun `should not create a customer if the name does not contain only letters, diacritics and whitespace separators`() {
        val newClient = ClientCreationRequest(
            cpf = "74638799094",
            name = "Rúbia 123",
            lastName = "Gonçalves",
            birthDate = LocalDate.now(),
            score = 0,
            address = AddressDto(
                street = "Rua Canário",
                city = "São Paulo",
                state = "SP",
                cep = "08373650"
            ),
            contacts = null
        )
        val violations = validator.validate(newClient)
        violations.size `should be equal to` 1
        violations.first().message `should be equal to` "must match only letters, diacritics, and whitespace separators"
    }

    @Test
    fun `should not create a customer if the name is empty`() {
        val newClient = ClientCreationRequest(
            cpf = "74638799094",
            name = "",
            lastName = "Gonçalves",
            birthDate = LocalDate.now(),
            score = 0,
            address = AddressDto(
                street = "Rua Canário",
                city = "São Paulo",
                state = "SP",
                cep = "08373650"
            ),
            contacts = null
        )
        val violations = validator.validate(newClient)
        violations.size `should be equal to` 2
        violations.map {
            it.message.shouldContainSome(
                "must not be empty",
                "must match only letters, diacritics, and whitespace separators"
            )
        }
    }

    @Test
    fun `should not create a customer if the lastname is invalid`() {
        val newClient = ClientCreationRequest(
            cpf = "74638799094",
            name = "Rúbia",
            lastName = "Gonçalves 123",
            birthDate = LocalDate.now(),
            score = 0,
            address = AddressDto(
                street = "Rua Canário",
                city = "São Paulo",
                state = "SP",
                cep = "08373650"
            ),
            contacts = null
        )
        val violations = validator.validate(newClient)
        violations.size `should be equal to` 1
        violations.first().message `should be equal to` "must match only letters, diacritics, and whitespace separators"
    }

    @Test
    fun `should not create a customer if the lastname is empty`() {
        val newClient = ClientCreationRequest(
            cpf = "74638799094",
            name = "Rúbia",
            lastName = "",
            birthDate = LocalDate.now(),
            score = 0,
            address = AddressDto(
                street = "Rua Canário",
                city = "São Paulo",
                state = "SP",
                cep = "08373650"
            ),
            contacts = null
        )
        val violations = validator.validate(newClient)
        violations.size `should be equal to` 2
        violations.map {
            it.message.shouldContainSome(
                "must not be empty",
                "must match only letters, diacritics, and whitespace separators"
            )
        }
    }

    @Test
    fun `should not create a customer if the street does not contain only letters, diacritics and whitespace separators`() {
        val newClient = ClientCreationRequest(
            cpf = "74638799094",
            name = "Rúbia",
            lastName = "Gonçalves",
            birthDate = LocalDate.now(),
            score = 0,
            address = AddressDto(
                street = "Rua Canário 1",
                city = "São Paulo",
                state = "SP",
                cep = "08373650"
            ),
            contacts = null
        )
        val violations = validator.validate(newClient)
        violations.size `should be equal to` 1
        violations.first().message `should be equal to` "must match only letters, diacritics, and whitespace separators"
    }

    @Test
    fun `should not create a customer if the city does not contain only letters, diacritics and whitespace separators`() {
        val newClient = ClientCreationRequest(
            cpf = "74638799094",
            name = "Rúbia",
            lastName = "Gonçalves",
            birthDate = LocalDate.now(),
            score = 0,
            address = AddressDto(
                street = "Rua Canário",
                city = "São Paulo 1",
                state = "SP",
                cep = "08373650"
            ),
            contacts = null
        )
        val violations = validator.validate(newClient)
        violations.size `should be equal to` 1
        violations.first().message `should be equal to` "must match only letters, diacritics, and whitespace separators"
    }

    @Test
    fun `should not create a customer if the state does not match the acronyms of the Brazilian states`() {
        val newClient = ClientCreationRequest(
            cpf = "74638799094",
            name = "Rúbia",
            lastName = "Gonçalves",
            birthDate = LocalDate.now(),
            score = 0,
            address = AddressDto(
                street = "Rua Canário",
                city = "São Paulo",
                state = "AA",
                cep = "08373650"
            ),
            contacts = null
        )
        val violations = validator.validate(newClient)
        violations.size `should be equal to` 1
        violations.first().message `should be equal to` "must match to an acronym of the Brazilian states"
    }

    @Test
    fun `should not create a customer if the state does not contain the length of two characters`() {
        val newClient = ClientCreationRequest(
            cpf = "74638799094",
            name = "Rúbia",
            lastName = "Gonçalves",
            birthDate = LocalDate.now(),
            score = 0,
            address = AddressDto(
                street = "Rua Canário",
                city = "São Paulo",
                state = "São Paulo",
                cep = "08373650"
            ),
            contacts = null
        )
        val violations = validator.validate(newClient)
        violations.size `should be equal to` 2
        violations.map {
            it.message.shouldContainSome(
                "must match to an acronym of the Brazilian states",
                "size must be 2"
            )
        }
    }

    @Test
    fun `should not create a customer if the cep does not contain the length of eight characters`() {
        val newClient = ClientCreationRequest(
            cpf = "74638799094",
            name = "Rúbia",
            lastName = "Gonçalves",
            birthDate = LocalDate.now(),
            score = 0,
            address = AddressDto(
                street = "Rua Canário",
                city = "São Paulo",
                state = "SP",
                cep = "083736500"
            ),
            contacts = null
        )
        val violations = validator.validate(newClient)
        violations.size `should be equal to` 1
        violations.first().message `should be equal to` "size must be 8"
    }

    @Test
    fun `should not create a custumer if the phone type is not cell or landline`() {
        invoking {
            ClientCreationRequest(
                cpf = "74638799094",
                name = "Rúbia",
                lastName = "Gonçalves",
                birthDate = LocalDate.now(),
                score = 0,
                address = AddressDto(
                    street = "Rua Canário",
                    city = "São Paulo",
                    state = "SP",
                    cep = "08373650"
                ),
                contacts = listOf(
                    ContactDto(
                        type = enumValueOf("TESTE"),
                        phoneNumber = "11977777777",
                        email = "teste@teste.com"
                    )
                )
            )
        } `should throw` IllegalArgumentException::class
    }

    @Test
    fun `should not create a customer if the phone number is not between 11 and 12 digits`() {
        val newClient = ClientCreationRequest(
            cpf = "74638799094",
            name = "Rúbia",
            lastName = "Gonçalves",
            birthDate = LocalDate.now(),
            score = 0,
            address = AddressDto(
                street = "Rua Canário",
                city = "São Paulo",
                state = "SP",
                cep = "08373650"
            ),
            contacts = listOf(
                ContactDto(
                    type = PhoneType.CELL_PHONE,
                    phoneNumber = "1190123456789",
                    email = "teste@teste.com"
                )
            )
        )
        val violations = validator.validate(newClient)
        violations.size `should be equal to` 1
        violations.first().message `should be equal to` "size must be between 11 and 12"
    }

    @Test
    fun `should not create a customer if the phone number does not only contain numbers`() {
        val newClient = ClientCreationRequest(
            cpf = "74638799094",
            name = "Rúbia",
            lastName = "Gonçalves",
            birthDate = LocalDate.now(),
            score = 0,
            address = AddressDto(
                street = "Rua Canário",
                city = "São Paulo",
                state = "SP",
                cep = "08373650"
            ),
            contacts = listOf(
                ContactDto(
                    type = PhoneType.CELL_PHONE,
                    phoneNumber = "(11)97777-7777",
                    email = "teste@teste.com"
                )
            )
        )
        val violations = validator.validate(newClient)
        violations.size `should be equal to` 2
        violations.map {
            it.message.shouldContainSome(
                "must match only numbers",
                "size must be between 11 and 12"
            )
        }
    }

    @Test
    fun `should not create a client if the email address is invalid`() {
        val newClient = ClientCreationRequest(
            cpf = "74638799094",
            name = "Rúbia",
            lastName = "Gonçalves",
            birthDate = LocalDate.now(),
            score = 0,
            address = AddressDto(
                street = "Rua Canário",
                city = "São Paulo",
                state = "SP",
                cep = "08373650"
            ),
            contacts = listOf(
                ContactDto(
                    type = PhoneType.CELL_PHONE,
                    phoneNumber = "11977777777",
                    email = "teste_email_invalido.com.br"
                )
            )
        )
        val violations = validator.validate(newClient)
        violations.size `should be equal to` 1
        violations.first().message `should be equal to` "must be a well-formed email address"
    }
}


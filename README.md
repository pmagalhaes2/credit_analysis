# Análise de Crédito

Contexto - Análise de Crédito considerando a pontuação do CPF/CNPJ (PQ)


## Projeto a ser desenvolvido

O projeto a ser desenvolvido é uma aplicação que realiza consultas de SCORE com base no CPF/CNPJ informado.

## Estrutura do Projeto

Pensando em como deve ser estrutura do lado de **_ClientPF_**, foi criado o esquema abaixo, o qual temos representada a estrutura da classe que será desenvolvida.
A classe **_br.com.creditas.credit_analysis.models.ClientPF_**, será responsável por ter os atributos e os relacionamentos:

![](https://documents.lucid.app/documents/1b4e0075-9d4c-4add-8986-59a912c457c3/pages/0_0?a=958&x=102&y=159&w=1276&h=453&store=1&accept=image%2F*&auth=LCA%205afe58fba57943ead0b59cdd784647c2997ca683-ts%3D1654264421)


| Atributos  | Type  | Descrição|
| ------------ | ------------------------- |------------ |
| id  | UUID | Responsável por armazenar uma chave id para cada cliente |
|  cpf |  String  |  Responsável por armazenar o cadastro de pessoa física (CPF) de cada cliente |
|  name |  String |  Responsável por armazenar o nome do cliente|
|  last_name |  String | Responsável por armazenar o sobrenome do cliente |
|  birth_date | Date  | Responsável por armazenar a data de nascimento do cliente  |
|  address |  Address | Responsável por armazenar as informações de endereço do cliente |
|  contact |  Contact | Responsável por armazenar as informações de contato do cliente |


Todos os clientes poderão executar as seguintes ações:

### C – Create
POST: [http://localhost:8080/accounts](http://localhost:8080/accounts)

### R – Read
GET: [http://localhost:8080/accounts](http://localhost:8080/accounts)

GET BY ID: [http://localhost:8080/accounts/{id}](http://localhost:8080/accounts/{id})

GET BY CPF: [http://localhost:8080/accounts/find?cpf={cpf}](http://localhost:8080/accounts/find?cpf={cpf})

### U - Update
UPDATE BY ID: [http://localhost:8080/accounts/{id}](http://localhost:8080/accounts/{id})

UPDATE BY CPF: [http://localhost:8080/accounts/update?cpf={cpf}](http://localhost:8080/accounts/update?cpf={cpf})

### D - Delete
DELETE BY ID: [http://localhost:8080/accounts/{id}](http://localhost:8080/accounts/{id})

DELETE BY CPF: [http://localhost:8080/accounts/delete?cpf={cpf}](http://localhost:8080/accounts/delete?cpf={cpf})
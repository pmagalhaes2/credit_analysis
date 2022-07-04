
# Análise de Crédito

Contexto - Análise de Crédito considerando a pontuação do CPF (PQ)


## Projeto a ser desenvolvido

O projeto a ser desenvolvido é uma aplicação que realiza consultas de SCORE com base no CPF informado.

## Estrutura do Projeto

Pensando em como deve ser estrutura do lado de **_ClientPF_**, foi criado o esquema abaixo, o qual temos representada a estrutura da classe que será desenvolvida.
A classe **_br.com.creditas.credit_analysis.models.ClientPF_**, será responsável por ter os atributos e os relacionamentos:

![](https://documents.lucid.app/documents/1b4e0075-9d4c-4add-8986-59a912c457c3/pages/0_0?a=1218&x=98&y=117&w=1364&h=497&store=1&accept=image%2F*&auth=LCA%20321fb09b598671ff24d83a3cf1db12a611d0886c-ts%3D1656967109)


| Atributos  | Type  | Descrição|
| ------------ | ------------------------- |------------ |
| id  | UUID | Responsável por armazenar uma chave id para cada cliente |
|  cpf |  String  |  Responsável por armazenar o cadastro de pessoa física (CPF) de cada cliente |
|  name |  String |  Responsável por armazenar o nome do cliente|
|  last_name |  String | Responsável por armazenar o sobrenome do cliente |
|  birth_date | LocalDate  | Responsável por armazenar a data de nascimento do cliente  |
|  score | Int  | Responsável por armazenar a informação de score do cliente  |
|  address |  Address | Responsável por armazenar as informações de endereço do cliente |
|  contacts |  Contact | Responsável por armazenar as informações de contato do cliente |


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
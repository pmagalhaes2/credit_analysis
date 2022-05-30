# Análise de Crédito

Contexto - Análise de Crédito considerando a pontuação do CPF/CNPJ (PQ)


## Projeto a ser desenvolvido

O projeto a ser desenvolvido é uma aplicação que realiza consultas de SCORE com base no CPF/CNPJ informado.

## Estrutura do Projeto

Pensando em como deve ser estrutura do lado de **_ClientPF_**, foi criado o esquema abaixo, o qual temos representada a estrutura da classe que será desenvolvida.
A classe **_br.com.creditas.credit_analysis.models.ClientPF_**, será responsável por ter os atributos:

![](https://documents.lucid.app/documents/27c2686d-e801-4c1e-bf50-701820cc8486/pages/HWEp-vi-RSFO?a=665&x=115&y=107&w=990&h=306&store=1&accept=image%2F*&auth=LCA%20ed164406467db36424f88e2f9e8f3624a626374a-ts%3D1653916997)

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

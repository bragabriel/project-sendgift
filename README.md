# Projeto - Send Gift 🎁

Este é um projeto de estudos para realizar a utilização de conceitos e ferramentas de nível intermediário-avançado, como <b>Banco de Dados NoSQL</b>, <b>Message-Broker</b>, <b>Microserviços</b>, <b>Arquitetura (Clean Arch)</b>, <b>Testes</b>, entre outros... <br>
A aplicação permite que usuários👨‍🦱 enviem presentes🎁 para outros usuários.

## Ideia do Projeto
O objetivo deste projeto é criar uma aplicação robusta e escalável, seguindo boas práticas de arquitetura e design de software. <br>
A aplicação permite o cadastro, atualização, obtenção e exclusão de usuários e presentes, bem como o 'envio/troca' de presentes entre usuários via <b>Kafka</b>. <br>

### Microserviço: CEP
A aplicação consome um [Microserviço que desenvolvi](https://github.com/bragabriel/microservice-cep) para a obtenção de um endereço a partir de um CEP. Neste Microserviço, utilizei <b>Open Feign (Spring)</b>, coloquei em prática a <b>Arquitetura Hexagonal</b>, bem como boas <b>Práticas de Resiliência de API's e Microserviços</b>, com a utilização do <b>Resilience4j</b> para o desenvolvimento de um <b>Circuit-Breaker</b>.

## Clean Architecture e SOLID
A escolha da <b>Clean Architecture</b> e dos princípios <b>SOLID</b> foi feita para garantir uma estrutura de código limpa, modular e  testável. A Clean Architecture enfatiza a separação de preocupações, com uma clara distinção entre as camadas de aplicação, domínio e infraestrutura. Isso permite que as mudanças em uma camada não afetem as outras, facilitando a manutenção e evolução do sistema ao longo do tempo. <br>
Os princípios SOLID são aplicados para garantir que as classes sejam coesas, flexíveis e fáceis de entender e modificar.

## Tecnologias utilizadas
No desenvolvimento deste projeto, foram utilizadas as seguintes tecnologias:
* Java 21;
* Spring 3.2;
* MongoDB;
* Kafka;
* Testes Unitários.
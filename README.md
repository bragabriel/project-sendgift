# Projeto - Send Gift 🎁

Este é um projeto de estudos para realizar a utilização de conceitos e ferramentas de nível intermediário-avançado, como **Banco de Dados NoSQL**, **Message-Broker**, **Microserviços**, **Arquitetura (Clean Arch)**, **Testes**, entre outros...

A aplicação permite que usuários👨‍🦱 enviem presentes🎁 para outros usuários.

## Ideia do Projeto

O objetivo deste projeto é criar uma aplicação robusta e escalável, seguindo boas práticas de arquitetura e design de software.

A aplicação permite o cadastro, atualização, obtenção e exclusão de usuários e presentes, bem como o 'envio/troca' de presentes entre usuários via **Kafka**.

## Clean Architecture e SOLID

A escolha da **Clean Architecture** e dos princípios **SOLID** foi feita para garantir uma estrutura de código limpa, modular e  testável. A Clean Architecture enfatiza a separação de preocupações, com uma clara distinção entre as camadas de aplicação, domínio e infraestrutura. Isso permite que as mudanças em uma camada não afetem as outras, facilitando a manutenção e evolução do sistema ao longo do tempo.

Os princípios SOLID são aplicados para garantir que as classes sejam coesas, flexíveis e fáceis de entender e modificar.

## Microserviços

A aplicação consome dois microserviços que desenvolvi, sendo eles:

* [CEP Microservice](https://github.com/bragabriel/microservice-cep)

* [E-mail Microservice](https://github.com/bragabriel/microservice-email)

### Microserviço: CEP

Este Microserviço é utilizado para a obtenção de um endereço a partir de um CEP.

Neste Microserviço, utilizei **Open Feign (Spring)**, coloquei em prática a **Arquitetura Hexagonal**, bem como boas **Práticas de Resiliência de API's e Microserviços**, com a utilização do **Resilience4j** para o desenvolvimento de um **Circuit-Breaker**.

### Microserviço: E-mail

Este Microserviço é utilizado para o envio de e-mails de confirmação.
Quando um usuário envia um presente, um e-mail de confirmação chega para ele, informando que o presente foi enviado para o destinatário com sucesso!

Da mesma forma, quando um usuário recebe um presente, um e-mail de feedback chega para ele, informando que recebeu um presente, na data X, da pessoa Y.

Neste Microserviço, utilizei **Open Feign (Spring)**, coloquei em prática a **Arquitetura Hexagonal**, e utilizei o **Amazon Simple Email Service (AWS SES)** como o serviço de e-mails.

## Tecnologias utilizadas

No desenvolvimento deste projeto, incluindo os microserviços, foram utilizadas as seguintes tecnologias e conceitos:

SendGift Application:

Técnologias:

    * Java 21;
    * Spring Framework 3.2;
    * MongoDB;
    * Kafka;
    * jUnit e Mockito;
    * Jenkins;
    * JaCoCo e SonarCube;
    * Spring Boot, Data e Cloud;
    * Resilience4j;
    * AWS (SES).

Conceitos:

    * Clean Architecture;
    * Hexagonal Architecture;
    * SOLID;
    * Práticas de Resiliência de API's e Microserviços;
    * Circuit-Breaker;
    * Microserviços;
    * Testes Unitários;
    * Mensageria;
    * Banco de Dados NoSQL;
    * Integração com Cloud.

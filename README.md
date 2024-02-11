# Projeto - Send Gift 🎁

Este é um projeto de estudos que demonstra a implementação de uma aplicação <b>Java</b> usando <b>Spring Boot</b>, <b>MongoDB</b>, e <b>Kafka</b>. <br>
A aplicação trata de operações relacionadas a usuários👨‍🦱 e presentes🎁, permitindo o cadastro, atualização, obtenção e exclusão de usuários e presentes, bem como o 'envio/troca' de presentes entre usuários via <b>Kafka</b>.

## Ideia do Projeto
O objetivo deste projeto é criar uma aplicação robusta e escalável, seguindo boas práticas de arquitetura e design de software. <br>
A aplicação permite que os usuários cadastrem suas informações e presentes, além de possibilitar a transferência de presentes entre usuários. <br>
A integração com a API [Via CEP](https://viacep.com.br/) é utilizada para completar automaticamente as informações de endereço dos usuários durante o cadastro.

## Clean Architecture e SOLID
A escolha da <b>Clean Architecture</b> e dos princípios <b>SOLID</b> foi feita para garantir uma estrutura de código limpa, modular e altamente testável. A Clean Architecture enfatiza a separação de preocupações, com uma clara distinção entre as camadas de aplicação, domínio e infraestrutura. Isso permite que as mudanças em uma camada não afetem as outras, facilitando a manutenção e evolução do sistema ao longo do tempo. <br>
Os princípios SOLID são aplicados para garantir que as classes sejam coesas, flexíveis e fáceis de entender e modificar.

## Tecnologias utilizadas
No desenvolvimento deste projeto, foram utilizadas as seguintes tecnologias:
* Java 21;
* Spring 3.2;
* MongoDB;
* Kafka;
* Testes Unitários.

## Estrutura de Pastas
A estrutura de pastas do projeto segue os princípios da Clean Architecture, organizando o código em camadas de acordo com suas responsabilidades:

core: <br>
|- adapter: <br>
|- domain: <br>
| |- model: <br>
| |- repository: <br>
| |- service: <br>
|- usercases: <br>
infrastructure: Contém as implementações concretas de detalhes de infraestrutura, como acesso a banco de dados e comunicação com sistemas externos.
resources: Contém recursos estáticos, como arquivos de propriedades de configuração.

Aqui está a estrutura completa de pastas do projeto:

```
projeto/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── example/
│   │   │   │   │   ├── core/
│   │   │   │   │   │   ├── adapter/
│   │   │   │   │   │   ├── domain/
│   │   │   │   │   │   │   ├── model/
│   │   │   │   │   │   │   ├── repository/
│   │   │   │   │   │   │   └── service/
│   │   │   │   │   │   └── usercases/
│   │   │   │   │   ├── infrastructure/
│   │   │   │   │   │   ├── messaging/
│   │   │   │   │   │   └── persistence/
│   │   │   │   │   │   │   ├── mongodb/
│   │   │   │   │   │   │   └── repository/
│   │   │   │   └── SendgiftApplication.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── kafka.properties
│   │   └── resources/
│   │       └── static/
│   └── test/
│       └── java/
└── pom.xml
```

# Getting Started

## swagger
- http://localhost:8091/swagger-ui/index.html
- http://simple-homolog.qualquercoisa.com.br/swagger-ui/index.html
- https://simplemicroservice.qualquercoisa.com.br/swagger-ui/index.html

## Designer pattern do projeto

Buscou-se neste projeto usar de alguns conceitos do clean arquitecture e alguns dos conceitos do DDD, como Entidades, agregados, objetos de valor e o dominio rico.
De forma que, no módulo de domain estão as regras de negócio, o equivalente a camada entities do clean architecture.
Na camada de aplicação, estão os use cases, que representam as intensões do "usuário", e as interfaces dos gateways/serviços/repositórios cujas implementações estão na camada de infrastructure.

## domain
- Estão as regras de negócio, as entidades, possíveis eventos de domínio, entidades de negócio, objetos de valor.
- Entidade é tudo que na visão de negócio há identificação. 
- É comum que as entidades possuem objetos de valor, que são propriedades imutáveis das entidades, por exemplo o Voucher.
- É importante dizer que estas entidades não são as entidade do banco de dados, são entidades do negócio.

## application
- Estão os casos de uso, representação da intensão de usuário, entendendo como usuário tudo que for usar este sistema.
- Estão as interfaces dos gateways/serviços/repositórios, contratos claros do que o sistema precisa para orquestrar as regras de negócio e executar a intensão do usuário.

## infraestructure
- É nesta e somente nesta camada que as decisões de ferramentas, tecnologias, framework são tomadas, tal como, qual banco, qual ORM, qual framwork, versões, serviços externos e etc.
- São feitas as implementações necessárias para executar os usecases.
- Nesta camada há por exemplo os Controllers da api rest. 
- Podem ser implementado nesta camada presenters para diferentes tipos resposta de api. 
- Aqui define/implementa-se as formas de servir gRPC, REST, SOAP, CLI.

O fluxo de dados basicamente é:

```cliente -> infrastructure -> application -> domain```

## funcionamento

O sistema possui o Profissional (Professional) e o Cliente (Client), O Professional é a representação da entidade de um Profissional Liberal, tal como um Psicólogo, e possui vários clientes, a principio 
a aplicação roda para cada profissional (o identificador do profissional é definido neste momento nas variáveis de ambiente), espera-se mudar isso futuramente, deixando dinâmico, multi-tenant por exemplo.
Continuando, o cliente possui seus pagamentos, que são coletados por serviços externos, podendo ser uma planilha ou um microserviço de gestão de pagamentos.

- O profissional pode fazer as seguintes solicitações (useCases)
  - CRUD do client
    - adicionar
    - editar
    - buscar
    - buscar todos
    - buscar pagamentos do cliente
    - remover client

- O administrador do sistema pode:
  - Criar Profissional (futuro)
  - Buscar Profissional

Tecnologia/ferramentas
- Framework: spring boot
- Banco de dados para persistência dos Profissionais e Clientes: Mysql
- ORM: JPA+Hibernate
- Circuit Breaker, Retry: resilience4j configurados na implementação do PaymentGateway -> FakePaymentGatewayWithResilience que tem como composição o FakePaymentGateway por meio da config ServiceInjection.
- Timeout podem ser configurados na implementação do PaymentGateway ->FakePaymentGateway.
- As injeções das implementações dos gateways, repositories e useCases a serem usados pelas UI (controller, scheduller), são injetadas nas configurações simple.microservice.infrastructure.configurations.

## Para rodar o projeto localmente via IDE (para debug por exemplo - dev)

- É preciso ter o banco mysql local 
  - caso não tenha pode-se usar subir pelo docker o mysql que as tabelas serão criadas. Para isso, precisa ter o docker instalado, execute na pasta do projeto:
    ```bash
    cd anexos
    docker compose up -d
    ```
- A porta 8091 não pode está em uso.

- instalar jdk 17.0.2 e maven 3.9.3
  - recomento o uso do sdkman
    - https://sdkman.io/install
    - após instalação:
      ```bash
      sdk install java 17.0.2-open  
      ```
      ```bash
      sdk install maven 3.9.3 
      ```
      ```bash
      sdk use java 17.0.2-open
      ```
      ```bash
      sdk use maven 3.9.3 
      ```
- O main (SimpleApplication do Spring boot) está localizado no módulo Infrastructure e deve ser executado com a seguinte VM option: 
  ```
    --add-opens=java.base/jdk.internal.misc=ALL-UNNAMED
  ```
  - se a IDE for o intellj uma configuração com nome SimpleApplication no canto superior direito já estará disponível para uso.

## Para rodar o projeto localmente via com docker compose (para caso precise usar com outro sistema, por exemplo guiche)

- Com docker e docker compose instalados na máquina, executar na pasta do projeto o comando:
  ```bash
  docker compose up
  ```
  ou para rodar em segundo plano
  ```bash
  docker compose up -d 
    ```
  - Observações:
    - O mysql rodará na porta 3307 (para não conflitar com outros)
    - É preciso se atentar se já não há outros containers rodando nas portas 3307 e na porta 8091 do projeto.

## Para testes de carga com jMeter
- Caso queira, usar o arquivo dentro da pasta load-test-jmeter, importante verificar se os endpoints estão atualizados.
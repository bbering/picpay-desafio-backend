# PicPay Desafio Backend

Este projeto consiste na implementação de um **"PicPay Simplificado"** desenvolvido para resolver o desafio backend proposto pela plataforma. Nele, são seguidos os modelos de negócio indicados na documentação oficial do desafio: [PicPay Desafio Backend](https://github.com/PicPay/picpay-desafio-backend)

O projeto também aborda conceitos importantes como:

- **Herança**: Organização de classes em hierarquias para promover reutilização e extensibilidade de código.
- **Integração com Serviços Externos**: A aplicação realiza comunicações com APIs externas para simular funcionalidades adicionais, como autorização e notificação de transações.

## Pré-requisitos

- **Java 17** ou superior
- **Maven**

## Clonando o projeto

1. Clone o repositório em ambiente local:

    ```bash
    git clone https://github.com/bbering/picpay-desafio-backend.git
    ```

2. Navegue até a pasta do projeto:

    ```bash
    cd picpay-desafio-backend
    ```

## Rodando o projeto

1. Para iniciar o projeto, execute:

    ```bash
    mvn spring-boot:run
    ```

2. A aplicação estará disponível em: `http://localhost:8080`

## Banco de Dados

A aplicação utiliza um banco de dados em memória (H2) para que não seja necessário configurar uma nova conexão à um banco assim que o repositório for clonado.

[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

As credenciais para acesso ao banco de dados são:

- **Username**: sa
- **Password**: 1234

## Swagger

A documentação da API está disponível via Swagger em:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Estrutura do Projeto

Este projeto adota uma arquitetura monolítica baseada em camadas (layered architecture), promovendo separação de responsabilidades entre a apresentação, lógica de negócios e persistência de dados.

- **Models**: Representações das entidades do banco de dados.
- **DTOs**: Objetos de transferência de dados utilizados na comunicação entre as camadas.
- **Services**: Contêm a lógica de negócio e as validações das regras da aplicação.
- **Repositories**: Interfaces de acesso ao banco de dados utilizando Spring Data JPA.
- **Controllers**: Onde são expostos os endpoints da API.

## Funcionalidades do Projeto

Este projeto implementa as seguintes funcionalidades principais:

1. **Cadastro de Usuários Comuns ou Lojistas**  
   Permite o registro de novos usuários do tipo comum ou lojista, além da atualização de informações de usuários existentes.

2. **Criação de Transações**  
   Realiza a transferência de valores entre usuários, respeitando regras de negócio como verificação de saldo e tipo de usuário.

3. **Autorização de Transações**  
   Antes de confirmar uma transação, o sistema realiza a comunicação com um serviço externo de autorização para validar a operação (esse serviço pode estar 
   indisponível, dessa forma não possibilitando a transação ser efetivada).

4. **Notificação de Transações**  
   Após a autorização, o sistema notifica o destinatário da transação por meio de um serviço externo de notificação (esse serviço pode estar disponível, dessa 
   forma também não permitindo a transação ser efetivada).

## Integração com Serviços Externos

O projeto integra com dois serviços externos simulados (*mocks*), responsáveis pela autorização e notificação de transações:

- **Autorizador de Transações**  
  Serviço utilizado para validar se uma transação pode ser autorizada.  
  Endpoint: [https://util.devi.tools/api/v2/authorize](https://util.devi.tools/api/v2/authorize) (requisição `GET`)

- **Notificador de Transações**  
  Serviço utilizado para notificar o usuário de que uma transação foi concluída com sucesso.  
  Endpoint: [https://util.devi.tools/api/v1/notify](https://util.devi.tools/api/v1/notify) (requisição `POST`)

> **Observação**: Ambos os serviços são *mocks* de APIs reais, utilizados com o intuito de simular a comunicação com serviços externos.

## Como Contribuir

1. Faça um fork do repositório.
2. Crie uma nova branch para suas alterações:
    ```bash
    git checkout -b minha-nova-feature
    ```
3. Realize seus commits:
    ```bash
    git commit -m "Adiciona nova funcionalidade"
    ```
4. Envie suas alterações para o repositório remoto:
    ```bash
    git push origin minha-nova-feature
    ```
5. Abra um Pull Request para o repositório original.

---

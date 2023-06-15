<h1 align="center"> Schedule API </h1>

![Badge Concluído](https://img.shields.io/static/v1?label=Status&message=Concluído&color=success&style=for-the-badge)
![Badge Java](https://img.shields.io/static/v1?label=Java&message=17&color=orange&style=for-the-badge&logo=java)
![Badge Springboot](https://img.shields.io/static/v1?label=Springboot&message=v3.0.7&color=brightgreen&style=for-the-badge&logo=springboot)
![Badge Heroku](https://img.shields.io/static/v1?label=Heroku&message=Deploy&color=4f3074&style=for-the-badge&logo=Heroku)

## :book: Resumo do projeto
REST API para gerenciamento de agenda pessoal.

Projeto com o objetivo de por em prática meus estudos sobre programação reativa com Project Reactor e Spring WebFlux. A aplicação possui endpoints para criaçao e login de usuários, endpoints para gerenciar e manipular o recurso Evento (Event), que são protegidos e requerem autenticaçao por JWT (Json Web Token) para serem manipulados.<br>

OBS 1: Realizei o deploy da aplicação com banco de dados em memória (H2) por se tratar de um projeto para estudo.

OBS 2: Não criei testes automatizados pois eu queria partir para o próximo estudo, assim que sobrar tempo eu irei implementa-los.

## :toolbox: Tecnologias e ferramentas

<a href="https://www.jetbrains.com/idea/" target="_blank"><img src="https://img.shields.io/badge/intellij-000000.svg?&style=for-the-badge&logo=intellijidea&logoColor=white" target="_blank"></a>

<a href="https://pt.wikipedia.org/wiki/Java_(linguagem_de_programa%C3%A7%C3%A3o)" target="_blank"><img src="https://img.shields.io/badge/java%2017-D32323.svg?&style=for-the-badge&logo=java&logoColor=white" target="_blank"></a>

<a href="https://spring.io/projects/spring-boot" target="_blank"><img src="https://img.shields.io/badge/Springboot-6db33f.svg?&style=for-the-badge&logo=springboot&logoColor=white" target="_blank"></a>
<a href="https://reflectoring.io/getting-started-with-spring-webflux/" target="_blank"><img src="https://img.shields.io/badge/Spring%20WebFlux-6db33f.svg?&style=for-the-badge&logo=spring&logoColor=white" target="_blank"></a>
<a href="https://spring.io/projects/spring-data-r2dbc" target="_blank"><img src="https://img.shields.io/badge/Spring%20Data%20r2dbc-6db33f.svg?&style=for-the-badge&logo=spring&logoColor=white" target="_blank"></a>
<a href="https://spring.io/projects/spring-security" target="_blank"><img src="https://img.shields.io/badge/Spring%20Security-6DB33F.svg?&style=for-the-badge&logo=springsecurity&logoColor=white" target="_blank"></a>

<a href="https://maven.apache.org/" target="_blank"><img src="https://img.shields.io/badge/Apache%20Maven-b8062e.svg?&style=for-the-badge&logo=apachemaven&logoColor=white" target="_blank"></a>

<a href="https://netty.io/" target="_blank"><img src="https://img.shields.io/badge/Netty-f8f8f8.svg?&style=for-the-badge&logo=netty&logoColor=black" target="_blank"></a>

<a href="https://projectlombok.org/" target="_blank"><img src="https://img.shields.io/badge/Lombok-a4a4a4.svg?&style=for-the-badge&logo=lombok&logoColor=black" target="_blank"></a>
<a href="https://github.com/jwtk/jjwt" target="_blank"><img src="https://img.shields.io/badge/JJWT-a4a4a4.svg?&style=for-the-badge&logo=jjwt&logoColor=black" target="_blank"></a>

<a href="https://www.postgresql.org/" target="_blank"><img src="https://img.shields.io/badge/PostgreSQL-4169E1.svg?&style=for-the-badge&logo=postgresql&logoColor=white" target="_blank"></a>
<a href="https://github.com/r2dbc/r2dbc-h2" target="_blank"><img src="https://img.shields.io/badge/R2DBC%20H2-a4a4a4.svg?&style=for-the-badge&logo=h2&logoColor=white" target="_blank"></a>
<a href="https://www.docker.com/" target="_blank"><img src="https://img.shields.io/badge/Docker-2496ED.svg?&style=for-the-badge&logo=docker&logoColor=white" target="_blank"></a>

<a href="https://www.heroku.com/home" target="_blank"><img src="https://img.shields.io/badge/Heroku-430098.svg?&style=for-the-badge&logo=heroku&logoColor=white" target="_blank"></a>

## Funcionalidades

### API de gerenciamento de Autenticação

- `Login de usuário - POST /api/auth`: O login é realizado enviando as credenciais do usuário (email e password) 
em um JSON no corpo da requisição. Segue abaixo um exemplo do corpo da requisição.
    ```json
    {
      "email" : "lorem@email.com",
      "password" : "1234567890"
    }
    ```
    Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo **token** e **type**, em que **token**
    é um JWT que deve ser enviado no *header* **Authorization** em requisições 
    que requerem usuário autenticado, e **type** é o tipo do token, no caso dessa aplicação é o tipo *bearer*.
    Segue abaixo um exemplo de corpo da resposta.
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJQb3dlcktSIFRlc3QgQVBJIiwic3ViIjoibG9yZW1AZW1haWwuY29tIiwiaWF0IjoxNjgzNDYwNjkxLCJleHAiOjE2ODM1NDcwOTF9.P6eDc2gizVNUvXE6B-6wYvC4hmK4ffQHTSwKdnlgaTM",
    "type": "Bearer"
  }
  ```
  
 ### API de gerenciamento de Usuário

- `Criar Usuário - POST /api/users`: Criar usuário enviando as informações **name**, **email**, **password** e **confirmPassword**
em um JSON no corpo da requisição.<br> 
Não é necessário estar autenticado.<br>
O password é salvo criptografado no banco de dados usando BCryp.
Segue abaixo um exemplo do corpo da requisição.
    ```json
    {
      "name" : "Lorem Ipsum",
      "email" : "lorem@email.com",
      "password" : "1234567890",
      "confirmPassword": "1234567890"
    }
    ```
  Em caso de sucesso a resposta tem status 201 com um JSON no corpo da resposta contendo **id**, **name** e **email** do 
  usuário cadastrado. Segue abaixo um exemplo do corpo da resposta.
    ```json
    {
      "id" : 150,
      "name" : "Lorem Ipsum",
      "email" : "lorem@email.com"
    }
    ```

- `Buscar Usuários - GET /api/users?page={PAGE}&size={SIZE}`: Busca paginada de usuários cadastrados no sistema. O cliente pode enviar os parâmetros **page** e **size** na URL, os valores padrão são 0 e 10, respectivamente.<br>
  É necessário enviar token de autenticação no header da requisição, exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**, e ser usuário ADMIN.
  <br>

  Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo uma lista de informações dos usuários cadastrados **id**, **name** e **email**. Segue abaixo um exemplo do corpo da resposta.
  ```json
  {
    "content": [
      {
        "id": 1,
        "name": "Administrator",
        "email": "admin@email.com"
      },
      {
        "id": 2,
        "name": "Lorem Ipsum",
        "email": "lorem@email.com"
      },
    ],
    "pageable": {
      "sort": {
        "empty": true,
        "unsorted": true,
        "sorted": false
      },
      "offset": 0,
      "pageNumber": 0,
      "pageSize": 10,
      "paged": true,
      "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 2,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
      "empty": true,
      "unsorted": true,
      "sorted": false
    },
    "numberOfElements": 2,
    "first": true,
    "empty": false
  }
  ```

- `Buscar Usuário por ID - GET /api/users/{ID}`: Buscar usuário por **ID**. Onde **ID** é o identificador do usuário.<br>
  É necessário enviar token de autenticação no header da requisição, exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**. Usuário ADMIN podem buscar qualquer outro usuário por ID, enquanto que usuários comuns conseguem buscar apenas a si mesmos.
  <br>
 
  Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo **id**, **name** e **email** do
  usuário buscado.<br> 
  Segue abaixo um exemplo do corpo da resposta.
    ```json
    {
      "id" : 150,
      "name" : "Lorem Ipsum",
      "email" : "lorem@email.com"
    }
    ```
    
### API de gerenciamento de Evento

- `Criar Evento - POST /api/events`: Criar evento enviando as informações **title**, **description** e **date_time** em um JSON no corpo da requisição.<br>
  É necessário enviar token de autenticação no header da requisição, 
  exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**.<br>
  Segue abaixo um exemplo do corpo da requisição.
    ```json
    {
      "title" : "Evento XPTO",
      "description" : "Descrição do evento xpto",
      "date_time" : "2023-06-22T10:30:00"
    }
    ```
  Em caso de sucesso a resposta tem status 201 com um JSON no corpo da resposta contendo **id**, **title**, 
  **description** e **date_time** do evento cadastrado. Segue abaixo um exemplo do corpo da resposta.
  ```json
  {
    "id" : 97,
    "title" : "Evento XPTO",
    "description" : "Descrição do evento xpto",
    "date_time" : "2023-06-22T10:30:00"
  }
  ```
<br>

- `Buscar Eventos - GET /api/events?page={PAGE}&size={SIZE}&date={DATE}`: Busca paginada dos eventos relacionados com o usuário logado, opcional busca por date (yyyy-MM-dd). O cliente pode enviar os parâmetros **page** e **size** na URL, os valores padrão são 0 e 10, respectivamente, e pode enviar o parâmetro **date** no formato yyyy-MM-dd para buscar por data<br>
  É necessário enviar token de autenticação no header da requisição, exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**.
  <br>

  Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo uma lista de informações
  dos eventos cadastrados **id**, **title**, **description**, e **date_time**.<br>
  Segue abaixo um exemplo do corpo da resposta.
  ```json
  {
    "content": [
      {
        "id" : 97,
        "title" : "Evento XPTO",
        "description" : "Descrição do evento xpto",
        "date_time" : "2023-06-22T10:30:00"
      },
      {
        "id" : 98,
        "title" : "Outro Event XPTO",
        "description" : "Descrição do outro evento xpto",
        "date_time" : "2023-06-22T12:30:00"
      },
    ],
    "pageable": {
      "sort": {
        "empty": true,
        "unsorted": true,
        "sorted": false
      },
      "offset": 0,
      "pageNumber": 0,
      "pageSize": 10,
      "paged": true,
      "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 2,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
      "empty": true,
      "unsorted": true,
      "sorted": false
    },
    "numberOfElements": 2,
    "first": true,
    "empty": false
  }
  ```

- `Buscar Evento por ID - GET /api/events/{ID}`: Buscar evento por **ID**. Onde **ID** é o identificador do evento. O cliente consegue buscar apenas eventos que estão relacionados á ele.<br>
  É necessário enviar token de autenticação no header da requisição, exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**.
  <br>

  Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo **id**, **title**, 
  **description**, e **date_time**.
  Segue abaixo um exemplo do corpo da resposta.
  ```json
  {
    "id" : 97,
    "title" : "Evento XPTO",
    "description" : "Descrição do evento xpto",
    "date_time" : "2023-06-22T10:30:00"
  }
  ```

- `Atualizar Evento - PUT /api/events/{ID}`: Atualizar tarefa por **ID**. Onde **ID** é o identificador da tarefa e
  enviar as novas informações da tarefa **title**, **description**, e **date_time** em um JSON no corpo da requisição. O cliente consegue atualizar apenas eventos que estão relacionados á ele.<br>
  É necessário enviar token de autenticação no header da requisição, exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**.
  <br>
  Segue abaixo um exemplo do corpo da requisição.
    ```json
    {
      "title" : "Evento XPTO atualizado",
      "description" : "Descrição do evento xpto atualizado",
      "date_time" : "2023-06-22T10:30:00"
    }
    ```

  Em caso de sucesso a resposta tem status 204.

- `Deletar Tarefa - DELETE /api/events/ID`: Deletar tarefa por **ID**. Onde **ID** é o identificador da tarefa a
  ser deletada. O cliente consegue deletar apenas eventos que estão relacionados á ele.<br>
  É necessário enviar token de autenticação no header da requisição, exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**.
  <br>

  Em caso de sucesso a resposta tem status 204.

## Documentação
A descrição de cada API e recursos está disponível na interface gráfica gerada pelo 
Swagger.

### Acesso a recursos que requerem usuário autenticado
Endpoints que requerem usuário autenticado devem receber um token no header Authorization da requisição, exemplo:
```
  'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJQb3dlcktSIFRlc3QgQVBJIiwic3ViIjoiZWRzb25AZW1haWwuY29tIiwiaWF0IjoxNjgzNDcxMzY3LCJleHAiOjE2ODM0NzQ5Njd9.RZ0rKo6LcLWlGj0vqfl9_AHCmDJXy9vJ4FS7C6_vRFg'
```
O token expira em 24 horas e pode ser gerado através do endpoint de autenticação.<br>
Caso o header não seja enviado, ou um token inválido seja enviado, uma resposta com status **401 (Unauthorized)**  será devolvida.

### Swagger
Para enviar requisições através do Swagger, basta clicar no 
recurso desejado, clicar no botão **Try it out**, adicionar Parâmetros (se necessário), 
adicionar corpo da requisição (se necessário) e clicar em **Execute**.<br><br>
Para enviar token no header Authorization, realize o login através do recurso Autenticação, 
copie o token que foi devolvido no corpo da resposta, sem as aspas, clique no botão **Authorize**, que abrirá 
um pop-up, cole o token no campo **value** e clique em **Authorize**. Feche o pop-up e pronto, suas requisições 
serão enviadas com o header Authorization e o token informado.<br>

OBS: Em breve colocarei imagens ilustrativas para as etapas acima.

## Deploy
Realizei o deploy da aplicação no **Heroku**, você pode testar através da interface gráfica gerada pelo swagger 
[swagger-ui](https://schedule-api-a0aa87e24e60.herokuapp.com/swagger-ui.html).

OBS: O plano que eu uso do Heroku **adormece** a aplicação depois de certo tempo inativo, 
então pode ser que a primeira requisição demore um pouco (até uns 60 segundos), apenas seja paciente :wink:.

## Atualizações futuras
- [ ] Implementar os testes automatizados (Unidade e Integração).

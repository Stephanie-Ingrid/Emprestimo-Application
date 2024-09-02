# Aplicação Simula Empréstimo

Essa aplicação cadastra pessoa, define um valor de empréstimo para cada perfil, após o cadastro,
a pessoa pode fazer uma solicitação de empréstimo, caso o valor do emprestimo esteja de acordo com o perfil, 
o emprestimo é aprovado e se comunica com a api de pagamentos que recebe a solicitação e confima o pagamento.

Arquivo do postman com as requisições está disponível na pasta resources

### Ferramentas

- Java17
- maven
- MySQL
- Postman

## Compilação

Para rodar projeto é necessário executar o schema SQL que está na pasta resources na pasta meu_schema.sql


### Porta

    sever.port=8080

### Swagger

    http://localhost:8080/swagger-ui/index.html#/


# Bem-vindo ao Projeto IUB

Serviço responsável por validar a força da senha informada


## Objetivo
Fornecer uma api para validação de senhas com determinados critérios (informados abaixo). 

Critérios: 
- Nove ou mais caracteres
- Ao menos 1 dígito
- Ao menos 1 letra minúscula
- Ao menos 1 letra maiúscula
- Ao menos 1 caractere especial
    - Considere como especial os seguintes caracteres: !@#$%^&*()-+
- Não possuir caracteres repetidos dentro do conjunto

## Solução
Com intuito de validar a senha com os critérios informados, utilizei uma solução baseada em [Regex](https://pt.wikipedia.org/wiki/Express%C3%A3o_regular), sendo mais performática
do que o desenvolvimento baseado em código. Dessa forma evitamos bugs nas regras de validação, redução de linhas de código e
consequentemente deixamos o código da aplicação mais limpo.

A solução atual foi baseada na linguagem de programação Java com Framework SpringBoot para o desenvolvimento da API. 

**Observação:** Por se tratar uma de uma aplicação com somente uma função, poderiamos ter aplicado em AWS Function e evitando custos no projeto. 

### Explicação da REGEX

Regex utilizada: 
```regex
^(?=.*[A-Z])(?=.*[!@#$%^&*()-+])(?=.*[0-9])(?=.*[a-z])(?!.*(.).*\1).*.{9,}$
```

- ```(?=.*[A-Z])```: Verifica se existe AO MENOS 1 letra Maiúscula na string. 
- ```(?=.*[!@#$%^&*()-+])```: Verifica AO MENOS 1 caractere especial na string.
- ```(?=.*[0-9])```: Verifica se existe AO MENOS 1 digito na string.
- ```(?=.*[a-z])```: Verifica se existe AO MENOS 1 letra Minúsculas na string. 
- ```(?!.*(.).*\1).```: Verifica caracteres repetidos na string.
- ```*.{9,}$```: Verifica quantidade de caracteres com 9 ou MAIS na string.

# Ambiente de desenvolvimento

O projeto está utilizando a JDK 11. 
Existem alguns passos para execução do projeto em ambiente local, necessário que alguns programas estejam corretamente instalados.

Framework/lib | Versão Recomendada | S.O Utilizado
--- | --- | ---
[Maven](https://maven.apache.org/download.cgi) | 3.6.3 | Windows / Linux
[JDK](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html) | 11 | Windows / Linux
[Intellij](https://www.jetbrains.com/pt-br/idea/)| - | Windows / Linux

## Execução do projeto

### Passo 1
Faça o clone do projeto
```shell script
$ https://github.com/Maikoncanuto/iub
```

### Passo 2
Entre na pasta raiz do projeto

```shell script
$ cd iub
```

### Passo 3
Executar o comando do maven para gerar o artefato 

```shell script
$ mvn clean install
```

### Passo 4
Executar a aplicação 

```shell script
java -jar target/iub-springboot-0.0.1-SNAPSHOT.jar
```

# Serviços da API

Todos os endpoints informados abaixo servem para o microservice

Serviço | URL | Status | Descrição
--- | --- | --- | ---
Swagger UI | http://localhost:8080/swagger-ui.html#/| :white_check_mark: | Swagger da aplicação
API-DOCS | http://localhost:8080/v2/api-docs | :white_check_mark: | Api Docs da aplicação
Endpoint de validação | http://localhost:8080/auth/validate-password | :white_check_mark: | Endpoint de validação de senha

# Exemplo de requisições para os Endpoints

## Validação da senha
**Url de requisição:**
```
http://localhost:8080/auth/validate-password
```
**Corpo da Requisição:**

Parâmetro | Obrigatorio | Formato | Tipo | Descrição | Exemplo
--- | --- | --- | --- | --- | ---  
payload | Obrigatorio  | X  | String  | Senha enviada para o Endpoint | M@i1Ko0n$9876

**Curl:**
```shell script
curl -X POST "http://localhost:8080/auth/validate-password" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"payload\": \"string\"}"
```

**Retorno:**
``` json
{
  "data": true
}
```
---
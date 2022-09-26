# Guia do projeto

## Tecnologias
* Java 18.0.2.1
* Maven para gerenciamento das dependências
* Banco de dados Postgres
* Spring Boot
  * Criação da aplicação
* Spring web
  * Desenvolvimento do CRUD REST
* Spring cache
  * Melhorar desempenho de consultas
* Spring security com autenticação JWT
  * Camada de segurança exposição dos recursos REST
* Spring JPA
  * Suporte para persistencia do banco de dados

## Autenticação
* A inserção do usuário (name, email, senha) foi realizada manualmente via sql.
* Para geração da senha no formato **BCrypt** existe uma função auxiliar no arquivo: *config/security/SecurityConfigurations.java*

## Modelagem dos recursos
* Foi compartilhado os recursos via collection postman, localizada em: */resources/Collection/Peixe Urbano.postman_collection.json*

## Motivação do projeto
A descrição completa do projeto se enconta no link

[Github ALClaudius](https://github.com/ALClaudius/java-challenge-developer)

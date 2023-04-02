# API REST - Aplicação de livros
Esse projeto é uma aplicação API REST com Spring Boot para gerenciamento de livros cadastrados em uma livraria com autenticação de usuários, com o Spring Security com utilização do Jwt. Esse projeto foi desenvolvido como projeto final do módulo Web II do Santander-Coders 2023, bootcamp do Santander em parceria com a Ada.

# Entidades
## Editora
Atributos:

* id - Long
* nome - String (máximo de 255 caracteres)
* descrição - String (não obrigatório)
## Categoria
Atributos:

* id - Long
* nome - String (máximo de 100 caracteres)

## Livro
Atributos:

* id - Long
* nome - String (máximo de 255 caracteres)
* isbn - String (máximo de 13 caracteres)
* editora_id - Long - relacionamento com a Entidade Editora
* categoria_id - Long - relacionamento com a Entidade Categoria

## Usuário
Atributos:

* id - Long
* email - String
* nome - String
* username - String (único)
* cpf - String
* password - String
* perfil_id - Long - relacionamento com a Entidade perfil
* descrição - String (não obrigatório)

## Perfil
Atributos:

* id - Long
* nome - String
## Livro Favorito
Atributos:

* id - Long
* livro_id - Long - relacionamento com a entidade livro
* user_id - Long - relacionamento com a entidade usuário

# Requisitos
## Principais
- [x] Crie o CRUD de cada entidade, possuindo os controllers, services, repositories, entities e DTOs
- [x] Crie um endpoint especifico para buscar os livros por categoria.
- [x] Crie um endpoint especifico para buscar os livros por editora.
- [x] Crie um endpoint que possa buscar o livro pelo nome ou pelo número isbn ou pelos dois, utilizando criteria ou query dsl.
## Bônus
- [x] Crie a parte de autenticação de usuário e faça endpoints para salvar e buscar os livros favoritos do usuário logado.

# Instruções
* Para inserir perfis de usuário
    * utilizar queries no banco de dados h2
    * ou, editar o h2 pela interface dele, em http://localhost:8080/h2

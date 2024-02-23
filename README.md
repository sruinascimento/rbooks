# Projeto Bookstore API

![status-projeto](https://img.shields.io/badge/status-desenvolvimento-gree) 
![Java-version](https://img.shields.io/badge/java-version_21-blue) ![springboot-version](https://img.shields.io/badge/springboot-version_3.2.1-gree)
![license](https://img.shields.io/badge/licence-MIT-gree)

Este projeto é uma API RESTful para o gerenciamento de uma livraria online. O principal objetivo é fornecer uma 
interface para o cadastro de livros, autores e a realização de compras de livros.


## 🪓 Tecnologias Utilizadas
- `Java 21`
- `Spring Boot 3`
- `MySQL 8`
- `Flyway Migration`
- `Insomnia`
- `Git`

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot&color=black)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![Insomnia](https://img.shields.io/badge/Insomnia-black?style=for-the-badge&logo=insomnia&logoColor=5849BE)


## Funcionalidades

| Método HTTP | Rota | Descrição |
|-------------|------|-----------|
| POST | /purchases | Cria uma nova compra |
| GET | /purchases/{id} | Retorna os detalhes de uma compra específica |
| POST | /books | Cria um novo livro |
| GET | /books | Lista todos os livros |
| GET | /books/{id} | Retorna os detalhes de um livro específico |
| POST | /authors | Cria um novo autor |
| POST | /states | Cria um novo estado |
| POST | /countries | Cria um novo país |
| POST | /categories | Cria uma nova categoria |
| POST | /coupons | Cria um novo cupom |
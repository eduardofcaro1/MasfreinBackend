# MasfreinBackend

Este projeto é uma API desenvolvida em **Spring Boot** para gerenciar informações de cursos, matrículas, semestres e outras entidades relacionadas. Ele utiliza o banco de dados **MySQL** e está configurado para ser executado em um ambiente Docker.

## Configuração do Banco de Dados

### Configurações de Conexão
As configurações de conexão com o banco de dados estão definidas no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost/projeto
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### Criação Manual do Banco de Dados
Caso o banco de dados não seja criado automaticamente, siga os passos abaixo:

1. Importe o arquivo `importacao.sql` no MySQL para criar o banco de dados e as tabelas necessárias.
2. Após a importação, execute os seguintes comandos SQL para inserir os dados iniciais.

### Login Inicial
Após a configuração do banco de dados, você pode acessar o sistema com as seguintes credenciais:

- **Usuário:** if
- **Senha:** if

Certifique-se de que o banco de dados está rodando e que as tabelas foram criadas corretamente antes de iniciar a aplicação.

## Como Executar o Projeto
1. Suba o banco de dados com Docker.
2. Compile e execute o projeto.
3. Acesse a API em: `http://localhost:8080`.

---

**Autores:** Eduardo Caro e Pedro Maschio
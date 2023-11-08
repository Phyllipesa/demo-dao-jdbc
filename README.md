# Demo DAO JDBC

Este é um projeto de exemplo que demonstra o uso de JDBC (Java Database Connectivity) para interagir com um banco de dados usando Java.
O projeto é gerenciado com o Apache Maven e pode ser facilmente executado em um contêiner Docker com um banco de dados MySQL.

## Objetivo geral:
- Conhecer os principais recursos do JDBC na teoria e prática
- Elaborar a estrutura básica de um projeto com JDBC
- Implementar o padrão DAO manualmente com JDBC

## Requisitos

- Java Development Kit (JDK)
- Apache Maven
- Docker


## Executar Localmente

Clone o repositório

   ```bash
   git clone git@github.com:Phyllipesa/demo-dao-jdbc.git
```

Acesse o diretório do projeto

   ```bash
cd demo-dao-jdbc
```

Construa a imagem do banco de dados MySQL usando o Dockerfile fornecido
   ```bash
docker build -t meu-mysql .
```

Inicie um contêiner Docker com o MySQL usando a imagem construída:
   ```bash
docker run -d -p 3306:3306 --name meu-mysql meu-mysql
```

Compile o projeto com o Maven
   ```bash
mvn compile
```

Abra o projeto com uma IDE de sua preferência e execute os arquivos Program e Program2 no diretório application.


## Tecnologia

Java

## Autores

- [@phyllipesa](https://github.com/phyllipesa) - Desenvolvimento do projeto


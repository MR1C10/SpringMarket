# Lista de Compras - Spring Boot

Aplicativo simples de lista de compras desenvolvido em Java com Spring Boot.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.1.5
- Spring Data JPA
- H2 Database (em memória)
- Thymeleaf
- Tailwind CSS
- Maven

## Funcionalidades

- Adicionar itens à lista de compras
- Marcar/desmarcar itens como comprados
- Editar informações dos itens
- Remover itens da lista
- Buscar itens por nome
- Visualizar estatísticas (itens pendentes e comprados)
- Interface responsiva e simples

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/example/listacompras/
│   │   ├── ListaComprasApplication.java      # Classe principal
│   │   ├── controller/
│   │   │   └── ItemControlador.java          # Controlador MVC
│   │   ├── model/
│   │   │   └── Item.java                     # Entidade JPA
│   │   ├── repository/
│   │   │   └── ItemRepositorio.java          # Repositório JPA
│   │   └── service/
│   │       └── ItemServico.java              # Camada de serviço
│   └── resources/
│       ├── templates/
│       │   ├── lista.html                    # Página principal
│       │   └── editar.html                   # Página de edição
│       └── application.properties            # Configurações
```

## Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior

## Como executar

### Método 1: Via Maven (recomendado)
```bash
# Navegar para o diretório do projeto
cd SpringMarket

# Executar a aplicação
mvn spring-boot:run
```

### Método 2: Via IDE
1. Abrir o projeto em sua IDE favorita (IntelliJ IDEA, Eclipse, VS Code)
2. Executar a classe `ListaComprasApplication.java`

### Método 3: Gerando JAR
```bash
# Compilar e gerar o JAR
mvn clean package

# Executar o JAR
java -jar target/lista-compras-0.0.1-SNAPSHOT.jar
```

## Acesso à Aplicação

Após iniciar a aplicação, acesse:
- **Aplicação Principal**: http://localhost:8080
- **Console H2 Database**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:lista_compras`
  - Username: `sa`
  - Password: (deixar em branco)

## Como Usar

1. **Adicionar Item**: Preencha o formulário na parte superior com nome (obrigatório), categoria, quantidade e observações
2. **Marcar como Comprado**: Clique no botão "Comprado" ao lado do item
3. **Editar Item**: Clique no botão "Editar" para modificar as informações
4. **Remover Item**: Clique no botão "Remover" (confirmação será solicitada)
5. **Buscar Item**: Use o campo de busca para encontrar itens específicos

## Características do Design

- Interface limpa e minimalista usando Tailwind CSS
- Design responsivo para desktop e mobile
- Cores suaves e layout organizado
- Sem elementos chamativos ou emojis
- Feedback visual para ações do usuário

## Banco de Dados

O aplicativo usa H2 Database em memória, que é criado automaticamente ao iniciar a aplicação. Os dados são perdidos quando a aplicação é encerrada.

## Estrutura MVC

- **Model**: Entidade `Item` com mapeamento JPA
- **View**: Templates Thymeleaf (`lista.html`, `editar.html`)
- **Controller**: `ItemControlador` gerencia todas as requisições HTTP
- **Service**: `ItemServico` contém a lógica de negócio
- **Repository**: `ItemRepositorio` para operações de banco de dados
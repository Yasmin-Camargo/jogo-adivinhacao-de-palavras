# 🤔🎮 Jogo de Adivinhação de Palavras 🎮🤔

Jogo de Adivinhação de Palavras com base na sua definição, desenvolvido em Java com o framework Spring Boot e uma interface frontend em React.

## 🚀 Tecnologias Utilizadas

### Backend
- **☕ Java**: Linguagem de programação utilizada.
- **🌱 Spring Boot**: Framework para criação de aplicações Java.
- **🔧 Maven**: Gerencia dependências e automatiza o processo de build do projeto.
- **🐘 PostgreSQL**: Banco de dados utilizado.
- **🍃 Hibernate**: Comunicação entre aplicações Java e bancos de dados.
- **🔄 Liquibase**: Ferramenta para versionamento de banco de dados.
- **📜 Swagger**: Documentação da API.
- **📜 JavaDoc**: Documentação do código Java.
- **🧪 JUnit**: Testes unitários em Java.

### Frontend
- **⚛️ React**: Biblioteca JavaScript para construção de interfaces de usuário.
- **📝 TypeScript**: Superset do JavaScript que adiciona tipagem estática.
- **💅 Chakra UI**: Biblioteca de componentes para React.
- **📦 npm**: Gerenciador de pacotes para JavaScript, utilizado para instalar dependências do projeto.
- **📦 Webpack**: Empacotador de módulos que compila e otimiza os arquivos do projeto.
- **🔄 Babel**: Converte código JavaScript moderno em versões compatíveis com navegadores mais antigos.
- **📜 JSDoc**: Documentação do código React.

## 🛠️ Como Executar

### Pré-requisitos

- **☕ Java 12** ou superior
- **🟢 Node.js** e **📦 npm**
- **🐳 Docker** (para executar o banco de dados PostgreSQL)

### Backend

1. Navegue até o diretório `backend`:
    ```sh
    cd backend
    ```

2. Inicie o banco de dados PostgreSQL usando Docker:
    ```sh
    docker-compose up -d
    ```

3. Execute a aplicação Spring Boot:
    ```sh
    ./mvnw spring-boot:run
    ```

Obs.: Para execução de testes
    ```
    mvn test
    ```

### Frontend

1. Navegue até o diretório `frontend`:
    ```sh
    cd frontend
    ```

2. Instale as dependências:
    ```sh
    npm install
    ```

3. Execute a aplicação React:
    ```sh
    npm start
    ```

### 🚪 Portas
- Backend rodando em: `http://localhost:8080`
- Frontend rodando em: `http://localhost:3000`
- Banco de dados PostgreSQL disponível na porta `5432`

## 📡 Endpoints da API

A API do backend possui os seguintes endpoints principais:

### Words
- **`POST /words`**: Salva uma nova palavra com sua definição, sinônimos e nível de dificuldade.
- **`GET /words`**: Retorna todas as palavras cadastradas no sistema.
- **`GET /words/{id}`**: Retorna os detalhes de uma palavra pelo seu ID.
- **`PUT /words/{id}`**: Atualiza os detalhes de uma palavra pelo seu ID.
- **`DELETE /words/{id}`**: Deleta uma palavra pelo seu ID.

### Game
- **`GET /game/start`**: Inicia um novo jogo.
- **`GET /game/check/{word}`**: Verifica se a palavra adivinhada está correta.

A documentação completa da API pode ser acessada em `/swagger-ui.html`.

## 🎮 Fluxo do Jogo

![image](https://github.com/user-attachments/assets/730d9ada-4a15-41cb-9c44-a45c7b542cc9)

1. O jogador inicia o jogo clicando no botão "Sortear nova palavra".
2. O backend retorna a descrição e o sinônimo da palavra.
3. O jogador tenta adivinhar a palavra digitando no campo de entrada e clicando no botão "Verificar".
4. O backend verifica a palavra e retorna uma mensagem indicando se a palavra está correta ou não.
5. O jogador pode usar uma dica, que conta como uma tentativa.
6. O jogo termina quando o jogador acerta a palavra ou todas tentativas são utilizadas


## 📝 Gerenciamento de Palavras

![image](https://github.com/user-attachments/assets/964d48b1-36d2-4981-ba0d-8bbe5bb2152b)

### 👀 Visualização de Palavras
1. O jogador acessa a página das palavras clicando na aba "Palavras cadastradas".
2. O backend retorna as palavras cadastradas no banco.
3. É possível ver a lista de palavras cadastradas, incluindo palavra, descrição, a dica e o nível.

### ➕ Cadastro de Palavras
1. Na aba "Palavras cadastradas", clique no botão "➕".
2. Preencha o formulário com os dados da nova palavra.
3. Clique em "Salvar", e os dados serão enviados ao backend, que salva a nova palavra no banco de dados.

### ❌ Deletando Palavras
1. Na aba "Palavras cadastradas", localize a palavra que deseja excluir.
2. Clique no botão "Excluir" ao lado da palavra.
3. O backend remove a palavra do banco de dados.

## 🗂️ Estrutura do Projeto

```plaintext
jogo-adivinhacao-de-palavras/
├── backend/
│   ├── src/                # Código fonte do backend
│   │   ├── main/           # Código principal da aplicação
│   │   │   ├── java/       # Classes Java
│   │   │   │   ├── controllers/     # Controladores da API
│   │   │   │   ├── dtos/            # Objetos de Transferência de Dados
│   │   │   │   ├── models/          # Modelos de dados
│   │   │   │   ├── repositories/    # Repositórios de acesso a dados
│   │   │   │   └── service/         # Lógica de negócio
│   │   │   └── resources/  # Arquivos de configuração e recursos
│   │   └── test/           # Testes unitários
│   ├── docker-compose.yml  # Arquivo de configuração do Docker Compose
│   ├── mvnw                # Wrapper do Maven para Linux/Mac
│   ├── mvnw.cmd            # Wrapper do Maven para Windows
│   └── pom.xml             # Arquivo de configuração do Maven
|
├── frontend/
│   ├── src/                # Código fonte do frontend
│   │   ├── components/     # Componentes reutilizáveis
│   │   ├── hooks/          # Hooks personalizados
│   │   ├── interface/      # Interfaces TypeScript
│   │   ├── pages/          # Páginas do aplicativo
│   │   └── style/          # Estilos do aplicativo
│   ├── index.html          # Ponto de entrada para a aplicação React.
│   ├── App.tsx             # Componente principal, onde a lógica do aplicativo é inicializada.
│   ├── package.json        # Arquivo de configuração do npm
│   ├── package-lock.json   # Dependencias do npm
│   ├── tsconfig.json       # Arquivo de configuração do typescript
│   ├── .babellrc           # Arquivo de configuração utilizado pelo Babel
│   └── webpack.config.js   # Configuração do Webpack
├── .gitignore              # Arquivos e diretórios devem ser ignorados pelo Git
└── README.md               # Documentação principal do projeto
```

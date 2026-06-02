# AP2 - Aplicações Front-End

## Identificação

Nome: Alan dos Santos Bauer
Curso: Análise e Desenvolvimento de Sistemas
Disciplina: Aplicações Front-End
Instituição: ULBRA

## Tema do projeto

CRUD de Viagens com Angular e JSON Server.

## Descrição

Aplicação desenvolvida em Angular para cadastro, listagem, edição e exclusão de viagens, utilizando JSON Server como API simulada. O sistema permite registrar destinos de viagem com informações como destino, país, datas, orçamento, tipo e status (realizada ou planejada).

## Entidade: Viagem

| Campo        | Tipo    | Descrição                              |
|--------------|---------|----------------------------------------|
| id           | string  | Identificador único (gerado pelo JSON Server) |
| destino      | string  | Nome do destino (ex: Paris, França)    |
| pais         | string  | País do destino                        |
| dataPartida  | string  | Data de partida (formato YYYY-MM-DD)   |
| dataRetorno  | string  | Data de retorno (formato YYYY-MM-DD)   |
| orcamento    | number  | Orçamento estimado em reais            |
| tipoViagem   | string  | Tipo: Turismo, Negócios, Aventura, etc.|
| realizada    | boolean | Indica se a viagem já foi realizada    |
| observacoes  | string  | Anotações e observações da viagem      |

## Tecnologias utilizadas

- Angular 19
- TypeScript
- HTML5
- CSS3
- JSON Server 0.17
- Git e GitHub
- Google Fonts (Playfair Display + DM Sans)

## Estrutura do projeto

```
src/app/
  components/
    navbar/          → Cabeçalho da aplicação
    viagem-form/     → Formulário de cadastro e edição
    viagem-list/     → Listagem com ações de editar e excluir
  models/
    viagem.ts        → Interface TypeScript da entidade
  services/
    viagem.service.ts → Comunicação HTTP com o JSON Server
  app.ts             → Componente raiz
  app.config.ts      → Configuração (HttpClient, Router)
db.json              → Banco de dados simulado
```

## Como executar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/usuario/ap2-crud-viagens.git
```

2. Acesse a pasta do projeto:

```bash
cd ap2-crud-viagens
```

3. Instale as dependências:

```bash
npm install
```

4. Execute a API fake (JSON Server):

```bash
npm run api
```

5. Em outro terminal, execute o Angular:

```bash
npm start
```

6. Ou execute os dois juntos com:

```bash
npm run start:all
```

7. Acesse no navegador:

```
http://localhost:4200
```

A API estará disponível em: `http://localhost:3000/viagens`

## Funcionalidades

- ✅ Cadastro de viagens com formulário validado
- ✅ Listagem de todas as viagens cadastradas
- ✅ Edição de viagens existentes (formulário reutilizado)
- ✅ Exclusão de viagens com confirmação
- ✅ Indicador de carregamento
- ✅ Mensagem para lista vazia
- ✅ Tratamento de erros de conexão com a API
- ✅ Diferenciação visual entre viagens realizadas e planejadas
- ✅ Interface responsiva

## Link do vídeo demonstrativo

[INSERIR LINK PÚBLICO DO VÍDEO AQUI]

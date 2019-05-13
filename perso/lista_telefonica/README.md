# Lista Telefonica

A aplicação foi construida com intuito de expor uma API RESTful simples usando Elixir, consumindo os dados da API usando Vue.js para agilizar o desenvolvimento

  - Elixir usando Plug e Cowboy
  - Vue.js usando Axios e vue-router

### Tecnologia

  - Elixir usando Plug e Cowboy
  - Vue.js usando Axios e vue-router

### Instalação

 - Elixir

Instale as dependencias

```sh
$ wget https://packages.erlang-solutions.com/erlang-solutions_1.0_all.deb && sudo dpkg -i erlang-solutions_1.0_all.deb
$ sudo apt-get update
$ sudo apt-get install esl-erlang
$ sudo apt-get install elixir
```
Construa o Projeto e rode o servidor

```sh
$ cd lista_telefonica/back
$ mix do deps.get, deps.compile, compile
$ mix run --no-halt
```
- Vue

Instale as dependencias

```sh
$ sudo apt-get update
$ sudo apt install nodejs npm
$ sudo apt install npm
```

Construa o projeto e rode o servidor

```sh
$ cd lista_telefonica/front
$ npm i
$ npm run serve
```

Com isso ambos Elixir e Vue estarão rodando na máquina, por padrão a porta na qual a API vai rodar será na 4000.


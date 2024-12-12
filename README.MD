Esse aplicativo feito com objetivo de aplicar meus conhecimentos para o Desafio Técnico da Shopper.
Nesse aplicativo, usamos uma API feita pela Shopper com 3 endpoints:

- POST para pesquisar se existe corridas disponível do endereço de origem para o endereço de destino.
- PATCH para confirmar a viagem quando estamos na segunda tela.
- GET para listar todas as corridas que o usuário tem no histórico.

Tecnólogias usadas no projeto:
 - RetroFit = Objetivo de fazer requisições HTTP para a API.
 - GSON = Objetivo para converter objetos em JSON.
 - Coil = Objetivo de carregar imagens de forma assíncrona.
 - LiveData = Objetivo de usar o Observer Pattern.

Design Patterns e Arquitetura usadas no Projeto:
 - Observer Pattern com os LiveDatas.
 - Repositories Pattern.
 - Arquitetura MVVM
 - DTOs para proteção melhor dos dados no core.

API Key:
 - A API key está localizada em xxx.

Arquivos:

Model: 
 - entities = Temos a pasta entities com todos os entities que precisamos para buscar dados na API e enviar dados.
 - DTO = Em DTO, temos o padrão DTO para protegermos e isolarmos os dados do core entities e usarmos em outros lugares.
 - RetroFit = Como o nome diz, na pasta RetroFit é onde temos as requisições HTTP.
 - Repositories: Aqui é onde fazemos os Repositories para isolarmos as requisições HTTP antes de enviarmos para o ViewModel fazer a regra de negócios.

ViewModel:
 - Nessa pasta temos apenas os ViewModels que gerenciam o estado e fazem a regra de negócio de cada requisição HTTP.

View: 
 - Em View, temos algumas subpastas:
      - navigation: Onde fica toda lógica de navegação entre telas do aplicativo.
      - components: Aqui fica todos os components que são reutilizaveis.
      - mainScreen: Onde fica a tela principal, com perguntas sobre qual seu Id, qual sua origem de viagem e qual será o destino da viagem, dentro temos mais arquivos além do principal.
      - avaliableDrivers: Aqui ficará tudo que envolve a criação da segunda tela, que após passar por toda verificação da primeira página, ele virá para essa tela com os motoristas disponiveis e um mapa estático da sua rota.
      - travelHistory: Aqui será o local para onde você irá após passar por todas as verificações de avaliabeDrivers, aqui terá dois campos para preencher, com seu id e um id do motorista, ai o usuário preencherá os campos e caso queira ver todos os motoristas no histórico, ele aperta em adicionar tudo, mas tem uma opção de filtrar o histórico, nesse filtro você poderá filtrar por nome do motorista, origem, destino e valor da viagem.

drawable/string: Todas as strings de Buttons e TextFields, ficaram aqui.


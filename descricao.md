# Github API

<aside>
ℹ️ Fique atento a todas as instruções que este documento oferece, a continuidade do processo dependerá disso!

</aside>

Você está na etapa do desafio técnico, parabéns por ter chegado até aqui! Neste desafio, queremos conhecer suas habilidades técnicas em foco prático e aplicado na resolução de um problema. Aqui conheceremos seu estilo de código, aptidões técnicas e, sobretudo, a sua capacidade de resolução de problemas.

# O desafio…

O nosso time gosta bastante de ficar atento as novidades no mundo da tecnologia, e com isso gostaríamos de ter um aplicativo que se comunicasse com o Github e nos mostrasse algumas informações de forma mais prática.

## Objetivo

Você deve criar uma aplicação que autentique com o Spotify e cumpra os seguintes casos de uso:

- Listar as pessoas que o usuário autenticado segue;
- Listar os repositórios das pessoas que o usuário autenticado segue;
- Listar os repositórios do usuário autenticado;
- Criar um novo repositório;
- Exibir os dados do usuário.

# Instruções

A seguir estão os requisitos (obrigatórios e bônus) que serão levados em consideração para a análise da sua solução.
Ao submeter o projeto, inclua o texto abaixo em seu README, marcando tudo aquilo que realmente foi feito.
# Requisitos
## Requisitos obrigatórios
- [ ] Autenticação via Github
- [ ] Listar pessoas que o usuário segue
- [ ] Listar repositórios das pessoas que o usuário segue
- [ ] Listar repositórios do usuário autenticado
- [ ] Criar um novo repositório
- [ ] Exibir dados do usuário
- [ ] Utilizar paginação (scroll infinito ou não)
- [ ] Funcionamento offline (manter dados em storage local)
- [ ] Testes unitários
- [ ] Seguimentação de commits

## Bônus
- [ ] Testes instrumentados
- [ ] Integração com Firebase (Crashlytics)
- [ ] CI/CD (pipelines e deploy)
- [ ] Responsividade (celular e tablet)
- [ ] Commits semânticos

  A solução do desafio deve seguir a interface proposta acima, e isso também será critério de avaliação.

## Github API

O desafio deverá obrigatoriamente utilizar a [API do Github](https://docs.github.com/en/rest?apiVersion=2022-11-28). Para faciltar o processo de implementação, deixaremos abaixo as rotas que serão utilizadas nos casos de uso:

- Listar pessoas que o usuário segue
    
    [Followers - GitHub Docs](https://docs.github.com/en/rest/users/followers?apiVersion=2022-11-28#list-the-people-a-user-follows)
    
- Listar os repositórios dessas pessoas
    
    [Repositories - GitHub Docs](https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-repositories-for-a-user)
    
- Listar os repositórios do usuário
    
    [Repositories - GitHub Docs](https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-repositories-for-the-authenticated-user)
    
- Criar um novo repositório
    
    [Repositories - GitHub Docs](https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#create-a-repository-for-the-authenticated-user)
    
- Exibir os dados do usuário
    
    [Users - GitHub Docs](https://docs.github.com/en/rest/users/users?apiVersion=2022-11-28#get-the-authenticated-user)
    

# TL;DR

1. A arquitetura fica a seu critério, mas serão avaliados os fatores: manutenabilidade, escalabilidade e desempenho.
2. É obrigatório o uso do [Android Nativo](https://developer.android.com/?hl=pt-br) para a resolução desse pro2. blema.
3. Escreva um README descrevendo o passo a passo de como executar a aplicação e o que é necessário para tal.
4. Deixe claro na documentação (README) as escolhas utilizadas, ao que tange tecnologia e padrões arquiteturais aplicados na resolução.    

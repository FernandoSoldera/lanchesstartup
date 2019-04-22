# lanchesstartup

Aplicação web para gerir uma lanchonete.

# Relatório design do código

 - Como a aplicação não possui nenhuma persistência de dados, utilizei uma classe de constantes para salvar os preços dos ingredientes. Essa classe também é uma solução para os problemas de inflação (preços mudam com uma frequência alta), pois todo o código utiliza essas constantes nos cálculos, inclusive os testes.
 - Eu deixei os lanches e os ingredientes sendo criados totalmente no backend, fazendo com que o frontend fique genérico, e apenas coloque na tela as informações que foram passadas pelo backend. Este modelo facilita uma expansão do projeto, onde basta adicionar um lanche ou um ingrediente apenas uma vez no backend, e o frontend irá adicionar nas respectivas listas.
 - Optei por WebSocket para a comunicação do frontend com o backend ao invés de uma API Rest. A opção foi tomada porque, a Websocket trabalha melhor com aplicações que necessitam que o frontend e o backend, faça requisições um para o outro. A implementação atual não possui necessidade do backend fazer requisições para o frontend, porém, em uma possível expansão pode ser que precise de algo do tipo (por exemplo o backend ter que informar ao frontend que algum ingrediente está indisponível no momento).
 - Todas as regras de negócios são calculadas em tempo real no backend, o que deixa a aplicação mais consistente.
 - No backend utilizei uma lista synchronized para o carrinho. Esta lista consegue previnir possíveis problemas de concorrência, que possam ter com requisições simultâneas.

# Tecnologias

 - HTML
 - CSS
 - JavaScript
 - Java 8
 - Mavenloca
 - Spring Boot
 - Docker
 - Junit
 
# Rodar imagem do docker

 - Instalar o Docker
 - Abrir o console ou terminal e rodar o comando "docker run -p 8080:8080 fernandosoldera/lanchesstartup:1.0"
 - Abrir o navegador na url "locahost:8080"

# Importar e buildar o projeto

 - Baixar e instalar o Maven https://maven.apache.org/download.cgi
 - Instalar o Docker
 - Baixar o projeto "git clone https://github.com/FernandoSoldera/lanchesstartup.git"
 - Importar projeto no eclipse (File > Import > Existing Maven Projec), procurar a pasta que foi clonado o projeto.
 - Rodar o projeto (RUN as -> Spring Boot App), caso sua IDE não possua esse run, basta instalar o plugin do Spring Boot.
 - Para gerar uma nova versão do projeto rodar o comando "mvn clean package" na raíz do projeto. Quando gerar essa versão os testes serão automaticamente rodados. Também pode rodar os testes manualmente através do (RUN as -> Junit tests) na classe LanchesStartupApplicationTests.java.
 - Ao gerar uma nova versão pode se criar uma imagem docker com o comando (mvn docker:build) na raíz do projeto. Criando uma imagem com o nome de fernandosoldera/lanchesstartup.

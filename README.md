**ESTÓRIAS DE USUÁRIO**
- US1 - Eu como usuario do sistema, gostaria de cadastrar, atualizar, listar e deletar um usuario (user) no sistema informando os campos: firstName, lastName, email, birthday, login, password, phone, cars.
- US2 - Eu como usuario do sistema, gostaria de cadastrar, atualizar, listar e deletar um carro (car)  no sistema informando os campos: year, licensePlate, model, color.
- US3 - Eu como usuario do sistema, desejo me autenticar no sistema informando meu login e senha.
- US4 - Eu como usuario do sistema gostaria que fosse contabilizado sempre que um carro fosse utilizado
- US5 - Eu como usuario do sistema gostaria que ao listar todos os meus carros, fosse devolvida a lista ordenada de forma decrescente pela quantidade de uso do carro. 

**SOLUÇÃO**
- O projeto utiliza SpringBoot, com Banco em memoria (H2) e flyway. 
- O flyway foi adotado para possibilitar a criação de tabelas, constraints e auxiliar na captura mais precisa das mensagens de erro do hibernate.

**EXECUÇÃO**
- Após baixar o projeto, acessa a pasta do mesmo via terminal e execute: **mvn clean install -U**
- Após conclusão do passo anterior execute: **mvn spring-boot:run**
- Após o serviço ser inicializado por completo, você poderá acessar o **Swagger** em http://localhost:8080/swagger-ui/index.html

# Academia_REST_Dio
Projeto de uma API REST no domínio de uma Academia. O projeto é um desafio de código do bootcamp Carrefour FullStack da Digital Innovation One.

Neste projeto, implementei uma API REST no domínio de uma Academia de Ginástica.
Utilizei os recursos Spring Boot com JPA, Hibernate, Lombok, DevTools, Validation, H2 e PostgreSQL.
O projeto foi criado no padrão MVC, aplicando principios SOLID.

PACKAGE CONFIG
Criei uma classe com uma carga inicial para algumas tabelas do banco de dados.
Essa classe só será executada quando o profile ativo no arquivo application.yml for "test".

PACKAGE MODELS
Nele, temos as entidades do nosso negócio (Aluno, Avaliações, Instrutores, Modalidades, Turmas e Matrículas).
Também implementei o padrão DTO para as entidades.

PACKAGE REPOSITORIES
Contém as interfaces de persistência, extendendo de JPA Repository.

PACKAGE SERVICES
Estão as interfaces com os métodos detentores das operações a serem realizadas quando o a camada de Controllers solicitar.
 - Package services.impl
   . Contém as classes que implementam as interfaces de serviços.
 - Package services.exceptions
   . Neste pacote, implementei a classe StandardError, para montar uma resposta no corpo da requisição quando ocorrer determinadas exceptions;
   . Também criei a classe ObjectNotFoundException que implememta RuntimeExceptions, para quando o objeto solicitado na requisição não for encontrado.
   . Por fim, a classe ResourceExceptionHandler, anotada com @ControllerAdvice(responsável por interceptar determinadas exceções para que o objeto execute um possível tratamento).
   Seu método recebe como parametros: a exceção personalizada (StandardError) e um objeto HttpServelRequest. A anotação @ExceptionHandler(ObjectNotFoundException.class) captura a exceção informada como parametro para que o método seja executado.
   Recebida a exception, o método monta uma resposta para devolver à requisição.
   
PACKAGE CONTROLLERS
Classes que enviam as solicitações para a camada de serviços, que validam as regras de negócio e as enviam para a camada de repositories, para realizar as operações com banco de dados.

Entre as regras de negócio, destaque para:
  - Um aluno não pode ser matriculado em turmas que não existem, ou em turmas nas quais já está matriculado, ou quando há conflito de horários entre as turmas atuais e a nova;
  - Seguindo o raciocínio, os instrutores também não podem estar em conflito de horários ou ser cadastrados em turmas que não existem.

Obrigado! 

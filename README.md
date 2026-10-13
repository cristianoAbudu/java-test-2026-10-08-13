# Teste técnico Java EE — 30 minutos

Complete a ação **Marcar como analisada** na listagem de solicitações de benefício.

1. Adicione um botão PrimeFaces na coluna Action de `index.xhtml`.
2. O botão deve chamar um método do bean CDI `BenefitRequestBean`.
3. O bean deve chamar o EJB `BenefitRequestService.markReviewed(id)` e recarregar a lista.
4. Complete o EJB para buscar a entidade via JPA e atualizar `status` para `REVIEWED`.

Tecnologias presentes: Java EE 8, JSF, PrimeFaces, CDI, EJB, JPA/Hibernate, SQL e Java Swing.

A aplicação web é um WAR para WildFly 26 / Java 11. Configure a fonte JNDI `java:jboss/datasources/BenefitDS`, execute `database/schema.sql` no banco e faça deploy de `web-app/target/benefit-web.war`.

Para abrir o exemplo Swing:
```bash
cd swing-client
javac BenefitDesktop.java
java BenefitDesktop
```

Solucao:

1. Criado docker file para instalar o wildfly e postgres
2. Configurado datasource com os seguintes comandos:
```bash
docker exec -it wildfly-server \
  /opt/jboss/wildfly/bin/jboss-cli.sh --connect
  
/subsystem=datasources:installed-drivers-list

data-source add --name=BenefitDS --jndi-name=java:jboss/datasources/BenefitDS --driver-name=postgresql.jar --connection-url=jdbc:postgresql://postgres:5432/appdb --user-name=appuser --password=apppassword

/subsystem=datasources/data-source=BenefitDS:enable
/subsystem=datasources/data-source=BenefitDS:test-connection-in-pool

```

3. Publicar aplicação no wildfly
```bash
docker cp target/benefit-web.war wildfly-server:/opt/jboss/wildfly/standalone/deployments/
```

## Implementação da solução

- Corrigidos os bindings JSF de `requeste` para `request` na tabela.
- Adicionado botão PrimeFaces que chama `benefitRequestBean.markReviewed(request.id)` e atualiza a tabela via AJAX.
- O botão deixa de aparecer após a solicitação atingir `REVIEWED`.
- Implementada a ação no bean CDI, que delega ao EJB e recarrega a listagem.
- Implementada a busca por ID com `EntityManager.find`; IDs nulos e registros inexistentes são tratados com exceções explícitas.
- A alteração de status é persistida pelo contexto JPA gerenciado na transação do EJB.

##Pado challenge

**Instruções para execução da aplicação**

1) Com o projeto aberto e o Docker aberto, inicie o docker-compose.yml para gerar o container do RabbitMQ.
`docker-compose up`

2) Inicie a aplicação.

O banco de dados pode ser acessado no console do H2: **http://localhost:8080/h2-console/**
Para acessar a interface do RabbitMQ: **http://localhost:15672**
O projeto possui integração com o Swagger e os endpoints podem ser visualizados em: **http://localhost:8080/swagger-ui.html**

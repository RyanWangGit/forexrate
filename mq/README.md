**GETTING IMAGE FROM DOCKER HUB**

`docker pull rabbitmq:3.8-management`

**RUN RABBITMQ IN DOCKER**

`docker run -d -p 15672:15672 -p 5672:5672 --name rabbitmq --hostname=rabbitmq rabbitmq:3.6-management`

**ACCESS RABBITMQ MANAGEMENT**

`http://192.168.99.100:15672`

username/password: guest/guest
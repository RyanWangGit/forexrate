This project is include 3 modules:

1. Fetching-service: this service monitors the FOREX rate from eodhistoricaldata.com, if the rate changes that user subscribed got any change, then send message to RabbitMQ.

2. MQ: this module is RabbitMQ, we can get image from docker hub.

3. Sending-service: this service is message consumer that monitors RqbbitMQ, then send mail to customer about FX rate changes.

**Fectching-service:**
- This service get the rate data from every minute, this i a schedule job, and can be change execution time in a application.properties

- This module will save the last time rate in the cache, so it can be compare with new rate, if they are not same, then service send new value to MQ.

- The api_token, fetching data link and FX currency can be edit in properties file. the fetching link can be store in cache，in order to avoid of assembling the link every time.

**Sending-service**

- this module get the topic message from MQ and send email to customer with attached csv file.

- The customer email address can be e edit in properties file and smtp, mail account, password can also be modified in application properties file.

- Logs and  attached file are located outside of application jar, they are easy to be viewed and modified.

**Docker command**

1. For fetching-srevice

`docker run -d  -v /Users/Ryan/Devops/docker/fetching-service/logs:/fetching-service/logs  -v /Users/Ryan/Devops/docker/fetching-service/config:/fetching-service/config --name fetching-service --hostname=fetching-service fetching-service:latest`

2. For sending-service

`docker run -d  -v /Users/Ryan/Devops/docker/sending-service/logs:/sending-service/logs  -v /Users/Ryan/Devops/docker/sending-service/config:/sending-service/config -v /Users/Ryan/Devops/docker/sending-service/output:/sending-service/output --name sending-service --hostname=sending-service sending-service:latest`


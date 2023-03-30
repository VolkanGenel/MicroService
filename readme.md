## RabbitMQ Docker
    docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=root rabbitmq:3-management

## Docker Image Oluşturmak
        docker build --build-arg JAR_FILE=ConfigServerGit/build/libs/ConfigServerGit-v.0.1.jar -t volkangenel/java6configservergit:v.0.1 .

    !!!! DİKKAT !!!! Eğer M1 Mac cihazı kullanıyorsanız
    docker build --platform linux/amd64 --build-arg JAR_FILE=ConfigServerGit/build/libs/ConfigServerGit-v.0.1.jar -t volkangenel/java6configservergit:v.0.1 .

        docker build --build-arg JAR_FILE=AuthMicroService/build/libs/AuthMicroService-v.0.1.jar -t volkangenel/java6configservergit:v.0.1 .

      !!!! DİKKAT !!!! Eğer M1 Mac cihazı kullanıyorsanız
    docker build --platform linux/amd64 --build-arg JAR_FILE=AuthMicroService/build/libs/AuthMicroService-v.0.1.jar -t volkangenel/java6authmicroservice:v.0.1 .
    
    docker build --platform linux/amd64 --build-arg JAR_FILE=UserProfileMicroService/build/libs/UserProfileMicroService-v.0.1.jar -t volkangenel/java6usermicroservice:v.0.1 .

    docker build --platform linux/amd64 --build-arg JAR_FILE=ApiGateWayService/build/libs/ApiGateWayService-v.0.1.jar -t volkangenel/java6gateway:v.0.1 .
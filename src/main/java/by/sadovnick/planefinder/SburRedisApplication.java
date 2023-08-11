package by.sadovnick.planefinder;

import by.sadovnick.planefinder.model.Aircraft;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class SburRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SburRedisApplication.class, args);
    }

    @Bean
    public RedisOperations<String, Aircraft> redisOperations(RedisConnectionFactory factory) {
        //Serializer для преобразования между объектами и записями
        //JSON. Поскольку для маршалинга/демаршалинга (сериализации/десериализации) значений JSON
        // применяется Jackson, уже включенный в вебприложения Spring Boot, я создал
        // Jackson2JsonRedisSerializer для объектов типа Aircraft.
        Jackson2JsonRedisSerializer<Aircraft> serializer = new Jackson2JsonRedisSerializer<>(Aircraft.class);
        //Объект RedisTemplate, принимающий ключи типа String и значения типа Aircraft, для работы с
        // получаемым на входе объектом Aircraft с идентификаторами типа String.
        RedisTemplate<String, Aircraft> template = new RedisTemplate<>();
        //позволяет создавать и получать соединение с базой данных Redis.
        template.setConnectionFactory(factory);
        //default сериализатор для Aircraft
        template.setDefaultSerializer(serializer);
        //сериализатор для ключей.
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }

}

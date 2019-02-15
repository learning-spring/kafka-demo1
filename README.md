# kafka-demo1
[Github](https://github.com/learning-spring/kafka-demo1)

### This demo is to learning how to intergrated Kafka with springboot
[Kafka Start with sprint boot](https://docs.spring.io/spring-kafka/docs/current/reference/html/_introduction.html#_even_quicker_with_spring_boot)

##Precondition
- zookeeper available with default port 2181
- kafka available with default port 9092
- cddocker && ./kafka.sh zookeeper kafka. This docker image is from Kafka reference

###Spring boot
when configuration class with @EnableKafka annotation, it will @Import(KafkaBootstrapConfiguration.class)
<pre>
Properties
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaProperties 

@Configuration
@ConditionalOnClass(EnableKafka.class)
class KafkaAnnotationDrivenConfiguration
If with class EnableKafka, will scan Kafka Annotations

@Configuration
@ConditionalOnClass(KafkaTemplate.class)
@EnableConfigurationProperties(KafkaProperties.class)
@Import({ KafkaAnnotationDrivenConfiguration.class,
		KafkaStreamsAnnotationDrivenConfiguration.class })
public class KafkaAutoConfiguration {

</pre>
###Kafka
Install Kafka from Docker
- [Kafka Image](https://hub.docker.com/r/wurstmeister/kafka)
- [Kafka Github with doc](https://github.com/wurstmeister/kafka-docker)
- [Kafka Official Document](https://kafka.apache.org/documentation/)
- [Docker compose run](https://www.jianshu.com/p/ac03f126980e)

##Reference
* [Spring-kafka github](https://github.com/spring-projects/spring-kafka)
* [Spring formal Doc](https://docs.spring.io/spring-kafka/docs/current/reference/html/)



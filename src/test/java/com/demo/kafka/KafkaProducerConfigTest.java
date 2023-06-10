package com.demo.kafka;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {KafkaProducerConfig.class})
@ExtendWith(SpringExtension.class)
class KafkaProducerConfigTest {
  @Autowired
  private KafkaProducerConfig kafkaProducerConfig;

  /**
   * Method under test: {@link KafkaProducerConfig#producerFactory()}
   */
  @Test
  void testProducerFactory() {
    assertTrue(kafkaProducerConfig.producerFactory() instanceof DefaultKafkaProducerFactory);
  }

}


package com.github.iquee.music;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(1)
public class RabbitAmqpRunner {

    private static final boolean NON_DURABLE = false;
    public final static String FANOUT_QUEUE_TRACKS = "com.luizhtaira.fanout.tracks";
    public final static String FANOUT_QUEUE_ALBUMS = "com.luizhtaira.fanout.albums";
    public final static String FANOUT_QUEUE_ARTISTS = "com.luizhtaira.fanout.artists";
    public final static String FANOUT_EXCHANGE_MUSIC = "com.luizhtaira.fanout.exchange";

    @Bean
    @Order(1)
    public Declarables fanoutBindings() {
        Queue fanoutQueue1 = new Queue(FANOUT_QUEUE_TRACKS, NON_DURABLE);
        Queue fanoutQueue2 = new Queue(FANOUT_QUEUE_ALBUMS, NON_DURABLE);
        Queue fanoutQueue3 = new Queue(FANOUT_QUEUE_ARTISTS, NON_DURABLE);
        FanoutExchange fanoutExchange = new FanoutExchange(FANOUT_EXCHANGE_MUSIC, NON_DURABLE, false);
        return new Declarables(
                fanoutQueue1,
                fanoutQueue2,
                fanoutQueue3,
                fanoutExchange,
                BindingBuilder.bind(fanoutQueue1).to(fanoutExchange),
                BindingBuilder.bind(fanoutQueue2).to(fanoutExchange),
                BindingBuilder.bind(fanoutQueue3).to(fanoutExchange));
    }
}

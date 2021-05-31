package ru.education.reverse_telegram.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TelegramRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("telegram:bots?authorizationToken=1604975855:AAFWNq78dMMUOLRFhzG_aIMQiIV3rz4g6sU")
                .process(exchange -> {
                    String incoming = exchange.getIn().getBody(String.class);
                    StringBuilder sb = new StringBuilder(incoming);
                    exchange.getIn().setBody(sb.reverse().toString()) ;
                })
                .to("telegram:bots?authorizationToken=1604975855:AAFWNq78dMMUOLRFhzG_aIMQiIV3rz4g6sU");
    }
}

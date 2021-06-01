package ru.education.reverse_telegram.route;

import org.apache.camel.builder.DefaultErrorHandlerBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.telegram.model.IncomingMessage;
import org.springframework.stereotype.Component;

@Component
public class TelegramRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("telegram:bots?authorizationToken={{auth-key}}")
                .errorHandler(new DefaultErrorHandlerBuilder())
                .process(exchange -> {
                    IncomingMessage in = (IncomingMessage) exchange.getIn().getBody();
                    try {
                        String incoming = in.getText();
                        StringBuilder sb = new StringBuilder(incoming);
                        exchange.getIn().setBody(sb.reverse().toString()) ;
                    }catch (Exception e){
                        exchange.getIn().setBody("Wrong format incoming message");
                    }

                })
                .to("telegram:bots?authorizationToken={{auth-key}}");
    }
}

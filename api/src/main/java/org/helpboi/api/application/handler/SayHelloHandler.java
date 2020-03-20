package org.helpboi.api.application.handler;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.persistence.HelloRepository;
import org.helpboi.api.domain.model.hello.Hello;
import org.helpboi.api.application.command.SayHello;

@Singleton
public class SayHelloHandler implements CommandHandler<SayHello> {

    @Inject
    private HelloRepository helloRepository;

    @Override
    @Transactional
    public void handle(SayHello command) {
        Hello hello = new Hello(null, command.getName());
        hello = helloRepository.save(hello);
        command.resolve("Hello " + command.getName() + ", you#ve got id: " + hello.getId());
    }
}

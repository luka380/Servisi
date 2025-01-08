package com.project.prjx;

import com.project.prjx.Data.Repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRun implements CommandLineRunner {

    private final ClientRepository userRepository;

    public CommandLineRun(ClientRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}

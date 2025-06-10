package ru.job4j.gc.leak;

import ru.job4j.gc.leak.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

public class UserGenerator implements Generate {

    public static final String PATH_NAMES = "files/names.txt";
    public static final String PATH_SURNAMES = "files/surnames.txt";
    public static final String PATH_PATRONS = "files/patr.txt";

    public static final int NEW_USERS = 1000;

    public List<String> names;
    public List<String> surnames;
    public List<String> patrons;
    private final List<User> users = new ArrayList<>();
    private final Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < NEW_USERS; i++) {
            StringJoiner name = new StringJoiner(" ");
            name.add(surnames.get(random.nextInt(surnames.size())));
            name.add(names.get(random.nextInt(names.size())));
            name.add(patrons.get(random.nextInt(patrons.size())));
            var user = new User();
            user.setName(name.toString());
            users.add(user);
        }
    }

    private void readAll() {
        try {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }
}
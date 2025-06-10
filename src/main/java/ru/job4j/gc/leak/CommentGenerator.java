package ru.job4j.gc.leak;

import ru.job4j.gc.leak.models.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {

    public final String pathPHRASES = "files/phrases.txt";

    public final String separator = System.lineSeparator();
    private final List<Comment> comments = new ArrayList<>();
    public final Integer count = 50;
    private List<String> phrases;
    private final UserGenerator userGenerator;
    private final Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(pathPHRASES);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        for (int i = 0; i < count; i++) {
            String text = String.format("%s%s%s%s%s",
                    phrases.get(random.nextInt(phrases.size())), separator,
                    phrases.get(random.nextInt(phrases.size())), separator,
                    phrases.get(random.nextInt(phrases.size())));
            var comment = new Comment();
            comment.setText(text);
            comment.setUser(userGenerator.randomUser());
            comments.add(comment);
        }
    }
}
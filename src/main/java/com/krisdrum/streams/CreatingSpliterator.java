package com.krisdrum.streams;


import com.krisdrum.streams.model.Person;
import com.krisdrum.streams.spliterator.PersonSpliterator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CreatingSpliterator {
    public static void main(String[] args){
        URL resource = CreatingSpliterator.class.getResource("/people.txt");

        Path path = null;
        try {
            path = Paths.get(resource.toURI());

            try(Stream<String> lines = Files.lines(path);) {
                Spliterator<String> lineSpliterator = lines.spliterator();
                Spliterator<Person> peopleSpliterator = new PersonSpliterator(lineSpliterator);

                Stream<Person> peopleStream = StreamSupport.stream(peopleSpliterator, false);
                peopleStream.forEach(System.out::println);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        System.out.println(path.toFile().exists());


    }
}

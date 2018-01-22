package com.krisdrum.streams.flatMap;

import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Stream<String> s1 = Stream.generate(()-> "jedza pija lulki pala");
        Stream<String> s2 = Stream.generate(()-> "tance hulanki swawole");
        Stream<String> s3 = Stream.generate(()-> "ledwo karczmy nie rozwala");


        // flattened stream of streams:
        Stream<String> streamOfStreams = Stream.of(s1, s2, s3)
                .flatMap(Function.identity());


        // flatmap string into stream of words:
        Function<String, Stream<String>> splitIntoWords =
                line -> Pattern.compile(" ").splitAsStream(line);

        Stream<String> words = Stream.of(s1, s2, s3)
                .flatMap(Function.identity())
                .flatMap(splitIntoWords);
    }
}

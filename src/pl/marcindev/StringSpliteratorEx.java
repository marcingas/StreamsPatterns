package pl.marcindev;

import java.util.Spliterator;
import java.util.stream.StreamSupport;

public class StringSpliteratorEx {
    public static void main(String[] args) {
        String sentence = "Hello World!";
        Spliterator<Character>stringSpliterator = sentence.chars()
                .mapToObj(ch->(char)ch)
                .spliterator();

        int [] countLetter = processStringWithSpliterator(stringSpliterator);
    for(int i =0; i < countLetter.length; i++){
        char letter = (char) ('A'+ i);
        System.out.println(letter + ": " + countLetter[i]);
    }
    }

    private static int[] processStringWithSpliterator(Spliterator<Character> spliterator) {
    return StreamSupport.stream(spliterator,false)
            .filter(Character::isLetter)
            .map(Character::toUpperCase)
            .mapToInt(ch->ch-'A')
            .collect(
                    ()-> new int[26],
                    (count,index)->count[index]++,
                    (count1, count2)->{
                        for(int i =0; i< 26; i++){
                            count1[i]+= count2[i];
                        }
                    }
            );
    }

}

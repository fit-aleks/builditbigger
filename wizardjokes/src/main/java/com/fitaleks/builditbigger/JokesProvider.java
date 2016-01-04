package com.fitaleks.builditbigger;

import java.util.Random;

public class JokesProvider {
    private static final String[] JOKES = new String[]{
            "Q: Where's Spiderman's home page?\nA: On the world wide web.",
            "Two chemists walk into a bar.\nThe first says, \"I'll have some H2O.\"\nThe second says \"I'll have some H2O too.\"\nThe second one dies.",
            "There are 10 kinds of people. Those who understand binary. And those who don't.",
            "How many theoretical physicists does it take to screw in a light bulb? Two. One to hold the bulb and one to rotate the Universe.",
            "My software never has bugs. It just develops random features.",
            "An infinite crowd of mathematicians enters a bar. The first one orders a pint, the second one a half pint, the third one a quarter pint… \"I understand\", says the bartender – and pours two pints.",
            "SODIUM SODIUM SODIUM SODIUM SODIUM SODIUM SODIUM SODIUM BATMAN!",
            "Schrödinger cat walks into a bar and doesn't",
            "The first rule of tautology club is the first rule of tautology club.",
            "Most people believe that if it ain't broke, don't fix it.\n" +
                    "Engineers believe that if it ain't broke, it doesn't have enough features yet."
    };

    private static final String[] JOKES_IMGS = new String[] {
            "http://img1.joyreactor.com/pics/post/geek-android-terminator-expectation-vs-reality-269949.jpeg",
            "http://i.kinja-img.com/gawker-media/image/upload/s--DCQhoUS4--/18wld88c8vzc2png.png",
            "https://qph.is.quoracdn.net/main-qimg-e0c9dafb319150b6c6d9816047ed9eae?convert_to_webp=true",
            "http://blog.protectedstatic.com/wp-content/uploads/2007/05/pointers.png",
            "http://i.imgur.com/gh2H9id.jpg",
            "http://favo.s3.amazonaws.com/if-programming-languages-were-essays.jpg",
            "http://i.imgur.com/B1EJLRS.jpg"
    };

    private static final Random random = new Random();

    public static String getJoke() {
        return JOKES[random.nextInt(JOKES.length)];
    }

    public static String getImgJoke() {
        return JOKES_IMGS[random.nextInt(JOKES_IMGS.length)];
    }
}

package com.fitaleks.builditbigger;

import java.util.Random;

public class JokesProvider {
    private static final String[] JOKES = new String[]{
            "Q: Where's Spiderman's home page?\nA: On the world wide web.",
            "Two chemists walk into a bar. The first says, \"I'll have some H2O.\" The second says \"I'll have some H2O too.\" The second one dies.",
            "There are 10 kinds of people. Those who understand binary. And those who don't.",
            "How many theoretical physicists does it take to screw in a light bulb? Two. One to hold the bulb and one to rotate the Universe.",
            "My software never has bugs. It just develops random features.",
            "An infinite crowd of mathematicians enters a bar. The first one orders a pint, the second one a half pint, the third one a quarter pint… \"I understand\", says the bartender – and pours two pints.",
            "SODIUM SODIUM SODIUM SODIUM SODIUM SODIUM SODIUM SODIUM BATMAN!",
            "Schrödinger cat walks into a bar and doesn't",
            "The first rule of tautology club is the first rule of tautology club."
    };

    private static final Random random = new Random();

    public static String getJoke() {
        return JOKES[random.nextInt(JOKES.length)];
    }
}

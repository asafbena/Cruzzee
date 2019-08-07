package cruzzee;

import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class Constants {
    private Constants(){

    }

    enum Dangers {
        PIRATE,
        ATTACK
    }

    final static Map<Dangers, List<String>> dangersKeyWords = ImmutableMap.of(
            Dangers.PIRATE, Arrays.asList("pirate", "attack", "robb", "abduct", "kidnap", "seize", "criminal", "terror", "hostage"),
            Dangers.ATTACK, Arrays.asList("army", "guard", "attack", "seize", "sink", "hostage")
    );

    final static Map<Dangers, String> dangersSearchQuery = ImmutableMap.of(
            Dangers.PIRATE, "+pirate+attack",
            Dangers.ATTACK, "+attack"
    );
}
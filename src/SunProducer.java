interface SunProducer {
    int produce_sun();
}

interface Attacker {
    //rangetype
    // 1 - single line
    // 2 - an area-of-effect (AOE)
    // 3 - on a limited range only
    // 4 - on a free range

    int attack();
    int rangeType();
}

interface InstantKiller {
    // 1 - instantly
    // 2 - close contact

    //Note that all InstantKiller has an infinite HP and
    //when an InstantKiller attacks, it dies right after attacking.

    int killType();
}

interface Upgradeable {
    PlantUpgrade upgrade();
}


interface PlantUpgrade {
    int concurrentSunCost();
}
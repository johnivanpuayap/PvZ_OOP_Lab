import java.util.Comparator;

public abstract class Plant implements Comparable<Plant> {
    public static final int INFINITE = Integer.MAX_VALUE;

    String name;
    int hp;
    int sun_cost;

    public Plant(String name, int sun_cost) {
        this.name = name;
        this.hp = 6;
        this.sun_cost = sun_cost;
    }

    public Plant(String name, int hp, int sun_cost) {
        this.name = name;
        this.hp = hp;
        this.sun_cost = sun_cost;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public String die() {
        return name + " dies";
    }

    @Override
    public String toString() {
        if(hp == INFINITE) {
            return name + " (∞) " + "- cost: " + sun_cost;
        }
        return name + " (" + hp + ") " + "- cost: " + sun_cost;
    }

    @Override
    public int compareTo(Plant p) {
        return this.name.compareTo(p.name);
    }

    public static class SunCostComparator implements Comparator<Plant> {
        @Override
        public int compare(Plant p1, Plant p2) {
            return Integer.compare(p2.sun_cost, p1.sun_cost);
        }
    }

    public static class HpComparator implements Comparator<Plant> {
        public int compare(Plant p1, Plant p2) {
            return Integer.compare(p2.hp, p1.hp);
        }
    }

    // Add Plant subclasses here, and
    // Hint: You can also add Comparator inner classes here
    // WallNut and CoffeeBean given for free
    public static class WallNut extends Plant{
        public WallNut() {
            super("Wall Nut", 25, 50);
        }
    }

    public static class CoffeeBean extends Plant{
        public CoffeeBean() {
            super("Coffee Bean", INFINITE, 75);
        }
    }

    public static class Sunflower extends Plant implements Upgradeable, SunProducer {

        public Sunflower() {
            super("Sunflower", 50);
        }


        @Override
        public PlantUpgrade upgrade() {
            return new Plant.TwinSunflower();
        }

        @Override
        public int produce_sun() {
            System.out.println(name + " produces 25 suns");
            return 25;
        }
    }

    public static class TwinSunflower extends Plant implements SunProducer, PlantUpgrade{
        public TwinSunflower() {
            super("Twin Sunflower", 50);
        }

        @Override
        public int concurrentSunCost() {
            return 50;
        }

        @Override
        public int produce_sun() {
            System.out.println(name + " produces 50 suns");
            return 50;
        }
    }

    public static class Peashooter extends Plant implements Attacker {
        public Peashooter() {
            super("Peashooter", 100);
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            return 1;
        }

        @Override
        public int rangeType() {
            return 1;
        }
    }

    public static class Squash extends Plant implements Attacker, InstantKiller {

        public Squash() {
            super("Squash", INFINITE, 50);
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            return 3;
        }

        @Override
        public int rangeType() {
            return 3;
        }

        @Override
        public int killType() {
            return 2;
        }

        @Override
        public String die() {
            return name + " dies while squashing zombies";
        }
    }

    public static class Jalapeno extends Plant implements Attacker, InstantKiller {

        public Jalapeno() {
            super("Jalapeno", INFINITE,125);
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            return 5;
        }

        @Override
        public int rangeType() {
            return 1;
        }

        @Override
        public int killType() {
            return 1;
        }

        @Override
        public String die() {
            return name + " dies while exploding";
        }
    }

    public static class LilyPad extends Plant implements Upgradeable {
        public LilyPad() {
            super("Lily Pad", 25);
        }

        @Override
        public PlantUpgrade upgrade() {
            return new Plant.CatTail();
        }
    }

    public static class CatTail extends Plant implements PlantUpgrade, Attacker{

        public CatTail() {
            super("Cattail", 225);
        }

        @Override
        public int concurrentSunCost() {
            return 25;
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            return 1;
        }

        @Override
        public int rangeType() {
            return 4;
        }
    }
}
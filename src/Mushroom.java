public abstract class Mushroom extends Plant{
    boolean state;
    public Mushroom(String name, int sun_cost, boolean state) {
        super(name, sun_cost);
        this.state = state;
    }

    public Mushroom(String name, int hp, int sun_cost, boolean state) {
        super(name, hp, sun_cost);
        this.state = state;
    }

    public boolean isAwake() {
        return state;
    }

    public void awaken(CoffeeBean coffeeBean) {
        state = true;
        System.out.println(coffeeBean.die());
    }

    // an example given for free
    public static class SunShroom extends Mushroom implements SunProducer {

        public SunShroom(boolean state) {
            super("Sun-shroom", 25, state);
        }

        @Override
        public int produce_sun() {
            if (isAwake()) {
                System.out.println(name + " produces 10 suns");
                return 10;
            }
            System.out.println(name + " is asleep and cannot produce sun");
            return 0;
        }
    }

    // Add more mushroom subclasses here
    public static class PuffShroom extends Mushroom implements Attacker {
        public PuffShroom(boolean state) {
            super("Puff-shroom", 0, state);
        }

        @Override
        public int attack() {
            if(state) {
                System.out.println(name + " attacks");
                return 1;
            }
            System.out.println(name + " is asleep and cannot attack");
            return 0;
        }

        @Override
        public int rangeType() {
            return 3;
        }
    }

    public static class DoomShroom extends Mushroom implements InstantKiller, Attacker {
        public DoomShroom(boolean state) {
            super("Doom-shroom", INFINITE, 125, state);
            if(!state) {
                super.hp = 6;
            }
        }

        @Override
        public int attack() {
            if(state) {
                System.out.println(name + " attacks");
                return 10;
            }
            System.out.println(name + " is asleep and cannot attack");
            return 0;
        }

        @Override
        public String die() {
            return name + " dies while exploding and leaves a crater";
        }

        @Override
        public int rangeType() {
            return 2;
        }

        @Override
        public int killType() {
            return 1;
        }
    }
}
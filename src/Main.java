import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Plant> plants = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Game Mode: ");
        String mode = sc.nextLine();

        boolean state = mode.equals("Fog") || mode.equals("Night");

        String input;
        do {
            System.out.print("Add a plant: ");
            input = sc.nextLine();
            switch (input) {
                case "DONE":
                    break;
                case "Sunflower":
                    plants.add(new Plant.Sunflower());
                    break;
                case "Peashooter":
                    plants.add(new Plant.Peashooter());
                    break;
                case "Wall Nut":
                    plants.add(new Plant.WallNut());
                    break;
                case "Lily Pad":
                    plants.add(new Plant.LilyPad());
                    break;
                case "Jalapeno":
                    plants.add(new Plant.Jalapeno());
                    break;
                case "Squash":
                    plants.add(new Plant.Squash());
                    break;
                case "Coffee Bean":
                    Plant.CoffeeBean coffeeBean = new Plant.CoffeeBean();
                    for(Plant p : plants) {
                        if(p instanceof Mushroom) {
                            if(!((Mushroom) p).state) {
                                ((Mushroom) p).awaken(coffeeBean);
                                if(p instanceof Mushroom.DoomShroom) {
                                    p.hp = Integer.MAX_VALUE;
                                }
                                break;
                            }
                        }
                    }
                    break;
                case "Twin Sunflower":
                    int i = 0;
                    for(Plant p : plants) {
                        if (p instanceof Plant.Sunflower) {
                            plants.set(i, (Plant) ((Plant.Sunflower) p).upgrade());
                            break;
                        }
                        i++;
                    }

                    break;
                case "Cattail":
                    i = 0;
                    for(Plant p : plants) {
                        if (p instanceof Plant.LilyPad) {
                            plants.set(i, (Plant) ((Plant.LilyPad) p).upgrade());
                            break;
                        }
                        i++;
                    }

                    break;
                case "Sun-shroom":
                    plants.add(new Mushroom.SunShroom(state));
                    break;
                case "Puff-shroom":
                    plants.add(new Mushroom.PuffShroom(state));
                    break;
                case "Doom-shroom":
                    plants.add(new Mushroom.DoomShroom(state));
                    break;
                default:
                    System.out.println(input + " is not a plant");
            }
        } while (!input.equals("DONE"));

        do {
            System.out.print("Do something: ");
            input = sc.nextLine();
            switch (input) {
                case "DONE":
                    break;
                case "Produce Sun":
                    int n = 0;
                    int x = 0;
                    for(Plant p : plants) {
                        if(p instanceof SunProducer) {
                            n++;
                            x += ((SunProducer) p).produce_sun();
                        }
                    }
                    if(n > 1) {
                        System.out.println(n + " sun producers gather " + x + " suns");
                        break;
                    }
                    System.out.println("You have no sun producers");
                    break;
                case "Attack":
                    n = 0;
                    x = 0;
                    for(Plant p : plants) {
                        if(p instanceof Attacker && p.isAlive()) {
                            n++;
                            int attack = ((Attacker) p).attack();
                            x += attack;
                            if(p instanceof InstantKiller) {
                                if (attack > 0) {
                                    System.out.println(p.die());
                                    p.hp = 0;
                                }
                            }
                        }
                    }
                    System.out.println(n + " attackers dealing " + x + " damage");
                    break;
                case "Instant Kill Status" :
                    n = 0;
                    for(Plant p : plants) {
                        if(p instanceof InstantKiller && p.isAlive()) {
                            int type = ((InstantKiller) p).killType();
                            if(type == 1) {
                                System.out.println(p.name + " can kill instantly");
                            }
                            if(type == 2) {
                                System.out.println(p.name + " can kill on contact");
                            }
                            n++;
                        }
                    }
                    if(n == 0) {
                        System.out.println("You have no plants which can kill instantly");
                    }
                    break;
                case "Attacker Status" :
                    for (Plant p : plants) {
                        if(p instanceof Attacker && p.isAlive()) {
                            int range = ((Attacker) p).rangeType();
                            switch (range) {
                                case 1 -> System.out.println(p.name + " can attack on a single line");
                                case 2 -> System.out.println(p.name + " can attack using area-of-effect");
                                case 3 -> System.out.println(p.name + " can attack only when enemy is nearby");
                                case 4 -> System.out.println(p.name + " can attack any enemies from anywhere");
                            }

                            /*switch (range) {
                                case 1:
                                    System.out.println(p.name + " can attack on a single line");
                                    break;
                                case 2:
                                    System.out.println(p.name + " can attack using area-of-effect");
                                    break;
                                case 3:
                                    System.out.println(p.name + " can attack only when enemy is nearby");
                                    break;
                                case 4:
                                    System.out.println(p.name + " can attack any enemies from anywhere");
                                    break;
                            }*/
                        }
                    }
                    break;
                case "Sort by HP" :
                    Collections.sort(plants);
                    plants.sort(new Plant.HpComparator());
                    for (Plant p : plants) {
                        System.out.println(p);
                    }
                    break;
                case "Sort by Name" :
                    Collections.sort(plants);
                    for(Plant p : plants) {
                        System.out.println(p);
                    }
                    break;
                case "Sort by Sun Cost" :
                    Collections.sort(plants);
                    plants.sort(new Plant.SunCostComparator());
                    for (Plant p : plants) {
                        System.out.println(p);
                    }
                    break;
                default:
                    System.out.println("Unknown action: " + input);
            }
        } while (!input.equals("DONE"));
    }
}
import java.util.Random;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * This class represents a Server object.
 * @author Alex Loomis
 * @version 1.0
*/

public class Server {

    private static Random rand = new Random();

    private static Person[] people = {
        new Person("Alex Loomis", "alex.v.loomis@gatech.edu"),
        new Person("Henry Peteet", "hvpeteet@gatech.edu"),
        new Person("Harry Potter", "hpotter@gatech.edu"),
        new Person("Eddard Stark", "estark@gatech.edu"),
        new Person("Nausicaa Ofthevalleyofthewind",
            "nofthevalleyofthewind@gatech.edu"),
        new Person("Eragon Silverpalm", "esilverpalm@gatech.edu")
    };
    private static String[] subjects = {
        "Trying out a new trick",
        "Check out this awesome not spam website",
        "You're our 100th visitor!",
        "Make sure you don't forget this!",
        "This is important and not spam. Please open"

    };
    private static String[] messageBodies = {
        "To die, thus more; for in there's deat we end sweary life; "
            + "for insolence of outrave, that dreams and sweat with and "
            + "the opprespect to sleep; not of? To be, and the law's we kn "
            + "nobler bear thers things of action: whips a bare bourn not  "
            + "long, thus ressor's than fly to, 'tis sicklied o'er a sle; t "
            + "nobler in those there's the pative us forthy to sleep; tore "
            + "them? Thers there's the wish'd. To die, them? Thus more; and"
            + "or whethe under beary from whips and make count",
        "PESTO CRUST PIZZA. Call now call now and garlicious crust is knee "
            + "just from a large 1-topping our new PESTO CRUST PIZZA. Cl "
            + "zesto is kneaded dough and autherbs and authentic Handmaded "
            + "and garlicious created into Crust is kneaded topping Pesto Ct"
            + "parsley and basil, parsley and Tossed off with herbs a large "
            + "our new PESTO CRUST PIZZA. Call Domino's a zesto Crust sen. "
            + "The newesto is crust from that you. Introducing our new PES "
            + "PIZZA. Call now and you. In",
        "Let us choose the work done by the kineticle. A constant force of "
            + "the kineticle in causing a displacement x? We have, acting "
            + "constate the particle is for constate the resent x? We may th"
            + "work done-half the work done in the x-axis way: The relations"
            + "constant kineticle. A constate the kineticle of the work done"
            + "V - v ) t = m a x = ½ ( V - v ) t. Here v ) t = F and at time"
            + "kineticle. A constant kinetic energy of a constate the may th"
            + "and V - v ) t ) ( V is W = ",
        "The high levels of perfor desting at won't based as are demanage, "
            + "and engage, company. Integical to und new years of our future"
            + "a valuable have full in to company's markedly. Integical stra"
            + "at effective company's companies: People timal stration a ke "
            + "of company. World-classume res, and practices ared values hav"
            + "recognize the company have a few years are viewed valuable t "
            + "marketplace of our full in to achieve are now better satiny "
            + "a values a share now becognize the high lev"
    };

    /**
     * Generates a new random Message.
     * @return The randomly generated Message.
    */

    public static Message getMessage() {
        int pIndex = rand.nextInt(people.length);
        int sIndex = rand.nextInt(subjects.length);
        int mIndex = rand.nextInt(messageBodies.length);
        ArrayList<Person> recips = new ArrayList<Person>();
        int rIndex = rand.nextInt(people.length);
        if (rIndex == 0) {
            rIndex++;
        }
        for (int i = 0; i <= rIndex; i++) {
            if (people[i] != people[pIndex]) {
                recips.add(people[i]);
            }
        }
        return new Message(people[pIndex], recips, subjects[sIndex],
            LocalDateTime.now(), messageBodies[mIndex]);
    }
}
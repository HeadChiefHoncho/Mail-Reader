import java.util.HashSet;

/**
 * This class represents a Mailbox object.
 * @author Alex Loomis
 * @version 1.0
*/

public class Mailbox {

    private String name;
    private HashSet<Message> messages = new HashSet<Message>();

    /**
     * Creates a new Mailbox with a specified name.
     * @param name The name of the Mailbox.
    */

    public Mailbox(String name) {
        this.name = name;
    }

    /**
     * Removes a Message from the Mailbox.
     * @param mail The Message to be removed.
    */

    public void remove(Message mail) {
        messages.remove(mail);
    }

    /**
     * Adds a Message to the Mailbox.
     * @param mail The Message to be added.
    */

    public void add(Message mail) {
        messages.add(mail);
    }

    /**
     * Moves a Message from this Mailbox to a specified Mailbox.
     * @param mail The Message to be moved.
     * @param mailbox The Mailbox it will be moved to.
    */

    public void move(Message mail, Mailbox mailbox) {
        remove(mail);
        mailbox.add(mail);
    }

    /**
     * Gets a String representation of this Mailbox.
     * @return A String representation of this Mailbox.
    */

    public String toString() {
        return name;
    }

    /**
     * Gets the name of the Mailbox.
     * @return The name of the Mailbox.
    */

    public String getName() {
        return name;
    }

    /**
     * Gets the Messages in the Mailbox.
     * @return The Messages in the Mailbox.
    */

    public HashSet<Message> getMessages() {
        return messages;
    }
}
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * This class represents a Message object.
 * @author Alex Loomis
 * @version 1.0
*/

public class Message implements Comparable<Message> {

    private Person sender;
    private ArrayList<Person> recipients;
    private String subject;
    private LocalDateTime dateTime;
    private String messageBody;

    /**
     * Creates a new Message.
     * @param sender The sender of the Message.
     * @param recipients The recipients of the Message.
     * @param subject The subject of the Message.
     * @param dateTime The Date and Time of the Message.
     * @param messageBody The body of the Message.
    */

    public Message(Person sender, ArrayList<Person> recipients,
        String subject, LocalDateTime dateTime, String messageBody) {
        this.sender = sender;
        this.recipients = recipients;
        this.subject = subject;
        this.dateTime = dateTime;
        this.messageBody = messageBody;
    }

    /**
     * Compares this Message to another Message.
     * @param m The Message to compare to this one.
     * @return An indication of how the Message compares to this Message.
    */

    public int compareTo(Message m) {
        if (this.dateTime.isBefore(m.dateTime)) {
            return -1;
        } else if (this.dateTime.isAfter(m.dateTime)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Checks for equality to a specified Message.
     * @param other The Message to be compared to this Message.
     * @return Whether or not the two Messages are equal.
    */

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Message)) {
            return false;
        }
        Message that = (Message) other;
        return (this.sender.equals(that.sender)
            && this.recipients.equals(that.recipients)
            && this.subject.equals(that.subject)
            && this.dateTime.equals(that.dateTime)
            && this.messageBody.equals(that.messageBody));
    }

    /**
     * Gets the hashCode of this Message.
     * @return The hashCode of this Message.
    */

    @Override
    public int hashCode() {
        return (12 + sender.hashCode() + recipients.hashCode()
            + subject.hashCode() + dateTime.hashCode()
            + messageBody.hashCode());
    }

    /**
     * Gets a String representation of this Message.
     * @return A String representation of this Message.
    */

    @Override
    public String toString() {
        return sender + ":\n " + subject;
    }

    /**
     * Gets the sender of this Message.
     * @return The sender of this Message.
    */

    public Person getSender() {
        return sender;
    }

    /**
     * Gets the recipients of this Message.
     * @return The recipients of this Message.
    */

    public ArrayList<Person> getRecipients() {
        return recipients;
    }

    /**
     * Gets the subject of this Message.
     * @return The subject of this Message.
    */

    public String getSubject() {
        return subject;
    }

    /**
     * Gets the date and time of this Message.
     * @return The date and time of this Message.
    */

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Gets the body of this Message.
     * @return The body of this Message.
    */

    public String getMessageBody() {
        return messageBody;
    }
}
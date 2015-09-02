/**
 * This class represents a Person object.
 * @author Alex Loomis
 * @version 1.0
*/

public class Person implements Comparable<Person> {

    private String name;
    private String email;

    /**
     * Creates a new Person with a specified name and email.
     * @param name The name of the Person.
     * @param email The email of the Person.
    */

    public Person(String name, String email) {
        if (isValidEmail(email)) {
            this.name = name;
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email!");
        }
    }

    /**
     * Compares this Person to another Person.
     * @param p The person to compare this Person to.
     * @return An indication of how the Person compares to this Person.
    */

    public int compareTo(Person p) {
        if (this.name.compareTo(p.name) > 0
            || this.name.compareTo(p.name) < 0) {
            return this.name.compareTo(p.name);
        } else {
            return this.email.compareTo(p.email);
        }
    }

    /**
     * Checks to see if the Person's email is valid.
     * @param mail The email to be checked for validity.
     * @return Whether or not the Person's email is valid.
    */

    private boolean isValidEmail(String mail) {
        if (mail.contains("@")) {
            int atIndex = mail.indexOf("@");
            if (mail.lastIndexOf("@") != atIndex) {
                return false;
            }
            if (mail.contains(".")) {
                int dotIndex = mail.lastIndexOf(".");
                if (mail.indexOf(".") != dotIndex) {
                    if (mail.indexOf(".") < atIndex && atIndex < dotIndex) {
                        for (int i = atIndex + 1; i < dotIndex; i++) {
                            if (String.valueOf(mail.charAt(i)).equals(".")) {
                                return false;
                            }
                        }
                        return true;
                    }
                } else if (dotIndex > atIndex) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks for equality to a specified Person.
     * @param other The Person to be compared to this Person.
     * @return Whether or not the two Persons are equal.
    */

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Person)) {
            return false;
        }
        Person that = (Person) other;
        return (this.name.equals(that.name) && this.email.equals(that.email));
    }

    /**
     * Gets the hashCode of this Person.
     * @return The hashCode of this Person.
    */

    public int hashCode() {
        return 51 + this.name.hashCode() + this.email.hashCode();
    }

    /**
     * Gets a String representation of this Person.
     * @return A String representation of this Person.
    */

    public String toString() {
        return name + " <" + email + ">";
    }

    /**
     * Gets the name of the Person.
     * @return The name of the Person.
    */

    public String getName() {
        return name;
    }

    /**
     * Gets the email of the Person.
     * @return The email of the Person.
    */

    public String getEmail() {
        return email;
    }
}
package seedu.address.model.contact;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a contact in the contact list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Contact {

    // Identity fields
    private final ContactName name;
    private final Email email;
    private final Telegram telegram;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();


    /**
     * Creates and initialises a Contact object with a Name, Email and Tag field, but
     * without a Telegram field.
     *
     * @param name Name field of the Contact object.
     * @param email Email field of the Contact object.
     * @param tags Set of tags of the Contact object.
     */
    public Contact(ContactName name, Email email, Set<Tag> tags) {
        requireAllNonNull(name, email);
        this.name = name;
        this.email = email;
        this.telegram = null;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null, except for the {@code telegram} field.
     *
     * @param name Name field of the Contact object.
     * @param email Email field of the Contact object.
     * @param telegram Telegram field of the Contact object.
     * @param tags Set of tags of the Contact object.
     */
    public Contact(ContactName name, Email email, Telegram telegram, Set<Tag> tags) {
        requireAllNonNull(name, email, telegram, tags);
        this.name = name;
        this.email = email;
        this.telegram = telegram;
        this.tags.addAll(tags);
    }

    public ContactName getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Optional<Telegram> getTelegram() {
        return Optional.ofNullable(this.telegram);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both contacts of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameContact(Contact otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both contacts have the same identity and data fields.
     * This defines a stronger notion of equality between two contacts.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Contact)) {
            return false;
        }

        Contact otherPerson = (Contact) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getTelegram().equals(getTelegram())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Email: ")
                .append(getEmail())
                .append(" Telegram: ")
                .append(getTelegram().isPresent() ? getTelegram().get() : "")
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}

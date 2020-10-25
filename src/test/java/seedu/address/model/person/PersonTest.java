package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_ADDRESS_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_EMAIL_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_GIT_USERNAME_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_NAME_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_PHONE_A;
import static seedu.address.testutil.TypicalPersons.DESC_A;
import static seedu.address.testutil.TypicalPersons.DESC_B;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void execute_isValidUpdateFields() {
        String expectedPersonName = "updatedName";
        String expectedPhone = "9291";
        String expectedEmail = "expect@gmail.com";
        String expectedAddress = "expected street";

        String expectedString = " Person name: updatedName"
            + " Git Username: Sparrow32"
            + " Phone: 9291"
            + " Email: expect@gmail.com"
            + " Address: expected street";

        Person teammate = new PersonBuilder(DESC_A).build();

        teammate.updatePersonName(expectedPersonName);
        teammate.updatePhone(expectedPhone);
        teammate.updateEmail(expectedEmail);
        teammate.updateAddress(expectedAddress);

        assertEquals(expectedString, teammate.toString());
    }

    @Test
    public void execute_isValidFields() {
        String expectedPersonName = VALID_TEAMMATE_NAME_A;
        String expectedGitUserName = VALID_TEAMMATE_GIT_USERNAME_A;
        String expectedPhone = VALID_TEAMMATE_PHONE_A;
        String expectedEmail = VALID_TEAMMATE_EMAIL_A;
        String expectedAddress = VALID_TEAMMATE_ADDRESS_A;

        assertEquals(expectedPersonName, DESC_A.getPersonName().toString());
        assertEquals(expectedGitUserName, DESC_A.getGitUserName().toString());
        assertEquals(expectedPhone, DESC_A.getPhone().toString());
        assertEquals(expectedEmail, DESC_A.getEmail().toString());
        assertEquals(expectedAddress, DESC_A.getAddress().toString());

    }

    @Test
    public void isSameTeamamate() {
        // same object -> return true
        assertTrue(DESC_A.isSamePerson(DESC_A));

        // null -> return false
        assertFalse(DESC_A.isSamePerson(null));

        // different personName -> return true
        Person editedDescAPersonName = new PersonBuilder(DESC_A).withPersonName("luanqiba").build();
        assertTrue(DESC_A.isSamePerson(editedDescAPersonName));

        // different gitUserName -> return false
        Person editedDescAGitUserName = new PersonBuilder(DESC_A).withGitUserName("different").build();
        assertFalse(DESC_A.isSamePerson(editedDescAGitUserName));

        // different phone -> return false
        Person editedDescAPhone = new PersonBuilder(DESC_A).withPhone("38283828311").build();
        assertFalse(DESC_A.isSamePerson(editedDescAPhone));

        // different email -> return false
        Person editedDescAEmail = new PersonBuilder(DESC_A).withEmail("jdjf@hmail.com").build();
        assertFalse(DESC_A.isSamePerson(editedDescAEmail));

        // different address -> return true
        Person editedDescAAddress = new PersonBuilder(DESC_A).withAddress("timbuktu").build();
        assertTrue(DESC_A.isSamePerson(editedDescAAddress));

    }

    @Test
    public void execute_validHashCode() {
        int expectedHash = Objects.hash(VALID_TEAMMATE_NAME_A, VALID_TEAMMATE_GIT_USERNAME_A, VALID_TEAMMATE_PHONE_A,
            VALID_TEAMMATE_EMAIL_A, VALID_TEAMMATE_ADDRESS_A);

        int resultHash = DESC_A.hashCode();

        assertTrue(resultHash == expectedHash);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(DESC_A, DESC_A);

        // null -> returns false
        assertNotEquals(DESC_A, null);

        // same values, different objects -> return true
        Person descACopy = new PersonBuilder(DESC_A).build();
        assertEquals(descACopy, DESC_A);

        // different type -> return false
        assertNotEquals(DESC_A, 5);

        // same type, different objects -> return false
        assertNotEquals(DESC_B, DESC_A);

        // different PersonName
        Person editedDescAPersonName = new PersonBuilder(DESC_A).withPersonName("Niaaz").build();
        assertNotEquals(editedDescAPersonName, DESC_A);

        // different gitUserName
        Person editedDescAGitUserName = new PersonBuilder(DESC_A).withGitUserName("Geniaaz982").build();
        assertNotEquals(editedDescAGitUserName, DESC_A);

        // different phone
        Person editedDescAPhone = new PersonBuilder(DESC_A).withPhone("3728271").build();
        assertNotEquals(editedDescAPhone, DESC_A);

        // different email
        Person editedDescAEmail = new PersonBuilder(DESC_A).withEmail("pb@gmail.com").build();
        assertNotEquals(editedDescAEmail, DESC_A);

        // different address
        Person editedDescAAddress = new PersonBuilder(DESC_A).withAddress("247 pasir ris").build();
        assertNotEquals(editedDescAAddress, DESC_A);
    }

    @Test
    public void execute_validToString() {
        String expectedString = " Person name: Jack Nicholson"
            + " Git Username: Sparrow32"
            + " Phone: 92883923"
            + " Email: jack@gmail.com"
            + " Address: 32 Lake Road";

        String resultString = DESC_A.toString();

        assertEquals(resultString, expectedString);
    }

}

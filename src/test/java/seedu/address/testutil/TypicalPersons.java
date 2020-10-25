package seedu.address.testutil;

import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_ADDRESS_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_ADDRESS_B;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_ADDRESS_C;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_EMAIL_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_EMAIL_B;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_EMAIL_C;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_GIT_USERNAME_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_GIT_USERNAME_B;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_GIT_USERNAME_C;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_NAME_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_NAME_B;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_NAME_C;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_PHONE_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_PHONE_B;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_PHONE_C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MainCatalogue;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Persons} objects to be used in tests.
 */
public class TypicalPersons {
    public static final Person ALICE = new PersonBuilder().withPersonName("Alice Pauline").withGitUserName("Allie32")
        .withPhone("12345678").withEmail("alicepauline@sample.com").withAddress("123, Jurong West Ave 6, #08-111")
        .build();

    public static final Person DESC_A = new PersonBuilder()
        .withPersonName(VALID_TEAMMATE_NAME_A)
        .withGitUserName(VALID_TEAMMATE_GIT_USERNAME_A)
        .withPhone(VALID_TEAMMATE_PHONE_A)
        .withEmail(VALID_TEAMMATE_EMAIL_A)
        .withAddress(VALID_TEAMMATE_ADDRESS_A).build();

    public static final Person DESC_B = new PersonBuilder()
        .withPersonName(VALID_TEAMMATE_NAME_B)
        .withGitUserName(VALID_TEAMMATE_GIT_USERNAME_B)
        .withPhone(VALID_TEAMMATE_PHONE_B)
        .withEmail(VALID_TEAMMATE_EMAIL_B)
        .withAddress(VALID_TEAMMATE_ADDRESS_B).build();

    public static final Person DESC_C = new PersonBuilder()
        .withPersonName(VALID_TEAMMATE_NAME_C)
        .withGitUserName(VALID_TEAMMATE_GIT_USERNAME_C)
        .withPhone(VALID_TEAMMATE_PHONE_C)
        .withEmail(VALID_TEAMMATE_EMAIL_C)
        .withAddress(VALID_TEAMMATE_ADDRESS_C).build();

    /**
     * Returns an {@code MainCatalogue} with all the typical projects.
     */
    public static MainCatalogue getTypicalMainCatalogue() {
        MainCatalogue ab = new MainCatalogue();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, DESC_A, DESC_B, DESC_C));
    }

}

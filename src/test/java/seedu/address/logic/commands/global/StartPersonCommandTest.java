package seedu.address.logic.commands.global;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.address.testutil.TypicalPersons.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code StartPersonCommand}.
 */
public class StartPersonCommandTest {

    private Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToStart = model.getFilteredPersonList().get(INDEX_FIRST_PROJECT.getZeroBased());
        StartPersonCommand startPersonCommand = new StartPersonCommand(INDEX_FIRST_PROJECT);

        String expectedMessage = String.format(StartPersonCommand.MESSAGE_START_PROJECT_SUCCESS, personToStart);

        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());
        expectedModel.enter(personToStart);

        assertCommandSuccess(startPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        StartPersonCommand startPersonCommand = new StartPersonCommand(outOfBoundIndex);

        assertCommandFailure(startPersonCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PROJECT);

        Person personToStart = model.getFilteredPersonList().get(INDEX_FIRST_PROJECT.getZeroBased());
        StartPersonCommand startPersonCommand = new StartPersonCommand(INDEX_FIRST_PROJECT);

        String expectedMessage = String.format(StartPersonCommand.MESSAGE_START_PROJECT_SUCCESS, personToStart);

        Model expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());
        expectedModel.enter(personToStart);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PROJECT);

        assertCommandSuccess(startPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PROJECT);

        Index outOfBoundIndex = INDEX_SECOND_PROJECT;
        // ensures that outOfBoundIndex is still in bounds of main catalogue list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProjectCatalogue().getPersonList().size());

        StartPersonCommand startPersonCommand = new StartPersonCommand(outOfBoundIndex);

        assertCommandFailure(startPersonCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        StartPersonCommand startFirstCommand = new StartPersonCommand(INDEX_FIRST_PROJECT);
        StartPersonCommand startSecondCommand = new StartPersonCommand(INDEX_SECOND_PROJECT);

        // same object -> returns true
        assertTrue(startFirstCommand.equals(startFirstCommand));

        // same values -> returns true
        StartPersonCommand startFirstCommandCopy = new StartPersonCommand(INDEX_FIRST_PROJECT);
        assertTrue(startFirstCommand.equals(startFirstCommandCopy));

        // different types -> returns false
        assertFalse(startFirstCommand.equals(1));

        // null -> returns false
        assertFalse(startFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(startFirstCommand.equals(startSecondCommand));
    }
}

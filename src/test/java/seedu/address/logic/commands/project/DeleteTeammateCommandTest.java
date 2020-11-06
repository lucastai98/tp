package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGitIndexes.GIT_USERINDEX_FIRST_TEAMMATE;
import static seedu.address.testutil.TypicalGitIndexes.GIT_USERINDEX_SECOND_TEAMMATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalPersons.DESC_A;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.participation.Participation;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * The tests do not cover invalid Person valid index because invalid person is covered by other test,
 * and would be unnecessary to add here to overlap in testing.
 */
public class DeleteTeammateCommandTest {

    @Test
    public void execute_validIndexValidPerson_throwsCommandException() {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);

        Person person = DESC_A;
        Model expectedModel = model;
        project.addParticipation(person);

        Participation participation = project.getParticipation(GIT_USERINDEX_FIRST_TEAMMATE
            .getGitUserName());
        model.addPerson(person);
        model.addParticipation(participation);

        DeleteTeammateCommand deleteTeammateCommand = new DeleteTeammateCommand(GIT_USERINDEX_FIRST_TEAMMATE);

        String expectedMessage = String.format(DeleteTeammateCommand.MESSAGE_DELETE_TEAMMATE_SUCCESS,
            person);

        assertCommandSuccess(deleteTeammateCommand, model, expectedMessage, expectedModel);
    }

    @Test public void execute_validIndexValidPersonNotAddedToList_throwsCommandException() {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        DeleteTeammateCommand deleteTeammateCommand = new DeleteTeammateCommand(GIT_USERINDEX_FIRST_TEAMMATE);
        assertThrows(CommandException.class, () -> deleteTeammateCommand.execute(model)); //
    }

    @Test
    public void execute_invalidIndexValidPerson_throwsCommandException() {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);

        Person person = DESC_A;
        project.addParticipation(person);

        Participation participation = project.getParticipation(GIT_USERINDEX_SECOND_TEAMMATE.getGitUserName());
        model.addPerson(person);
        assertThrows(NullPointerException.class, () -> model.addParticipation(participation));
    }

    @Test
    public void equals() {
        DeleteTeammateCommand deleteOne = new DeleteTeammateCommand(GIT_USERINDEX_FIRST_TEAMMATE);
        DeleteTeammateCommand deleteTwe = new DeleteTeammateCommand(GIT_USERINDEX_FIRST_TEAMMATE);
        DeleteTeammateCommand deleteThree = new DeleteTeammateCommand(GIT_USERINDEX_SECOND_TEAMMATE);

        // Same object -> returns true
        assertTrue(deleteOne.equals(deleteOne));

        // Same values -> return true
        assertTrue(deleteOne.equals(deleteTwe));

        // different types -> returns false
        assertFalse(deleteOne.equals(1));

        // null -> returns false
        assertFalse(deleteOne.equals(null));

        // different task -> returns false
        assertFalse(deleteOne.equals(deleteThree));
    }

}
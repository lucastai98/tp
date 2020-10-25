package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code StartProjectCommand}.
 */
public class LeaveCommandTest {

    private Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());

    @Test
    public void execute_success() {
        Project projectToStart = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(projectToStart);
        LeaveCommand leaveCommand = new LeaveCommand();

        String expectedMessage = LeaveCommand.MESSAGE_LEAVE_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());

        assertCommandSuccess(leaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        assertEquals(new LeaveCommand(), new LeaveCommand());
    }
}

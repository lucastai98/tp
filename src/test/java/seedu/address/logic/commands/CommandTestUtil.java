package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPOURL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.global.EditCommand;
import seedu.address.model.MainCatalogue;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.project.NameContainsKeywordsPredicate;
import seedu.address.model.project.Project;
import seedu.address.testutil.EditProjectDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_PROJECT_NAME_A = "ToastAI";
    public static final String VALID_PROJECT_NAME_B = "Coders without Borders";
    public static final String VALID_DEADLINE_A = "21-03-2020 10:00:00";
    public static final String VALID_DEADLINE_B = "31-12-2020 10:00:00";
    public static final String VALID_REPOURL_A = "https://github.com/valid/a.git";
    public static final String VALID_REPOURL_B = "https://github.com/valid/b.git";
    public static final String VALID_PROJECT_DESCRIPTION_A = "Bring AI into every toaster";
    public static final String VALID_PROJECT_DESCRIPTION_B = "Better people through code";
    public static final String VALID_PROJECT_TAG_A = "hang";
    public static final String VALID_PROJECT_TAG_B = "fiend";
    public static final String VALID_PROJECT_TAG_DG = "DG";
    public static final String VALID_TASK_MODEL = "model";
    public static final String VALID_TEAMMATE = "Codey";
    //TODO: after Parsing of tasks is refined, may update these to be more meaningful

    public static final String PROJECT_NAME_DESC_AMY = " " + PREFIX_PROJECT_NAME + VALID_PROJECT_NAME_A;
    public static final String PROJECT_NAME_DESC_BOB = " " + PREFIX_PROJECT_NAME + VALID_PROJECT_NAME_B;
    public static final String DEADLINE_DESC_A = " " + PREFIX_DEADLINE + VALID_DEADLINE_A;
    public static final String DEADLINE_DESC_B = " " + PREFIX_DEADLINE + VALID_DEADLINE_B;
    public static final String REPOURL_DESC_A = " " + PREFIX_REPOURL + VALID_REPOURL_A;
    public static final String REPOURL_DESC_B = " " + PREFIX_REPOURL + VALID_REPOURL_B;
    public static final String PROJECT_DESCRIPTION_DESC_AMY = " " + PREFIX_PROJECT_DESCRIPTION
        + VALID_PROJECT_DESCRIPTION_A;
    public static final String PROJECT_DESCRIPTION_DESC_BOB = " " + PREFIX_PROJECT_DESCRIPTION
        + VALID_PROJECT_DESCRIPTION_B;
    public static final String PROJECT_TAG_DESC_FRIEND = " " + PREFIX_PROJECT_TAG + VALID_PROJECT_TAG_B;
    public static final String PROJECT_TAG_DESC_HUSBAND = " " + PREFIX_PROJECT_TAG + VALID_PROJECT_TAG_A;
    public static final String TASK_DESC_DG = " " + PREFIX_TASK + VALID_PROJECT_TAG_DG;
    public static final String TASK_DESC_MODEL = " " + PREFIX_TASK + VALID_TASK_MODEL;

    public static final String INVALID_NAME_DESC = " " + PREFIX_PROJECT_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE
            + "29/02/1999 00:00:00"; // '-' is used instead of '/'
    public static final String INVALID_REPOURL_DESC = " " + PREFIX_REPOURL
            + "https://github.com/a/b"; // missing '.git' part
    public static final String INVALID_PROJECT_DESCRIPTION_DESC = " "
        + PREFIX_PROJECT_DESCRIPTION; // empty string not allowed
    // for addresses
    public static final String INVALID_PROJECT_TAG_DESC = " " + PREFIX_PROJECT_TAG
        + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditProjectDescriptor DESC_A;
    public static final EditCommand.EditProjectDescriptor DESC_B;

    static {
        DESC_A = new EditProjectDescriptorBuilder().withProjectName(VALID_PROJECT_NAME_A)
                .withDeadline(VALID_DEADLINE_A).withRepoUrl(VALID_REPOURL_A).withProjectDescription(
                VALID_PROJECT_DESCRIPTION_A)
                .withTags(VALID_PROJECT_TAG_B).withTasks(VALID_PROJECT_TAG_DG, VALID_TASK_MODEL).build();
        DESC_B = new EditProjectDescriptorBuilder().withProjectName(VALID_PROJECT_NAME_B)
                .withDeadline(VALID_DEADLINE_B).withRepoUrl(VALID_REPOURL_B).withProjectDescription(
                VALID_PROJECT_DESCRIPTION_B)
                .withTags(VALID_PROJECT_TAG_A, VALID_PROJECT_TAG_B).withTasks(VALID_PROJECT_TAG_DG).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the main catalogue, filtered project list and selected project in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        MainCatalogue expectedMainCatalogue = new MainCatalogue(actualModel.getProjectCatalogue());
        List<Project> expectedFilteredList = new ArrayList<>(actualModel.getFilteredProjectList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedMainCatalogue, actualModel.getProjectCatalogue());
        assertEquals(expectedFilteredList, actualModel.getFilteredProjectList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the project at the given {@code targetIndex} in the
     * {@code model}'s main catalogue.
     */
    public static void showProjectAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProjectList().size());

        Project project = model.getFilteredProjectList().get(targetIndex.getZeroBased());
        final String[] splitName = project.getProjectName().fullProjectName.split("\\s+");
        model.updateFilteredProjectList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredProjectList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s main catalogue.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        model.updateFilteredPersonList(p -> p.getGitUserNameString().equals(person.getGitUserNameString()));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}

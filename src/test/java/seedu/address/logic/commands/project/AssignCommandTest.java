package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalMainCatalogue.getTypicalMainCatalogue;
import static seedu.address.testutil.TypicalPersons.DESC_A;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.participation.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class AssignCommandTest {

    @Test
    public void execute_validIndexPersonInvalidAssign_throwsCommandException() {

        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        project.addParticipation(DESC_A);
        Task taskToAssign = project.getFilteredSortedTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Participation assignee = project.getParticipation(DESC_A.getGitUserNameString());
        assignee.addTask(taskToAssign);
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_TASK, DESC_A.getGitUserNameString());

        assertCommandFailure(assignCommand, model, String.format(
            Messages.MESSAGE_REASSIGNMENT_OF_SAME_TASK_TO_SAME_PERSON, assignee.getAssigneeName()));
    }

    @Test
    public void execute_validIndexValidPersonFilteredList_success() {

        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        project.addParticipation(DESC_A);
        model.addParticipation(project.getParticipation(DESC_A.getGitUserNameString()));
        project.updateTaskFilter(x -> true);
        Task taskToAssign = project.getFilteredSortedTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        Participation assignee = project.getParticipation(DESC_A.getGitUserNameString());
        project.updateTaskFilter(task -> task.getTaskName().contains(taskToAssign.getTaskName()));
        ModelManager expectedModel = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_TASK, DESC_A.getGitUserNameString());
        String expectedMessage = String.format(AssignCommand.MESSAGE_ASSIGN_TASK_SUCCESS, taskToAssign, assignee);

        Project projectCopy = new Project(project.getProjectName(), project.getDeadline(),
            project.getRepoUrl(), project.getProjectDescription(), project.getProjectTags(),
            new HashMap<>(), project.getTasks());
        projectCopy.addParticipation(DESC_A);
        expectedModel.setProject(project, projectCopy);
        expectedModel.enter(projectCopy);
        expectedModel.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased())
            .getParticipation(DESC_A.getGitUserNameString()).addTask(taskToAssign);

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexValidPerson_throwsCommandException() {

        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        Index outOfBoundIndex = Index.fromOneBased(project.getFilteredSortedTaskList().size() + 1);
        model.enter(project);
        project.addParticipation(DESC_A);
        AssignCommand assignCommand = new AssignCommand(outOfBoundIndex, DESC_A.getGitUserNameString());

        assertCommandFailure(assignCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        Project.getAllProjects().clear();
    }

    @Test
    public void execute_validIndexValidPersonUnfilteredList_success() {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        project.addParticipation(DESC_A);
        model.addParticipation(project.getParticipation(DESC_A.getGitUserNameString()));
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());

        Task taskToAssign = project.getFilteredSortedTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Participation assignee = project.getParticipation(DESC_A.getGitUserNameString());
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_TASK, DESC_A.getGitUserNameString());


        String expectedMessage = String.format(AssignCommand.MESSAGE_ASSIGN_TASK_SUCCESS, taskToAssign, assignee);

        Project projectCopy = new Project(project.getProjectName(), project.getDeadline(),
                project.getRepoUrl(), project.getProjectDescription(), project.getProjectTags(),
                new HashMap<>(), project.getTasks());
        projectCopy.addParticipation(DESC_A);
        // TODO: After refining Participation getters and setters this part can be done fully via getters
        expectedModel.setProject(project, projectCopy);
        expectedModel.enter(projectCopy);
        expectedModel.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased())
                .getParticipation(DESC_A.getGitUserNameString()).addTask(taskToAssign);

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexInvalidPerson_throwsCommandException() {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        project.removeParticipationWithName(DESC_A.getGitUserNameString());

        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_TASK, DESC_A.getGitUserNameString());
        assertCommandFailure(assignCommand, model,
            String.format(Messages.MESSAGE_MEMBER_NOT_PRESENT, DESC_A.getGitUserName()));
    }

    @Test
    public void equals() {
        String name = DESC_A.getGitUserNameString();
        AssignCommand assignFirstCommand = new AssignCommand(INDEX_FIRST_TASK, name);
        AssignCommand assignSecondCommand = new AssignCommand(INDEX_SECOND_TASK, name);
        AssignCommand assignNullPerson = new AssignCommand(INDEX_FIRST_TASK, "");

        // same object -> returns true
        assertTrue(assignFirstCommand.equals(assignFirstCommand));

        // same values -> returns true
        AssignCommand assignFirstCommandCopy = new AssignCommand(INDEX_FIRST_TASK, name);
        assertTrue(assignFirstCommand.equals(assignFirstCommandCopy));

        // different types -> returns false
        assertFalse(assignFirstCommand.equals(1));

        // null -> returns false
        assertFalse(assignFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(assignFirstCommand.equals(assignSecondCommand));

        // different person -> returns false
        assertFalse(assignFirstCommand.equals(assignNullPerson));
    }
}

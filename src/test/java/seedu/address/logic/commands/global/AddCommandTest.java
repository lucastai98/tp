package seedu.address.logic.commands.global;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.MainCatalogue;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyMainCatalogue;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Status;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;
import seedu.address.testutil.ProjectBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_projectAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingProjectAdded modelStub = new ModelStubAcceptingProjectAdded();
        Project validProject = new ProjectBuilder().build();

        CommandResult commandResult = new AddCommand(validProject).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validProject), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProject), modelStub.projectsAdded);
    }

    @Test
    public void execute_duplicateProject_throwsCommandException() {
        Project validProject = new ProjectBuilder().build();
        AddCommand addCommand = new AddCommand(validProject);
        ModelStub modelStub = new ModelStubWithProject(validProject);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PROJECT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Project alice = new ProjectBuilder().withProjectName("Alice").build();
        Project bob = new ProjectBuilder().withProjectName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different project -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getProjectCatalogueFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProjectCatalogueFilePath(Path mainCatalogueFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProject(Project project) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProjectCatalogue(ReadOnlyMainCatalogue newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMainCatalogue getProjectCatalogue() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProject(Project project) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProject(Project target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProject(Project target, Project editedProject) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Project> getFilteredProjectList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredProjectList(Predicate<Project> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Status getStatus() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void enter(Project project) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void enter(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void quit() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void enterTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void enterTeammate(Participation teammate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void enterMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateProjectToBeDisplayedOnDashboard(Project project) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Project> getProjectToBeDisplayedOnDashboard() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateTaskToBeDisplayedOnDashboard(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Task> getTaskToBeDisplayedOnDashboard() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateTeammateToBeDisplayedOnDashboard(Participation teammate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Participation> getTeammateToBeDisplayedOnDashboard() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateMeetingToBeDisplayedOnDashboard(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Meeting> getMeetingToBeDisplayedOnDashboard() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAsProjectList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAsPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePersonToBeDisplayedOnDashboard(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Person> getPersonToBeDisplayedOnDashboard() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single project.
     */
    private class ModelStubWithProject extends ModelStub {
        private final Project project;

        ModelStubWithProject(Project project) {
            requireNonNull(project);
            this.project = project;
        }

        @Override
        public boolean hasProject(Project project) {
            requireNonNull(project);
            return this.project.isSameProject(project);
        }
    }

    /**
     * A Model stub that always accept the project being added.
     */
    private class ModelStubAcceptingProjectAdded extends ModelStub {
        final ArrayList<Project> projectsAdded = new ArrayList<>();

        @Override
        public boolean hasProject(Project project) {
            requireNonNull(project);
            return projectsAdded.stream().anyMatch(project::isSameProject);
        }

        @Override
        public void addProject(Project project) {
            requireNonNull(project);
            projectsAdded.add(project);
        }

        @Override
        public ReadOnlyMainCatalogue getProjectCatalogue() {
            return new MainCatalogue();
        }
    }

}

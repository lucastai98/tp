package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_ADDRESS_B;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalProjects.APEAKAPP;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;
import static seedu.address.testutil.TypicalTasks.PLAN_MEETING;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.model.project.exceptions.ProjectNotFoundException;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ProjectBuilder;

public class MainCatalogueTest {

    private final MainCatalogue mainCatalogue = new MainCatalogue();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), mainCatalogue.getProjectList());
        assertEquals(Collections.emptyList(), mainCatalogue.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mainCatalogue.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMainCatalogue_replacesData() {
        MainCatalogue newData = getTypicalMainCatalogue();
        mainCatalogue.resetData(newData);
        assertEquals(newData, mainCatalogue);
    }

    @Test
    public void resetData_withDuplicateProjects_throwsDuplicateProjectException() {
        // Two projects with the same identity fields
        Project editedApeakapp = new ProjectBuilder(APEAKAPP).withProjectDescription(
            VALID_PROJECT_DESCRIPTION_B).withTags(
            VALID_PROJECT_TAG_A)
                .withTasks(SampleDataUtil.getTask4())
                .build();
        List<Project> newProjects = Arrays.asList(APEAKAPP, editedApeakapp);
        MainCatalogueStub newData = new MainCatalogueStub(newProjects, Collections.emptyList());

        assertThrows(DuplicateProjectException.class, () -> mainCatalogue.resetData(newData));
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_TEAMMATE_ADDRESS_B).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        MainCatalogueStub newData = new MainCatalogueStub(Collections.emptyList(), newPersons);

        assertThrows(DuplicatePersonException.class, () -> mainCatalogue.resetData(newData));
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mainCatalogue.hasProject(null));
    }

    @Test
    public void hasProject_projectNotInMainCatalogue_returnsFalse() {
        assertFalse(
            mainCatalogue.hasProject(APEAKAPP));
    }

    @Test
    public void hasProject_projectInMainCatalogue_returnsTrue() {
        mainCatalogue.addProject(APEAKAPP);
        assertTrue(mainCatalogue.hasProject(APEAKAPP));
    }

    @Test
    public void hasProject_projectWithSameIdentityFieldsInMainCatalogue_returnsTrue() {
        mainCatalogue.addProject(APEAKAPP);
        Project editedAlice = new ProjectBuilder(APEAKAPP).withProjectDescription(
            VALID_PROJECT_DESCRIPTION_B).withTags(
            VALID_PROJECT_TAG_A)
                .withTasks(SampleDataUtil.getTask1())
                .build();
        assertTrue(mainCatalogue.hasProject(editedAlice));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mainCatalogue.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInMainCatalogue_returnsFalse() {
        assertFalse(
                mainCatalogue.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInMainCatalogue_returnsTrue() {
        mainCatalogue.addPerson(ALICE);
        assertTrue(mainCatalogue.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInMainCatalogue_returnsTrue() {
        mainCatalogue.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_TEAMMATE_ADDRESS_B).build();
        assertTrue(mainCatalogue.hasPerson(editedAlice));
    }

    @Test
    public void getProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> mainCatalogue.getProjectList().remove(0));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> mainCatalogue.getPersonList().remove(0));
    }

    @Test
    public void enterQuit_correctScope_success() {
        try {
            mainCatalogue.addProject(APEAKAPP);
            mainCatalogue.addPerson(ALICE);
            mainCatalogue.enter(APEAKAPP);
            mainCatalogue.enterTask(PLAN_MEETING);
            mainCatalogue.quit();
            mainCatalogue.quit();
            mainCatalogue.enter(ALICE);
            mainCatalogue.quit();
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void enter_nonExisting_throwNotFoundException() {
        assertThrows(ProjectNotFoundException.class, () -> mainCatalogue.enter(APEAKAPP));
        assertThrows(PersonNotFoundException.class, () -> mainCatalogue.enter(ALICE));
    }

    @Test
    public void enter_sameButNotEqual_success() {
        mainCatalogue.addProject(APEAKAPP);
        Project adaptedProject = new ProjectBuilder(APEAKAPP).withTags().build();
        try {
            mainCatalogue.enter(adaptedProject);
        } catch (Exception e) {
            fail();
        }

        mainCatalogue.addPerson(ALICE);
        Person adaptedPerson = new PersonBuilder(ALICE).withAddress(VALID_TEAMMATE_ADDRESS_B).build();
        try {
            mainCatalogue.enter(adaptedPerson);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * A stub ReadOnlyMainCatalogue whose projects list can violate interface constraints.
     */
    private static class MainCatalogueStub implements ReadOnlyMainCatalogue {
        private final ObservableList<Project> projects = FXCollections.observableArrayList();
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private Status status = Status.PROJECT_LIST;

        MainCatalogueStub(Collection<Project> projects, Collection<Person> persons) {
            this.projects.setAll(projects);
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Project> getProjectList() {
            return projects;
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public Status getStatus() {
            return status;
        }

        @Override
        public void enter(Project project) {
            // TODO: Add content if test case need this.
        }

        @Override
        public void enter(Person person) {
            // TODO: Add content if test case need this.
        }

        @Override
        public void quit() {
            // TODO: Add content if test case need this.
        }

        @Override
        public void enterTask(Task task) {
            // TODO: Add content if test case need this.
        }

        @Override
        public void enterTeammate(Participation teammate) {
            // TODO: Add content if test case need this.
        }

        @Override
        public void enterMeeting(Meeting meeting) {
            // TODO: Add content if test case need this.
        }

        @Override
        public void setStatus(Status status) {
            // TODO: Add content if test case need this.
        }
    }

}

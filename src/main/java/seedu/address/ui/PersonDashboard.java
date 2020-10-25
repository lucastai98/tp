package seedu.address.ui;

import java.util.Comparator;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonDashboard extends UiPart<Region> {

    private static final String FXML = "PersonDashboard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on MainCatalogue level 4</a>
     */

    public final Person person;

    @FXML
    private HBox dashboardPane;
    @FXML
    private Label personName;
    @FXML
    private Label gitUserName;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label header1;
    @FXML
    private Label header2;
    @FXML
    private Label header3;
    @FXML
    private FlowPane projects;
    @FXML
    private FlowPane tasks;
    @FXML
    private FlowPane meetings;

    /**
     * Creates a {@code PersonDashboardCode} with the given {@code Person} and index to display.
     */
    public PersonDashboard(Optional<Person> person) {
        super(FXML);
        this.person = person.get();
        personName.setText(this.person.getPersonName().fullPersonName);
        gitUserName.setText("GitHub username: " + this.person.getGitUserNameString());
        phone.setText("Contact number: " + this.person.getPhone().value);
        email.setText("Email: " + this.person.getEmail().value);
        address.setText("Address: " + this.person.getAddress().value);
        header1.setText("Projects: ");
        this.person.getProjects().stream()
                .sorted(Comparator.comparing(project -> project.getProjectName().fullProjectName))
                .forEach(project -> projects.getChildren().add(new Label(project.getProjectName().fullProjectName)));
        header2.setText("Tasks: ");
        this.person.getTasks().stream()
                .sorted(Comparator.comparing(task -> task.taskName))
                .forEach(task -> tasks.getChildren().add(new Label(task.taskName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonDashboard)) {
            return false;
        }

        // state check
        PersonDashboard card = (PersonDashboard) other;
        return person.equals(card.person);
    }
}

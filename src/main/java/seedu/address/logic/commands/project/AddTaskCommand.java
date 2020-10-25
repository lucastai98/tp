package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_IS_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * Adds a project to the main catalogue.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the project. "
            + "Parameters: "
            + PREFIX_TASK_NAME + "TASK NAME "
            + PREFIX_TASK_PROGRESS + "TASK PROGRESS PERCENT "
            + PREFIX_TASK_IS_DONE + "TASK STATUS "
            + "[" + PREFIX_TASK_DEADLINE + "TASK DEADLINE] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_NAME + "Create new Person Class "
            + PREFIX_TASK_PROGRESS + "35 "
            + PREFIX_TASK_IS_DONE + "true "
            + PREFIX_TASK_DEADLINE + "29-02-2020 00:00:00";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    private final Task toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        project.addTask(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}

package seedu.address.logic.commands.global;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all projects in the main catalogue to the user.
 */
public class ListProjectsCommand extends Command {

    public static final String COMMAND_WORD = "listprojects";

    public static final String MESSAGE_SUCCESS = "Listed all projects";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        model.setAsProjectList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

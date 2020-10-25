package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Leaves the project view and go back to the main catalogue view.
 */
public class LeaveCommand extends Command {

    public static final String COMMAND_WORD = "leave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Leaves the current detail view and go back to the upper level view.\n";

    public static final String MESSAGE_LEAVE_SUCCESS = "Left the current detail view";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.quit();
        return new CommandResult(MESSAGE_LEAVE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof LeaveCommand; // instanceof handles nulls
    }
}

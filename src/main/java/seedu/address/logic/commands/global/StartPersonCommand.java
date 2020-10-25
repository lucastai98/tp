package seedu.address.logic.commands.global;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * Starts a person identified using it's displayed index from the main catalogue.
 */
public class StartPersonCommand extends Command {

    public static final String COMMAND_WORD = "startperson";

    public static final String MESSAGE_START_PROJECT_SUCCESS = "Started Person: %1$s";

    private static final Logger logger = Logger.getLogger("StartCommandLogger");

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Starts the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    public StartPersonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Person personToStart = lastShownList.get(targetIndex.getZeroBased());
        model.enter(personToStart);
        logger.log(Level.INFO, "end of starting a person.");
        return new CommandResult(String.format(MESSAGE_START_PROJECT_SUCCESS, personToStart));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartPersonCommand // instanceof handles nulls
                && targetIndex.equals(((StartPersonCommand) other).targetIndex)); // state check
    }
}

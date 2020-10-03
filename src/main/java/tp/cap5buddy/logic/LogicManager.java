package tp.cap5buddy.logic;

import java.io.IOException;
import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.ResultCommand;
import tp.cap5buddy.logic.parser.ParserManager;
import tp.cap5buddy.logic.parser.exception.ParseException;
import tp.cap5buddy.modules.Module;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.storage.JsonModuleListStorage;
import tp.cap5buddy.storage.Storage;
import tp.cap5buddy.storage.StorageManager;


/**
 * The brain of the program, handles parsing and commands.
 */
public class LogicManager implements Logic {

    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    //private ModuleList modlist;
    private ParserManager pm;
    private final StorageManager storage;
    private final ModuleList moduleList;

    /**
     * Represents the constructor of the Manager.
     */
    public LogicManager(StorageManager storage, ModuleList moduleList) {
        //this.modlist = new ModuleList(new ArrayList<Module>());
        this.pm = new ParserManager();
        this.storage = storage;
        this.moduleList = moduleList;
    }

    /**
     * Returns the result container with all the relevant information.
     * @param userInput user input of user.
     * @return ResultCommand result container.
     * @throws ParseException invalid command.
     */
    @Override
    public ResultCommand execute(String userInput) throws ParseException {
        Command command = pm.parse(userInput);
        ResultCommand result = command.execute(moduleList);
        try {
            storage.saveModules(moduleList);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return result;
    }
}

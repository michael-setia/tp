package seedu.address.logic.commands.modulelistcommands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ArchivedModuleList;
import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleLesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULELESSONTYPE_ES2660;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULELESSONTYPE;


public class DeleteZoomLinkCommandTest {

    private Model model = new ModelManager(getTypicalModuleList(), new ArchivedModuleList(),
            new ContactList(), new TodoList(), new EventList(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        ModuleLesson validLesson = new ModuleLesson(VALID_MODULELESSONTYPE);
        assertThrows(NullPointerException.class, () -> new DeleteZoomLinkCommand(null, validLesson));
    }

    @Test
    public void constructor_nullLesson_throwsNullPointerException() {
        Index validIndex = INDEX_FIRST_MODULE;
        assertThrows(NullPointerException.class, () -> new DeleteZoomLinkCommand(validIndex, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        ModuleLesson validLesson = new ModuleLesson(VALID_MODULELESSONTYPE);
        DeleteZoomLinkCommand deleteZoomLinkCommand = new DeleteZoomLinkCommand(INDEX_FIRST_MODULE, validLesson);
        assertThrows(NullPointerException.class, () -> deleteZoomLinkCommand.execute(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_deleteSuccess() {
        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        ModuleLesson validLesson = new ModuleLesson(VALID_MODULELESSONTYPE);
        Module updatedModule = moduleToUpdate.deleteZoomLink(validLesson);
        DeleteZoomLinkCommand deleteZoomLinkCommand = new DeleteZoomLinkCommand(INDEX_FIRST_MODULE, validLesson);

        String expectedMessage = String.format(DeleteZoomLinkCommand.MESSAGE_DELETE_ZOOM_SUCCESS,
                validLesson, moduleToUpdate.getName());

        ModelManager expectedModel = new ModelManager(model.getModuleList(), model.getArchivedModuleList(),
                model.getContactList(), model.getTodoList(), model.getEventList(), new UserPrefs());
        expectedModel.setModule(moduleToUpdate, updatedModule);

        assertCommandSuccess(deleteZoomLinkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        int outOfBoundIndex = model.getFilteredModuleList().size() + 1;
        Index invalidIndex = Index.fromOneBased(outOfBoundIndex);
        ModuleLesson validLesson = new ModuleLesson(VALID_MODULELESSONTYPE);

        DeleteZoomLinkCommand deleteZoomLinkCommand = new DeleteZoomLinkCommand(invalidIndex, validLesson);

        assertCommandFailure(deleteZoomLinkCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_deleteSuccess() {

        // update module filtered list to contain only a single module
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        ModuleLesson validLesson = new ModuleLesson(VALID_MODULELESSONTYPE);
        Module updatedModule = moduleToUpdate.deleteZoomLink(validLesson);
        DeleteZoomLinkCommand deleteZoomLinkCommand = new DeleteZoomLinkCommand(INDEX_FIRST_MODULE, validLesson);

        String expectedMessage = String.format(DeleteZoomLinkCommand.MESSAGE_DELETE_ZOOM_SUCCESS,
                validLesson, moduleToUpdate.getName());

        Model expectedModel = new ModelManager(model.getModuleList(), model.getArchivedModuleList(),
                model.getContactList(), model.getTodoList(), model.getEventList(), new UserPrefs());
        expectedModel.setModule(moduleToUpdate, updatedModule);

        assertCommandSuccess(deleteZoomLinkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        Index outOfBoundIndex = INDEX_SECOND_MODULE;
        ModuleLesson validLesson = new ModuleLesson(VALID_MODULELESSONTYPE);
        // ensures that outOfBoundIndex is still in bounds of module list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleList().getModuleList().size());

        DeleteZoomLinkCommand deleteZoomLinkCommand = new DeleteZoomLinkCommand(outOfBoundIndex, validLesson);

        assertCommandFailure(deleteZoomLinkCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidModuleLesson_throwsCommandException() {
        Index validIndex = INDEX_FIRST_MODULE;
        ModuleLesson invalidModuleLesson = new ModuleLesson(VALID_MODULELESSONTYPE_ES2660);
        DeleteZoomLinkCommand deleteZoomLinkCommand = new DeleteZoomLinkCommand(validIndex, invalidModuleLesson);

        assertCommandFailure(deleteZoomLinkCommand, model, DeleteZoomLinkCommand.MESSAGE_INVALID_ZOOM_LINK);

    }

    @Test
    public void equals() {

        DeleteZoomLinkCommand deleteFirstCommand = new DeleteZoomLinkCommand(INDEX_FIRST_MODULE,
                new ModuleLesson(VALID_MODULELESSONTYPE));
        DeleteZoomLinkCommand deleteSecondCommand = new DeleteZoomLinkCommand(INDEX_SECOND_MODULE,
                new ModuleLesson(VALID_MODULELESSONTYPE_ES2660));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteZoomLinkCommand deleteFirstCommandCopy = new DeleteZoomLinkCommand(INDEX_FIRST_MODULE,
                new ModuleLesson(VALID_MODULELESSONTYPE));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(8));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different value -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}

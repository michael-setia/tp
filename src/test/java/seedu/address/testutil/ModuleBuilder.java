package seedu.address.testutil;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;

/**
 * A utility class to help with building Person objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_MODULENAME = "CS2103T";
    public static final String DEFAULT_ZOOMLINK = "https://nus-sg.zoom.us/abvuavhua";

    private ModuleName moduleName;
    private ZoomLink zoomLink;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleName = new ModuleName(DEFAULT_MODULENAME);
        zoomLink = new ZoomLink(DEFAULT_ZOOMLINK);
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleName = moduleToCopy.getName();

    }

    /**
     * Sets the {@code Name} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.moduleName = new ModuleName(name);
        return this;
    }

    /**
     * Sets the {@code ZoomLink} of the {@code Module} that we are building.
     */
    public ModuleBuilder withZoomLink(String zoomLink) {
        this.zoomLink = new ZoomLink(zoomLink);
        return this;
    }

    public Module build() {
        return new Module(moduleName, zoomLink);
    }

}

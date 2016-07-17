package de.ibsys.planningTool.controller.tab;

import de.ibsys.planningTool.controller.MainController;
import javafx.application.Application;

/**
 * Created by minhnguyen on 17.07.16.
 */
public abstract class BaseTabController extends Application {
    protected MainController main;

    public void init(MainController main) {
        this.main = main;
    }
}

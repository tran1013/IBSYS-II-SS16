package de.ibsys.planningTool.controller.tab;

import org.apache.log4j.Logger;

import de.ibsys.planningTool.controller.MainController;
import javafx.application.Application;

/**
 * Created by minhnguyen on 17.07.16.
 */
public abstract class BaseTabController extends Application {

    protected Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    protected MainController main;

    public void init(MainController main) {
        this.main = main;
    }

    protected String getI18NText(String splitPriorityDialogTitle) {
        return main.getTranslation().getString(splitPriorityDialogTitle);
    }
}

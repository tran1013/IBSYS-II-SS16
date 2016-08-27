package de.ibsys.planningTool.controller.tab;

import de.ibsys.planningTool.controller.MainController;
import de.ibsys.planningTool.util.I18N;
import javafx.application.Application;
import org.apache.log4j.Logger;

import java.util.ResourceBundle;

/**
 * Created by minhnguyen on 17.07.16.
 */
public abstract class BaseTabController extends Application {

    protected Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    protected MainController main;

    public void init(MainController main) {
        this.main = main;
    }
}

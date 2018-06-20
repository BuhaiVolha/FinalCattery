package by.epam.cattery.controller.command.client;

import by.epam.cattery.controller.command.LoginCommand;
import by.epam.cattery.controller.command.RegistrationCommand;
import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.LogoutCommand;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}

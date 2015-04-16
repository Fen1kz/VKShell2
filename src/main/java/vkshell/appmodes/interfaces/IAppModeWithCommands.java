package vkshell.appmodes.interfaces;

import vkshell.commands.interfaces.ICommand;

import java.util.SortedMap;

public interface IAppModeWithCommands extends IAppMode {
    public SortedMap<String, Class<? extends ICommand>> getCommandMap();
}

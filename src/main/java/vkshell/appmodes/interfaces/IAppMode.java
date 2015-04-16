package vkshell.appmodes.interfaces;

public interface IAppMode {
    void start();

    String getPrompt();

    Object getState();
    Object getPosition();
}

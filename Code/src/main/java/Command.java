public class Command {
    private int numOfCommands;
    private int commandNum;
    public final int optionStart = 0;
    public final int optionChangeLevel = 1;
    public final int optionExit = 2;

    Command(int sz, int n) {
        numOfCommands = sz;
        commandNum = n;
    }

    // returns the number of commands
    public int size() {
        return numOfCommands;
    }

    // sets the command number and returns true, if fails returns false
    public boolean setCommandNum(int n) {
        if(n >= 0 && n < numOfCommands) {
            commandNum = n;
            return true;
        }
        return false;
    }

    // gets the current command number
    public int getCommandNum() {
        return commandNum;
    }
}

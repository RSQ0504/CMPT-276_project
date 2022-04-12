import java.util.HashMap;
import java.util.Map;

public class Command {
    private Map<String, Integer> commandOption = new HashMap<String, Integer>();
    private int commandNum;

    Command() {
        commandOption.put("start", 0);
        commandOption.put("changeLevel", 1);
        commandOption.put("exit", 2);
        commandNum = 0;
    }

    public int getNumOfCommands() {
        return commandOption.size();
    }

    public void setCommandNum(int n) {
        if(commandOption.containsValue(n)) {
            commandNum = n;
        }else {
            // exception: set the default value 0
            commandNum = 0;
        }
    }

    public int getCommandNum() {
        return commandNum;
    }
}

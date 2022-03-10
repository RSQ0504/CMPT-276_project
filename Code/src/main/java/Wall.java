public class Wall extends GameObject{
    //attributes of Wall
    private boolean DoorStatus;

    /**
     * Get function to return private Wall attributes
     * @return DoorStatus
     */
    public boolean getDoorStatus() {return DoorStatus;}

    /**
     * Set function to set private Wall attributes
     */
    public void setDoorStatus(boolean DoorStatus) { this.DoorStatus = DoorStatus; }
}

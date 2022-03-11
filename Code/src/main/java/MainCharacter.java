public class MainCharacter extends DynamicCharacter{
    /**
     * The attributes of main character: the number of vaccines, the HP
     */
    private int vaccines;
    private int HP;

    /**
     * Return the number of vaccine
     */
    public int getVaccines() {
        return vaccines;
    }

    /**
     * Return the HP
     */
    public int getHP() {
        return HP;
    }

    /**
     * Set the number of vaccines
     */
    public void setVaccines(int vaccines) {
        this.vaccines = vaccines;
    }

    /**
     * Set the HP
     */
    public void setHP(int HP) {
        this.HP = HP;
    }
}

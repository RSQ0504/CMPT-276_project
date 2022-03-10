public class Zombie extends DynamicCharacter{
    /**
     * The attributes of zombie: the number of HP decrease from MainCharacter (damage)
     */
    private int damage;

    /**
     * Return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Set the damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void Damage(MainCharacter mc){
        mc.setHP(mc.getHP()-damage);
    }
}

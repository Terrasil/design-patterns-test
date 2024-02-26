package kingdom.weapons.enums;

public enum WeaponType {
    AXE,
    HAMMER,
    SWORD, UNKNOWN;
    public static WeaponType lesserType(WeaponType weaponType){
        if(weaponType == WeaponType.HAMMER) return WeaponType.AXE;
        if(weaponType == WeaponType.AXE) return WeaponType.SWORD;
        if(weaponType == WeaponType.SWORD) return WeaponType.HAMMER;
        return weaponType;
    }
    public static WeaponType greaterType(WeaponType weaponType){
        if(weaponType == WeaponType.HAMMER) return WeaponType.SWORD;
        if(weaponType == WeaponType.AXE) return WeaponType.HAMMER;
        if(weaponType == WeaponType.SWORD) return WeaponType.AXE;
        return weaponType;
    }
}

package kingdom;

import kingdom.composites.Army;
import kingdom.enemies.Enemy;
import kingdom.enemies.enums.EventType;
import kingdom.enemies.factory.Event;
import kingdom.items.classes.IronOre;
import kingdom.items.interfaces.Item;
import kingdom.items.classes.Meat;
import kingdom.mediator.World;
import kingdom.units.*;
import kingdom.units.behaviors.*;
import kingdom.units.enums.UnitType;
import kingdom.units.factory.Barracks;
import kingdom.weapons.Weapon;

import java.util.*;
import java.util.stream.Collectors;

public class Kingdom {
    private static Kingdom uniqueInstance;
    private Integer day = 0;
    private List<Item> warehouse = new ArrayList<>();
    private List<Weapon> armory = new ArrayList<>();
    private List<Human> citizens = new ArrayList<>();

    private World world = new World();
    public List<Item> getWarehouse(){
        return warehouse;
    }
    private Random rng = new Random(System.currentTimeMillis());


    public static Kingdom getInstance() {
        if (uniqueInstance == null) {
            synchronized (Kingdom.class) {
                if (uniqueInstance == null) {
                    System.out.format("Zakładanie królestwa%n");
                    uniqueInstance = new Kingdom();
                }
            }
        }
        return uniqueInstance;
    }

    public Boolean stillExist(){
        return !this.citizens.isEmpty();
    }

    public void initialize(){
        Integer citizensStartCount = 10 + this.rng.nextInt(10);
        for(Integer i = 0; i < citizensStartCount; i++){
            this.citizens.add(Barracks.createUnit(UnitType.HUMAN));
        }
        Integer meatStartCount = 10 + this.rng.nextInt(10);
        for(Integer i = 0; i < meatStartCount; i++){
            this.warehouse.add(new Meat());
        }
        this.warehouse.add(new IronOre());
        this.warehouse.add(new IronOre());
    }

    public void simulateDay() throws InterruptedException {
        this.day++;
        kingdomGrowth();
        manageCitizens();
        citizensStatus();
        warehouseStatus();
        armoryStatus();
        knownEnemies();
        spawnEvent();
        Thread.sleep(1000);
    }

    private void spawnEvent(){
        Integer chance = rng.nextInt(10);
        if(chance == 1){
            Integer type = this.rng.nextInt(4);
            Enemy newEnemy = null;
            switch (type){
                case 1:
                    newEnemy = Event.createUnit(EventType.DRAGON);
                    break;
                case 2:
                    newEnemy = Event.createUnit(EventType.WOLF_PACK);
                    break;
                case 3:
                    newEnemy = Event.createUnit(EventType.IRON_ORE_VEIN);
                    break;
                default:
                    newEnemy = Event.createUnit(EventType.WOLF);
                    break;
            }
            this.world.setNewEvent(newEnemy);
        }
    }

    private void manageCitizens() {
        Long knightCount = this.citizens.stream().filter(k -> k instanceof Knight).count();
        if((knightCount <= 0 || this.knightsCount() < this.citizensCount()/10) && this.noWorkerCount() > 0){
            Human human = this.citizens.stream().filter(k -> k instanceof Human && !(k instanceof Army)).findFirst().get();
            Boolean removedCitizen = this.citizens.remove(human);
            if(removedCitizen) this.citizens.add(Barracks.upgradeUnit(UnitType.KNIGHT, human));
        }
        Long minerCount = this.citizens.stream().filter(k -> k instanceof Miner).count();
        if(minerCount <= 0 || this.noWorkerCount() > 0){
            Human human = this.citizens.stream().filter(m -> m instanceof Human && !(m instanceof Army)).findFirst().get();
            Boolean removedCitizen = this.citizens.remove(human);
            if(removedCitizen) this.citizens.add(Barracks.upgradeUnit(UnitType.MINER, human));
        }
        Long blacksmithCount = this.citizens.stream().filter(k -> k instanceof Blacksmith).count();
        if(blacksmithCount <= 0 || this.noWorkerCount() > 0){
            Human human = this.citizens.stream().filter(b -> b instanceof Human && !(b instanceof Army)).findFirst().get();
            Boolean removedCitizen = this.citizens.remove(human);
            if(removedCitizen) this.citizens.add(Barracks.upgradeUnit(UnitType.BLACKSMITH, human));
        }
        Long armyCount = this.citizens.stream().filter(a -> a instanceof Army).count();
        if(armyCount <= 0 || this.noWorkerCount() > 0){
            Optional<Human> optionalKnight = this.citizens.stream().filter(k -> k instanceof Knight && !(k instanceof Army)).findFirst();
            Knight knight = null;
            if(!optionalKnight.isEmpty()){
                knight = (Knight) optionalKnight.get();
                Boolean removedKnight = this.citizens.remove(knight);
                if(removedKnight) this.citizens.add(new Army.Builder().knights(Arrays.asList(knight)).build());
            }
        }

        // Karmienie mieszkańców
        List<Human> sortedCitizens = this.citizens;
        sortedCitizens.sort((c1, c2) -> Integer.compare(c2.getHunger() != null ? c2.getHunger() : 0, c1.getHunger() != null ? c1.getHunger() : 0));
        sortedCitizens.sort((c1, c2) -> Integer.compare(c2.getHealth() != null ? c2.getHealth() : 0, c1.getHealth() != null ? c1.getHealth() : 0));
        for(Human h: sortedCitizens) {
            if(h instanceof Army){
                for (Human k: ((Army) h).getKnights()) {
                    if(armory.size() > 0) {
                        if (((Knight) k).getWeapon() == null) {
                            Weapon weaponToGive = armory.get(0);
                            ((Knight) k).setWeapon(weaponToGive);
                            armory.remove(weaponToGive);
                        }
                    }
                    k.setBehavior(new EatingBehavior());
                    k.performBehavior();
                    k.setBehavior(new AttackBehavior());
                    k.performBehavior();
                }
            }
            else{
                h.setBehavior(new EatingBehavior());
                h.performBehavior();
            }
        }

        // Craftowanie
        List<Human> blacksmiths = this.citizens.stream().filter(b -> b instanceof Blacksmith).toList();
        for(Human blacksmith: blacksmiths){
            blacksmith.setBehavior(new CraftingBehavior());
            blacksmith.performBehavior();
        }

        // Wydobywanie
        List<Human> miners = this.citizens.stream().filter(m -> m instanceof Miner).toList();
        for(Human miner: miners){
            miner.setBehavior(new MiningBehavior());
            miner.performBehavior();
        }

        // Walka wolnych rycerzy
        List<Human> noArmyKnights = this.citizens.stream().filter(k -> k instanceof Knight).toList();
        for(Human knight: noArmyKnights){
            if(!(knight instanceof Army)){
                // uzbrojenie
                if(armory.size() > 0) {
                    if (((Knight) knight).getWeapon() == null) {
                        Weapon weaponToGive = armory.get(0);
                        ((Knight) knight).setWeapon(armory.get(0));
                        armory.remove(weaponToGive);
                    }
                }
                knight.setBehavior(new AttackBehavior());
                knight.performBehavior();
            }
        }
    }

    public Integer citizensCount(){
        Integer citizensCount = this.citizens.stream().filter(n -> !(n instanceof Army)).toList().size();
        List<Army> armies = this.citizens.stream().filter(n -> n instanceof Army).map(n -> (Army) n).toList();
        for(Army army : armies){
            citizensCount += army.getKnights().size();
        }
        return citizensCount;
    }

    private Integer noWorkerCount(){
        return this.citizens.stream().filter(n -> !(n instanceof Army) &&
                !(n instanceof Knight) &&
                !(n instanceof Blacksmith) &&
                !(n instanceof Miner)
        ).toList().size();
    }
    public Integer knightsCount(){
        Integer knightsCount = this.citizens.stream().filter(n -> n instanceof Knight).toList().size();
        List<Army> armies = this.citizens.stream().filter(n -> n instanceof Army).map(n -> (Army) n).toList();
        for(Army army : armies){
            knightsCount += army.getKnights().size();
        }
        return knightsCount;
    }

    private void citizensStatus(){
        Map<String, Long> citizensType = this.citizens.stream()
                .collect(Collectors.groupingBy(s -> s.getName(), Collectors.counting()));
        for (Map.Entry<String, Long> citizen : citizensType.entrySet()) {
            System.out.format(" %-8s %d%n", citizen.getKey(), citizen.getValue());
        }
    }
    private void warehouseStatus(){
        Map<String, Long> warehouseItems = this.warehouse.stream()
                .collect(Collectors.groupingBy(s -> s.getName(), Collectors.counting()));
        System.out.format("Magazyn:%n");
        if(warehouseItems.isEmpty()){
            System.out.format(" pusty%n");
        }else{
            for (Map.Entry<String, Long> item : warehouseItems.entrySet()) {
                System.out.format(" %-8s %d%n", item.getKey(), item.getValue());
            }
        }
    }
    private void knownEnemies(){
        Map<String, Long> enmies = this.world.getEnemies().stream()
                .collect(Collectors.groupingBy(s -> s.getName(), Collectors.counting()));
        System.out.format("Przeciwnicy:%n");
        if(enmies.isEmpty()){
            System.out.format(" brak%n");
        }else{
            for (Map.Entry<String, Long> item : enmies.entrySet()) {
                System.out.format(" %-8s %d%n", item.getKey(), item.getValue());
            }
        }
    }

    private void armoryStatus(){
        Map<String, Long> armoryItems = this.armory.stream()
                .collect(Collectors.groupingBy(s -> s.getName(), Collectors.counting()));
        System.out.format("Zbrojownia:%n");
        if(armoryItems.isEmpty()){
            System.out.format(" pusta%n");
        }else{
            for (Map.Entry<String, Long> weapon : armoryItems.entrySet()) {
                System.out.format(" %-8s %d%n", weapon.getKey(), weapon.getValue());
            }
        }
    }

    private void kingdomGrowth(){
        Integer chance = rng.nextInt(100);
        if(chance <= this.citizens.size()){
            Integer amount = 1 + rng.nextInt(1 + (this.citizens.size() / 200));
            System.out.format("[i] Pojawił/o się %d mieszkaniec/cy%n", amount);
            for(Integer count = 0; count < amount; count++){
                this.citizens.add(Barracks.createUnit(UnitType.HUMAN));
            }
        }
        System.out.format("Populacja: %s%n" ,this.citizensCount());
    }

    public void describeKingdom() {
        System.out.format("------------------------ Dzień %d ------------------------%n", this.day);
    }

    public void setWarehouse(List<Item> warehouse) {
        this.warehouse = warehouse;
    }

    public List<Human> getCitizens() {
        return this.citizens;
    }

    public List<Weapon> getArmory() {
        return this.armory;
    }

    public void setArmory(List<Weapon> armory) {
        this.armory = armory;
    }

    public World getWorld() {
        return this.world;
    }
}

package com.cs370.gwtm.destinygearandguns.model;

import java.util.List;

/**
 * Created by Kiladre on 4/25/15.
 * Attempting GSONs auto populate from JSON
 */
public class Equippable {
    public long getBucketHash() {
        return bucketHash;
    }

    public void setBucketHash(long bucketHash) {
        this.bucketHash = bucketHash;
    }

    public List<items> getItems() {
        return items;
    }

    public void setItems(List<items> items) {
        this.items = items;
    }

    long bucketHash;
    public List<items> items;

    public Equippable() {
    }

    public class stats {
        public stats() {

        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getMaximumValue() {
            return maximumValue;
        }

        public void setMaximumValue(int maximumValue) {
            this.maximumValue = maximumValue;
        }

        public long getStatHash() {
            return statHash;
        }

        public void setStatHash(long statHash) {
            this.statHash = statHash;
        }

        long statHash;
        int value;
        int maximumValue;
    }

    public class primaryStat {
        public primaryStat() {

        }

        public long getStatHash() {
            return statHash;
        }

        public void setStatHash(long statHash) {
            this.statHash = statHash;
        }

        long statHash;
        int value;
        int maximumValue;
    }

    public class nodes {
        public nodes() {

        }

        public boolean isActivated() {
            return isActivated;
        }

        public void setActivated(boolean isActivated) {
            this.isActivated = isActivated;
        }

        boolean isActivated;

        public int getStepIndex() {
            return stepIndex;
        }

        public void setStepIndex(int stepIndex) {
            this.stepIndex = stepIndex;
        }

        public long getNodeHash() {
            return nodeHash;
        }

        public void setNodeHash(long nodeHash) {
            this.nodeHash = nodeHash;
        }

        int stepIndex;
        long nodeHash;
    }

    public class progression {
        public progression() {

        }

        public int getDailyProgress() {
            return dailyProgress;
        }

        public void setDailyProgress(int dailyProgress) {
            this.dailyProgress = dailyProgress;
        }

        public int getWeeklyProgress() {
            return weeklyProgress;
        }

        public void setWeeklyProgress(int weeklyProgress) {
            this.weeklyProgress = weeklyProgress;
        }

        public long getCurrentProgress() {
            return currentProgress;
        }

        public void setCurrentProgress(long currentProgress) {
            this.currentProgress = currentProgress;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public int getProgressToNextLevel() {
            return progressToNextLevel;
        }

        public void setProgressToNextLevel(int progressToNextLevel) {
            this.progressToNextLevel = progressToNextLevel;
        }

        public int getNextLevelAt() {
            return nextLevelAt;
        }

        public void setNextLevelAt(int nextLevelAt) {
            this.nextLevelAt = nextLevelAt;
        }

        public long getProgressionHash() {
            return progressionHash;
        }

        public void setProgressionHash(long progressionHash) {
            this.progressionHash = progressionHash;
        }

        int dailyProgress;
        int weeklyProgress;
        long currentProgress;
        int level;
        int step;
        int progressToNextLevel;
        int nextLevelAt;
        long progressionHash;
    }

    public class artRegions {
        public artRegions() {

        }

        public int getThree() {
            return three;
        }

        public void setThree(int three) {
            this.three = three;
        }

        public int getFive() {
            return five;
        }

        public void setFive(int five) {
            this.five = five;
        }

        public int getTwentyone() {
            return twentyone;
        }

        public void setTwentyone(int twentyone) {
            this.twentyone = twentyone;
        }

        int three;
        int five;
        int twentyone;
    }

    public class perks {
        public perks() {

        }

        public String getIconPath() {
            return iconPath;
        }

        public void setIconPath(String iconPath) {
            this.iconPath = iconPath;
        }

        public long getPerkHash() {
            return perkHash;
        }

        public void setPerkHash(long perkHash) {
            this.perkHash = perkHash;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean isActive) {
            this.isActive = isActive;
        }

        String iconPath;
        long perkHash;
        boolean isActive;
    }

    public class items {
        public items() {

        }

        public long getItemHash() {
            return itemHash;
        }

        public void setItemHash(long itemHash) {
            this.itemHash = itemHash;
        }

        public int getBindStatus() {
            return bindStatus;
        }

        public void setBindStatus(int bindStatus) {
            this.bindStatus = bindStatus;
        }

        public boolean isEquipped() {
            return isEquipped;
        }

        public void setEquipped(boolean isEquipped) {
            this.isEquipped = isEquipped;
        }

        public long getItemInstanceId() {
            return itemInstanceId;
        }

        public void setItemInstanceId(long itemInstanceId) {
            this.itemInstanceId = itemInstanceId;
        }

        public int getItemLevel() {
            return itemLevel;
        }

        public void setItemLevel(int itemLevel) {
            this.itemLevel = itemLevel;
        }

        public int getStackSize() {
            return stackSize;
        }

        public void setStackSize(int stackSize) {
            this.stackSize = stackSize;
        }

        public int getQualityLevel() {
            return qualityLevel;
        }

        public void setQualityLevel(int qualityLevel) {
            this.qualityLevel = qualityLevel;
        }

        public List<stats> getStats() {
            return stats;
        }

        public void setStats(List<stats> stats) {
            this.stats = stats;
        }

        public boolean isCanEquip() {
            return canEquip;
        }

        public void setCanEquip(boolean canEquip) {
            this.canEquip = canEquip;
        }

        public int getEquipRequiredLevel() {
            return equipRequiredLevel;
        }

        public void setEquipRequiredLevel(int equipRequiredLevel) {
            this.equipRequiredLevel = equipRequiredLevel;
        }

        public long getUnlockFlagHashRequiredToEquip() {
            return unlockFlagHashRequiredToEquip;
        }

        public void setUnlockFlagHashRequiredToEquip(long unlockFlagHashRequiredToEquip) {
            this.unlockFlagHashRequiredToEquip = unlockFlagHashRequiredToEquip;
        }

        public int getCannotEquipReason() {
            return cannotEquipReason;
        }

        public void setCannotEquipReason(int cannotEquipReason) {
            this.cannotEquipReason = cannotEquipReason;
        }

        public int getDamageType() {
            return damageType;
        }

        public void setDamageType(int damageType) {
            this.damageType = damageType;
        }

        public int getDamageTypeNodeIndex() {
            return damageTypeNodeIndex;
        }

        public void setDamageTypeNodeIndex(int damageTypeNodeIndex) {
            this.damageTypeNodeIndex = damageTypeNodeIndex;
        }

        public int getDamageTypeStepIndex() {
            return damageTypeStepIndex;
        }

        public void setDamageTypeStepIndex(int damageTypeStepIndex) {
            this.damageTypeStepIndex = damageTypeStepIndex;
        }

        public progression getProgression() {
            return progression;
        }

        public void setProgression(progression progression) {
            this.progression = progression;
        }

        public long getTalentGridHash() {
            return talentGridHash;
        }

        public void setTalentGridHash(long talentGridHash) {
            this.talentGridHash = talentGridHash;
        }

        public boolean isUseCustomDyes() {
            return useCustomDyes;
        }

        public void setUseCustomDyes(boolean useCustomDyes) {
            this.useCustomDyes = useCustomDyes;
        }

        public artRegions getArtRegions() {
            return artRegions;
        }

        public void setArtRegions(artRegions artRegions) {
            this.artRegions = artRegions;
        }

        public boolean isEquipment() {
            return isEquipment;
        }

        public void setEquipment(boolean isEquipment) {
            this.isEquipment = isEquipment;
        }

        public boolean isGridComplete() {
            return isGridComplete;
        }

        public void setGridComplete(boolean isGridComplete) {
            this.isGridComplete = isGridComplete;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }

        public int getTransferStatus() {
            return transferStatus;
        }

        public void setTransferStatus(int transferStatus) {
            this.transferStatus = transferStatus;
        }

        long itemHash;
        int bindStatus;
        boolean isEquipped;
        long itemInstanceId;
        int itemLevel;
        int stackSize;
        int qualityLevel;
        public List<stats> stats;
        boolean canEquip;
        int equipRequiredLevel;
        long unlockFlagHashRequiredToEquip;
        int cannotEquipReason;
        int damageType;
        int damageTypeNodeIndex;
        int damageTypeStepIndex;
        progression progression;
        long talentGridHash;
        public List<nodes> nodes;
        boolean useCustomDyes;
        artRegions artRegions;
        boolean isEquipment;
        boolean isGridComplete;
        public List<perks> perks;
        int location;
        int transferStatus;
    }
}
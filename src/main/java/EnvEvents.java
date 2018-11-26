
class EnvState {
    private int fireLevel;
    private int smokeLevel;
    private int gasLevel;

    public EnvState(int fireLevel, int smokeLevel, int gasLevel) {
        this.fireLevel = fireLevel;
        this.smokeLevel = smokeLevel;
        this.gasLevel = gasLevel;
    }

    public int getFireLevel() {
        return fireLevel;
    }

    public void setFireLevel(int fireLevel) {
        this.fireLevel = fireLevel;
    }

    public int getSmokeLevel() {
        return smokeLevel;
    }

    public void setSmokeLevel(int smokeLevel) {
        this.smokeLevel = smokeLevel;
    }

    public int getGasLevel() {
        return gasLevel;
    }

    public void setGasLevel(int gasLevel) {
        this.gasLevel = gasLevel;
    }
}

class SecurityConfig {

    private double sensitivity;
    private SecurityMode securityMode;
    private int state;

    SecurityConfig(double sensitivity,
                   SecurityMode securityMode) {

        this.sensitivity = sensitivity;
        this.securityMode = securityMode;
    }

    public SecurityMode getSecurityMode() {
        return securityMode;
    }

    public void setSecurityMode(SecurityMode securityMode) {
        this.securityMode = securityMode;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(double sensitivity) {
        this.sensitivity = sensitivity;
    }
}


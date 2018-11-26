
enum Time { Day, Night }
enum SecurityMode { Normal_Mode, Vacation_Mode, Thief_Mode }

public class SmartHomeController {

    private SecurityConfig securityConfig;
    private EnvState envState;
    private Time currentTime;
    private HomeState homeState;

    public SmartHomeController(SecurityConfig securityConfig,
                               EnvState envState,
                               Time currentTime) {

        this.securityConfig = securityConfig;
        this.currentTime = currentTime;
        this.homeState = new HomeState();
        this.envState = envState;
    }

    public double calcEmergLevel() {
        return (envState.getFireLevel() +
                envState.getSmokeLevel() +
                envState.getGasLevel() ) * securityConfig.getSensitivity();
    }


    // Fire is detected
    public void fire(int fireLevel) {

        this.envState.setFireLevel(fireLevel);

        if (securityConfig.getSecurityMode().equals(SecurityMode.Normal_Mode) &&

                currentTime.equals(Time.Day) && envState.getSmokeLevel()>1 && envState.getGasLevel()>1) {

            homeState.setWindows(Windows.Open);
            homeState.setDoors(Doors.Open);

        } else if(securityConfig.getSecurityMode().equals(SecurityMode.Normal_Mode) &&

                currentTime.equals(Time.Day) && (envState.getSmokeLevel()>1 || envState.getGasLevel()>1)) {

            homeState.setWindows(Windows.Open);

        } else if (securityConfig.getSecurityMode().equals(SecurityMode.Thief_Mode) &&

                envState.getFireLevel()>1 && (envState.getGasLevel()>1 || envState.getSmokeLevel()>1)) {

            homeState.setLights(Lights.Emergency);
            homeState.setAlarm(Alarm.On);
            homeState.setWindows(Windows.Close);
            homeState.setDoors(Doors.Close);

        } else if (securityConfig.equals(SecurityMode.Vacation_Mode)) {

            if (homeState.getDoors().equals(Doors.Open) && currentTime.equals(Time.Night)){

                if(envState.getFireLevel() != 0)
                    homeState.setLights(Lights.Emergency);

                if(envState.getGasLevel() != 0 || envState.getSmokeLevel() != 0){
                    homeState.setWindows(Windows.Open);
                    homeState.setDoors(Doors.Open);
                }
            }

        }

    }

    // Smoke is detected
    public void smoke(int smokeLevel) {

        this.envState.setSmokeLevel(smokeLevel);

        if (securityConfig.getSecurityMode().equals(SecurityMode.Normal_Mode))

            if(currentTime.equals(Time.Day) && calcEmergLevel() < 1.5) {

                homeState.setWindows(Windows.Open);

            } else if(currentTime.equals(Time.Day)) {

            homeState.setLights(Lights.Emergency);
            homeState.setWindows(Windows.Open);

            }else{

                homeState.setWindows(Windows.Close);
                homeState.setDoors(Doors.Close);
            }
        else if (securityConfig.getSecurityMode().equals(SecurityMode.Thief_Mode) && securityConfig.getSensitivity() > 3 &&

                currentTime.equals(Time.Night) && calcEmergLevel() > 1 ) {

            homeState.setLights(Lights.Emergency);
            homeState.setWindows(Windows.Open);
            homeState.setDoors(Doors.Open);

        } if (securityConfig.getSecurityMode().equals(SecurityMode.Vacation_Mode)) {

            homeState.setLights(Lights.Emergency);
            homeState.setWindows(Windows.Open);
            homeState.setAlarm(Alarm.On);
        }

    }

    // gas is detected
    public void gas(int gasLevel) {
        this.envState.setGasLevel(gasLevel);

        if (securityConfig.getSecurityMode().equals(SecurityMode.Normal_Mode) &&

                (currentTime.equals(Time.Day) || calcEmergLevel() < 1)) {

            if(securityConfig.getSensitivity() > 2)
                homeState.setAlarm(Alarm.On);

            homeState.setWindows(Windows.Open);

        } else if(securityConfig.getSecurityMode().equals(SecurityMode.Normal_Mode) && currentTime.equals(Time.Night)

                && securityConfig.getSensitivity() > 4 && calcEmergLevel() >= 2) {

            homeState.setLights(Lights.Emergency);
            homeState.setWindows(Windows.Open);
            homeState.setDoors(Doors.Open);
            homeState.setAlarm(Alarm.On);

        } else if(securityConfig.getSecurityMode().equals(SecurityMode.Vacation_Mode) &&

                currentTime.equals(Time.Night)) {

            homeState.setLights(Lights.Emergency);
            homeState.setWindows(Windows.Open);

        } else {

            homeState.setLights(Lights.Emergency);
            homeState.setWindows(Windows.Open);
            homeState.setAlarm(Alarm.On);
        }

    }



    public Time getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Time currentTime) {
        this.currentTime = currentTime;
    }

    public void setHomeState(HomeState homeState) {
        this.homeState = homeState;
    }

    public HomeState getHomeState() {
        return this.homeState;
    };

    public SecurityConfig getSecurityConfig() { return this.securityConfig; }
}


enum Windows {Open, Close}
enum Doors {Open, Close}
enum Lights {Off, NormalDay, NormalNight, Emergency}
enum Alarm {On, Off}

public class HomeState {

    private Windows windows;
    private Doors doors;
    private Lights lights;
    private Alarm alarm;

    public HomeState() {
        this.windows = Windows.Close;
        this.doors = Doors.Close;
        this.lights = Lights.Off;
        this.alarm = Alarm.Off;
    }

    public HomeState(Windows windows, Doors doors, Lights lights) {
        this.windows = windows;
        this.doors = doors;
        this.lights = lights;
        this.alarm = Alarm.Off;
    }

    public HomeState(Windows windows, Doors doors, Lights lights, Alarm alarm) {
        this.windows = windows;
        this.doors = doors;
        this.lights = lights;
        this.alarm = alarm;
    }

    public Windows getWindows() {
        return windows;
    }

    public void setWindows(Windows windows) {
        this.windows = windows;
    }

    public Doors getDoors() {
        return doors;
    }

    public void setDoors(Doors doors) {
        this.doors = doors;
    }

    public Lights getLights() {
        return lights;
    }

    public void setLights(Lights lights) {
        this.lights = lights;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public boolean equals(Object obj) {
        HomeState homeState = (HomeState) obj;
        return this.lights == homeState.lights &&
                this.doors == homeState.doors &&
                this.windows == homeState.windows &&
                this.alarm == homeState.alarm;
    }
}
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SmartHomeCntrlTest {
    private SecurityConfig securityConfig;
    private EnvState envState;
    private SmartHomeController smartHomeController;


    @Before
    public void setup() {
        this.securityConfig = new SecurityConfig(1, SecurityMode.Normal_Mode);
        this.envState = new EnvState(0, 2, 3);
        this.smartHomeController = new SmartHomeController(securityConfig, envState, Time.Day);
    }

//    @Test
//    public void test1() {
//        SecurityConfig securityConfig = new SecurityConfig(1, SecurityMode.Normal_Mode);
//        EnvState envState = new EnvState(0, 0, 0);
//        SmartHomeController smartHomeController =
//                new SmartHomeController(securityConfig, envState, Time.Night);
//        smartHomeController.gas(2);
//        assertEquals(new HomeState(Windows.Close, Doors.Close, Lights.NormalDay, Alarm.Off),
//                smartHomeController.getHomeState());
//    }

    @Test
    public void fire_IF1_CACC_test1() {
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Open, Doors.Open, Lights.Off, Alarm.Off);

        assertEquals(expected, actual);
    }

    @Test
    public void fire_IF1_CACC_test2() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF1_CACC_test3() {
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF1_CACC_test4() {
        this.envState.setSmokeLevel(0);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF1_CACC_test5() {
        this.envState.setGasLevel(0);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF2_CACC_test1() {
        this.envState.setGasLevel(0);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState();
        expected.setWindows(Windows.Open);

        assertEquals(expected, actual);
    }

    @Test
    public void fire_IF2_CACC_test2() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.envState.setSmokeLevel(0);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState();
        unexpected.setWindows(Windows.Open);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF2_CACC_test3() {
        this.envState.setSmokeLevel(0);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState();
        expected.setWindows(Windows.Open);

        assertEquals(expected, actual);
    }

    @Test
    public void fire_IF2_CACC_test4() {
        this.smartHomeController.setCurrentTime(Time.Night);
        this.envState.setSmokeLevel(0);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState();
        unexpected.setWindows(Windows.Open);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF2_CACC_test5() {
        this.envState.setSmokeLevel(0);
        this.envState.setGasLevel(0);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState();
        unexpected.setWindows(Windows.Open);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF3_CACC_test1() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Close, Doors.Close, Lights.Emergency, Alarm.On);

        assertEquals(expected, actual);
    }

    @Test
    public void fire_IF3_CACC_test2() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Close, Doors.Close, Lights.Emergency, Alarm.On);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF3_CACC_test3() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.envState.setGasLevel(0);
        this.smartHomeController.fire(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Close, Doors.Close, Lights.Emergency, Alarm.On);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF3_CACC_test4() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.envState.setSmokeLevel(0);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Close, Doors.Close, Lights.Emergency, Alarm.On);

        assertEquals(expected, actual);
    }

    @Test
    public void fire_IF3_CACC_test5() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.envState.setGasLevel(0);
        this.envState.setSmokeLevel(0);
        this.smartHomeController.fire(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Close, Doors.Close, Lights.Emergency, Alarm.On);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF3_CACC_test6() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.envState.setGasLevel(0);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Close, Doors.Close, Lights.Emergency, Alarm.On);

        assertEquals(expected, actual);
    }

    @Test
    public void fire_IF4_CACC_test1() {
        this.envState.setSmokeLevel(0);
        this.envState.setGasLevel(0);

        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.getHomeState().setDoors(Doors.Open);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Close, Doors.Close, Lights.Emergency, Alarm.Off);

        assertEquals(expected, actual);
    }

    @Test
    public void fire_IF4_CACC_test2() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.smartHomeController.getHomeState().setDoors(Doors.Open);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Close, Doors.Close, Lights.Emergency, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF4_CACC_test3() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.getHomeState().setDoors(Doors.Close);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Close, Doors.Close, Lights.Emergency, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF4_CACC_test4() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.getHomeState().setDoors(Doors.Open);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.smartHomeController.fire(2);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Close, Doors.Close, Lights.Emergency, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF4_CACC_test5() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.getHomeState().setDoors(Doors.Open);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.fire(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Close, Doors.Close, Lights.Emergency, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF5_CACC_test1() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.getHomeState().setDoors(Doors.Open);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.envState.setGasLevel(0);
        this.smartHomeController.fire(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Open, Doors.Open, Lights.Off, Alarm.Off);

        assertEquals(expected, actual);
    }

    @Test
    public void fire_IF5_CACC_test2() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.smartHomeController.getHomeState().setDoors(Doors.Open);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.envState.setGasLevel(0);
        this.smartHomeController.fire(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF5_CACC_test3() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.getHomeState().setDoors(Doors.Close);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.envState.setGasLevel(0);
        this.smartHomeController.fire(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF5_CACC_test4() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.getHomeState().setDoors(Doors.Open);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.envState.setGasLevel(0);
        this.smartHomeController.fire(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void fire_IF5_CACC_test5() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.getHomeState().setDoors(Doors.Open);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.envState.setSmokeLevel(0);
        this.smartHomeController.fire(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Open, Doors.Open, Lights.Off, Alarm.Off);

        assertEquals(expected, actual);
    }

    @Test
    public void fire_IF5_CACC_test6() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.getHomeState().setDoors(Doors.Open);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.envState.setGasLevel(0);
        this.envState.setSmokeLevel(0);
        this.smartHomeController.fire(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void smoke_IF1_CACC_test1() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.smartHomeController.smoke(-10);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Open, Doors.Close, Lights.Off, Alarm.Off);

        assertEquals(expected, actual);
    }

    @Test
    public void smoke_IF1_CACC_test2() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.smartHomeController.smoke(-10);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Close, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void smoke_IF1_CACC_test3() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.envState.setGasLevel(0);
        this.envState.setFireLevel(0);
        this.smartHomeController.smoke(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Close, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void smoke_IF1_CACC_test4() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.smartHomeController.smoke(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Close, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void smoke_IF2_CACC_test1() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.smartHomeController.smoke(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Open, Doors.Close, Lights.Emergency, Alarm.Off);

        assertEquals(expected, actual);
    }

    @Test
    public void smoke_IF2_CACC_test2() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.smartHomeController.smoke(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Close, Lights.Emergency, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void smoke_IF2_CACC_test3() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.smoke(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Close, Lights.Emergency, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void smoke_IF3_CACC_test1() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.smoke(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Close, Doors.Close, Lights.Off, Alarm.Off);

        assertEquals(expected, actual);
    }

    @Test
    public void smoke_IF3_CACC_test2() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.smoke(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Close, Doors.Close, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void smoke_IF3_CACC_test3() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.smartHomeController.smoke(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Close, Doors.Close, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void smoke_IF4_CACC_test1() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.securityConfig.setSensitivity(4);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.smoke(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Open, Doors.Open, Lights.Emergency, Alarm.Off);

        assertEquals(expected, actual);
    }

    @Test
    public void smoke_IF4_CACC_test2() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.securityConfig.setSensitivity(4);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.smoke(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Emergency, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void smoke_IF4_CACC_test3() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.smoke(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Emergency, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void smoke_IF4_CACC_test4() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.securityConfig.setSensitivity(4);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.smartHomeController.smoke(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Emergency, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void smoke_IF4_CACC_test5() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.securityConfig.setSensitivity(4);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.smoke(-10);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Emergency, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void smoke_IF5_CACC_test1() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.smoke(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Open, Doors.Close, Lights.Emergency, Alarm.On);

        assertEquals(expected, actual);
    }

    @Test
    public void smoke_IF5_CACC_test2() {
        this.securityConfig.setSecurityMode(SecurityMode.Thief_Mode);
        this.smartHomeController.smoke(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Close, Lights.Emergency, Alarm.On);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void gas_IF1_CACC_test1() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.smartHomeController.gas(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Open, Doors.Close, Lights.Off, Alarm.Off);

        assertEquals(expected, actual);
    }

    @Test
    public void gas_IF1_CACC_test2() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.smartHomeController.gas(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Close, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void gas_IF1_CACC_test3() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.gas(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Close, Lights.Off, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void gas_IF1_CACC_test4() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.gas(-10);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Open, Doors.Close, Lights.Off, Alarm.Off);

        assertEquals(expected, actual);
    }

    @Test
    public void gas_IF2_CACC_test1() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.securityConfig.setSensitivity(3);
        this.smartHomeController.gas(-10);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Open, Doors.Close, Lights.Off, Alarm.On);

        assertEquals(expected, actual);
    }

    @Test
    public void gas_IF2_CACC_test2() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.securityConfig.setSensitivity(3);
        this.smartHomeController.gas(-10);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Close, Lights.Off, Alarm.On);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void gas_IF2_CACC_test3() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.securityConfig.setSensitivity(3);
        this.smartHomeController.gas(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Open, Doors.Close, Lights.Off, Alarm.On);

        assertEquals(expected, actual);
    }

    @Test
    public void gas_IF2_CACC_test4() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.securityConfig.setSensitivity(3);
        this.smartHomeController.gas(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Close, Lights.Off, Alarm.On);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void gas_IF2_CACC_test5() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.smartHomeController.gas(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Close, Lights.Off, Alarm.On);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void gas_IF3_CACC_test1() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.securityConfig.setSensitivity(5);
        this.smartHomeController.gas(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Open, Doors.Open, Lights.Emergency, Alarm.On);

        assertEquals(expected, actual);
    }

    @Test
    public void gas_IF3_CACC_test2() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.securityConfig.setSensitivity(5);
        this.smartHomeController.gas(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Emergency, Alarm.On);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void gas_IF3_CACC_test3() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.securityConfig.setSensitivity(5);
        this.smartHomeController.gas(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Emergency, Alarm.On);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void gas_IF3_CACC_test4() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.gas(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Emergency, Alarm.On);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void gas_IF3_CACC_test5() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.securityConfig.setSensitivity(5);
        this.smartHomeController.gas(-10);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Open, Lights.Emergency, Alarm.On);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void gas_IF4_CACC_test1() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.gas(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState expected = new HomeState(Windows.Open, Doors.Close, Lights.Emergency, Alarm.Off);

        assertEquals(expected, actual);
    }

    @Test
    public void gas_IF4_CACC_test2() {
        this.securityConfig.setSecurityMode(SecurityMode.Normal_Mode);
        this.smartHomeController.setCurrentTime(Time.Night);
        this.smartHomeController.gas(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Close, Lights.Emergency, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void gas_IF4_CACC_test3() {
        this.securityConfig.setSecurityMode(SecurityMode.Vacation_Mode);
        this.smartHomeController.setCurrentTime(Time.Day);
        this.smartHomeController.gas(0);
        HomeState actual = this.smartHomeController.getHomeState();
        HomeState unexpected = new HomeState(Windows.Open, Doors.Close, Lights.Emergency, Alarm.Off);

        assertNotEquals(unexpected, actual);
    }
}

import org.junit.Test;

import static org.junit.Assert.*;

public class SmartHomeCntrlTest {
    @Test
    public void test1() {
        SecurityConfig securityConfig = new SecurityConfig(1, SecurityMode.Normal_Mode);
        EnvState envState = new EnvState(0, 0, 0);
        SmartHomeController smartHomeController =
                new SmartHomeController(securityConfig, envState, Time.Night);
        smartHomeController.gas(2);
        assertEquals(new HomeState(Windows.Close, Doors.Close, Lights.NormalDay, Alarm.Off),
                smartHomeController.getHomeState());
    }

    @Test
    public void fire_IF1_CACC_test1() {
        SecurityConfig securityConfig = new SecurityConfig(1, SecurityMode.Normal_Mode);
        EnvState envState = new EnvState(0, 2, 3);
        SmartHomeController smartHomeController = new SmartHomeController(securityConfig, envState, Time.Day);
        smartHomeController.fire(2);
        HomeState homeState = smartHomeController.getHomeState();

        assertTrue("windows or doors are close", homeState.getWindows().equals(Windows.Open) &&
                homeState.getDoors().equals(Doors.Open));
    }

    @Test
    public void fire_IF1_CACC_test2() {
        SecurityConfig securityConfig = new SecurityConfig(1, SecurityMode.Thief_Mode);
        EnvState envState = new EnvState(0, 2, 3);
        SmartHomeController smartHomeController = new SmartHomeController(securityConfig, envState, Time.Day);
        smartHomeController.fire(2);
        HomeState homeState = smartHomeController.getHomeState();

        assertFalse("windows and doors are open", homeState.getWindows().equals(Windows.Open) &&
                homeState.getDoors().equals(Doors.Open));
    }

    @Test
    public void fire_IF1_CACC_test3() {
        SecurityConfig securityConfig = new SecurityConfig(1, SecurityMode.Normal_Mode);
        EnvState envState = new EnvState(0, 2, 3);
        SmartHomeController smartHomeController = new SmartHomeController(securityConfig, envState, Time.Night);
        smartHomeController.fire(2);
        HomeState homeState = smartHomeController.getHomeState();

        assertFalse("windows and doors are open", homeState.getWindows().equals(Windows.Open) &&
                homeState.getDoors().equals(Doors.Open));
    }

    @Test
    public void fire_IF1_CACC_test4() {
        SecurityConfig securityConfig = new SecurityConfig(1, SecurityMode.Normal_Mode);
        EnvState envState = new EnvState(0, 0, 3);
        SmartHomeController smartHomeController = new SmartHomeController(securityConfig, envState, Time.Day);
        smartHomeController.fire(2);
        HomeState homeState = smartHomeController.getHomeState();

        assertFalse("windows and doors are open", homeState.getWindows().equals(Windows.Open) &&
                homeState.getDoors().equals(Doors.Open));
    }

    @Test
    public void fire_IF1_CACC_test5() {
        SecurityConfig securityConfig = new SecurityConfig(1, SecurityMode.Normal_Mode);
        EnvState envState = new EnvState(0, 2, 0);
        SmartHomeController smartHomeController = new SmartHomeController(securityConfig, envState, Time.Day);
        smartHomeController.fire(2);
        HomeState homeState = smartHomeController.getHomeState();

        assertFalse("windows and doors are open", homeState.getWindows().equals(Windows.Open) &&
                homeState.getDoors().equals(Doors.Open));
    }
}

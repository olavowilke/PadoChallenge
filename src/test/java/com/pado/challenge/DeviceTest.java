package com.pado.challenge;

import com.pado.challenge.domain.device.Device;
import com.pado.challenge.domain.device.DeviceRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ChallengeApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
public class DeviceTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private DeviceRepository deviceRepository;

    private Device device1, device2;
    private String device1Id, device2Id;

    @Before
    public void setUp() {
        prepararDados();
    }

    public void prepararDados() {
        Device device1 = new Device("Roberto", "01-AB-85", "roberto@xyz.com", "45125129",
                "2962892");
        this.device1 = deviceRepository.save(device1);
        this.device1Id = device1.getDeviceId().toString();
        Device device2 = new Device("Carlos", "04-AC-05", "carlos@xyz.com", "1581894",
                "25625812");
        this.device2 = deviceRepository.save(device2);
        this.device2Id = device2.getDeviceId().toString();
    }

    private String registrarDeviceJson() throws JSONException {
        JSONObject registrarDeviceJson = new JSONObject();
        registrarDeviceJson.put("name", "Roberto");
        registrarDeviceJson.put("mac", "01-AB-85");
        registrarDeviceJson.put("email", "roberto@xyz.com");
        registrarDeviceJson.put("latitude", "145151541");
        registrarDeviceJson.put("longitude", "51961584");
        return registrarDeviceJson.toString();
    }

    private String registrarDevicesJson() throws JSONException {
        JSONObject deviceJson1 = new JSONObject();
        deviceJson1.put("name", "Roberto");
        deviceJson1.put("mac", "01-AB-85");
        deviceJson1.put("email", "roberto@xyz.com");
        deviceJson1.put("latitude", "145151541");
        deviceJson1.put("longitude", "51961584");
        JSONObject deviceJson2 = new JSONObject();
        deviceJson2.put("name", "Carlos");
        deviceJson2.put("mac", "04-AC-05");
        deviceJson2.put("email", "carlos@xyz.com");
        deviceJson2.put("latitude", "62562451");
        deviceJson2.put("longitude", "45154156");

        return List.of(deviceJson1, deviceJson2).toString();
    }

    @Test
    public void listarDevices_Retornando200() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:" + port + "/pado/listar")
                .then()
                .body("deviceId", is(List.of(device1Id, device2Id)))
                .body("name", is(List.of(device1.getName(), device2.getName())))
                .body("mac", is(List.of(device1.getMac(), device2.getMac())))
                .body("email", is(List.of(device1.getEmail(), device2.getEmail())))
                .body("latitude", is(List.of(device1.getLatitude(), device2.getLatitude())))
                .body("longitude", is(List.of(device1.getLongitude(), device2.getLongitude())))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void registrarDevice_Retornando201() throws JSONException {
        String payload = registrarDeviceJson();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("http://localhost:" + port + "/pado/registrar")
                .then()
                .body("deviceId", Matchers.notNullValue())
                .body("mac", is("01-AB-85"))
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void findById_Retornando200() {
        RestAssured
                .given()
                .pathParam("deviceId", device1Id)
                .when()
                .get("http://localhost:" + port + "/pado/listar/{deviceId}")
                .then()
                .body("deviceId", is(device1Id))
                .body("name", is("Roberto"))
                .body("mac", is("01-AB-85"))
                .body("email", is("roberto@xyz.com"))
                .body("latitude", is("45125129"))
                .body("longitude", is("2962892"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void registrarFila_Retornando200() throws JSONException {
        String payload = registrarDevicesJson();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("http://localhost:" + port + "/pado/registrar/async")
                .then()
                .statusCode(HttpStatus.ACCEPTED.value());
    }

}

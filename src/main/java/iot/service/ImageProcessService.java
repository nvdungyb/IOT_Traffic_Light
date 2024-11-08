package iot.service;

import iot.entity.Vehicle;
import iot.repository.VehicleRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class ImageProcessService {
    private final VehicleRepository vehicleRepository;

    public ImageProcessService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public int processImage(String imageBase64) {
        int imageId = 0;
        try {
            HttpClient client = HttpClient.newHttpClient();
            // Tạo request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:5000/detect"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(imageBase64))
                    .build();

            // Gửi request và lấy phản hồi
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Xử lý JSON trả về
            JSONObject jsonResponse = new JSONObject(response.body());
            int busCount = jsonResponse.getInt("bus");
            int carCount = jsonResponse.getInt("car");
            int motorbikeCount = jsonResponse.getInt("motorbike");
            int truckCount = jsonResponse.getInt("truck");

            Vehicle vehicle = new Vehicle(busCount, carCount, motorbikeCount, truckCount);
            System.out.println(vehicle);

            // Lưu vào csdl và trả imageId.
            imageId = vehicleRepository.save(vehicle).getId();

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return imageId;
    }

    public Vehicle getVehicleById(int id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            return vehicle.get();
        }
        throw new RuntimeException("id: " + id + " not exists.");
    }
}

package iot.controller;

import iot.AI.ImageProcessService;
import iot.entity.Vehicle;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ImageController {
    private final ImageProcessService imageProcessService;

    public ImageController(ImageProcessService imageProcessService) {
        this.imageProcessService = imageProcessService;
    }

    @PostMapping("/processImage")
    public int processingImage(@RequestBody String base64Image) {
        // Loại bỏ tiền tố của base64 nếu có (ví dụ: "data:image/jpeg;base64,")
        if (base64Image.contains(",")) {
            base64Image = base64Image.split(",")[1];
        }

        return imageProcessService.processImage(base64Image);
    }

    @GetMapping("/image/{id}")
    public Vehicle getTrafficLightStatus(@PathVariable int id) {
        Vehicle vehicle = imageProcessService.getVehicleById(id);
        // xử lý khác ở đây.
        return vehicle;
    }
}

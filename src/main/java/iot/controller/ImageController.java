package iot.controller;

import iot.service.ImageProcessService;
import iot.entity.Vehicle;
import iot.service.ThinkSpeakUploader;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class ImageController {
    private final ImageProcessService imageProcessService;
    private final ThinkSpeakUploader thinkSpeakUploader;

    public ImageController(ImageProcessService imageProcessService, ThinkSpeakUploader thinkSpeakUploader) {
        this.imageProcessService = imageProcessService;
        this.thinkSpeakUploader = thinkSpeakUploader;
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

    @GetMapping("/write")
    public void writeData() {
        LocalDate localDate = LocalDate.now();
        String time = localDate.toString();

        thinkSpeakUploader.sendData(time);
    }

    @GetMapping("/read/{num}")
    public void readData(@PathVariable int num) {
        thinkSpeakUploader.readChannel(num);
    }
}

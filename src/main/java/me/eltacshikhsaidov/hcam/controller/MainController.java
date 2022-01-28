package me.eltacshikhsaidov.hcam.controller;

import java.io.IOException;
import java.util.Base64;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.util.ImageUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model) throws IOException {

        Webcam webcam = Webcam.getDefault();
        webcam.open();
        byte[] array = getImageBytes(webcam, "PNG");
        String image = Base64.getEncoder().encodeToString(array);
        model.addAttribute("image", image);
        // System.out.println(image);
        webcam.close();
        return "index";
    }

    public static byte[] getImageBytes(Webcam webcam, String format) {
        return ImageUtils.toByteArray(webcam.getImage(), format);
    }
    
}

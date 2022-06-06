package com.app.bikestore.production.common.images.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bikestore.production.common.images.repository.ImageRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/production/image")
public class ImageController {

	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
//	@GetMapping("/{ImageId}")
//	public Image getImage(@PathVariable("ImageId") Long ImageId) throws IOException {
//		final Optional<Image> retrievedImage = imageRepository.findById(ImageId);
//		Image img = new Image(retrievedImage.get().getName(),
//				retrievedImage.get().getType(),
//				decompressBytes(retrievedImage.get().getPicByte()));
//		return img;
//	}
	
	@GetMapping("/{id}")
	public Map<String, String> getImage(@PathVariable String id) throws IOException {
//		File file = new ClassPathResource("/jumia.JPG").getFile();
		File file = resourceLoader.getResource("classpath:images/testimg.JPG").getFile();
		String encodedImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));
		Map<String, String>jsonMap = new HashMap<>();
		jsonMap.put("content", encodedImage);
		
		return jsonMap;
	}
	
	private static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] bufer = new byte[1024];
		try {
			while(!inflater.finished()) {
				int count = inflater.inflate(bufer);
				outputStream.write(bufer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException dfe) {
		}
		return outputStream.toByteArray();
	}
}

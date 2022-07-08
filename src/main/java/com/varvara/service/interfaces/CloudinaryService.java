package com.varvara.service.interfaces;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

public interface CloudinaryService {

    public Map upload(MultipartFile multipartFile) throws IOException;

    public Map delete(String id) throws IOException;

    public Map getCloudinaryMap(MultipartFile userImg);
}

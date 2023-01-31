package com.dmdev.http.web.servlet;

import com.dmdev.http.web.service.ImageService;
import com.dmdev.http.web.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(UrlPath.IMAGES +"/*")
public class ImageServlet extends HttpServlet {
    private final ImageService imageService = ImageService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();//содержит все кроме хоста и порта
        String imagePath = requestURI.replace("/images","");// получили непосредственно путь к картинке
        imageService.get(imagePath).ifPresentOrElse(image -> {
            resp.setContentType("application/octet-stream");
            writeImage(image,resp);
        },()-> resp.setStatus(404));//в методе get() получили входной поток байт(наша картинка)
        //если он не пустой, то записываем нашу картинку в выходной поток нашего сервлета, иначе отправляем 404 если из get()
        // получили пустой поток
    }
    @SneakyThrows
    private void writeImage(InputStream image, HttpServletResponse resp) {
        try(image; ServletOutputStream outputStream = resp.getOutputStream()){
            int currentByte;
            while ((currentByte = image.read()) != -1){
                outputStream.write(currentByte);
            }
        }
    }
}

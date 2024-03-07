package tech.jamersondev.springdesk.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;

public class UploadUtil {

    public static boolean fazerUploadImagem(MultipartFile file) {
        boolean sucessoUpload = false;

        if (!file.isEmpty()) {
            String nomeArquivo = file.getOriginalFilename();
            try {
                String pastaUploadImagem = "C:\\Users\\gabri\\OneDrive\\Área de Trabalho\\springdesk-curso\\src\\main\\resources\\static\\images\\img-uploads";
                File dir = new File(pastaUploadImagem);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);

                BufferedOutputStream stream = new BufferedOutputStream(new java.io.FileOutputStream(serverFile));

                stream.write(file.getBytes());
                stream.close();

                System.out.println("Arquivo salvo em: " + serverFile.getAbsolutePath());
                System.out.println("Você fez o upload do arquivo " + nomeArquivo + " com sucesso");
                sucessoUpload = true;

            } catch (Exception e) {
                System.out.println("Você falhou em fazer o upload do arquivo " + nomeArquivo + " => " + e.getMessage());
            }


        } else {
            System.out.println("Você falhou em fazer o upload do arquivo porque ele está vazio");
        }

        return sucessoUpload;
    }
}

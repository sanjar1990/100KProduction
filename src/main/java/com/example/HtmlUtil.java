package com.example;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlUtil {
        public static ResponseEntity<String>  successOrdered() {
            String html = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                      <title>SUCCESS ORDERED</title>
                      <style>
                        body {
                          display: flex;
                          justify-content: center;
                          align-items: center;
                          height: 100vh;
                          margin: 0;
                          background-color: #f2f2f2;
                        }

                        .message {
                          font-size: 36px;
                          font-weight: bold;
                          color: #007BFF;
                          animation: fadeIn 2s ease-in-out;
                          transition: background-color 0.3s;
                        }

                        .message:hover {
                          background-color: #FFC107;
                        }

                        @keyframes fadeIn {
                          from {
                            opacity: 0;
                          }
                          to {
                            opacity: 1;
                          }
                        }
                        </style>
                        </head>
                        <body>
                        <p class="message">Congratulations! Muvaffaqiyatli Xarid Qilindi!</p>
                        </body>
                        </html>""";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);

            return new ResponseEntity<>(html, headers, HttpStatus.OK);
        }

    public static ResponseEntity<String>  failedOrdered() {
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Not Found</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            background-color: #f5f5f5;\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            text-align: center;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            font-size: 36px;\n" +
                "        }\n" +
                "\n" +
                "        .container {\n" +
                "            position: absolute;\n" +
                "            top: 50%;\n" +
                "            left: 50%;\n" +
                "            transform: translate(-50%, -50%);\n" +
                "            background: #fff;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 5px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            color: #ff4500;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "    <h1>404 - Not Found</h1>\n" +
                "    <p>The requested page was not found.</p>\n" +
                "    <a th:href=\"@{/home}\">go to home</a>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        return new ResponseEntity<>(html, headers, HttpStatus.OK);
    }
    }

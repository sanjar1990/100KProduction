package com.example.controller;

public class SmsSender {
    public String send() {
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Registration Completed</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      display: flex;\n" +
                "      justify-content: center;\n" +
                "      align-items: center;\n" +
                "      height: 100vh;\n" +
                "      margin: 0;\n" +
                "      background-color: #f2f2f2;\n" +
                "    }\n" +
                "\n" +
                "    .message {\n" +
                "      font-size: 36px;\n" +
                "      font-weight: bold;\n" +
                "      color: #007BFF;\n" +
                "      animation: fadeIn 2s ease-in-out;\n" +
                "      transition: background-color 0.3s;\n" +
                "    }\n" +
                "\n" +
                "    .message:hover {\n" +
                "      background-color: #FFC107;\n" +
                "    }\n" +
                "\n" +
                "    @keyframes fadeIn {\n" +
                "      from {\n" +
                "        opacity: 0;\n" +
                "      }\n" +
                "      to {\n" +
                "        opacity: 1;\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    </style>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "    <p class=\"message\">Congratulations! Registration completed successfully!</p>\n" +
                "    </body>\n" +
                "    </html>";
        return html;
    }

}

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;

public class ChatbotGUI {

   private static String getReply(String message) {
    String input = message.toLowerCase().trim();
    
        if (input.contains("hello") || input.contains("hi")) {
            return "Hello! Welcome to Java AI Chatbot.";
        } else if (input.contains("java")) {
            return "Java is an object-oriented programming language.";
        } else if (input.contains("your name")) {
            return "I am JavaBot, your virtual assistant.";
        } else if (input.contains("how are you")) {
            return "I am fine! How can I help you?";
        } else if (input.contains("bye")) {
            return "Goodbye! Have a great day.";
        } else {
           return "Sorry, I don't understand. Try asking about Java.";
        }
    }
        private static void saveMessage(String message) {
    try (FileWriter writer =
             new FileWriter("chat_history.txt", true)) {
        writer.write(message + System.lineSeparator());
    } catch (IOException e) {
        System.out.println("Could not save chat history.");
    }
        }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Java AI Chatbot");
        JTextArea chatArea = new JTextArea();
        JTextField inputField = new JTextField();
        JButton sendButton = new JButton("Send");

        chatArea.setEditable(false);
        chatArea.setLineWrap(true);

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        frame.setLayout(new BorderLayout(5, 5));
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        Runnable sendMessage = () -> {
            String message = inputField.getText().trim();

            if (message.isEmpty()) {
                return;
            }

            chatArea.append("You: " + message + "\n");
            chatArea.append("Bot: " + getReply(message) + "\n\n");
            inputField.setText("");
        };

        sendButton.addActionListener(e -> sendMessage.run());
        inputField.addActionListener(e -> sendMessage.run());

        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
    
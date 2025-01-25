import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConsoleWindow {

    private JFrame frame;
    private JTextArea textArea;
    private JTextField inputField;
    private BlockingQueue<String> inputQueue;

    public ConsoleWindow(Runnable program) {
        inputQueue = new LinkedBlockingQueue<>();

        // Configurar o JFrame
        frame = new JFrame("Console Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        // Configurar a área de texto
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.GREEN);
        textArea.setCaretColor(Color.GREEN);

        // Configurar o campo de entrada
        inputField = new JTextField();
        inputField.setFont(new Font("Consolas", Font.PLAIN, 14));
        inputField.setBackground(Color.BLACK);
        inputField.setForeground(Color.GREEN);
        inputField.setCaretColor(Color.GREEN);
        inputField.addActionListener(e -> {
            String text = inputField.getText();
            inputField.setText("");
            inputQueue.add(text + "\n"); // Adiciona a entrada na fila
        });

        // Adicionar componentes ao JFrame
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputField, BorderLayout.SOUTH);

        // Redirecionar as streams
        redirectSystemStreams();
        redirectInputStream();

        // Tornar a janela visível
        frame.setVisible(true);

        // Executar o programa
        new Thread(program).start();
    }

    private void redirectSystemStreams() {
        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int b) {
                textArea.append(String.valueOf((char) b));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }

            @Override
            public void write(byte[] b, int off, int len) {
                textArea.append(new String(b, off, len));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        };

        System.setOut(new PrintStream(outputStream, true));
        System.setErr(new PrintStream(outputStream, true));
    }

    private void redirectInputStream() {
        InputStream inputStream = new InputStream() {
            @Override
            public int read() {
                try {
                    return inputQueue.take().charAt(0);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return -1;
                }
            }

            @Override
            public int read(byte[] b, int off, int len) {
                try {
                    String input = inputQueue.take();
                    byte[] inputBytes = input.getBytes();
                    System.arraycopy(inputBytes, 0, b, off, Math.min(len, inputBytes.length));
                    return inputBytes.length;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return -1;
                }
            }
        };

        System.setIn(inputStream);
    }
}
